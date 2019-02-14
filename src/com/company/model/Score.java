package com.company.model;

import com.company.model.exceptions.MissCountedGameException;

public class Score implements Comparable<Score> {
	private int gameId;
	private Player player;
	private int points;
	private int placing;

	public Score(int gameId, Player player, int points) {
		this.gameId = gameId;
		this.player = player;
		this.points = points;
		placing = 0;
	}

	public Score(int gameId, Player player, int points, int placing) {
		this.gameId = gameId;
		this.player = player;
		this.points = points;
		this.placing = placing;
	}

	public Player getPlayer() {
		return player;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public int compareTo(Score other) {
		if(this.points > other.points) {
			return -1;
		} else if(this.points < other.points) {
			return 1;
		} else if(this.points == other.points) {
			if(this.placing > other.placing) {
				return 1;
			} else if(this.placing < other.placing) {
				return -1;
			} else if(this.placing == other.placing) {
				throw new MissCountedGameException("Scores for " + this.player.getName() + " & " +
						other.player.getName() + " are the same. Placings has to be added to game " + gameId);
			}
		}
		return 0; // should never get here
	}
}
