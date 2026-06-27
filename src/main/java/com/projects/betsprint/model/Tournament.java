package com.projects.betsprint.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tournamentId;

    private String title;
    private LocalDateTime startDateTime;

    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false)
    @JsonBackReference("sport-tournaments")
    private Sport sport;

    @OneToMany(mappedBy = "tournament")
    @JsonManagedReference("tournament-matches")
    private List<Match> matchList;

}
