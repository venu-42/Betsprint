package com.projects.betsprint.repository;

import com.projects.betsprint.model.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamPlayerRepository extends JpaRepository<TeamPlayer, Long> {

    @Query("SELECT tp FROM TeamPlayer tp JOIN FETCH tp.player WHERE tp.team.teamId = :teamId AND tp.tournament.tournamentId = :tournamentId")
    List<TeamPlayer> findSquadByTeamAndTournament(@Param("teamId") Long teamId, @Param("tournamentId") UUID tournamentId);
}
