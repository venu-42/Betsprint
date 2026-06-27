package com.projects.betsprint.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @ManyToOne
    @JoinColumn(name = "tournament_id",nullable = false)
    @JsonBackReference("tournament-matches")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "home_team_id",nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id",nullable = false)
    private Team awayTeam;

    @OneToMany(mappedBy = "match")
    @JsonManagedReference("match-fantasy-teams")
    List<FantasyTeam> fantasyTeamList;

    @OneToMany(mappedBy = "match")
    @JsonManagedReference("match-contests")
    List<Contest> contestList;
}
