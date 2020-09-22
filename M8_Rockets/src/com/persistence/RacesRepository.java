package com.persistence;

import java.util.ArrayList;
import java.util.List;

import com.domain.Rocket;

public class RacesRepository {
	
	private int goal;
	private List<Rocket> rockets;

	public RacesRepository(int goal) {
		this.goal = goal;
		this.rockets = new ArrayList<>();
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public List<Rocket> getRockets() {
		return rockets;
	}

	public void addRocket(Rocket rocket) {
		rockets.add(rocket);
	}
	


}
