package com.projects.betsprint.dto;

public record CreatePlayerRequest(
        String name,
        Long teamId
) {
}
