package com.domain;

public class Propellant {
	
	int maximumPower, currentPower = 0;

	public Propellant(int maximumPower) {
		this.maximumPower = maximumPower;
	}

	public int getMaximumPower() {
		return maximumPower;
	}

	public void setMaximumPower(int maximumPower) {
		this.maximumPower = maximumPower;
	}

	public int getCurrentPower() {
		return currentPower;
	}

	public void setCurrentPower(int currentPower) {
		if (currentPower >= 0 || currentPower <= maximumPower) {			
			this.currentPower = currentPower;
		}
	}

	public void setAcceleration(boolean isAccelerating) {
		if (isAccelerating && currentPower < maximumPower) {
			currentPower++;			
		} else if (!isAccelerating || currentPower > 0) {
			currentPower--;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(maximumPower);
		return builder.toString();
	}
	
	
	
}
