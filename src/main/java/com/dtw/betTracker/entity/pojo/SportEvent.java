package com.dtw.betTracker.entity.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dtw.betTracker.entity.Competition;
import com.dtw.betTracker.entity.Sport;
import com.dtw.betTracker.enums.GameResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SportEvent {
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date gameDate;
	
	@OneToOne
	@JoinColumn(name = "competition_id", referencedColumnName = "id")
	private Competition competition;
	
	@OneToOne
	@JoinColumn(name = "sport_id", referencedColumnName = "id", nullable = false)
	private Sport sport;
	
	@Column(nullable = false)
	private String team1;
	
	@Column(nullable = false)
	private String team2;
	
	@Enumerated(EnumType.STRING)
	private GameResult result;
}