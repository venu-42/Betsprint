package com.projects.betsprint.repository;

import com.projects.betsprint.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByTeamTeamId(Long teamId);

    @Query("""
            SELECT p FROM Player p
            JOIN FETCH p.team t
            WHERE t.teamId = (SELECT m.homeTeam.teamId FROM Match m WHERE m.matchId = :matchId)
               OR t.teamId = (SELECT m.awayTeam.teamId FROM Match m WHERE m.matchId = :matchId)
            """)
    List<Player> findAllByMatchId(@Param("matchId") Long matchId);
}
