package com.projects.betsprint.dto;

import com.projects.betsprint.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionResponse(
        UUID transactionId,
        TransactionType transactionType,
        BigDecimal amount,
        UUID userId,
        LocalDateTime createdAt,
        String status
) {
}
