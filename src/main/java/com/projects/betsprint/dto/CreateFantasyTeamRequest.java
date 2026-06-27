package com.projects.betsprint.dto;

import java.util.UUID;

public record CreateFantasyTeamRequest(
        UUID userId,
        Long matchId,
        Long captainId,
        Long viceCaptainId
) {
}
