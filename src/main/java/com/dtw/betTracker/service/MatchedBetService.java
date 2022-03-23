package com.dtw.betTracker.service;

import java.math.BigDecimal;
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
	private final String bookMakerResourceName = "BookMaker";
	private final String bookMakerIdentifierName = "name";
	
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

		//check of entities
		if(sport == null) {
			return ApiError.entityNotFound(sportResourceName, sportIdentifierName, matchedBetDto.getSportEvent().getSport()).buildResponseEntity();
		}
		
		if(competition == null && matchedBetDto.getSportEvent().getCompetition() != null) {
			return ApiError.entityNotFound(competitionResourceName, competitionIdentifierName, matchedBetDto.getSportEvent().getCompetition()).buildResponseEntity();
		}
		
		if(!sport.getCompetitions().contains(competition)) {
			return ApiError.entityDoesntContainEntity(sportResourceName, competitionResourceName, 
					competitionIdentifierName, matchedBetDto.getSportEvent().getCompetition()).buildResponseEntity();
		}
		
		if(matchedBet.getBackBet().getBookMaker() == null) {
			return ApiError.entityNotFound(bookMakerResourceName, bookMakerIdentifierName, matchedBetDto.getBackBet().getBookMaker()).buildResponseEntity();
		}
		
		//set liability
		BigDecimal layBettedAmmount = new BigDecimal(matchedBet.getLayBet().getBettedAmmount().toString());
		BigDecimal layOdds = new BigDecimal(matchedBet.getLayBet().getOdds().toString());
		matchedBet.getLayBet().setLiability(layBettedAmmount.multiply(layOdds.subtract(BigDecimal.ONE)).doubleValue());
		
		//if backBet wins
		if(matchedBet.getSportEvent().getResult() == matchedBet.getBackBet().getBettedResult()) {
			BigDecimal backBettedAmmount = new BigDecimal(matchedBet.getBackBet().getBettedAmmount().toString());
			BigDecimal backOdds = new BigDecimal(matchedBet.getBackBet().getOdds().toString());
			
			matchedBet.getBackBet().setBackReturn(backBettedAmmount.multiply(backOdds.subtract(BigDecimal.ONE)).doubleValue());
			matchedBet.getLayBet().setLayReturn(-1 * matchedBet.getLayBet().getLiability());
		}
		//if layBet wins
		else {
			matchedBet.getBackBet().setBackReturn(-1 * matchedBet.getBackBet().getBettedAmmount());
			matchedBet.getLayBet().setLayReturn(matchedBet.getLayBet().getBettedAmmount());
		}
		//total return
		matchedBet.setTotalReturn(new BigDecimal(matchedBet.getBackBet().getBackReturn().toString())
				.add(new BigDecimal(matchedBet.getLayBet().getLayReturn().toString())).doubleValue());
		
		matchedBet = matchedBetRepo.save(matchedBet);
		return new ResponseEntity<>(dtoAssembler.toModel(matchedBet), HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> update(MatchedBetDto matchedBetDto, Long id) {
		
		if(!matchedBetRepo.existsById(id)) {
			return ApiError.entityNotFound(resourceName, identifierName, id).buildResponseEntity();
		}
		
		matchedBetDto.setId(id);
		return new ResponseEntity<>(create(matchedBetDto).getBody(), HttpStatus.OK);
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