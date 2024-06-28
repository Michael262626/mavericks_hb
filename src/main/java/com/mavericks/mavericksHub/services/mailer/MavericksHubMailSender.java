package com.mavericks.mavericksHub.services.mailer;

import com.mavericks.mavericksHub.services.interfaces.MailService;
import org.springframework.stereotype.Service;

@Service
public class MavericksHubMailSender implements MailService {
    @Override
    public String sendEmail(String email) {
        return "";
    }
}
