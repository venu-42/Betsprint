package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Player> playerList;


    @OneToMany(mappedBy = "homeTeam")
    private List<Match> homeTeams;

    @OneToMany(mappedBy = "awayTeam")
    private List<Match> awayTeams;
}
