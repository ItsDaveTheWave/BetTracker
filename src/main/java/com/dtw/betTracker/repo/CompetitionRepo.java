package com.dtw.betTracker.repo;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtw.betTracker.entity.Competition;

public interface CompetitionRepo extends JpaRepository<Competition, Long> {

	public Optional<Competition> findByCompetitionNameIgnoreCase(String competition);
	public Set<Competition> findBySport_SportNameIgnoreCase(String sport);
}