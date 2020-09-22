package com.domain;

import java.util.ArrayList;
import java.util.List;

import com.persistence.RacesRepository;

public class Rocket implements Runnable {
	
	private String codeId;
	private int maximumPower;
	private int currentPower;
	private int targetPower;
	private int velocity;
	private List<Propellant> listPropellants;
	private RacesRepository race;
	private long elapsed;
	
	public Rocket(String codeId,  RacesRepository race) {
		this.codeId = codeId;
		this.maximumPower = 0;
		this.currentPower = 0;
		this.targetPower = race.getGoal();
		this.velocity = 0;
		this.listPropellants = new ArrayList<>();
		this.race = race;
	}

	public String getCodeId() {
		return codeId;
	}

	public int getMaximumPower() {
		setMaximumPower();
		return maximumPower;
	}

	private void setMaximumPower() {
		int powerNow = 0;
		for (Propellant p : listPropellants) {
			powerNow += p.getMaximumPower();
		}
		maximumPower = powerNow;
	}
	
	public int getCurrentPower() {
		setCurrentPower();
		return currentPower;
	}
	
	private void setCurrentPower() {
		int powerNow = 0;
		for (Propellant p : listPropellants) {
			powerNow += p.getCurrentPower();
		}
		currentPower = powerNow;
	}

	public int getTargetPower() {
		return targetPower;
	}
	
	public void setTargetPower() {
		targetPower = race.getGoal();
	}

	public int getVelocity() {
		setVelocity();
		return velocity;
	}
	
	public void setVelocity() {
		Double calc = velocity + 100*Math.sqrt(getCurrentPower());
		velocity = (int) Math.abs(calc);
	}

	public List<Propellant> getListPropellants() {
		return listPropellants;
	}

	public void addListPropellants(Propellant propellant) {
		listPropellants.add(propellant);
	}
	
	
	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long start) {
		this.elapsed = System.currentTimeMillis()-start;
	}

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
		if (elapsed!=0) {			
			builder.append(" - " + elapsed + " milliseconds");
		}
		return builder.toString();
	}
	
	@Override
	public void run() {	
		long start = System.currentTimeMillis();
		while (getTargetPower() != getCurrentPower()) {			
			for (Propellant propellant : listPropellants) {
				new Thread(propellant).start();
			}	
			setElapsed(start);
			System.out.println(toString());
		}
	}

}