package com.banking.auditlservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

public class AuditEventConsumer {
    @KafkaListener(topics = "payment.completed")
    public void consumerAuditPaymentCompletedLog(@Payload Map<String, Object> payload){

    }
    @KafkaListener(topics = "payment.failed")
    public void consumerAuditPaymentFailedLog(@Payload Map<String, Object> payload){

    }
}
