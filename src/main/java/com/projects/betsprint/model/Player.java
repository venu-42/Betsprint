package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "captain")
    private List<FantasyTeam> captainfantasyTeamList;

    @OneToMany(mappedBy = "viceCaptain")
    private List<FantasyTeam> viceCaptainfantasyTeamList;

    @OneToMany(mappedBy = "player")
    private List<FantasyTeamPlayer> fantasyTeamPlayerList;

}
