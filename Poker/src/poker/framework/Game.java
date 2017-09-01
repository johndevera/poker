package poker.framework;

import java.util.HashMap;
import java.util.Map;
import static poker.framework.Street.*;

public class Game {

	public Map<Player, Decision> preFlopDecisions;
	public Map<Player, Decision> flopDecisions;
	public Map<Player, Decision> turnDecisions;
	public Map<Player, Decision> riverDecisions;
	public Map<Player, Integer> playerBets;
	
	private Player[] positions;
	
	private int pot;
	
	private int currentBet;
	
	private Street currentStreet;
	
	private final String gameID;
	
	//public int[] playerBets;
		
	public Game(Player[] positions) {
		resetPot();
		gameID = java.util.UUID.randomUUID().toString();
		preFlopDecisions = new HashMap<>();
		flopDecisions = new HashMap<>();
		turnDecisions = new HashMap<>();
		riverDecisions = new HashMap<>();
		this.positions = positions;
		currentStreet = PRE_FLOP;
		this.currentBet = 2;
		
		//playerBets.put(key, value)
		//playerBets = new int[positions.length];
		//for (int i = 0; i < playerBets.length; i++) {
		//	playerBets[i] = 0;
		//}
		
	}
		
	public int getPosIndexOfPlayer(Player p) {
		for(int i = 0; i < positions.length; i++) {
			if(p.equals(positions[i])) {
				return i;
			}
		}
		return -1;
	}
	
	public Player getPlayerAtPosIndex(int i) {
		return positions[i];
	}
	
	public int getNumPlayers() {
		return positions.length;
	}
	
	public Street getCurrentStreet() {
		return currentStreet;
	}
	
	public void nextStreet() {
		currentStreet = currentStreet.nextStreet();
	}
	
	public void increasePot(int amount) {
		pot += amount;
	}
	
	public void resetPot() {
		pot = 0;
	}
	
	public void addPreFlopDecision(Player player, Decision decision) {
		preFlopDecisions.put(player, decision);
	}
	
	public void addFlopDecision(Player player, Decision decision) {
		flopDecisions.put(player, decision);
	}
	
	public void addTurnDecision(Player player, Decision decision) {
		turnDecisions.put(player, decision);
	}
	
	public void addRiverDecision(Player player, Decision decision) {
		riverDecisions.put(player, decision);
	}

	public Map<Player, Decision> getPreFlopDecisions() {
		return preFlopDecisions;
	}

	public Map<Player, Decision> getFlopDecisions() {
		return flopDecisions;
	}

	public Map<Player, Decision> getTurnDecisions() {
		return turnDecisions;
	}

	public Map<Player, Decision> getRiverDecisions() {
		return riverDecisions;
	}

	public void setBet(int amount) {
		
		// if there is no current bet OR is at least double the current bet
		if(currentBet == 0 || (amount >= (currentBet * 2))) {
			currentBet = amount;
		}
	}

	public int getCurrentBet() {
		return currentBet;
	}
	
	public int getPot() {
		return pot;
	}

	public String getGameID() {
		return gameID;
	}
	
}
