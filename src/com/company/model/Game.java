package com.company.model;

import com.company.model.exceptions.MissCountedGameException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Game {
	private int id;
	private int timeStamp;

	private List<Score> scores = new LinkedList<Score>();

	public Game(int id, int timeStamp, List<Player> players, List<Integer> points) {
		this.id = id;
		this.timeStamp = timeStamp;

		for(int i = 0; i < 4; i++) {
			scores.add(new Score(id, players.get(i), points.get(i)));
		}

		Collections.sort(scores);

		for(Player player : players) {
			player.addGame(this);
		}

		checkPointTotal();
	}

	public Game(int id, int timeStamp, List<Player> players, List<Integer> points, List<Integer> placings) {
		this.id = id;
		this.timeStamp = timeStamp;

		for(int i = 0; i < 4; i++) {
			scores.add(new Score(id, players.get(i), points.get(i), placings.get(i)));
		}

		Collections.sort(scores);

		for(Player player : players) {
			player.addGame(this);
		}

		checkPointTotal();
	}

	public int getId() {
		return id;
	}

	public List<Player> getPlayers() {
		List<Player> players = new LinkedList<Player>();

		for(Score score : scores) {
			players.add(score.getPlayer());
		}

		return Collections.unmodifiableList(players);
	}

	public List<Integer> getPoints() {
		List<Integer> points = new LinkedList<Integer>();

		for(Score score : scores) {
			points.add(score.getPoints());
		}

		return Collections.unmodifiableList(points);
	}

	private void checkPointTotal() {
		int pointTotal = 0;
		for(Score score : scores) {
			pointTotal += score.getPoints();
		}

		if(pointTotal != 100000) {
			throw new MissCountedGameException("Miss Counted Game at Game " + id + ", total = " + pointTotal);
		}
	}
}