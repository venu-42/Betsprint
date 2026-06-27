package com.projects.betsprint.repository;

import com.projects.betsprint.model.FantasyTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FantasyTeamRepository extends JpaRepository<FantasyTeam, Long> {

    @Query("SELECT ft FROM FantasyTeam ft JOIN FETCH ft.captain JOIN FETCH ft.viceCaptain WHERE ft.user.userId = :userId AND ft.match.matchId = :matchId")
    List<FantasyTeam> findAllByUserIdAndMatchId(@Param("userId") UUID userId, @Param("matchId") Long matchId);

    long countByUserUserIdAndMatchMatchId(UUID userId, Long matchId);
}
