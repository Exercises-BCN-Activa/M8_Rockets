package com.application;

import com.persistence.Race;

import com.domain.*;


/**
 * class that performs the whole application
 * @author faunoguazina
 *
 */
public class RaceController {
	
	private Race repository = new Race(); //creates a repository and a race
	
	public RaceController() {} //empty builder
	
	/**
	 * function that starts the whole competition.
	 * first check if there are registered rounds,
	 * then triggers setter goal for each round,
	 * prints on the console which round will perform,
	 * buckle for fires each rocket in one thread,
	 * join method in try / catch to avoid confusion between rockets and rounds
	 */
	public void startRace() {
		if (!repository.getRounds().isEmpty()) {
			for (int i : repository.getRounds()) {
				repository.setGoal(i);
				System.out.println("\nRound - "+i+" <-----------------------------\n");
				for (Rocket rocket : repository.getRockets()) {
					if (!rocket.getListPropellants().isEmpty()) {
						rocket.setTargetPower();
						System.out.println("\nto ------> "+i+" - Rocket: "+rocket.getCodeId()+"\n");
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
	
	/**
	 * prints all registered rockets
	 */
	public void showRockets() {
		if (!repository.getRockets().isEmpty()) {
			System.out.println("\nThe rockets for this competition will be:\n");
			for (Rocket rocket : repository.getRockets()) {
				System.out.println(rocket);				
			}
		}
	}
	
	/**
	 * insert rounds into the races, filling your list 
	 * with whole numbers greater than one,
	 * first asks how many rounds you want to register,
	 * then a buckle goes to introduce the power of each round.
	 * finally prints out which rounds the rockets will perform.
	 */
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
	
	/**
	 * method that creates rockets, requests name and quantity of propellants,
	 * if the user does not insert thrusters properly, the rocket is not created.
	 * 
	 */
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
	
	/**
	 * simple database that is inserted if the user does not insert any rocket
	 */
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
