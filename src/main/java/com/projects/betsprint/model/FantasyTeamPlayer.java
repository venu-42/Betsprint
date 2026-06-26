package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "fantasy_team_players")
public class FantasyTeamPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fantasyTeamPlayerId;

    @ManyToOne
    @JoinColumn(name = "fantasy_team_id", nullable = false)
    private FantasyTeam fantasyTeam;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
}
