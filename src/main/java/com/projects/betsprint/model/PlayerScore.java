package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;


@Entity
@Table(name = "player_scores")
@NoArgsConstructor
public class PlayerScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sportId;

    @ManyToOne
    @JoinColumn(name = "match_id",nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "player_id",nullable = false)
    private Player player;

    private Double fantasyPoints;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Double> scoreBreakdown;
}
