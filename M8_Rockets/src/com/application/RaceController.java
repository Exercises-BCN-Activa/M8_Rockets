package com.application;

import com.persistence.RocketsRepository;

import java.util.Iterator;

import com.domain.*;

public class RaceController {
	
	private RocketsRepository repository = new RocketsRepository();
	
	public RaceController() {}
	
	public void createRocket() {
		String codeId = Utilities.input3cases("Enter the code name of the rocket", 0); 
		String propellants = Utilities.input3cases("Enter the amount of propellants in Rocket "+ codeId, 1);
		int amountPropellants = Integer.parseInt(propellants);
		Rocket rocket = new Rocket(codeId, amountPropellants);
		for (int i=1; i<=amountPropellants; i++) {
			rocket.addListPropellants(createPropellant(i));
		}
		if (rocket.getListPropellants().size() == rocket.getAmountPropellants()) {
			repository.addRocket(rocket);			
		}
	}
	
	private Propellant createPropellant(int position) {
		String power = Utilities
				.input3cases("Enter the maximum power of the "+position+"º propeller", 2);
		int maximumPower = Integer.parseInt(power);
		Propellant propellant = new Propellant(maximumPower);
		return propellant;
	}
	
	public void showRockets() {
		if (!repository.getRockets().isEmpty()) {
			Iterator<Rocket> it = repository.getRockets().iterator();
			while (it.hasNext()) {
				System.out.println(it.next());				
			}
		}
	}
	
	public void testDbRockets() {
		Rocket rocket = new Rocket("32WESSDS", 3);
		rocket.addListPropellants(new Propellant(10));
		rocket.addListPropellants(new Propellant(30));
		rocket.addListPropellants(new Propellant(80));
		repository.addRocket(rocket);
		rocket = new Rocket("LDSFJA32", 6);
		rocket.addListPropellants(new Propellant(30));
		rocket.addListPropellants(new Propellant(40));
		rocket.addListPropellants(new Propellant(50));
		rocket.addListPropellants(new Propellant(50));
		rocket.addListPropellants(new Propellant(30));
		rocket.addListPropellants(new Propellant(10));
		repository.addRocket(rocket);
		
	}

}
