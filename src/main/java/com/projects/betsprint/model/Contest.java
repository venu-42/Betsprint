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
@Table(name = "contests")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contestId;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    @JsonBackReference("match-contests")
    private Match match;

    @OneToMany(mappedBy = "contest")
    @JsonManagedReference("contest-contest-entries")
    private List<ContestEntry> contestEntryList;

    private String name;
    private String prizePool;
    private Long maxEntries;
    private Long entryFees;
}
