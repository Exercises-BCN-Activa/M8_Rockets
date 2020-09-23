package com.domain;


public class Propellant implements Runnable{
	
	private int maximumPower;
	private int currentPower;
	private Rocket rocket;
	
	public Propellant(int maximumPower, Rocket rocket) {
		this.maximumPower = maximumPower;
		this.currentPower = 0;
		this.rocket = rocket;
	}

	public int getMaximumPower() {
		return maximumPower;
	}

	public int getCurrentPower() {
		return currentPower;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(currentPower);
		builder.append("/");
		builder.append(maximumPower);
		return builder.toString();
	}

	@Override
	public void run() {	
		if (rocket.getTargetPower() > rocket.getCurrentPower() && currentPower < maximumPower) {
			
			currentPower++;
			
		} else if (rocket.getTargetPower() < rocket.getCurrentPower() && currentPower > 0) {
			
			currentPower--;
			
		} 
	}
	

}
