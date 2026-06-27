package com.projects.betsprint.repository;

import com.projects.betsprint.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest, Long> {

    /**
     * Prevents N+1 queries when building match lobby display grids.
     */
    @Query("SELECT c FROM Contest c JOIN FETCH c.match m JOIN FETCH m.homeTeam JOIN FETCH m.awayTeam WHERE m.matchId = :matchId")
    List<Contest> findAllContestsByMatchId(@Param("matchId") Long matchId);
}
