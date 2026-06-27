package com.projects.betsprint.dto;

import java.math.BigDecimal;

public record CreateUserRequest(
        String name,
        String email,
        BigDecimal walletBalance
) {
}
