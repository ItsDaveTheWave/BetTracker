package com.dtw.betTracker.controller;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtw.betTracker.dto.collection.CompetitionCollectionDto;
import com.dtw.betTracker.service.CompetitionService;

@RestController
@Validated
@RequestMapping("/competition")
public class CompetitionController {

	@Autowired
	private CompetitionService competitionService;
	
	@GetMapping
	public ResponseEntity<CompetitionCollectionDto> getAll(
			@RequestParam(name = "page", required = false, defaultValue ="0") @Min(0) Integer page, 
			@RequestParam(name = "size", required = false, defaultValue = "20") @Min(1) Integer size) {
		
		return competitionService.getAll(PageRequest.of(page, size));
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
		
        return competitionService.getOne(id);
    }
}