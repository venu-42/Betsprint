package com.projects.betsprint.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateContestEntryRequest(
        Long contestId,
        Long fantasyTeamId,
        UUID userId,
        Double finalScore,
        Integer rank,
        BigDecimal winnings,
        LocalDateTime joinedAt
) {
}
