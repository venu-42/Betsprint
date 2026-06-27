package com.projects.betsprint.repository;

import com.projects.betsprint.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT m FROM Match m JOIN FETCH m.homeTeam JOIN FETCH m.awayTeam WHERE m.tournament.tournamentId = :tournamentId")
    List<Match> findAllByTournamentId(@Param("tournamentId") UUID tournamentId);
}
