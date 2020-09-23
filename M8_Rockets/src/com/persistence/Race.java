package com.persistence;

import java.util.ArrayList;
import java.util.List;

import com.domain.Rocket;

public class Race {
	
	private int goal;
	private ArrayList<Integer>rounds= new ArrayList<>();
	private List<Rocket> rockets;

	public Race() {
		this.goal = 0;
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

	public ArrayList<Integer> getRounds() {
		return rounds;
	}

	public void addRounds(Integer round) {
		this.rounds.add(round);
	}
	
	
	


}
