package com.projects.betsprint.dto;

import java.util.UUID;

public record CreateTeamPlayerRequest(
        Long teamId,
        Long playerId,
        UUID tournamentId
) {
}
