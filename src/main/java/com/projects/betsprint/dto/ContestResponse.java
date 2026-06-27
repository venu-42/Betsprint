package com.projects.betsprint.dto;

public record ContestResponse(
        Long contestId,
        Long matchId,
        String name,
        String prizePool,
        Long maxEntries,
        Long entryFees
) {
}
