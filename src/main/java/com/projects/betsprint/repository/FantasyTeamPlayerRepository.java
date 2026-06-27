package com.projects.betsprint.repository;

import com.projects.betsprint.model.FantasyTeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FantasyTeamPlayerRepository extends JpaRepository<FantasyTeamPlayer, Long> {

    /**
     * Pulls line-up vectors matching a requested strategy grid.
     */
    @Query("SELECT ftp FROM FantasyTeamPlayer ftp JOIN FETCH ftp.player WHERE ftp.fantasyTeam.fantasyTeamId = :fantasyTeamId")
    List<FantasyTeamPlayer> findAllPlayersByTeamId(@Param("fantasyTeamId") Long fantasyTeamId);
}
