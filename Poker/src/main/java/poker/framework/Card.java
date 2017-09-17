package poker.framework;

import static poker.framework.Rank.*;
import static poker.framework.Suit.*;

public enum Card {

	TWO_OF_DIAMONDS(TWO, DIAMOND, "2d"),
	THREE_OF_DIAMONDS(THREE, DIAMOND, "3d"),
	FOUR_OF_DIAMONDS(FOUR, DIAMOND, "4d"),
	FIVE_OF_DIAMONDS(FIVE, DIAMOND, "5d"),
	SIX_OF_DIAMONDS(SIX, DIAMOND, "6d"),
	SEVEN_OF_DIAMONDS(SEVEN, DIAMOND, "7d"),
	EIGHT_OF_DIAMONDS(EIGHT, DIAMOND, "8d"),
	NINE_OF_DIAMONDS(NINE, DIAMOND, "9d"),
	TEN_OF_DIAMONDS(TEN, DIAMOND, "Td"),
	JACK_OF_DIAMONDS(JACK, DIAMOND, "Jd"),
	QUEEN_OF_DIAMONDS(QUEEN, DIAMOND, "Qd"),
	KING_OF_DIAMONDS(KING, DIAMOND, "Kd"),
	ACE_OF_DIAMONDS(ACE, DIAMOND, "Ad"),
	
	TWO_OF_CLUBS(TWO, CLUB, "2c"),
	THREE_OF_CLUBS(THREE, CLUB, "3c"),
	FOUR_OF_CLUBS(FOUR, CLUB, "4c"),
	FIVE_OF_CLUBS(FIVE, CLUB, "5c"),
	SIX_OF_CLUBS(SIX, CLUB, "6c"),
	SEVEN_OF_CLUBS(SEVEN, CLUB, "7c"),
	EIGHT_OF_CLUBS(EIGHT, CLUB, "8c"),
	NINE_OF_CLUBS(NINE, CLUB, "9c"),
	TEN_OF_CLUBS(TEN, CLUB, "Tc"),
	JACK_OF_CLUBS(JACK, CLUB, "Jc"),
	QUEEN_OF_CLUBS(QUEEN, CLUB, "Qc"),
	KING_OF_CLUBS(KING, CLUB, "Kc"),
	ACE_OF_CLUBS(ACE, CLUB, "Ac"),
	
	TWO_OF_HEARTS(TWO, HEART, "2h"),
	THREE_OF_HEARTS(THREE, HEART, "3h"),
	FOUR_OF_HEARTS(FOUR, HEART, "4h"),
	FIVE_OF_HEARTS(FIVE, HEART, "5h"),
	SIX_OF_HEARTS(SIX, HEART, "6h"),
	SEVEN_OF_HEARTS(SEVEN, HEART, "7h"),
	EIGHT_OF_HEARTS(EIGHT, HEART, "8h"),
	NINE_OF_HEARTS(NINE, HEART, "9h"),
	TEN_OF_HEARTS(TEN, HEART, "Th"),
	JACK_OF_HEARTS(JACK, HEART, "Jh"),
	QUEEN_OF_HEARTS(QUEEN, HEART, "Qh"),
	KING_OF_HEARTS(KING, HEART, "Kh"),
	ACE_OF_HEARTS(ACE, HEART, "Ah"),
	
	TWO_OF_SPADES(TWO, SPADE, "2s"),
	THREE_OF_SPADES(THREE, SPADE, "3s"),
	FOUR_OF_SPADES(FOUR, SPADE, "4s"),
	FIVE_OF_SPADES(FIVE, SPADE, "5s"),
	SIX_OF_SPADES(SIX, SPADE, "6s"),
	SEVEN_OF_SPADES(SEVEN, SPADE, "7s"),
	EIGHT_OF_SPADES(EIGHT, SPADE, "8s"),
	NINE_OF_SPADES(NINE, SPADE, "9s"),
	TEN_OF_SPADES(TEN, SPADE, "Ts"),
	JACK_OF_SPADES(JACK, SPADE, "Js"),
	QUEEN_OF_SPADES(QUEEN, SPADE, "Qs"),
	KING_OF_SPADES(KING, SPADE, "Ks"),
	ACE_OF_SPADES(ACE, SPADE, "As");
	
	private final Rank rank;
	private final Suit suit;
	private final String shortName;
	
	/**
	 * Constructor sets an enum containing rank, sut and shortName, which are all used to identify this card as 1 in 52.
	 * @param rank
	 * @param suit
	 * @param shortName
	 */
	private Card(Rank rank, Suit suit, String shortName) {
		this.rank = rank;
		this.suit = suit;
		this.shortName = shortName;
	}
	
	/**
	 * Returns the rank of the card, as 1 in 13. 
	 * @return
	 */
	public Rank getRank() {
		return rank;
	}
	
	/**
	 * Returns the suit of the card, as in 1 in 4.
	 * @return
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * Returns the string identifier of this enum.
	 * @return
	 */
	public String getShortName() {
		return shortName;
	}
}

