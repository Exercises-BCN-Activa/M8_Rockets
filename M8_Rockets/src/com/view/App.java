package com.view;

import com.application.RaceController;

public class App {

	public static void main(String[] args) {
		
		RaceController race = new RaceController(); //controller instance
		race.insertRounds(); //function that inserts target power into the competition, rounds or laps
		race.createRocket(); //function to create a new rocket
		race.testeDataBase(); //test database provided by the exercise
		race.showRockets(); //function that displays the competition rockets
		race.startRace(); //function that sets the competition in motion
		
		

	}

}
