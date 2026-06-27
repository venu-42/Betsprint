package com.projects.betsprint.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UserResponse(
        UUID userId,
        String name,
        String email,
        BigDecimal walletBalance
) {
}
