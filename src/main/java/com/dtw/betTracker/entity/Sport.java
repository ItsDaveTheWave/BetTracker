package com.dtw.betTracker.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.dtw.betTracker.service.lifecycleEventListener.SportLifecycleEvenListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
@EntityListeners(SportLifecycleEvenListener.class)
public class Sport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String sportName;
	
	@OneToMany(mappedBy = "sport", cascade = CascadeType.REMOVE)
	private Set<Competition> competitions;
	
	@OneToMany(mappedBy = "sportEvent.sport")
	private List<MatchedBet> matchedBets;
}