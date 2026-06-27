package com.projects.betsprint.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "fantasy_team_players")
public class FantasyTeamPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fantasyTeamPlayerId;

    @ManyToOne
    @JoinColumn(name = "fantasy_team_id", nullable = false)
    @JsonBackReference("fantasy-team-players")
    private FantasyTeam fantasyTeam;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
}
