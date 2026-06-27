package com.projects.betsprint.dto;

import java.math.BigDecimal;

public record WalletRequest(
        BigDecimal amount
) {
}
