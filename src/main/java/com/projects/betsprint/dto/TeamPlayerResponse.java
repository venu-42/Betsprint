package com.projects.betsprint.dto;

import java.util.UUID;

public record TeamPlayerResponse(
        Long id,
        Long teamId,
        Long playerId,
        UUID tournamentId
) {
}
