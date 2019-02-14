package com.company.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EloCalculator {
	private int n;
	private int exp;
	private List<Integer> placingAdjustments;
	private RankingSystemType rankingSystemType;

	public EloCalculator(int n, int exp, List<Integer> placingAdjustments, RankingSystemType rankingSystemType) {
		this.n = n;
		this.exp = exp;
		this.placingAdjustments = placingAdjustments;
		this.rankingSystemType = rankingSystemType;
	}

	public void eloGameAdjust(Game game) {
		List<Double> expectedScores = expectedScores(game);
		List<Double> adjustedScores = adjustedScores(game);

		for(Player player: GameManager.getInstance().getPlayers()) {
			if(game.getPlayers().contains(player)) {
				int i = game.getPlayers().indexOf(player);
				double eloChange = eloChange(player, expectedScores.get(i), adjustedScores.get(i));
				player.setElo(player.getElo() + eloChange);
			} else {
				player.setElo(player.getElo());
			}
		}
	}

	private List<Double> expectedScores(Game game) {
		double rawExpectedScoreSum = 0.0;
		List<Double> rawExpectedScores = new LinkedList<Double>();
		List<Double> expectedScores = new LinkedList<Double>();

		for(Player player : game.getPlayers()) {
			double rawExpectedScore = 1 / (1 + Math.pow(this.exp, (fieldElo(player, game) - player.getElo()) / this.n));
			rawExpectedScoreSum += rawExpectedScore;
			rawExpectedScores.add(rawExpectedScore);
		}

		for(Double rawExpectedScore : rawExpectedScores) {
			expectedScores.add(rawExpectedScore / rawExpectedScoreSum);
		}

		return expectedScores;
	}

	private double fieldElo(Player player, Game game) {
		double fieldElo = 0.0;

		for(Player p : game.getPlayers()) {
			if(!player.equals(p)) {
				fieldElo += p.getElo();
			}
		}
		return fieldElo / 3;
	}

	private List<Double> adjustedScores(Game game) {
		List<Double> adjustedScores = new LinkedList<Double>();

		for(int i = 0; i < 4; i++) {
			adjustedScores.add((game.getPoints().get(i) + placingAdjustments.get(i)) / 100000.0);
		}

		return adjustedScores;
	}

	private double eloChange(Player player, double expectedScore, double adjustedScore) {
		int k;

		switch(rankingSystemType) {
			case CURRENT_SYSTEM:
				k = 100 - Math.min(player.gamesPlayed(), 10) - Math.min(Math.max(player.gamesPlayed() - 10, 0), 10) * 2;
				break;
			default:
				k = 0;
		}
		return k * (adjustedScore - expectedScore);

	}
}