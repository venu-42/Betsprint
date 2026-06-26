package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "fantasy_teams")
public class FantasyTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fantasyTeamId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "captain_id", nullable = false)
    private Player captain;

    @ManyToOne
    @JoinColumn(name = "vice_captain_id", nullable = false)
    private Player viceCaptain;

    @OneToMany(mappedBy = "fantasyTeam")
    private List<FantasyTeamPlayer> fantasyTeamPlayerList;

    @OneToMany(mappedBy = "fantasyTeam")
    private List<ContestEntry> contestEntryList;
}
