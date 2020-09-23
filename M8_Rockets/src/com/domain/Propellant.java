package com.domain;


/**
 * class that creates a propellant, implements the Runnable interface,
 * @author faunoguazina
 *
 */
public class Propellant implements Runnable{
	
	private int maximumPower;
	private int currentPower;
	private Rocket rocket;
	
	/**
	 * constructor that requires a maximum power and an object of the Rocket class
	 * @param maximumPower integer number
	 * @param rocket object
	 */
	public Propellant(int maximumPower, Rocket rocket) {
		this.maximumPower = maximumPower;
		this.currentPower = 0; //standard 0 in construction
		this.rocket = rocket;
	}

	//GETTERS
	public int getMaximumPower() {
		return maximumPower;
	}

	public int getCurrentPower() {
		return currentPower;
	}

	/**
	 *displays the current and maximum value separated by bar /
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(currentPower);
		builder.append("/");
		builder.append(maximumPower);
		return builder.toString();
	}

	/**
	 *method that implies the extension of the Runnable interface.
	 *has an if and if else system, in which the first checks whether 
	 *the rocket objective power is greater than the rocket current and 
	 *if the propellant current power is less than the propellant maximum, 
	 *in the other case it checks whether the rocket objective power is 
	 *less than the rocket current and whether the propellant current power
	 * is greater than zero.
	 */
	@Override
	public void run() {	
		if (rocket.getTargetPower() > rocket.getCurrentPower() && currentPower < maximumPower) {
			
			currentPower++;
			
		} else if (rocket.getTargetPower() < rocket.getCurrentPower() && currentPower > 0) {
			
			currentPower--;
			
		} 
	}
	

}
