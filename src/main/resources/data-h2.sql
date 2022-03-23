insert into sport (id, sport_name) values 
	(1, 'FootBall');
	
insert into competition (id, competition_name, sport_id) values 
	(1, 'Mundial UEFA', 1),
	(2, 'Premier League', 1),
	(3, 'Segunda division', 1),
	(4, 'Clasificatorio Mundial Europa', 1);

insert into book_maker (id, name) values
	(1, 'betFair'),
	(2, 'betFred'),
	(3, 'bwin'),
	(4, 'codere');

insert into matched_bet (id, bet_date, game_date, sport_id, competition_id, team1, team2, result, total_return, bonus_type, 
		back_bookmaker_id, back_betted_result, back_odds, back_betted_ammount, back_return, lay_betted_ammount, lay_odds, lay_return, liability) values 
	(1, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 1, 'Gales', 'Bielorrusia',           'TEAM_1_WIN', null, null, 4, 'TIE',        7,    10, null, 7.56, 8,     null, 52.92),
	(2, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('21-11-2021','dd-MM-yyyy'), 1, 2, 'Manchester City', 'Everton',     'TEAM_1_WIN', null, null, 4, 'TIE',        8,    10, null, 7.34, 9.6,   null, 63.12),
	(3, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('12-11-2021','dd-MM-yyyy'), 1, 4, 'Austria', 'Israel',              'TEAM_1_WIN', null, null, 2, 'TEAM_1_WIN', 1.53, 15, null, 14.76, 1.62, null, 9.15),
	(4, TO_DATE('12-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 3, 'UD Las Palmas', 'Real Zaragoza', 'TEAM_2_WIN', null, null, 3, 'TIE',        3.1,  50, null, 45.12, 3.5,  null, 112.80),
	(5, TO_DATE('13-11-2021','dd-MM-yyyy'), TO_DATE('13-11-2021','dd-MM-yyyy'), 1, 4, 'Montenegro', 'Holanda',          'TIE',        null, null, 3, 'TIE',        6.75, 25, null, 20.73, 7,    null, 124.38);