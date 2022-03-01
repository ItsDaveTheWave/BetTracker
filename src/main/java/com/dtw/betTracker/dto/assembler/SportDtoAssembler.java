package com.dtw.betTracker.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dtw.betTracker.controller.SportController;
import com.dtw.betTracker.dto.SportDto;
import com.dtw.betTracker.entity.Sport;
import com.dtw.betTracker.repo.CompetitionRepo;

@Component
public class SportDtoAssembler implements RepresentationModelAssembler<Sport, SportDto> {

	@Autowired
	private CompetitionRepo competitionRepo;
	
	@Autowired
	private CompetitionDtoAssembler competitionDtoAssembler;
	
	public Sport toEntity(SportDto dto) {
		return Sport.builder()
				.id(dto.getId())
				.sportName(dto.getSportName())
				.competitions(competitionRepo.findBySport_SportNameIgnoreCase(dto.getSportName()))
				.build();
	}
	
	@Override
	public SportDto toModel(Sport entity) {
		
		SportDto model = buildModel(entity);
		model.add(linkTo(methodOn(SportController.class).getOne(entity.getId())).withSelfRel());
		
		return model;
	}
	
	@Override
	public CollectionModel<SportDto> toCollectionModel(Iterable<? extends Sport> entities) {
		
		return StreamSupport.stream(entities.spliterator(), false)
				.map(this::toModel)
				.collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel::of));
	}
	
	private SportDto buildModel(Sport entity) {
		
		return SportDto.builder()
				.id(entity.getId())
				.sportName(entity.getSportName())
				.competitions(
						StreamSupport.stream(entity.getCompetitions().spliterator(), false)
						.map(competitionDtoAssembler::toModel)
						.collect(Collectors.toSet()))
				.build();
	}
}