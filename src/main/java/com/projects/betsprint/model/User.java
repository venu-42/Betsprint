package com.projects.betsprint.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    private String name;


    @OneToMany(mappedBy = "user", orphanRemoval = false)
    List<Transaction> transactionsList;

    @OneToMany(mappedBy = "user")
    List<FantasyTeam> fantasyTeamList;

    @OneToMany(mappedBy = "user")
    List<ContestEntry> contestEntryList;

    private String email;
    private BigDecimal walletBalance;
}
