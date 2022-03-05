package com.dtw.betTracker.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dtw.betTracker.dto.SportDto;
import com.dtw.betTracker.dto.collection.SportCollectionDto;
import com.dtw.betTracker.error.ApiError;
import com.dtw.betTracker.service.SportService;

@RestController
@Validated
@RequestMapping("/sport")
public class SportController {

	@Autowired
	private SportService sportService;
	
	@GetMapping
	public ResponseEntity<SportCollectionDto> getAll(
			@RequestParam(name = "page", required = false, defaultValue ="0") @Min(0) Integer page, 
			@RequestParam(name = "size", required = false, defaultValue = "20") @Min(1) Integer size) {
		
		return sportService.getAll(PageRequest.of(page, size));
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
		
        return sportService.getOne(id);
    }
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid SportDto sportDto) {
		
        return sportService.create(sportDto);
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiError> delete(@PathVariable("id") Long id) {
		
		return sportService.delete(id);
	}
}