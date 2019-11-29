package com.bandaddict.Schedulers;

import com.bandaddict.Repository.EventRepository;
import com.bandaddict.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EventNotifierScheduler {
    private static final long ONE_DAY = 86400000;

    private final EventRepository eventRepository;
    private final EmailService emailService;

    @Value("${check.events.enable}")
    private boolean isActive;

    @Async
    @Scheduled(fixedRate = 86400000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        if (isActive) {
            try {
                checkEvents();
            } catch (Exception e) {
                throw new SchedulingException("Failed to check events: ", e);
            }

            Thread.sleep(ONE_DAY);
        }
    }

    private void checkEvents() {
        final Date today = new Date();

        eventRepository.findAll().forEach(event -> {
            boolean lessThanDay = Math.abs(today.getTime() - event.getStart().getTime()) < ONE_DAY;

            if (lessThanDay) {
                event.getCreatedBy().getBandMembers().forEach(member -> {
                    emailService.sendEventNotificationMail(member, event, Locale.ENGLISH);
                });
            }
        });
    }
}
