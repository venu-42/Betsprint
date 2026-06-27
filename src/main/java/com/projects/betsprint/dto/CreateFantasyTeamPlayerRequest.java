package com.projects.betsprint.dto;

public record CreateFantasyTeamPlayerRequest(
        Long fantasyTeamId,
        Long playerId
) {
}
