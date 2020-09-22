package com.view;

import com.application.RaceController;

public class App {

	public static void main(String[] args) {
		
		RaceController race = new RaceController(25);
		
		race.testeDataBase();
		race.showRockets();
		
		
		
		

	}

}
