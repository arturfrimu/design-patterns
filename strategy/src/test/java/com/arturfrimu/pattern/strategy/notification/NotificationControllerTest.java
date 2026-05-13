package com.arturfrimu.pattern.strategy.notification;

import com.arturfrimu.pattern.strategy.notification.NotificationController.NotificationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NotificationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/notification";
    }

    @Test
    void testSendNotification_EmailStrategy() {
        NotificationRequest request = new NotificationRequest();
        request.setContent("Hello, email world!");
        request.setSender("test@example.com");
        request.setRecipient("recipient@example.com");
        request.setEmailSpecificContent("emailSpecificContent");

        HttpEntity<NotificationRequest> entity = new HttpEntity<>(request);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "?strategyType=EMAIL", POST, entity, String.class);

        assertEquals("OK", response.getBody());
    }

    @Test
    void testSendNotification_SmsStrategy() {
        NotificationRequest request = new NotificationRequest();
        request.setContent("Hello, sms world!");
        request.setSender("123456789");
        request.setRecipient("987654321");
        request.setSmsSpecificContent("smsSpecificContent");

        HttpEntity<NotificationRequest> entity = new HttpEntity<>(request);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "?strategyType=SMS", POST, entity, String.class);

        assertEquals("OK", response.getBody());
    }

    @Test
    void testSendNotification_AppStrategy() {
        NotificationRequest request = new NotificationRequest();
        request.setContent("Hello, app world!");
        request.setSender("123456789");
        request.setRecipient("987654321");
        request.setAppSpecificContent("appSpecificContent");

        HttpEntity<NotificationRequest> entity = new HttpEntity<>(request);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "?strategyType=APP", POST, entity, String.class);

        assertEquals("OK", response.getBody());
    }
}