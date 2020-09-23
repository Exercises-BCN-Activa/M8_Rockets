package com.domain;

import java.util.ArrayList;
import java.util.List;

import com.persistence.Race;

/**
 * class that creates a rocket, implements the Runnable interface,
 * @author faunoguazina
 *
 */
public class Rocket implements Runnable {
	
	private String codeId;
	private int maximumPower;
	private int currentPower;
	private int targetPower;
	private int velocity;
	private List<Propellant> listPropellants;
	private Race race;
	private long elapsed;
	
	/**
	 * constructor that requires a name code and an object of the Race class
	 * @param codeId string, must be 8 characters and at least one number
	 * @param race object to which the rocket will be stored and which will perform the competition
	 */
	public Rocket(String codeId,  Race race) {
		this.codeId = codeId; 
		this.race = race;
		
		//standard 0 in construction
		this.maximumPower = 0; 
		this.currentPower = 0;
		this.targetPower = race.getGoal();
		this.velocity = 0;
		this.listPropellants = new ArrayList<>();
	}

	/**
	 * standard getter
	 * @return string codeId, rocket name
	 */
	public String getCodeId() {
		return codeId;
	}

	/**
	 * getter which reviews the setter each time it is consulted
	 * @return integer with maximum power rocket
	 */
	public int getMaximumPower() {
		setMaximumPower();
		return maximumPower;
	}

	/**
	 * setter of maximum power rocket
	 * scrolls through the list of propellants and adds their maximum power values
	 */
	private void setMaximumPower() {
		int powerNow = 0;
		for (Propellant p : listPropellants) {
			powerNow += p.getMaximumPower();
		}
		maximumPower = powerNow;
	}
	
	/**
	 * getter which reviews the setter each time it is consulted
	 * @return integer with the current power rocket
	 */
	public int getCurrentPower() {
		setCurrentPower();
		return currentPower;
	}
	
	/**
	 * setter of current power rocket
	 * scrolls through the list of propellants and adds their current power values
	 */
	private void setCurrentPower() {
		int powerNow = 0;
		for (Propellant p : listPropellants) {
			powerNow += p.getCurrentPower();
		}
		currentPower = powerNow;
	}

	/**
	 * standard getter
	 * @return integer target power rocket
	 */
	public int getTargetPower() {
		return targetPower;
	}
	
	/**
	 * setter that seeks in the race object the objective power of the moment
	 */
	public void setTargetPower() {
		targetPower = race.getGoal();
	}

	/**
	 * getter which reviews the setter each time it is consulted
	 * @return integer value
	 */
	public int getVelocity() {
		setVelocity();
		return velocity;
	}
	
	/**
	 * performs a calculation based on the current rocket power
	 * speed is equal to speed at the time of calculation plus 
	 * 100 times the square root of the current power
	 */
	public void setVelocity() {
		Double calc = velocity + 100*Math.sqrt(getCurrentPower());
		velocity = (int) Math.abs(calc);
	}

	/**
	 * standard getter
	 * @return list of propellants
	 */
	public List<Propellant> getListPropellants() {
		return listPropellants;
	}

	/**
	 * setter that adds a propellant
	 * @param propellant object
	 */
	public void addListPropellants(Propellant propellant) {
		listPropellants.add(propellant);
	}
	
	
	/**
	 * @return long time taken in calculating acceleration
	 */
	public long getElapsed() {
		return elapsed;
	}

	/**
	 * setter that calculates the acceleration time
	 * @param start long with time from the instant the acceleration starts
	 */
	public void setElapsed(long start) {
		this.elapsed = System.currentTimeMillis()-start;
	}

	/**
	 *displays the name of the rocket, its list of thrusters, current speed, current power and total power
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(codeId);
		builder.append(": ");
		builder.append(listPropellants);
		builder.append(" {V:");
		builder.append(getVelocity());
		builder.append(" - P:");
		builder.append(getCurrentPower());
		builder.append("/");
		builder.append(getMaximumPower());
		builder.append("}");
		return builder.toString();
	}
	
	/**
	 * method that implies the extension of the Runnable interface.
	 *Proceeds with acceleration or deceleration of the rocket, 
	 *establishes a long time to start calculating the time to reach the objective.
	 *in a bucke while it checks the objective power and the current power and if 
	 *they are different, proceed with the activation of the propellants.
	 *each thruster is a different thread and the bucle while has a sleep of 100 milliseconds.
	 *if the rocket reaches maximum speed and still doesn’t reach the objective speed 
	 *it informs that the rocket doesn’t have enough power.
	 */
	@Override
	public void run() {
		long start, totalTime=0;//seconds variable definition
		while (getTargetPower() != getCurrentPower() && // target power different current power AND
				(getCurrentPower()<getMaximumPower()||getTargetPower()<getMaximumPower())) { // current potential less than total power OR objective power less than total power
			start = System.currentTimeMillis(); //initialization of the time variable
			for (Propellant propellant : listPropellants) { //scroll the propellants
				new Thread(propellant).start(); // thread for each one
			}	
			setElapsed(start); totalTime+=getElapsed(); //setter that calculates propulsion action time
			System.out.println(toString() + " - " + elapsed + "ms"); //time impression
			try {Thread.sleep(100);} catch (InterruptedException e) {return;} // sleep in try/catch
		}
		System.out.println(codeId + ": Total Time - " + totalTime + "ms"); //total time impression
		if (getCurrentPower()==getMaximumPower() && getTargetPower()>getMaximumPower()) { //if the rocket does not have enough power it gives this warning
			System.out.println("\n"+codeId+" cannot accelerate ends at the speed indicated by lack of power\n");
		}
		
	}
	
}