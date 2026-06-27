package com.projects.betsprint.dto;

import java.time.LocalDateTime;

public record CreateTournamentRequest(
        String title,
        LocalDateTime startDateTime,
        Long sportId
) {
}
