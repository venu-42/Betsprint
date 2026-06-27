package com.projects.betsprint.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "fantasy_teams")
public class FantasyTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fantasyTeamId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-fantasy-teams")
    private User user;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    @JsonBackReference("match-fantasy-teams")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "captain_id", nullable = false)
    private Player captain;

    @ManyToOne
    @JoinColumn(name = "vice_captain_id", nullable = false)
    private Player viceCaptain;

    @OneToMany(mappedBy = "fantasyTeam")
    @JsonManagedReference("fantasy-team-players")
    private List<FantasyTeamPlayer> fantasyTeamPlayerList;

    @OneToMany(mappedBy = "fantasyTeam")
    @JsonManagedReference("fantasy-team-contest-entries")
    private List<ContestEntry> contestEntryList;
}
