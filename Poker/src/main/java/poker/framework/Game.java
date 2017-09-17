package poker.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static poker.framework.Street.*;

public class Game {

	/*
	 * Create the various collections that will take place during the game.
	 * Such as decisions at all the streets, the community cards, the player positions,
	 * And the all in players
	 */
	public Map<Player, List<Decision>> preFlopDecisions;
	public Map<Player, List<Decision>> flopDecisions;
	public Map<Player, List<Decision>> turnDecisions;
	public Map<Player, List<Decision>> riverDecisions;
	public Map<Player, Integer> playerBets;
	
	private List<Card> communityCards;
	private Set<Player> allInPlayers;
	private Player[] positions;
	
	
	/*
	 * Money
	 */
	private int pot;
	private int currentBet;
	private Street currentStreet;
	
	// Game ID
	private final String gameID;
	
	//Game Constructor	
	public Game(Player[] positions, int bigBlind) {
		resetPot();
		gameID = java.util.UUID.randomUUID().toString();
		preFlopDecisions = new HashMap<>();
		flopDecisions = new HashMap<>();
		turnDecisions = new HashMap<>();
		riverDecisions = new HashMap<>();
		this.positions = positions;
		currentStreet = PRE_FLOP;
		this.currentBet = bigBlind;
		this.communityCards = new ArrayList<>(5);
		this.allInPlayers = new HashSet<>();
	}
	
	//Each new card drawn at a street is added to the Community Card List.
	public void addCommunityCard(Card card) {
		this.communityCards.add(card);
	}
	
	/**
	 * This is good to know. When you want to get the community cards but don't want the list to be changed, you return a copy. If you return
	 * a reference to the community cards themselves, the contents of the list can be changed by the caller. By returning a copy, you ensure
	 * that the state here can't be changed.
	 */
	public List<Card> getCommunityCards() {
		
		List<Card> communityCardsCopy = new ArrayList<>();
		for(Card card : communityCards) {
			communityCardsCopy.add(card);
		}
		return communityCardsCopy;
	}
		
	//Returns position of player p with respect to small blind position being 1.
	public int getPosIndexOfPlayer(Player p) {
		for(int i = 0; i < positions.length; i++) {
			if(p.equals(positions[i])) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns the player who is at a specific position.
	 * @param i
	 * @return
	 */
	public Player getPlayerAtPosIndex(int i) {
		return positions[i];
	}
	
	/**
	 * Returns the number of players at the game.
	 * @return
	 */
	public int getNumPlayers() {
		return positions.length;
	}
	
	/**
	 * Returns the current street. Either preflop, flop, turn, or river.
	 * @return
	 */
	public Street getCurrentStreet() {
		return currentStreet;
	}
	
	/**
	 * Increases the street index by one.
	 */
	public void nextStreet() {
		currentStreet = currentStreet.nextStreet();
	}
	
	/**
	 * Places the value added by the player into the pot.
	 * @param amount
	 */
	public void increasePot(int amount) {
		pot += amount;
	}
	
	/**
	 * Called at start of game or after pot has been distributed. 
	 */
	public void resetPot() {
		pot = 0;
	}
	
	/*
	 * Adds player to the list of current all in players.
	 */
	public void addAllInPlayer(Player player) {
		allInPlayers.add(player);
	}
	
	/**
	 * Checks if a specific player is all in.
	 * @param player
	 * @return
	 */
	public boolean isPlayerAllIn(Player player) {
		return allInPlayers.contains(player);
	}
	
	/**
	 * Sets the current bet.
	 * @param amount 
	 */
	public void setBet(int amount) {
		
		this.currentBet = amount;		
	}

	/**
	 * Called at the beginning of the game. 
	 */
	public void resetBet() {
		
		setBet(0);
	}

	/**
	 * 
	 * @return Returns the current bet in the game
	 */
	public int getCurrentBet() {
		return currentBet;
	}
	
	/**
	 * 
	 * @return Returns the potsize of the current game.
	 */
	public int getPot() {
		return pot;
	}

	/**
	 * 
	 * @return Returns the gameID
	 */
	public String getGameID() {
		return gameID;
	}
	
	
	/**
	 * In preflop decision, it adds a specific player's decision to the map of preflop decisions.
	 * @param player
	 * @param decision
	 */
	public void addPreFlopDecision(Player player, Decision decision) {
		List<Decision> decisions = preFlopDecisions.get(player);
		if(decisions == null) {
			decisions = new ArrayList<>();
			preFlopDecisions.put(player, decisions);
		}
		decisions.add(decision);		
	}
	
	/**
	 * In Flop decision, it adds a specific player's decision to the map of Flop decisions.
	 * @param player
	 * @param decision
	 */
	public void addFlopDecision(Player player, Decision decision) {
		List<Decision> decisions = flopDecisions.get(player);
		if(decisions == null) {
			decisions = new ArrayList<>();
			flopDecisions.put(player, decisions);
		}
		decisions.add(decision);
	}
	
	/**
	 * In Turn decision, it adds a specific player's decision to the map of Turn decisions.
	 * @param player
	 * @param decision
	 */
	public void addTurnDecision(Player player, Decision decision) {
		List<Decision> decisions = turnDecisions.get(player);
		if(decisions == null) {
			decisions = new ArrayList<>();
			turnDecisions.put(player, decisions);
		}
		decisions.add(decision);
	}
	
	/**
	 * In River decision, it adds a specific player's decision to the map of River decisions.
	 * @param player
	 * @param decision
	 */
	public void addRiverDecision(Player player, Decision decision) {
		List<Decision> decisions = riverDecisions.get(player);
		if(decisions == null) {
			decisions = new ArrayList<>();
			riverDecisions.put(player, decisions);
		}
		decisions.add(decision);
	}

	/**
	 * @return Returns the map of all decisions that took place during Preflop
	 */
	public Map<Player, List<Decision>> getPreFlopDecisions() {
		return preFlopDecisions;
	}

	/**
	 * 
	 * @return Returns the map of all decisions that took place during Flop
	 */
	public Map<Player, List<Decision>> getFlopDecisions() {
		return flopDecisions;
	}

	/**
	 * 
	 * @return Returns the map of all decisions that took place during Turn
	 */
	public Map<Player, List<Decision>> getTurnDecisions() {
		return turnDecisions;
	}

	/**
	 * 
	 * @return Returns the map of all decisions that took place during River
	 */
	public Map<Player, List<Decision>> getRiverDecisions() {
		return riverDecisions;
	}

}

