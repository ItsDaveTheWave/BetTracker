package com.dtw.betTracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dtw.betTracker.dto.CompetitionDto;
import com.dtw.betTracker.dto.assembler.CompetitionDtoAssembler;
import com.dtw.betTracker.dto.collection.CompetitionCollectionDto;
import com.dtw.betTracker.entity.Competition;
import com.dtw.betTracker.error.ApiError;
import com.dtw.betTracker.repo.CompetitionRepo;

@Service
public class CompetitionService {

	@Autowired
	private CompetitionRepo competitionRepo;
	
	@Autowired
	private CompetitionDtoAssembler dtoAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Competition> pagedAssembler;
	
	private final String resourceName = "Competition";
	private final String identifierName = "id";
	
	public ResponseEntity<CompetitionCollectionDto> getAll(Pageable pageable) {
		
		Page<Competition> competitionPage = competitionRepo.findAll(pageable);
		if(competitionPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		PagedModel<CompetitionDto> pagedModel = pagedAssembler.toModel(competitionPage, dtoAssembler);
		return ResponseEntity.ok(new CompetitionCollectionDto(pagedModel.getContent(), pagedModel.getLinks()));
	}
	
	public ResponseEntity<?> getOne(Long id) {
		
		Optional<Competition> competitionOpt = competitionRepo.findById(id);
		
		if(competitionOpt.isPresent()) {
			return competitionOpt
					.map(dtoAssembler::toModel)
					.map(ResponseEntity::ok)
					.get();
		}
		else {
			return ApiError.entityNotFound(resourceName, identifierName, id).buildResponseEntity();
		}
	}
}