package com.dtw.betTracker.dto.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.dtw.betTracker.controller.CompetitionController;
import com.dtw.betTracker.dto.CompetitionDto;
import com.dtw.betTracker.entity.Competition;
import com.dtw.betTracker.repo.SportRepo;

@Component
public class CompetitionDtoAssembler implements RepresentationModelAssembler<Competition, CompetitionDto> {

	@Autowired
	private SportRepo sportRepo;
	
	public Competition toEntity(CompetitionDto dto) {
		return Competition.builder()
			.id(dto.getId())
			.competitionName(dto.getCompetitionName())
			.sport(sportRepo.findBySportNameIgnoreCase(dto.getSport()).orElse(null))
			.build();
	}
	
	@Override
	public CompetitionDto toModel(Competition entity) {
		
		CompetitionDto model = buildModel(entity);
		model.add(linkTo(methodOn(CompetitionController.class).getOne(entity.getId())).withSelfRel());
		
		return model;
	}
	
	@Override
	public CollectionModel<CompetitionDto> toCollectionModel(Iterable<? extends Competition> entities) {
		
		return StreamSupport.stream(entities.spliterator(), false)
			.map(this::toModel)
			.collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel::of));
	}
	
	private CompetitionDto buildModel(Competition entity) {
		return CompetitionDto.builder()
			.id(entity.getId())
			.competitionName(entity.getCompetitionName())
			.sport(entity.getSport().getSportName())
			.build();
	}
}
