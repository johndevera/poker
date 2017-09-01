package poker.framework;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;
import java.util.*;

import poker.strategy.DefaultStrategyImpl;
import poker.framework.Game;
//import sun.misc.Queue;

public class Table {
	
	private Deck deck;
	
	private static final int SMALL_BLIND = 2;
	private static final int BIG_BLIND = 5;
	
	//int currentPot = SMALL_BLIND + BIG_BLIND;
	
	private static final String SMALL_BLIND_IND 	= "( SB)";
	private static final String BIG_BLIND_IND 		= "( BB)";
	private static final String DEALER_IND 			= "(  D)";
	private static final String UNDER_THE_GUN_IND 	= "(UTG)";
	private static final String OTHER 				= "(   )";
		
	private int smallBlind = 0;
	//private int firstToAct = null;
	//private int current
	
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
			System.out.print(positions[i] + ", ");
			
		}
		System.out.println();
		return positions;
	}
	
	public void playGame(List<Player> players, int gameNum) {
		
		deck.init();
		deck.shuffle();
		
		System.out.println("Now in GitHub");
		
		System.out.println();
		System.out.println("Game #" + gameNum);
		System.out.println("---------");
		
		Game game = new Game(getPlayerPositions(players));
		
		List<PlayerHand> playerHands = deal(deck, players);
		//List<Integer> playerBets = playerHands;
		//game.increasePot(SMALL_BLIND + BIG_BLIND);

		System.out.println("--------PREFLOP------");
		preflop(game, deck, playerHands);
		System.out.println(" ");
		
		System.out.println("--------FLOP------");
		flop(game, deck, playerHands);
		System.out.println(" ");
		
		System.out.println("--------TURN------");
		turn(game, deck, playerHands);
		System.out.println(" ");
		
		System.out.println("--------RIVER------");
		river(game, deck, playerHands);
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
			else if(underTheGun == i) positionIndicator 	= UNDER_THE_GUN_IND;
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
			else if(underTheGun == i) positionIndicator 	= UNDER_THE_GUN_IND;
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
			//this.currentPot = SMALL_BLIND + BIG_BLIND;
			
		}
		//players position is set by currentAction
		
		
		
		//while (pendingQ.size() != 0) {
			if(firstToAct == playerHands.size()) firstToAct = 0;
			else if (firstToAct == playerHands.size() + 1) firstToAct = 1;
							
			boolean allHandsPlayed = false;
			
			//int[] playerBets = new int[playerHands.size()];
			List<Integer> playerBets = new ArrayList<>();
			
			while(!pendingQ.isEmpty()) {
			//while(currentAction < playerHands.size() && !allHandsPlayed) {
				
				PlayerHand playerHand 	= playerHands.get(currentAction);
				Player currentPlayer 	= playerHand.getPlayer();
				Hand currentHand 		= playerHand.getHand();
				
				
				Decision decision = 
						currentPlayer.getStrategy().decide(currentPlayer, currentHand, game, null);	
				
				System.out.println(currentPlayer + ": " + decision.getType() + ": " + decision.getAmount());
				
				game.addPreFlopDecision(currentPlayer, decision);						
				
				//playerBets.add(currentAction, decision.getAmount());
				
				DecisionType choice = decision.getType();
				if (choice == DecisionType.RAISE) { //raise NEEDS TO BE WORKED ON. LOGIC STEP THROUGH
					game.setBet(decision.getAmount());
					//currentPlayer.myBet = decision.getAmount();
					//playerBets[currentAction] = decision.getAmount();
					pendingQ.remove();
					//finishedQ.
					while(!finishedQ.isEmpty())
						pendingQ.add(finishedQ.remove());
					finishedQ.add(playerHands.get(currentAction));
					
				}
				else if(choice == DecisionType.CALL || choice == DecisionType.CHECK) { //call or check. Preflop all calls -> queues work well.
					//if(game.getCurrentBet() > 0)
						//game.setBet(decision.getAmount());
					pendingQ.remove(); //this is playerHands.get(currentAction)
					finishedQ.add(playerHands.get(currentAction));
				}	
				else if (choice == DecisionType.FOLD){
					playerHands.remove(currentAction); //fold. Seems to work well
					pendingQ.remove();
					currentAction--;
				}
				else { // all in
					game.setBet(decision.getAmount());
				}

				//game.playerBets[currentAction] = decision.getAmount(); //whatever action is done, that person's bet becomes that amount.
				currentPlayer.setMyBet(decision.getAmount());
				game.increasePot(decision.getAmount());
				System.out.println("Pot: " + game.getPot());
				currentAction++;
				
				if(currentAction == playerHands.size()) {
					currentAction = 0;
				}
				
				if(currentAction == firstToAct) {
					allHandsPlayed = true;
				}
			}
	return playerHands;
	}
	
	private List<PlayerHand> preflop(Game game, Deck deck, List<PlayerHand> playerHands) {
		game.resetPot();
		game.increasePot(SMALL_BLIND + BIG_BLIND);
		game.setBet(0);
		return betting(game, playerHands, true);
	}
	
	private List<PlayerHand> flop(Game game, Deck deck, List<PlayerHand> playerHands) {
		game.setBet(0);
		return betting(game, playerHands, false);
	}

	private List<PlayerHand> turn(Game game, Deck deck, List<PlayerHand> playerHands) {
		game.setBet(0);
		return betting(game, playerHands, false);
	}
	
	private List<PlayerHand> river(Game game, Deck deck, List<PlayerHand> playerHands) {
		game.setBet(0);
		List<PlayerHand> endOfGame = betting(game, playerHands, false);
		game.resetPot();
		return endOfGame;
	}
		
	public static void main(String [] args) {
		
		List<Player> players = new ArrayList<Player>();
		players.add(new Player("Adam", 1000, true));
		players.add(new Player("Ben", 2000, true));
		players.add(new Player("Cliff", 1275, true));
		players.add(new Player("Dennis", 845, true));
		players.add(new Player("Eric", 2495, true));
		players.add(new Player("Florence", 1800, true));

		Table table = new Table();
		table.open();
		
		int totalGames = 5;
		
		int gameNum = 1;
		while(gameNum <= totalGames) {
			
			table.playGame(players, gameNum);
			gameNum++;
		}
		
		table.close();
	}
}
