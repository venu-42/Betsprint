package com.projects.betsprint.dto;

public record PlayerResponse(
        Long playerId,
        String name,
        Long teamId
) {
}
