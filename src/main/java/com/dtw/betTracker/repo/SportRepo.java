package com.dtw.betTracker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtw.betTracker.entity.Sport;

public interface SportRepo extends JpaRepository<Sport, Long> {

	public Optional<Sport> findBySportNameIgnoreCase(String sport);
}