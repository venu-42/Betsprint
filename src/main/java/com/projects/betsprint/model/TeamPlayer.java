package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "team_players",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"team_id", "player_id", "tournament_id"}
        )
)
public class TeamPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}