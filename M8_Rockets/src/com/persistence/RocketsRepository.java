package com.persistence;

import java.util.ArrayList;
import java.util.List;

import com.domain.Rocket;

public class RocketsRepository {
	
	List<Rocket> rockets = new ArrayList<>();
	
	public RocketsRepository() {}

	public List<Rocket> getRockets() {
		return rockets;
	}

	public void addRocket(Rocket rocket) {
		rockets.add(rocket);
	}
	
	

}
