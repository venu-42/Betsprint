package com.projects.betsprint.dto;

import java.util.UUID;

public record FantasyTeamResponse(
        Long fantasyTeamId,
        UUID userId,
        Long matchId,
        Long captainId,
        Long viceCaptainId
) {
}
