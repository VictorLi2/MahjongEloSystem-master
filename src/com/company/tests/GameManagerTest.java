package com.company.tests;

import com.company.graphs.PlayerHistoricalElo;
import com.company.model.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

public class GameManagerTest {

	@Before
	public void setUp() {
		GameManager.getInstance().setEloCalculator(new EloCalculator(
				2000,
				5,
				Arrays.asList(15000, 0, -5000, -10000),
				RankingSystemType.CURRENT_SYSTEM));

		List<Player> playerList = Arrays.asList(new Player("A"), new Player("B"), new Player("C"), new Player("D"));

//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(59300, 26800, 12000, 1900)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(57700, 24300, 17900, 100)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(50400, 42300, 5600, 1700)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(32400, 30600, 29200, 7800)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(34500, 31100, 20100, 14300)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(35500, 34600, 32100, -2200)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(46300, 28000, 23400, 2300)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(49700, 42100, 13000, -4800)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(54800, 25000, 25000, -4800), Arrays.asList(1, 2, 3, 4)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(69600, 34700, 3400, -7700)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(52600, 31000, 17800, -1400)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(62200, 21200, 16200, 400)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(37300, 34600, 21700, 6400)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(47200, 45500, 10100, -2800)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(40300, 36900, 23000, -200)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(30400, 25800, 22100, 21700)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(42100, 34700, 25700, -2500)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(42400, 36900, 18700, 2000)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(35600, 28000, 24000, 12400)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(52600, 35200, 7200, 5000)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(41500, 39200, 24100, -4800)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(37400, 31300, 20600, 10700)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(30200, 30100, 20100, 19600)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(47000, 27300, 28000, -2300)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(32000, 30500, 27400, 10100)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(53000, 42000, 15700, -10700)));
//		GameManager.getInstance().addGame(new Game(playerList, Arrays.asList(42200, 22300, 20900, 14600)));
	}

	@Test
	public void test() {
		for(int i = 0; i < 4; i++) {
			PlayerHistoricalElo.graph(GameManager.getInstance().getPlayers().get(i));
		}
	}
}
