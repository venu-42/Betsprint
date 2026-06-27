package com.projects.betsprint.dto;

import java.util.UUID;

public record CreateMatchRequest(
        UUID tournamentId,
        Long homeTeamId,
        Long awayTeamId
) {
}
