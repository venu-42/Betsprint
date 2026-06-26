package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// ContestEntry.java — new entity needed
@NoArgsConstructor
@Entity
@Table(name = "contest_entries")
public class ContestEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @ManyToOne
    @JoinColumn(name = "fantasy_team_id")
    private FantasyTeam fantasyTeam;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double finalScore;
    private Integer rank;
    private BigDecimal winnings;
    private LocalDateTime joinedAt;
}
