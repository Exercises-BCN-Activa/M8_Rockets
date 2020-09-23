package com.persistence;

import java.util.ArrayList;
import java.util.List;

import com.domain.Rocket;

/**
 * base class that creates a structure capable of holding 
 * the rockets and the rounds that will be competed.
 * @author faunoguazina
 *
 */
public class Race {
	
	private int goal;
	private ArrayList<Integer>rounds;
	private List<Rocket> rockets;

	//basic builder that sets the standards
	public Race() {
		this.goal = 0;
		this.rounds= new ArrayList<>();
		this.rockets = new ArrayList<>();
	}

	//GETTERS
	public int getGoal() {
		return goal;
	}
	
	public List<Rocket> getRockets() {
		return rockets;
	}
	
	public ArrayList<Integer> getRounds() {
		return rounds;
	}

	//SETTERS
	public void setGoal(int goal) {
		this.goal = goal;
	}

	//set function of the lists is just addition
	public void addRocket(Rocket rocket) {
		rockets.add(rocket);
	}

	public void addRounds(Integer round) {
		this.rounds.add(round);
	}
	
	
	


}
