package poker.framework;

public enum FiveCardHand {

	/**
	 * Initialize cardValue to lowest value of that hand
	 * ROYAL_FLUSH(10, 10), //10 J Q K A
	 * STRAIGHT_FLUSH(9, 5) //A2345
	 * FOUR_OF_A_KIND(8, 2), //2222
	 * FULL_HOUSE(7, 2), //22233
	 * FLUSH(6, 7), //If two players have flush, take higher card. Lowest regular flush is 7. 23456 is a straight flush. A2345 is straight flush with A
	 * STRAIGHT(5, 5),//A2345
	 * THREE_OF_A_KIND(4, 2),//222
	 * TWO_PAIR(3, 2), //If top pair card is the same, cardValue takes the value of the second pair. Such as (4,2) and (4,3)
	 * ONE_PAIR(2, 2),
	 * HIGH_CARD(1, 2);
	 */

	ROYAL_FLUSH(10, 10),
	
	STRAIGHT_FLUSH_K(9, 13),
	STRAIGHT_FLUSH_Q(9, 12),
	STRAIGHT_FLUSH_J(9, 11),
	STRAIGHT_FLUSH_10(9,10),
	STRAIGHT_FLUSH_9(9, 9),
	STRAIGHT_FLUSH_8(9, 8),
	STRAIGHT_FLUSH_7(9, 7),
	STRAIGHT_FLUSH_6(9, 6),
	STRAIGHT_FLUSH(9, 5),
	
	FOUR_OF_A_KIND_A(8, 14),
	FOUR_OF_A_KIND_K(8, 13),
	FOUR_OF_A_KIND_Q(8, 12),
	FOUR_OF_A_KIND_J(8, 11),
	FOUR_OF_A_KIND_10(8, 10),
	FOUR_OF_A_KIND_9(8, 9),
	FOUR_OF_A_KIND_8(8, 8),
	FOUR_OF_A_KIND_7(8, 7),
	FOUR_OF_A_KIND_6(8, 6),
	FOUR_OF_A_KIND_5(8, 5),
	FOUR_OF_A_KIND_4(8, 4),
	FOUR_OF_A_KIND_3(8, 3),
	FOUR_OF_A_KIND(8, 2),
	
	FULL_HOUSE_A(7, 14),
	FULL_HOUSE_K(7, 13),
	FULL_HOUSE_Q(7, 12),
	FULL_HOUSE_J(7, 11),
	FULL_HOUSE_10(7, 10),
	FULL_HOUSE_9(7, 9),
	FULL_HOUSE_8(7, 8),
	FULL_HOUSE_7(7, 7),
	FULL_HOUSE_6(7, 6),
	FULL_HOUSE_5(7, 5),
	FULL_HOUSE_4(7, 4),
	FULL_HOUSE_3(7, 3),
	FULL_HOUSE(7, 2),
	
	FLUSH_A(6, 14),
	FLUSH_K(6, 13),
	FLUSH_Q(6, 12),
	FLUSH_J(6, 11),
	FLUSH_10(6, 10),
	FLUSH_9(6, 9),
	FLUSH_8(6, 8),
	FLUSH(6, 7),
	
	
	STRAIGHT_A(5, 14),
	STRAIGHT_K(5, 13),
	STRAIGHT_Q(5, 12),
	STRAIGHT_J(5, 11),
	STRAIGHT_10(5, 10),
	STRAIGHT_9(5, 9),
	STRAIGHT_8(5, 8),
	STRAIGHT_7(5, 7),
	STRAIGHT_6(5, 6),
	STRAIGHT(5, 5),
	
	THREE_OF_A_KIND_A(4, 14),
	THREE_OF_A_KIND_K(4, 13),
	THREE_OF_A_KIND_Q(4, 12),
	THREE_OF_A_KIND_J(4, 11),
	THREE_OF_A_KIND_10(4,10),
	THREE_OF_A_KIND_9(4, 9),
	THREE_OF_A_KIND_8(4, 8),
	THREE_OF_A_KIND_7(4, 7),
	THREE_OF_A_KIND_6(4, 6),
	THREE_OF_A_KIND_5(4, 5),
	THREE_OF_A_KIND_4(4, 4),
	THREE_OF_A_KIND_3(4, 3),
	THREE_OF_A_KIND(4, 2),
	
	TWO_PAIR_A(3, 14), 
	TWO_PAIR_K(3, 13),
	TWO_PAIR_Q(3, 12),
	TWO_PAIR_J(3, 11),
	TWO_PAIR_10(3, 10),
	TWO_PAIR_9(3, 9),
	TWO_PAIR_8(3, 8),
	TWO_PAIR_7(3, 7),
	TWO_PAIR_6(3, 6),
	TWO_PAIR_5(3, 5),
	TWO_PAIR_4(3, 4),
	TWO_PAIR_3(3, 3),
	TWO_PAIR(3, 2),
	
	ONE_PAIR_A(2, 14),
	ONE_PAIR_K(2, 13),
	ONE_PAIR_Q(2, 12),
	ONE_PAIR_J(2, 11),
	ONE_PAIR_10(2, 10),
	ONE_PAIR_9(2, 9),
	ONE_PAIR_8(2, 8),
	ONE_PAIR_7(2, 7),
	ONE_PAIR_6(2, 6),
	ONE_PAIR_5(2, 5),
	ONE_PAIR_4(2, 4),
	ONE_PAIR_3(2, 3),
	ONE_PAIR(2, 2),
	
	HIGH_CARD_A(1, 14),
	HIGH_CARD_K(1, 13),
	HIGH_CARD_Q(1, 12),
	HIGH_CARD_J(1, 11),
	HIGH_CARD_10(1, 10),
	HIGH_CARD_9(1, 9),
	HIGH_CARD_8(1, 8),
	HIGH_CARD_7(1, 7),
	HIGH_CARD_6(1, 6),
	HIGH_CARD_5(1, 5),
	HIGH_CARD_4(1, 4),
	HIGH_CARD_3(1, 3),
	HIGH_CARD(1, 2);
	
	
	private int value;
	private int cardValue;
	
	/**
	 * Set the parameters of the FiveCardHand
	 * @param value
	 * @param cardValue
	 */
	FiveCardHand(int value, int cardValue) {
		this.value = value;
		this.cardValue = cardValue;
	}	
	
	/**
	 * 
	 * @return Return the value of the hand
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Set the value of the FiveCardHand
	 * @param value 
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @return Return the highest card value of the FiveCardHand
	 */
	public int getCardValue() {
		return cardValue;
	}
	
	/**
	 *
	 * @param value Set the cardValue of the Card
	 */
	public void setCardValue(int value) {
		this.cardValue = value;
	}
	
	/**
	 * Set the value and the Card Value of the FiveCardHand.
	 * @param value
	 * @param cardValue
	 */
	public void setCard(int value, int cardValue) {
		this.value = value;
		this.cardValue = cardValue;
	}
}
