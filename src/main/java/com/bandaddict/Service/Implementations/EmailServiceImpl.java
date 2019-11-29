package com.bandaddict.Service.Implementations;

import com.bandaddict.Entity.Event;
import com.bandaddict.Entity.User;
import com.bandaddict.Exception.EmailException;
import com.bandaddict.Model.Mail;
import com.bandaddict.Service.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Implementation of email service interface
 */
@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;
    private Configuration freemarkerConfig;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Value("${frontend.url}")
    private String frontendUrl;
    @Value("${admin.email}")
    private String adminEmail;

    public EmailServiceImpl(final JavaMailSender mailSender, final Configuration freemarkerConfig) {
        this.mailSender = mailSender;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Async
    public void sendMail(final Mail mail) throws RuntimeException {
        try {
            final MimeMessage message = mailSender.createMimeMessage();
            final MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            final Template template = freemarkerConfig.getTemplate(mail.getTemplateFileName(), mail.getLocale());
            final String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());

            helper.setFrom(mail.getFrom());
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());

            mailSender.send(message);
        } catch (MessagingException | IOException | TemplateException e) {
            throw new EmailException("Failed to create email!", e);
        }
    }

    @Override
    public void sendActivationMail(final User user, final String token, final Locale locale) throws RuntimeException {
        final Mail mail = new Mail();
        final Map<String, String> model = new HashMap<>();

        model.put("url", frontendUrl);
        model.put("token", token);
        model.put("name", user.getName());

        mail.setModel(model);
        mail.setSubject("Registration successful");
        mail.setFrom(adminEmail);
        mail.setTo(user.getEmail());
        mail.setTemplateFileName("activation_en.ftl");

        sendMail(mail);
    }


    @Override
    public void sendEventNotificationMail(final User user, final Event event, final Locale locale) throws RuntimeException {
        final Mail mail = new Mail();
        final Map<String, String> model = new HashMap<>();

        model.put("name", user.getName());
        model.put("date", event.getEnd() != null ? dateFormat.format(event.getStart()) + " - " + dateFormat.format(event.getEnd()) : dateFormat.format(event.getStart()));
        model.put("content", event.getDescription());
        model.put("title", event.getTitle());

        mail.setModel(model);
        mail.setSubject("Upcoming event");
        mail.setFrom(adminEmail);
        mail.setTo(user.getEmail());
        mail.setTemplateFileName("event_en.ftl");

        sendMail(mail);
    }
}
