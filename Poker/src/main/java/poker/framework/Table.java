package poker.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import poker.framework.Game;

public class Table {
	
	private Deck deck;
	
	private static final int BIG_BLIND 		= 5;
	private static final int SMALL_BLIND 	= BIG_BLIND/2;
	
	private static final String SMALL_BLIND_IND 	= "( SB)";
	private static final String BIG_BLIND_IND 		= "( BB)";
	private static final String DEALER_IND 			= "( D )";
	private static final String UNDER_THE_GUN_IND 	= "(UTG)";
	private static final String OTHER 				= "(   )";
		
	private int smallBlind = 0;
	
	public void open() {
		deck = new Deck();
	}
	
	public void close() {
		
	}
	
	private Player[] getPlayerPositions(List<Player> players) {
	
		Player[] positions = new Player[players.size()];
		
		int currIndex = 0;
		
		for(int i = smallBlind; i <= players.size() - 1; i++) {
			positions[currIndex] = players.get(i);
			currIndex++;
		}
		for(int i = 0; i < smallBlind; i++) {
			positions[currIndex] = players.get(i);
			currIndex++;
		}
		
		for(int i = 0; i < positions.length; i++) {
			Player player = positions[i];
			System.out.print(player + "[" + player.getStack() + "], ");			
		}
		System.out.println();
		return positions;
	}
	
	private void calcTotals(List<Player> players) {
		int sum = 0;
		for(int i = 0; i < players.size(); i++) {
			sum += players.get(i).getStack();
		System.out.println("Total Money: " + sum);	
		}
		
	}
	public void playGame(List<Player> players, int gameNum) {
		
		deck.init();
		deck.shuffle();
		
		System.out.println();
		System.out.println("Game #" + gameNum);
		System.out.println("---------");
		
		calcTotals(players);
		
		
		Game game = new Game(getPlayerPositions(players), BIG_BLIND);
		
		List<PlayerHand> playerHands = deal(deck, players);

		System.out.println("--------PREFLOP------");
		preflop(game, deck, playerHands);
		System.out.println(" ");
		if(playerHands.size() == 1) return;
		
		System.out.println("--------FLOP------");
		nextStreet(game, deck, playerHands);
		System.out.println(" ");
		if(playerHands.size() == 1) return;
		
		System.out.println("--------TURN------");
		nextStreet(game, deck, playerHands);
		System.out.println(" ");
		if(playerHands.size() == 1) return;
		
		System.out.println("--------RIVER------");
		nextStreet(game, deck, playerHands);
		if(playerHands.size() == 1) return;
		
		// At this point, there are multiple players who made it through the river.  Now it's time to determine the winner in a showdown.
		Player [] winners = determineWinners(game, playerHands);

		// Pay the pot to the winners
		//try {
		emptyPot(winners, game);
		//}
		//catch (IllegalArithmeticException e) {
			//TODO Create method to distribute pot to entire table.
			//emptyPot(playerHands, game);
		//}
		System.out.println(" ");

		deck.draw();
		
		smallBlind++;

		if(smallBlind == players.size()) {
			smallBlind = 0;
		}
	}
	
	private List<PlayerHand> deal(Deck deck, List<Player> players) {
	
		List<PlayerHand> playerHands = new ArrayList<>();
		
		int bigBlind = (smallBlind < players.size() - 1) 
				? smallBlind + 1
				: 0;
		
		int underTheGun = (bigBlind < players.size() - 1) 
				? bigBlind + 1
				: 0;		
		
		int dealer = (smallBlind == 0)
				? players.size() - 1
				: smallBlind - 1;
				
		 
		for(int i = smallBlind; i < players.size(); i++) {
			
			PlayerHand playerHand = 
					new PlayerHand(players.get(i), new Hand(deck.draw(), deck.draw()));			
			playerHands.add(playerHand);
			
			String positionIndicator = "";
			if(smallBlind == i) positionIndicator 		= SMALL_BLIND_IND;
			else if(bigBlind == i) positionIndicator 	= BIG_BLIND_IND;
			else if(underTheGun == i) positionIndicator = UNDER_THE_GUN_IND;
			else if(dealer == i) positionIndicator 		= DEALER_IND;
			else positionIndicator						= OTHER;
			
			System.out.println(positionIndicator + " " + playerHand);
		}
		
		for(int i = 0; i < smallBlind; i++) {
			
			PlayerHand playerHand = 
					new PlayerHand(players.get(i), new Hand(deck.draw(), deck.draw()));			
			playerHands.add(playerHand);
			
			String positionIndicator = "";
			if(smallBlind == i) positionIndicator 		= SMALL_BLIND_IND;
			else if(bigBlind == i) positionIndicator 	= BIG_BLIND_IND;
			else if(underTheGun == i) positionIndicator = UNDER_THE_GUN_IND;
			else if(dealer == i) positionIndicator 		= DEALER_IND;
			else positionIndicator						= OTHER;
			
			System.out.println(positionIndicator + " " + playerHand);
		}
		return playerHands;
	}
	
	private List<PlayerHand> betting(Game game, List<PlayerHand> playerHands, boolean isPreflop) {
		Queue<PlayerHand> pendingQ = new LinkedList<>(playerHands);
		Queue<PlayerHand> finishedQ = new LinkedList<>();
		
		
		int firstToAct = 0;
		int currentAction = 0;
		
		if (isPreflop == true){
			firstToAct = 2;	
			currentAction = firstToAct;
			pendingQ.add(pendingQ.remove()); //take the SB and put them to the back
			pendingQ.add(pendingQ.remove()); //take the BB and put them to the back
			
		}

		if(firstToAct == playerHands.size()) firstToAct = 0;
		else if (firstToAct == playerHands.size() + 1) firstToAct = 1;
						
		//boolean allHandsPlayed = false;
		
		while(pendingQ.size() > 0) {
			PlayerHand playerHand 	= playerHands.get(currentAction);
			Player currentPlayer 	= playerHand.getPlayer();
			Hand currentHand 		= playerHand.getHand();

			if(game.isPlayerAllIn(currentPlayer)) {
				System.out.println("--" + currentPlayer + " is already all in.  No decisions--");
				finishedQ.add(pendingQ.remove());
				continue; // This means go back to top of the loop...
			}
			
			ActionValidator action = new ActionValidator();
			
			Decision decision = 
					currentPlayer.getStrategy().decide(currentPlayer, currentHand, game, null, action);	
			
			if(game.getCurrentStreet() == Street.PRE_FLOP) {
				game.addPreFlopDecision(currentPlayer, decision);	
			}
			else if(game.getCurrentStreet() == Street.FLOP) {
				game.addFlopDecision(currentPlayer, decision);	
			}
			else if(game.getCurrentStreet() == Street.TURN) {
				game.addTurnDecision(currentPlayer, decision);	
			}
			else {
				game.addRiverDecision(currentPlayer, decision);
			}
									
			int amount = decision.getAmount();
			DecisionType choice = decision.getType();
			
			if (choice == DecisionType.RAISE) { //raise NEEDS TO BE WORKED ON. LOGIC STEP THROUGH
				game.setBet(amount);
				//printResults(currentPlayer, game, amount, decision);
				//System.out.println("--" + currentPlayer + " raises|bets--");
				//increasePot(currentPlayer, game, amount, decision);
				pendingQ.remove();
				while(!finishedQ.isEmpty())
					pendingQ.add(finishedQ.remove());
				finishedQ.add(playerHands.get(currentAction));
				
			}
			else if(choice == DecisionType.CALL || choice == DecisionType.CHECK) { //call or check. Preflop all calls -> queues work well.
				if(choice == DecisionType.CALL) {
					//System.out.println("--" + currentPlayer + " calls--");
					//increasePot(currentPlayer, game, amount, decision);
				}
				else {
					//System.out.println("--" + currentPlayer + " checks--");
				}
				pendingQ.remove(); //this is playerHands.get(currentAction)
				finishedQ.add(playerHands.get(currentAction));
			}	
			else if (choice == DecisionType.FOLD){
				//System.out.println("--" + currentPlayer + " folds--");
				playerHands.remove(currentAction); //fold. Seems to work well
				pendingQ.remove();
				currentAction--;
			}
			else { // all in
				game.setBet(amount);
				game.addAllInPlayer(currentPlayer);
				//System.out.println("--" + currentPlayer + " all-in--");
				//increasePot(currentPlayer, game, amount, decision);
			}
			increasePot(currentPlayer, game, amount, decision.getType());
			currentAction++;
			
			if(currentAction == playerHands.size()) {
				currentAction = 0;
			}
			
			if(currentAction == firstToAct) {
				//allHandsPlayed = true;
			}
			
			try {
				Thread.sleep(50);
			}catch(Exception e) {
					e.printStackTrace();
			}
			//printResults(currentPlayer, game, amount, decision);
			
			//Used to make sure there is at least one person who wins in pendingQ or finishedQ 
			//PendingQ has to be size 1 and FinishedQ has to be size 0  and vice versa
			if ((pendingQ.size() == 1 && finishedQ.size() == 0)||(pendingQ.size() == 0 && finishedQ.size() == 1)) {
				break;
			}
		}
		return playerHands;
	}
	
	private void printResults(Player player, Game game, int oldStack, int amount, DecisionType decision){
		//int oldStack = player.getStack();
		System.out.println(player + ":\t" + decision + "\tStack:" + oldStack + "\t-" + amount + " = " + player.getStack()+ "\t ... POT = " + game.getPot());
	}
	/**
	 * This method is synchronized because we need to ensure increasing pot + deducting amount from player is an atomic transaction
	 */
	private synchronized void increasePot(Player player, Game game, int amount, DecisionType decision) {
		int oldStack = player.getStack();
		player.setMyBet(amount);
		player.deduct(amount);
		game.increasePot(amount);
		printResults(player, game, oldStack, amount, decision);
		//System.out.println(player + " -" + amount + " ... pot = " + game.getPot());
		//System.out.println(player + ": \tStack:" + oldStack + "-" + amount + " = " + player.getStack()+ "\t ... POT = " + game.getPot());
	}
	
	/**
	 * This method is synchronized because we need to ensure emptying pot + adding amount to player(s) is an atomic transaction
	 * Multiple players are passed in case of a split pot
	 */
	private synchronized void emptyPot(Player [] players, Game game) throws IllegalArgumentException{
		
		int pot = game.getPot();
		
		int numWinningPlayers = players.length;
		int splitPot = 0;
		if(numWinningPlayers != 0) {
			splitPot = pot/numWinningPlayers;
		}
		else {
			throw new IllegalArgumentException("No players won. Redistribute pot");
		}
		//catch (IllegalArgumentException e){
			//NO ONE wins. Split the pot and distribute to everyone
			//splitPot = pot/players.length;
			
		//}
		
		for(Player player : players) {
			player.add(splitPot);
			System.out.println("--" + player + " wins--");
			System.out.println(player + " +" + splitPot);
		}
		
		pot -= (splitPot * numWinningPlayers);
		
		// In a split pot situation where the money doesn't divide equally, by default, just give the full remainder to the first player.
		// This logic can be changed when doing more fine-tuning.
		if(pot > 0) {			
			players[0].add(pot);
		}
		
		game.resetPot(); // back to zero
	}
	
	private void preflop(Game game, Deck deck, List<PlayerHand> playerHands) {
		
		Player smallBlindPlayer = playerHands.get(0).getPlayer();
		Player bigBlindPlayer 	= playerHands.get(1).getPlayer();
		
		increasePot(smallBlindPlayer, game, SMALL_BLIND, DecisionType.RAISE);
		increasePot(bigBlindPlayer  , game, BIG_BLIND, DecisionType.RAISE);
		playerHands = betting(game, playerHands, true);
		
		if(playerHands.size() == 1) {
			Player [] winner = {playerHands.get(0).getPlayer()};
			emptyPot(winner, game);
		}
	}
	
	private List<PlayerHand> nextStreet(Game game, Deck deck, List<PlayerHand> playerHands) {
		
		game.nextStreet();
		game.resetBet();
		
		int numCardsToDeal = (game.getCurrentStreet() == Street.FLOP)
			? 3
			: 1;
		
		for(int i = 0; i < numCardsToDeal; i++) {
			game.addCommunityCard(deck.draw());
		}
		
		for(Card card : game.getCommunityCards()) {
			System.out.print(card.getShortName() + ", ");
		}
		System.out.println();
				
		List<PlayerHand> remainingPlayerHands = betting(game, playerHands, false);
		
		if(remainingPlayerHands.size() == 1) {
			Player [] winner = {remainingPlayerHands.get(0).getPlayer()};
			emptyPot(winner, game);
		}
		
		return remainingPlayerHands;
	}
	
	private Player [] determineWinners(Game game, List<PlayerHand> playerHandShowdowns) {
		
		Map<Player, FiveCardHand> finalHands = new HashMap<>();
		
		List<Card> communityCards = game.getCommunityCards();
				
		FiveCardHand winningHand = null;
				
		// Iterate over remaining players' hands to determine what's the winning hand
		for(PlayerHand playerHand : playerHandShowdowns) {
			List<Card> playerCards = new ArrayList<>(communityCards);
			
			Hand hand = playerHand.getHand();
			playerCards.add(hand.getFirst());
			playerCards.add(hand.getSecond());
			
			FiveCardHand fiveCardHand = HandEvaluator.evaluateSeven(playerCards.toArray(new Card[7]));
			
			if(winningHand == null || fiveCardHand.getValue() > winningHand.getValue()) {
				winningHand = fiveCardHand;
			}
			
			finalHands.put(playerHand.getPlayer(), fiveCardHand);			
		}
		
		System.out.println("The Winning hand is " + winningHand);
		
		List<Player> winningPlayers = new ArrayList<>();
		
		
		// Iterate over remaining players.  Whoever has that hand is a winner.
		// TODO: Be able to differentiate between higher flush or higher straight or higher pair, etc...
		for(Player player : finalHands.keySet()) {
						
			FiveCardHand fiveCardHand = finalHands.get(player);
			
			if(winningHand == fiveCardHand) {
				winningPlayers.add(player);
			}
		}
				
		return winningPlayers.toArray(new Player[0]);
	}
		
	public static void main(String [] args) {
		
		List<Player> players = new ArrayList<Player>();
		players.add(new Player("Abe", 1000, true));
		players.add(new Player("Ben", 2000, true));
		players.add(new Player("Cat", 1275, true));
		players.add(new Player("Dan", 845, true));
		players.add(new Player("Edd", 2495, true));
		players.add(new Player("Fay", 1800, true));

		Table table = new Table();
		table.open();
		
		int totalGames = 10;
		
		int gameNum = 1;
		while(gameNum <= totalGames) {
			
			table.playGame(players, gameNum);
			gameNum++;
		}
		
		System.out.println("Final Stack Sizes");
		System.out.println("-----------------");
		for(Player player : players) {
			System.out.print(player + "[" + player.getStack() + "], ");
		}
		
		table.close();
	}
}
