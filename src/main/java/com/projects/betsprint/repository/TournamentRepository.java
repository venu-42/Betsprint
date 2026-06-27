package com.projects.betsprint.repository;

import com.projects.betsprint.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, UUID> {

    @Query("SELECT t FROM Tournament t JOIN FETCH t.sport WHERE t.sport.sportId = :sportId")
    List<Tournament> findAllBySportId(@Param("sportId") Long sportId);
}
