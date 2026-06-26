package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "tournament_id",nullable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "home_team_id",nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id",nullable = false)
    private Team awayTeam;

    @OneToMany(mappedBy = "match")
    List<FantasyTeam> fantasyTeamList;

    @OneToMany(mappedBy = "match")
    List<Contest> contestList;
}


