package com.company.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Manages games played between the players.
 *
 * Singleton pattern applied to ensure only a single instance of this class
 * is globally accessible throughout application.
 */
public class GameManager {
	private static GameManager instance;

	private int currentGameNumber;
	private int currentPlayerNumber;
	private List<Game> games;
	private List<Player> players;
	private EloCalculator eloCalculator;

	private GameManager() {
		currentGameNumber = 0;
		currentPlayerNumber = 0;
		games = new LinkedList<Game>();
		players = new LinkedList<Player>();
	}

	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}

	public void setEloCalculator(EloCalculator eloCalculator) {
		this.eloCalculator = eloCalculator;
	}

	public int getCurrentGameNumber() {
		return currentGameNumber;
	}

	public int getCurrentPlayerNumber() {
		return currentPlayerNumber;
	}

	public List<Game> getGames() {
		return Collections.unmodifiableList(games);
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public void addGame(Game game) {
		games.add(game);
		currentGameNumber++;
		eloCalculator.eloGameAdjust(game);
		Collections.sort(players);
	}

	public void addPlayer(Player player) {
		if (!players.contains(player)) {
			players.add(player);
			currentPlayerNumber++;
			Collections.sort(players);
		}
	}
}
