package com.projects.betsprint.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record TournamentResponse(
        UUID tournamentId,
        String title,
        LocalDateTime startDateTime,
        Long sportId
) {
}
