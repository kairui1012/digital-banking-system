package com.banking.frauddetectionservice.service;

import com.banking.frauddetectionservice.client.AccountServiceClient;
import com.banking.frauddetectionservice.model.FraudCheckResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor


public class FraudDetectionService {

    private final AccountServiceClient accountServiceClient;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    private static final String VERIFICATION_REQUIRED_TOPIC = "verification.required";
    private static final String FRAUD_CHECK_CLEAN_RESULT_TOPIC = "fraud.check.clean";

    public void checkTransaction(Map<String,Object> payload){
        String transactionId = (String) payload.get("transactionId");
        String accountNumber =  (String) payload.get("senderAccountNumber");
        BigDecimal amount =  new BigDecimal(payload.get("amount").toString());

        // Fetch real balance from Account Service
        BigDecimal senderBalance = accountServiceClient.getBalance(accountNumber);
        log.info("Checking Transaction: {}, account: {}, amount: {}, balance: {}",transactionId,accountNumber,amount,senderBalance);

        FraudCheckResult result = performFraudChecks(accountNumber,amount,senderBalance);

        if (result.isFraud()) {
            log.info("Suspicious activity detected - account: {} " +
                            "reason: {} - requesting OTP verification",
                    accountNumber, result.getReason());
            Map<String,Object> verificationEvent = new HashMap<>();
            verificationEvent.put("transactionId",transactionId);
            verificationEvent.put("accountNumber",accountNumber);
            verificationEvent.put("amount",amount);
            verificationEvent.put("reason",result.getReason());

            kafkaTemplate.send(VERIFICATION_REQUIRED_TOPIC,transactionId, verificationEvent);
        }
        else
        {
            // Transaction is clean
            log.info("Transaction clean");
            Map<String,Object> verificationCleanEvent = new HashMap<>();
            verificationCleanEvent.put("transactionId",transactionId);
            verificationCleanEvent.put("isFraud",false);
            verificationCleanEvent.put("reason",null);

            kafkaTemplate.send(FRAUD_CHECK_CLEAN_RESULT_TOPIC,transactionId, verificationCleanEvent);
        }
    }

    private FraudCheckResult performFraudChecks(
            String accountNumber,
            BigDecimal amount,
            BigDecimal senderBalance) {

        // Fraud Check Pattern 1: Velocity Check
        if(isVelocityExceeded(accountNumber)){
            return new FraudCheckResult(
                    true,"too many transaction in 60 seconds" + "- Velocity limit exceeded"
            );
        }
        // Fraud Check Pattern 2: Amount Check
        if(isAmountSuspicious(accountNumber,amount)){
            return new FraudCheckResult(
                    true,"Unusual transaction amount" + "- Exceeds 3x your average"
            );
        }

        // Fraud Check Pattern 3: Balance Check
        if(senderBalance.compareTo(BigDecimal.ZERO) && is){
            return new FraudCheckResult(
                    true,"Unusual transaction amount" + "- Exceeds 3x your average"
            );
        }
    }
}


