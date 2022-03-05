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

import com.dtw.betTracker.dto.MatchedBetDto;
import com.dtw.betTracker.dto.assembler.MatchedBetDtoAssembler;
import com.dtw.betTracker.dto.collection.MatchedBetCollectionDto;
import com.dtw.betTracker.entity.Competition;
import com.dtw.betTracker.entity.MatchedBet;
import com.dtw.betTracker.entity.Sport;
import com.dtw.betTracker.error.ApiError;
import com.dtw.betTracker.repo.MatchedBetRepo;

@Service
public class MatchedBetService {

	@Autowired
	private MatchedBetRepo matchedBetRepo;
	
	@Autowired
	private MatchedBetDtoAssembler dtoAssembler;
	
	@Autowired
	private PagedResourcesAssembler<MatchedBet> pagedAssembler;
	
	private final String resourceName = "MatchedBet";
	private final String identifierName = "id";
	private final String sportResourceName = "Sport";
	private final String sportIdentifierName = "name";
	private final String competitionResourceName = "Competition";
	private final String competitionIdentifierName = "name";
	
	public ResponseEntity<MatchedBetCollectionDto> getAll(Pageable pageable) {
		
		Page<MatchedBet> betPage = matchedBetRepo.findAll(pageable);
		if(betPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		PagedModel<MatchedBetDto> pagedModel = pagedAssembler.toModel(betPage, dtoAssembler);
		return ResponseEntity.ok(new MatchedBetCollectionDto(pagedModel.getContent(), pagedModel.getLinks()));
	}
	
	public ResponseEntity<?> getOne(Long id) {
		
		Optional<MatchedBet> matchedBetOpt = matchedBetRepo.findById(id);
		if(matchedBetOpt.isPresent()) {
			return matchedBetOpt
	        		.map(dtoAssembler::toModel)
	        		.map(ResponseEntity::ok)
	        		.get();
		}
		else {
			return ApiError.entityNotFound(resourceName, identifierName, id).buildResponseEntity();
		}
	}

	public ResponseEntity<?> create(MatchedBetDto matchedBetDto) {
		
		MatchedBet matchedBet = dtoAssembler.toEntity(matchedBetDto);
		Sport sport = matchedBet.getSportEvent().getSport();
		Competition competition = matchedBet.getSportEvent().getCompetition();

		if(sport == null) {
			return ApiError.entityNotFound(sportResourceName, sportIdentifierName, matchedBetDto.getSport()).buildResponseEntity();
		}
		if(competition == null && matchedBetDto.getCompetition() != null) {
			return ApiError.entityNotFound(competitionResourceName, competitionIdentifierName, matchedBetDto.getCompetition()).buildResponseEntity();
		}
		
		if(!sport.getCompetitions().contains(competition)) {
			return ApiError.entityDoesntContainEntity(sportResourceName, competitionResourceName, 
					competitionIdentifierName, matchedBetDto.getCompetition()).buildResponseEntity();
		}
		
		matchedBet = matchedBetRepo.save(matchedBet);
		return new ResponseEntity<>(dtoAssembler.toModel(matchedBet), HttpStatus.CREATED);
	}
	
	public ResponseEntity<ApiError> delete(Long id) {
		
		if(matchedBetRepo.existsById(id)) {
			matchedBetRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return ApiError.entityNotFound(resourceName, identifierName, id).buildResponseEntity();
		}
	}
}