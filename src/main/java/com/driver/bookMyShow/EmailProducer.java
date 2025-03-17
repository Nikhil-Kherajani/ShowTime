package com.driver.bookMyShow;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.driver.bookMyShow.Dtos.ResponseDtos.EmailEvent;

@Service
public class EmailProducer {
    private final KafkaTemplate<String, EmailEvent> kafkaTemplate;

    public EmailProducer(KafkaTemplate<String, EmailEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmailEvent(EmailEvent emailEvent) {
        kafkaTemplate.send("email-topic", emailEvent);
    }
}
