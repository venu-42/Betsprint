package com.projects.betsprint.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// ContestEntry.java — new entity needed
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contest_entries")
public class ContestEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    @JsonBackReference("contest-contest-entries")
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "fantasy_team_id")
    @JsonBackReference("fantasy-team-contest-entries")
    private FantasyTeam fantasyTeam;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-contest-entries")
    private User user;

    private Double finalScore;
    private Integer rank;
    private BigDecimal winnings;
    private LocalDateTime joinedAt;
}
