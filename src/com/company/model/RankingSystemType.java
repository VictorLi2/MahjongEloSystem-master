package com.company.model;

public enum RankingSystemType {
	CURRENT_SYSTEM(0),
	OLD_SYSTEM(1);

	private final int rankingSystemType;

	RankingSystemType(int rankingSystemType) {
		this.rankingSystemType = rankingSystemType;
	}

	public int getRankingSystemType() {
		return rankingSystemType;
	}
}
