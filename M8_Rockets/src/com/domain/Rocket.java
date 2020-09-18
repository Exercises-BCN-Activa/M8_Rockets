package com.domain;

import java.util.ArrayList;
import java.util.List;

public class Rocket {
	
	String codeId;
	int amountPropellants;
	Double velocity;
	List<Propellant> listPropellants = new ArrayList<>();

	public Rocket(String codeId, int amountPropellants) {
		this.codeId = codeId;
		this.amountPropellants = amountPropellants;
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

	public Double getVelocity() {
		return velocity;
	}

	public void setVelocity(Double velocity) {
		this.velocity = velocity;
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
		return builder.toString();
	}
	
}
