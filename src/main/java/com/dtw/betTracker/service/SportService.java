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

import com.dtw.betTracker.dto.SportDto;
import com.dtw.betTracker.dto.assembler.SportDtoAssembler;
import com.dtw.betTracker.dto.collection.SportCollectionDto;
import com.dtw.betTracker.entity.Sport;
import com.dtw.betTracker.error.ApiError;
import com.dtw.betTracker.repo.SportRepo;

@Service
public class SportService {

	@Autowired
	private SportRepo sportRepo;
	
	@Autowired
	private SportDtoAssembler dtoAssembler;
	
	@Autowired
	private PagedResourcesAssembler<Sport> pagedAssembler;
	
	private final String resourceName = "Sport";
	private final String identifierNotFound = "id";
	private final String identifierAlreadyExists = "name";
	
	public ResponseEntity<SportCollectionDto> getAll(Pageable pageable) {
		
		Page<Sport> sportPage = sportRepo.findAll(pageable);
		if(sportPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		PagedModel<SportDto> pagedModel = pagedAssembler.toModel(sportPage, dtoAssembler);
		return ResponseEntity.ok(new SportCollectionDto(pagedModel.getContent(), pagedModel.getLinks()));
	}
	
	public ResponseEntity<?> getOne(Long id) {
		
		Optional<Sport> sportOpt = sportRepo.findById(id);
		
		if(sportOpt.isPresent()) {
			return sportOpt
					.map(dtoAssembler::toModel)
					.map(ResponseEntity::ok)
					.get();
		}
		else {
			return ApiError.entityNotFound(resourceName, identifierNotFound, id).buildResponseEntity();
		}
	}
	
	public ResponseEntity<?> create(SportDto sportDto) {

		Sport sport = dtoAssembler.toEntity(sportDto);

		if(sportRepo.findBySportNameIgnoreCase(sportDto.getSportName()).isPresent()) {
			return ApiError.entityAlreadyExists(resourceName, identifierAlreadyExists, sportDto.getSportName()).buildResponseEntity();
		}

		sport = sportRepo.save(sport);
        return new ResponseEntity<>(dtoAssembler.toModel(sport), HttpStatus.CREATED);
    }

	public ResponseEntity<ApiError> delete(Long id) {
		
		if(sportRepo.existsById(id)) {
			sportRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return ApiError.entityNotFound(resourceName, identifierNotFound, id).buildResponseEntity();
		}
	}
}