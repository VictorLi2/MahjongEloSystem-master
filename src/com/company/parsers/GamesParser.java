package com.company.parsers;

import com.company.model.Game;
import com.company.model.GameManager;
import com.company.model.Player;

import java.util.*;

import javax.json.*;
import java.io.StringReader;


public class GamesParser {

	public void parseGames(String jsonResponse) {

		JsonReader reader = Json.createReader(new StringReader(jsonResponse));
		JsonArray gameArray = reader.readArray();

		for(int i = 0; i < gameArray.size(); i++) {
			JsonObject gameObject = gameArray.getJsonObject(i);
			Game game = parseGame(gameObject);
			GameManager.getInstance().addGame(game);
		}
	}


	private Game parseGame(JsonObject gameObject) {
		int id = gameObject.getInt("_id");
		int timeStamp = gameObject.getInt("timestamp");

		JsonString[] playerNameArray = {gameObject.getJsonString("east_player"), gameObject.getJsonString("south_player"),
				gameObject.getJsonString("west_player"), gameObject.getJsonString("north_player")};

		JsonNumber[] scoreArray = {gameObject.getJsonNumber("east_score"), gameObject.getJsonNumber("south_score"),
			gameObject.getJsonNumber("west_score"), gameObject.getJsonNumber("north_score")};

		return new Game(id, timeStamp, parsePlayerNames(playerNameArray), parseScores(scoreArray), List.of(1, 2, 3, 4));
	}

	private List<Player> parsePlayerNames(JsonString[] playerNameArray) {
		List<Player> players = new LinkedList<Player>();

		for(int i = 0; i < playerNameArray.length; i++) {
			for(Player player : GameManager.getInstance().getPlayers()) {
				if(player.getName().equals(playerNameArray[i].getString())) {
					players.add(player);
				}
			}

			if(players.size() != (i + 1)) {
				players.add(new Player(playerNameArray[i].getString()));
			}
		}

		return players;
	}

	private List<Integer> parseScores(JsonNumber[] scoreArray) {
		List<Integer> scores = new LinkedList<Integer>();

		for(int i = 0; i < scoreArray.length; i++) {
			scores.add(scoreArray[i].intValue());
		}

		return scores;
	}
}
