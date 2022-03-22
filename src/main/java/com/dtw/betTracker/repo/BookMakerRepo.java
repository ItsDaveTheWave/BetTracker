package com.dtw.betTracker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dtw.betTracker.entity.BookMaker;

public interface BookMakerRepo extends JpaRepository<BookMaker, Long> {

	public Optional<BookMaker> findByNameIgnoreCase(String name);
}