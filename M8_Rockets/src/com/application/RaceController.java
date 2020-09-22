package com.application;

import com.persistence.RacesRepository;
import com.domain.*;


public class RaceController {
	
	private RacesRepository repository;
	
	public RaceController(int power) {
		this.repository = new RacesRepository(power);
	}
	
	public void startRace() {
		for (Rocket rocket : repository.getRockets()) {
			new Thread(rocket).start();
		}
		
	}
	
	public void showRockets() {
		if (!repository.getRockets().isEmpty()) {
			for (Rocket rocket : repository.getRockets()) {
				System.out.println(rocket);				
			}
		}
	}
	
	public void createRocket() {
		String codeId = Utilities.input3cases("Enter the code name of the rocket", 0); 
		String propellants = Utilities.input3cases("Enter the amount of propellants in Rocket "+ codeId, 1);
		int amountPropellants = Integer.parseInt(propellants);
		Rocket rocket = new Rocket(codeId, repository);
		for (int i=1; i<=amountPropellants; i++) {
			rocket.addListPropellants(createPropellant(i, rocket));
		}
		
	}
	
	private Propellant createPropellant(int position, Rocket rocket) {
		String power = Utilities
				.input3cases("Enter the maximum power of the "+position+"º propeller", 2);
		int maximumPower = Integer.parseInt(power);
		Propellant propellant = new Propellant(maximumPower, rocket);
		return propellant;
	}
	
	public void testeDataBase() {
		Rocket rocket = new Rocket("32WESSDS", repository);
		rocket.addListPropellants(new Propellant(10, rocket));
		rocket.addListPropellants(new Propellant(30, rocket));
		rocket.addListPropellants(new Propellant(80, rocket));
		repository.addRocket(rocket);
		rocket = new Rocket("LDSFJA32", repository);
		rocket.addListPropellants(new Propellant(30, rocket));
		rocket.addListPropellants(new Propellant(40, rocket));
		rocket.addListPropellants(new Propellant(50, rocket));
		rocket.addListPropellants(new Propellant(50, rocket));
		rocket.addListPropellants(new Propellant(30, rocket));
		rocket.addListPropellants(new Propellant(10, rocket));
		repository.addRocket(rocket);
	}

}
