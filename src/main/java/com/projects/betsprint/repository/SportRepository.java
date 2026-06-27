package com.projects.betsprint.repository;

import com.projects.betsprint.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

    Optional<Sport> findByNameIgnoreCase(String name);
}
