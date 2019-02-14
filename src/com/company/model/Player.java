package com.company.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player implements Comparable<Player> {
	private int id;
	private String name;

	private double elo;

	private List<Game> games;
	private List<Double> historicalElo;

	public Player(String name) {
		id = GameManager.getInstance().getCurrentPlayerNumber();
		this.name = name;
		GameManager.getInstance().addPlayer(this);

		elo = 1500.0;
		games = new LinkedList<Game>();
		historicalElo = new LinkedList<Double>();

		for(int i = 0; i < GameManager.getInstance().getCurrentGameNumber() + 1; i++) {
			historicalElo.add(elo);
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setElo(double elo) {
		this.elo = elo;
		historicalElo.add(elo);
	}

	public double getElo() {
		return elo;
	}

	public void removeLastHistoricalElo() {
		historicalElo.remove(historicalElo.size() - 1);
	}

	public int getRoundedElo() {
		if(elo - Math.floor(elo) < 0.5) {
			return (int) elo;
		} else {
			return (int) elo + 1;
		}
	}

	public void addGame(Game game) {
		games.add(game);
	}

	public List<Game> getGames() {
		return Collections.unmodifiableList(games);
	}

	public int gamesPlayed() {
		return this.games.size();
	}

	public List<Double> getHistoricalElo() {
		return Collections.unmodifiableList(historicalElo);
	}

	@Override
	public int compareTo(Player other) {
		if(this.elo > other.elo) {
			return 1;
		} else if(this.elo == other.elo) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Player)) return false;

		Player player = (Player) o;

		return getId() == player.getId();

	}

	@Override
	public int hashCode() {
		return getId();
	}
}