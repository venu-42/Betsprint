package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "sports")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sportId;

    private String name;

    @OneToMany(mappedBy = "sport")
    private List<Tournament> tournamentList;

}
