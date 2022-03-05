package com.dtw.betTracker.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.dtw.betTracker.service.lifecycleEventListener.CompetitionLifecycleEvenListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@EntityListeners(CompetitionLifecycleEvenListener.class)
public class Competition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String competitionName;
	
	@ManyToOne
	@JoinColumn(name = "sport_id", referencedColumnName = "id", nullable = false)
	private Sport sport;
	
	@OneToMany(mappedBy = "sportEvent.competition")
	private List<MatchedBet> matchedBets;
}