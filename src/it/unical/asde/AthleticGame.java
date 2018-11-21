package it.unical.asde;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class AthleticGame {

	private String sport;
	private Map<String, Instant> arrivals;
	private Instant startTime;

	public AthleticGame(String sport) {
		super();
		this.sport = sport;
		arrivals = new HashMap<>();
	}

	public String getSport() {
		return sport;
	}

	public long getParecipiantTime(String participant) throws IllegalArgumentException {
		if(!arrivals.containsKey(participant)) {
			throw new IllegalArgumentException("Invalid partecipiant "+participant);
		}
		return ChronoUnit.MILLIS.between(startTime, arrivals.get(participant));
	}

	public String getWinner() {
		String best = null;
		long bestTime = Long.MAX_VALUE;

		for (String participant : arrivals.keySet()) {
			if (getParecipiantTime(participant) < bestTime) {
				best = participant;
				bestTime = getParecipiantTime(participant);
			}
		}

		return best;
	}
	
	public void addArrival(String participant, Instant arrival) {
		arrivals.put(participant, arrival);
	}
	
	public Map<String, Instant> getArrivals() {
		return arrivals;
	}
	
	public void reset() {
		arrivals.clear();
	}
	
	public void start() {
		startTime = Instant.now();
	}

}
