package com.bandaddict.Service;

import com.bandaddict.Entity.Event;
import com.bandaddict.Entity.User;
import com.bandaddict.Model.Mail;

import java.util.Locale;

/**
 * Email service interface
 */
public interface EmailService {

    /**
     * Send email
     * @param mail mail
     */
    void sendMail(final Mail mail);

    /**
     * Send email with activation link
     * @param user user
     * @param token jwt token
     * @param locale locale
     * @throws RuntimeException exception
     */
    void sendActivationMail(final User user, final String token, final Locale locale) throws RuntimeException;

    /**
     * Send event notification email
     * @param user user
     * @param event event
     * @param locale locale
     * @throws RuntimeException exception
     */
    void sendEventNotificationMail(final User user, final Event event, final Locale locale) throws RuntimeException;

}
