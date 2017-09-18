package poker.learning;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.swing.plaf.synth.SynthSliderUI;

import poker.framework.Card;
import poker.framework.Decision;
import poker.framework.Deck;
import poker.framework.Street;

public class Scenario {
	
	private String id;
	
	private Card myCard1;
	
	private Card myCard2;
	
	private Position myPosition;

	private Position bettorOpponentPosition;
	
	private Card [] communityCards;
	
	private Size currentPot;
	
	private Size currentBet;
	
	private Street currentStreet;
	
	private Size [] playerStacks;
	
	private Map<Street,Decision []> playerDecisions; 
	
	private int numPlayersRemaining;
	
	private static final int NUM_PLAYERS = 10;
	
	public Scenario() {
		this.id = UUID.randomUUID().toString();
		
		myPosition = Position.random();

		do {
		
			bettorOpponentPosition = Position.random();
		}
		while(myPosition == bettorOpponentPosition);
		
		currentStreet = Street.random();
		
		Deck deck = new Deck();
		deck.init();
		deck.shuffle();
		
		myCard1 = deck.draw();
		myCard2 = deck.draw();
		
		communityCards = drawCards(currentStreet, deck);
		
		currentPot = Size.random();
		
		currentBet = Size.random();
				
		playerStacks = new Size[NUM_PLAYERS];
		
		for(int i = 0; i < playerStacks.length; i++) {
			playerStacks[i] = Size.random();
		}
		
		playerDecisions = new HashMap<>();
		
		int adjustment = 0;
		if(currentStreet == Street.PRE_FLOP) {
			adjustment = 0;
		}
		else if (currentStreet == Street.FLOP) {
			adjustment = 4;
		}
		else if (currentStreet == Street.TURN) {
			adjustment = 5;
		}
		else if (currentStreet == Street.RIVER) {
			adjustment = 6;
		}
		
		// simple way to adjust how many players remaining based on later streets
		numPlayersRemaining = (int) ( (NUM_PLAYERS-adjustment) * Math.random());
		
		if(numPlayersRemaining <= 1)
			numPlayersRemaining = 2;
	}
	
	/**
	 * [Community Cards] 	= 85 bits (17 bits/card   * 5 cards)
	 * [My Cards] 			= 34 bits (17 bits/card   * 2 cards)
	 * [Player Stacks]		= 50 bits (5  bits/player * 10 players)
	 * [Bet Amount]			= 5  bits (5  bits/bet	  * 1 bet)
	 * [Pot Amount]			= 5  bits (5  bits/pot	  * 1 pot)
	 * [My Position]			= 10 bits (10 bits/pos	  * 1 player)
	 * [Opponent Position]	= 10 bits (10 bits/pos	  * 1 player)
	 * [Num Players Left]	= 10 bits (10 bits/game	  * 1 game)
	 * [Street]				= 4  bits (4  bits/game	  * 1 game)
	 */
	public boolean [] bitArray() {
		
		// Community Cards Bits - Bits [0..84]
		List<CardBits> communityCardBits = new ArrayList<>();
		for(Card card : communityCards) {
			CardBits cardBits = new CardBits(card);
			communityCardBits.add(cardBits);
		}
		
		// My Cards Bits - Bits [85..118]
		CardBits myCard1Bits = new CardBits(myCard1);
		CardBits myCard2Bits = new CardBits(myCard2);
		
		// Player Stacks Bits - Bits [119..168]
		List<MoneySizeBits> playerStacksBits = new ArrayList<>();
		for(Size size : playerStacks) {
			playerStacksBits.add(new MoneySizeBits(size));
		}
		
		// Bet Amount Bits [169..173]
		MoneySizeBits betAmountBits = new MoneySizeBits(currentBet);
		
		// Pot Amount Bits [174..178]
		MoneySizeBits potAmountBits = new MoneySizeBits(currentPot);
		
		// My Position Bits [179..188]
		PositionBits positionBits = new PositionBits(myPosition);
		
		// My Bettor Opponent Bits [189..198]
		PositionBits betterOpponentBits = new PositionBits(bettorOpponentPosition);
		
		// Players Remaining Bits [199..208]
		PlayersRemainingBits playersRemainingBits = new PlayersRemainingBits(numPlayersRemaining);
		
		// Street Bits [209..212]
		StreetBits streetBits = new StreetBits(currentStreet);
		
		
		boolean [] bitArray = new boolean[216];  // Total # of bits - not sure why it's 216
		
		boolean completeCommunityCards 	= false;
		boolean completeMyCards 			= false;
		boolean completePlayerStacks 	= false;
		boolean completeBetAmount 		= false;
		boolean completePotAmount 		= false;
		boolean completeMyPosition 		= false;
		boolean completeOpponentPosition = false;
		boolean completeRemainingPlayers = false;
		boolean completeStreet			= false;
		
		for(int i = 0; i < bitArray.length; i++) {
			
			// CommunityCards
			if(i >= 0 && i <= 84 && !completeCommunityCards) {
				
				for(CardBits cardBits : communityCardBits) {
					
					boolean [] cardBitsArray = cardBits.getBits();
					
					for(int j = 0; j < cardBitsArray.length; j++) {
						bitArray[i] = cardBitsArray[j];
						i++; //Iterate main bitArray
					}
				}
				completeCommunityCards = true;
			}
			// My Cards
			else if (i >= 85 && i <= 118  && !completeMyCards) {
				
				boolean [] card1BitsArray = myCard1Bits.getBits();
				
				for(int j = 0; j < card1BitsArray.length; j++) {
					bitArray[i] = card1BitsArray[j];
					i++; //Iterate main bitArray
				}
				
				boolean [] card2BitsArray = myCard2Bits.getBits();
				
				for(int j = 0; j < card2BitsArray.length; j++) {
					bitArray[i] = card2BitsArray[j];
					i++; //Iterate main bitArray
				}
				completeMyCards = true;
			}
			//Player Stacks
			else if (i >= 119 && i <= 168  && !completePlayerStacks) {
				
				for(MoneySizeBits playerStackBits : playerStacksBits) {
					boolean [] sizeBits = playerStackBits.getBits();
					for(int j = 0; j < sizeBits.length; j++) {
						bitArray[i] = sizeBits[j];
						i++; //Iterate main bitArray
					}
				}
				completePlayerStacks = true;	
			}
			//Bet Amount
			else if (i >= 169 && i <= 173  && !completeBetAmount) {
				
				boolean [] betAmountBitsArray = betAmountBits.getBits(); 
				for(int j = 0; j < betAmountBitsArray.length; j++) {
					bitArray[i] = betAmountBitsArray[j];
					i++; //Iterate main bitArray
				}
				completeBetAmount = true;	
			}
			//Pot Amount
			else if (i >= 174 && i <= 178  && !completePotAmount) {
				
				boolean [] potAmountBitsArray = potAmountBits.getBits(); 
				for(int j = 0; j < potAmountBitsArray.length; j++) {
					bitArray[i] = potAmountBitsArray[j];
					i++; //Iterate main bitArray
				}
				completePotAmount = true;	
			}
			//My Position
			else if (i >= 179 && i <= 188  && !completeMyPosition) {
				
				boolean [] positionBitsArray = positionBits.getBits(); 
				for(int j = 0; j < positionBitsArray.length; j++) {
					bitArray[i] = positionBitsArray[j];
					i++; //Iterate main bitArray
				}
				completeMyPosition = true;	
			}
			//Bettor Opponent's Position
			else if (i >= 189 && i <= 198  && !completeOpponentPosition) {
				
				boolean [] betterOpponentBitsArray = betterOpponentBits.getBits(); 
				for(int j = 0; j < betterOpponentBitsArray.length; j++) {
					bitArray[i] = betterOpponentBitsArray[j];
					i++; //Iterate main bitArray
				}
				completeOpponentPosition = true;	
			}
			//Players Remaining
			else if (i >= 199 && i <= 208  && !completeRemainingPlayers) {
				
				boolean [] playersRemainingBitsArray = playersRemainingBits.getBits(); 
				for(int j = 0; j < playersRemainingBitsArray.length; j++) {
					bitArray[i] = playersRemainingBitsArray[j];
					i++; //Iterate main bitArray
				}
				completeRemainingPlayers = true;	
			}
			//Street
			else if (i >= 209 && i <= 212  && !completeStreet) {
				
				boolean [] streetBitsArray = streetBits.getBits(); 
				for(int j = 0; j < streetBitsArray.length; j++) {
					bitArray[i] = streetBitsArray[j];
					i++; //Iterate main bitArray
				}
				completeStreet = true;	
			}
		}
		return bitArray;
	}
	
	public static void main(String [] args) {
		Scenario scenario = new Scenario();
		System.out.println(scenario);
		boolean [] bitArray = scenario.bitArray();
		
		for(int i = 0; i < bitArray.length; i++) {
			
			int bit = (bitArray[i]) ? 1 : 0;
			
			System.out.print(bit + " ");
			
			if(i % 40 == 0  && i > 0) {
				System.out.println();
			}
		}
	}
	
	@Override
	public String toString() {
		
		Card ccard1 = null;
		Card ccard2 = null;
		Card ccard3 = null;
		Card ccard4 = null;
		Card ccard5 = null;
		
		for(int i = 0; i < communityCards.length; i++) {
			if(i == 0) ccard1 = communityCards[0];
			if(i == 1) ccard2 = communityCards[1];
			if(i == 2) ccard3 = communityCards[2];
			if(i == 3) ccard4 = communityCards[3];
			if(i == 4) ccard5 = communityCards[4];
		}
		
		String ccard1Name = (ccard1 != null) ? ccard1.getShortName() : "";
		String ccard2Name = (ccard2 != null) ? ccard2.getShortName() : "";
		String ccard3Name = (ccard3 != null) ? ccard3.getShortName() : "";
		String ccard4Name = (ccard4 != null) ? ccard4.getShortName() : "";
		String ccard5Name = (ccard5 != null) ? ccard5.getShortName() : "";
		
		return 
		"Game ID: " + getId() + "\n" + 
		"---------------------------\n" + 
		"Street: " + currentStreet + "\n" + 
		"Num Remaining Players: " + numPlayersRemaining + "\n" +
		"Stacks: " + "\n" + printStacks() + "\n" + 
		"My Position: " + myPosition + " (" + myPosition.getValue() + ")\n" + 
		"Opponent Position: " + bettorOpponentPosition + " (" + bettorOpponentPosition.getValue() + ")\n" + 
		"My Cards: " + myCard1.getShortName() + " ... " + myCard2.getShortName() + "\n" + 
		"Community Cards: " + ccard1Name + "..." + ccard2Name + "..." + ccard3Name + "..." + ccard4Name + " ... " + ccard5Name + "\n" + 
		"Bet Amount: " + currentBet + "\n" +
		"Pot Amount: " + currentPot + "\n\n";
	}
	
	private String printStacks() {
		String toPrint = "";
		for(int i = 0; i < playerStacks.length; i++) {
			toPrint += (i + ": " + playerStacks[i] + "\n");
		}
		return toPrint;
	}
	
	private Card [] drawCards(Street street, Deck deck) {
		
		Card [] communityCards = null;
		
		if(currentStreet == Street.FLOP) {
			communityCards = new Card[3];
		}
		else if (currentStreet == Street.TURN) {
			communityCards = new Card[4]; 
		}
		else if (currentStreet == Street.RIVER) {
			communityCards = new Card[5];
		}
		else {
			communityCards = new Card[0];
		}
		
		for(int i = 0; i < communityCards.length; i++) {
			communityCards[i] = deck.draw();
		}
		
		return communityCards;
	}

	public String getId() {
		return id;
	}

	public Card getMyCard1() {
		return myCard1;
	}

	public Card getMyCard2() {
		return myCard2;
	}

	public Position getMyPosition() {
		return myPosition;
	}

	public Position getBettorOpponentPosition() {
		return bettorOpponentPosition;
	}

	public Card[] getCommunityCards() {
		return communityCards;
	}

	public Size getCurrentPot() {
		return currentPot;
	}

	public Size getCurrentBet() {
		return currentBet;
	}

	public Street getCurrentStreet() {
		return currentStreet;
	}

	public Size[] getPlayerStacks() {
		return playerStacks;
	}

	public Map<Street, Decision[]> getPlayerDecisions() {
		return playerDecisions;
	}

	public int getNumPlayersRemaining() {
		return numPlayersRemaining;
	}

	public static int getNumPlayers() {
		return NUM_PLAYERS;
	}
	
	
}
