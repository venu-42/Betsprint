package com.projects.betsprint.dto;

public record CreateContestRequest(
        Long matchId,
        String name,
        String prizePool,
        Long maxEntries,
        Long entryFees
) {
}
