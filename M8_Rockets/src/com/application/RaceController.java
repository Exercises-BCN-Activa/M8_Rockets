package com.application;

import com.persistence.Race;

import com.domain.*;


public class RaceController {
	
	private Race repository = new Race();
	
	public RaceController() {}
	
	public void startRace() {
		if (!repository.getRounds().isEmpty()) {
			for (int i : repository.getRounds()) {
				repository.setGoal(i);
				System.out.println("\nRound - "+i+" <-----------------------------\n");
				for (Rocket rocket : repository.getRockets()) {
					if (!rocket.getListPropellants().isEmpty()) {
						rocket.setTargetPower();
						System.out.println("------> "+i+" - "+rocket.getCodeId()+"\n");
						Thread t = new Thread(rocket);
						t.start();
						try {t.join();} catch (InterruptedException e) {return;}							
					}
				}
			}			
		} else {
			System.out.println("\nthere are no registered rounds!!!\n".toUpperCase());
		}
	}
	
	public void showRockets() {
		if (!repository.getRockets().isEmpty()) {
			System.out.println("\nThe rockets for this competition will be:\n");
			for (Rocket rocket : repository.getRockets()) {
				System.out.println(rocket);				
			}
		}
	}
	
	public void insertRounds() {
		String stringRound = Utilities.input3cases("how many laps do you want to insert in the race?", 1);
		if (!stringRound.equalsIgnoreCase("NulL")) {
			int intRound, laps = Integer.parseInt(stringRound);
			for (int i = 0; i < laps; i++) {
				stringRound = Utilities.input3cases("Enter the power of the round "+(i+1)+" in this race", 2);
				if (!stringRound.equalsIgnoreCase("NulL")) {
					intRound = Integer.parseInt(stringRound);
					repository.addRounds(intRound);					
				}
			}
			System.out.println("\nThe power rounds of this competition will be: " + repository.getRounds()+"\n");
		}
	}
	
	public void createRocket() {
		String codeId = Utilities.input3cases("Enter the code name of the rocket", 0); 
		if (!codeId.equalsIgnoreCase("NulL")) {
			Rocket rocket = new Rocket(codeId, repository);
			String propellants = Utilities.input3cases("Enter the amount of propellants in Rocket "+ codeId, 1);
			if (!propellants.equalsIgnoreCase("NulL")) {
				int amountPropellants = Integer.parseInt(propellants);
				Propellant propellant;
				String power;
				int maximumPower;
				for (int i=1; i<=amountPropellants; i++) {
					power = Utilities.input3cases("Enter the maximum power of the "+i+"º propeller", 2);
					if (!power.equalsIgnoreCase("NulL")) {
						maximumPower = Integer.parseInt(power);
						propellant = new Propellant(maximumPower, rocket);
						rocket.addListPropellants(propellant);
					}
				}
				if (!rocket.getListPropellants().isEmpty()) {
					repository.addRocket(rocket);
				}
			}
		}
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
