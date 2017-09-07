package poker.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static poker.framework.Street.*;

public class Game {

	public Map<Player, List<Decision>> preFlopDecisions;
	public Map<Player, List<Decision>> flopDecisions;
	public Map<Player, List<Decision>> turnDecisions;
	public Map<Player, List<Decision>> riverDecisions;
	public Map<Player, Integer> playerBets;
	
	private Set<Player> allInPlayers;
	
	private Player[] positions;
	
	private int pot;
	
	//private int bigBlind;
	
	private int currentBet;
	
	private Street currentStreet;
	
	private List<Card> communityCards;
	
	private final String gameID;
	
	//public int[] playerBets;
		
	public Game(Player[] positions, int bigBlind) {
		resetPot();
		gameID = java.util.UUID.randomUUID().toString();
		preFlopDecisions = new HashMap<>();
		flopDecisions = new HashMap<>();
		turnDecisions = new HashMap<>();
		riverDecisions = new HashMap<>();
		this.positions = positions;
		currentStreet = PRE_FLOP;
		//this.bigBlind = bigBlind;
		this.currentBet = bigBlind;
		this.communityCards = new ArrayList<>(5);
		this.allInPlayers = new HashSet<>();
	}
	
	public void addCommunityCard(Card card) {
		this.communityCards.add(card);
	}
	
	/**
	 * This is good to know.  When you want to get the community cards but don't want the list to be changed, you return a copy.  If you return
	 * a reference to the community cards themselves, the contents of the list can be changed by the caller.  By returning a copy, you ensure
	 * that the state here can't be changed.
	 * 
	 */
	public List<Card> getCommunityCards() {
		
		List<Card> communityCardsCopy = new ArrayList<>();
		for(Card card : communityCards) {
			communityCardsCopy.add(card);
		}
		return communityCardsCopy;
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
	
	public void addAllInPlayer(Player player) {
		allInPlayers.add(player);
	}
	
	public boolean isPlayerAllIn(Player player) {
		return allInPlayers.contains(player);
	}
	
	public void addPreFlopDecision(Player player, Decision decision) {
		List<Decision> decisions = preFlopDecisions.get(player);
		if(decisions == null) {
			decisions = new ArrayList<>();
			preFlopDecisions.put(player, decisions);
		}
		decisions.add(decision);		
	}
	
	public void addFlopDecision(Player player, Decision decision) {
		List<Decision> decisions = flopDecisions.get(player);
		if(decisions == null) {
			decisions = new ArrayList<>();
			flopDecisions.put(player, decisions);
		}
		decisions.add(decision);
	}
	
	public void addTurnDecision(Player player, Decision decision) {
		List<Decision> decisions = turnDecisions.get(player);
		if(decisions == null) {
			decisions = new ArrayList<>();
			turnDecisions.put(player, decisions);
		}
		decisions.add(decision);
	}
	
	public void addRiverDecision(Player player, Decision decision) {
		List<Decision> decisions = riverDecisions.get(player);
		if(decisions == null) {
			decisions = new ArrayList<>();
			riverDecisions.put(player, decisions);
		}
		decisions.add(decision);
	}

	public Map<Player, List<Decision>> getPreFlopDecisions() {
		return preFlopDecisions;
	}

	public Map<Player, List<Decision>> getFlopDecisions() {
		return flopDecisions;
	}

	public Map<Player, List<Decision>> getTurnDecisions() {
		return turnDecisions;
	}

	public Map<Player, List<Decision>> getRiverDecisions() {
		return riverDecisions;
	}

	public void setBet(int amount) {
		
		this.currentBet = amount;		
	}

	public void resetBet() {
		
		setBet(0);
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
