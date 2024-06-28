package com.mavericks.mavericksHub.test.services;

import com.mavericks.mavericksHub.services.interfaces.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MailSenderTest {
    @Autowired
    private MailService mailService;
    @Test
    void testSendEmails(){
        String response = mailService.sendEmail("officialbiokesayo@gmail.com");
        assertThat(response).isNotNull();
        assertThat(response).containsIgnoringCase("success");
    }
}
