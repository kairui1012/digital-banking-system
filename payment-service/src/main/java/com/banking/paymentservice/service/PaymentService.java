package com.banking.paymentservice.service;

import com.banking.paymentservice.dto.CreatePaymentRequest;
import com.banking.paymentservice.dto.PaymentOrderResponse;
import com.banking.paymentservice.repository.PaymentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j

public class PaymentService {

    public final PaymentRepository paymentRepository;
    
    public PaymentOrderResponse createPaymentOrder(@Valid CreatePaymentRequest request) {
    }
}
