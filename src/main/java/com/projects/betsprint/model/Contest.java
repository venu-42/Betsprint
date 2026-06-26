package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "contests")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contestId;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @OneToMany(mappedBy = "contest")
    private List<ContestEntry> contestEntryList;


    private Long totalEntries;
}
