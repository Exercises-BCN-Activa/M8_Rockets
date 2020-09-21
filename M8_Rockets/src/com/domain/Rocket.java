package com.domain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Rocket {
	
	private String codeId;
	private Integer amountPropellants, maximumPower = 0, currentPower = 0;
	private Integer velocity = 0;
	private List<Propellant> listPropellants = new ArrayList<>();

	public Rocket(String codeId, int amountPropellants) {
		this.codeId = codeId;
		this.amountPropellants = amountPropellants;
	}
	
	public void speedUp() {
		int powerNow = 0;
		for (Propellant p : listPropellants) {
			powerNow += p.setAcceleration(true);
		}
		currentPower = powerNow;
		
	}
	
	public void speedDown(int targetPower) {
		int powerNow = 0;
		for (Propellant p : listPropellants) {
			powerNow += p.setAcceleration(false);
		}
		currentPower = powerNow;
	}
	
	public int calcPowerNecessary(int desiredVelocity) {
		Double calc = Math.pow((desiredVelocity-getVelocity())/100, 2);
		int necessaryPower = (int) Math.round(calc);
		return necessaryPower;
	}
	
	public int getVelocity() {
		setVelocity();
		return velocity;
	}
	
	private void setVelocity() {
		Double calc = velocity + 100 * Math.sqrt(this.getCurrentPower());
		velocity = (int) Math.round(calc);
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

	public int getMaximumPower() {
		setMaximumPower();
		return maximumPower;
	}

	private void setMaximumPower() {
		for (Propellant p : listPropellants) {
			maximumPower += p.getMaximumPower();
		}
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public int getAmountPropellants() {
		return amountPropellants;
	}

	public void setAmountPropellants(int amountPropellants) {
		this.amountPropellants = amountPropellants;
	}

	public List<Propellant> getListPropellants() {
		return listPropellants;
	}

	public void setListPropellants(List<Propellant> listPropellants) {
		this.listPropellants = listPropellants;
	}

	public void addListPropellants(Propellant propellant) {
		listPropellants.add(propellant);
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
		return builder.toString();
	}
	
}
