package com.banking.accountservice.dto;

import com.banking.accountservice.entity.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateAccountRequest {
    @NotBlank(message = "account holder name is required")
    private String accountHolderName;

    @Email(message = "invalid email format")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "phone is required")
    private String phone;

    @NotNull(message = "account type is required")
    private AccountType accountType;

    // 比如银行开户口第一笔钱
    @NotNull(message = "initial deposit is required")
    @Positive(message = "initial deposit must be positive")
    private BigDecimal initialDeposit;
}
