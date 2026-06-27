package com.projects.betsprint.dto;

import java.util.UUID;

public record MatchResponse(
        Long matchId,
        UUID tournamentId,
        Long homeTeamId,
        Long awayTeamId
) {
}
