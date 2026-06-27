package com.projects.betsprint.repository;

import com.projects.betsprint.model.ContestEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContestEntryRepository extends JpaRepository<ContestEntry, Long> {

    /**
     * Builds live leaderboards instantly while loading entity context.
     */
    @Query("SELECT ce FROM ContestEntry ce JOIN FETCH ce.user JOIN FETCH ce.fantasyTeam WHERE ce.contest.contestId = :contestId ORDER BY ce.finalScore DESC, ce.joinedAt ASC")
    List<ContestEntry> findLeaderboardByContestId(@Param("contestId") Long contestId);

    @Query("SELECT ce FROM ContestEntry ce JOIN FETCH ce.contest c JOIN FETCH c.match WHERE ce.user.userId = :userId")
    List<ContestEntry> findAllByUserId(@Param("userId") UUID userId);

    boolean existsByContest_ContestIdAndFantasyTeam_FantasyTeamId(Long contestId, Long fantasyTeamId);
    long countByContestContestId(Long contestId);
}
