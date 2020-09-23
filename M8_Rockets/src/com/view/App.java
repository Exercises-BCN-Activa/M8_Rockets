package com.view;

import com.application.RaceController;

public class App {

	public static void main(String[] args) {
		
		RaceController race = new RaceController();
		race.insertRounds();
		race.createRocket();
		race.testeDataBase();
		race.showRockets();
		race.startRace();
		
		

	}

}
