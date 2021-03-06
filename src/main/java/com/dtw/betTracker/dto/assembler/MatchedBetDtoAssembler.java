package com.dtw.betTracker.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dtw.betTracker.controller.MatchedBetController;
import com.dtw.betTracker.dto.BackBetDto;
import com.dtw.betTracker.dto.LayBetDto;
import com.dtw.betTracker.dto.MatchedBetDto;
import com.dtw.betTracker.dto.SportEventDto;
import com.dtw.betTracker.entity.MatchedBet;
import com.dtw.betTracker.entity.pojo.BackBet;
import com.dtw.betTracker.entity.pojo.LayBet;
import com.dtw.betTracker.entity.pojo.SportEvent;
import com.dtw.betTracker.enums.GameResult;
import com.dtw.betTracker.repo.BookMakerRepo;
import com.dtw.betTracker.repo.CompetitionRepo;
import com.dtw.betTracker.repo.SportRepo;

@Component
public class MatchedBetDtoAssembler implements RepresentationModelAssembler<MatchedBet, MatchedBetDto> {

	@Autowired
	private SportRepo sportRepo;
	
	@Autowired
	private CompetitionRepo competitionRepo;
	
	@Autowired
	private BookMakerRepo bookMakerRepo;
	
	public MatchedBet toEntity(MatchedBetDto dto) {
		return MatchedBet.builder()
				.id(dto.getId())
				.betDate(dto.getBetDate())
				.sportEvent(
						SportEvent.builder()
						.gameDate(dto.getSportEvent().getGameDate())
						.sport(sportRepo.findBySportNameIgnoreCase(dto.getSportEvent().getSport()).orElse(null))
						.competition(competitionRepo.findByCompetitionNameIgnoreCase(dto.getSportEvent().getCompetition()).orElse(null))
						.team1(dto.getSportEvent().getTeam1())
						.team2(dto.getSportEvent().getTeam2())
						.result(GameResult.valueOf(dto.getSportEvent().getResult()))
						.build())
				.backBet(
						BackBet.builder()
						.bookMaker(bookMakerRepo.findByNameIgnoreCase(dto.getBackBet().getBookMaker()).orElse(null))
						.bettedResult(GameResult.valueOf(dto.getBackBet().getBettedResult()))
						.bettedAmmount(dto.getBackBet().getBettedAmmount())
						.odds(dto.getBackBet().getOdds())
						.backReturn(dto.getBackBet().getBackReturn())
						.build())
				.layBet(
						LayBet.builder()
						.bettedAmmount(dto.getLayBet().getBettedAmmount())
						.odds(dto.getLayBet().getOdds())
						.layReturn(dto.getLayBet().getLayReturn())
						.liability(dto.getLayBet().getLiability())
						.build())
				.totalReturn(dto.getTotalReturn())
				.bonusType(dto.getBonusType())
				.build();
	}
	
	@Override
	public MatchedBetDto toModel(MatchedBet entity) {
		
		MatchedBetDto model = buildModel(entity);
		model.add(linkTo(methodOn(MatchedBetController.class).getOne(entity.getId())).withSelfRel());
		
		return model;
	}
	
	@Override
	public CollectionModel<MatchedBetDto> toCollectionModel(Iterable<? extends MatchedBet> entities) {
		
		return StreamSupport.stream(entities.spliterator(), false)
				.map(this::toModel)
				.collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel::of));
	}
	
	private MatchedBetDto buildModel(MatchedBet entity) {
		return MatchedBetDto.builder()
				.id(entity.getId())
				.betDate(entity.getBetDate())
				.totalReturn(entity.getTotalReturn())
				.bonusType(entity.getBonusType())
				.sportEvent(
						SportEventDto.builder()
						.gameDate(entity.getSportEvent().getGameDate())
						.sport(entity.getSportEvent().getSport() != null ? entity.getSportEvent().getSport().getSportName() : null)
						.competition(entity.getSportEvent().getCompetition() != null ? entity.getSportEvent().getCompetition().getCompetitionName() : null)
						.team1(entity.getSportEvent().getTeam1())
						.team2(entity.getSportEvent().getTeam2())
						.result(entity.getSportEvent().getResult().name())
						.build())
				.backBet(
						BackBetDto.builder()
						.bookMaker(entity.getBackBet().getBookMaker().getName())
						.bettedResult(entity.getBackBet().getBettedResult().name())
						.bettedAmmount(entity.getBackBet().getBettedAmmount())
						.odds(entity.getBackBet().getOdds())
						.backReturn(entity.getBackBet().getBackReturn())
						.build())
				.layBet(
						LayBetDto.builder()
						.bettedAmmount(entity.getLayBet().getBettedAmmount())
						.odds(entity.getLayBet().getOdds())
						.layReturn(entity.getLayBet().getLayReturn())
						.liability(entity.getLayBet().getLiability())
						.build())
				.build();
	}
}