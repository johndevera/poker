package poker.framework;

public enum FiveCardHand {

	//initialize cardValue to lowest value of that hand
	/*
	ROYAL_FLUSH(10, 10), //10 J Q K A
	STRAIGHT_FLUSH(9, 5), //A2345
	FOUR_OF_A_KIND(8, 2), //2222
	FULL_HOUSE(7, 2), //22233
	FLUSH(6, 0), //XXXXX not card value dependent. If two players have flush, split.
	STRAIGHT(5, 5),//A2345
	THREE_OF_A_KIND(4, 2),//222
	TWO_PAIR(3, 2), //
	ONE_PAIR(2, 2), //2
	HIGH_CARD(1, 2);//2
	*/
	ROYAL_FLUSH(10, 10), //10 J Q K A
	
	STRAIGHT_FLUSH_K(9, 13), //A2345
	STRAIGHT_FLUSH_Q(9, 12), //A2345
	STRAIGHT_FLUSH_J(9, 11), //A2345
	STRAIGHT_FLUSH_10(9,10), //A2345
	STRAIGHT_FLUSH_9(9, 9), //A2345
	STRAIGHT_FLUSH_8(9, 8), //A2345
	STRAIGHT_FLUSH_7(9, 7), //A2345
	STRAIGHT_FLUSH_6(9, 6), //A2345
	STRAIGHT_FLUSH(9, 5), //A2345
	
	FOUR_OF_A_KIND_A(8, 14), //2222
	FOUR_OF_A_KIND_K(8, 13), //2222
	FOUR_OF_A_KIND_Q(8, 12), //2222
	FOUR_OF_A_KIND_J(8, 11), //2222
	FOUR_OF_A_KIND_10(8, 10), //2222
	FOUR_OF_A_KIND_9(8, 9), //2222
	FOUR_OF_A_KIND_8(8, 8), //2222
	FOUR_OF_A_KIND_7(8, 7), //2222
	FOUR_OF_A_KIND_6(8, 6), //2222
	FOUR_OF_A_KIND_5(8, 5), //2222
	FOUR_OF_A_KIND_4(8, 4), //2222
	FOUR_OF_A_KIND_3(8, 3), //2222
	FOUR_OF_A_KIND(8, 2), //22222222
	
	FULL_HOUSE_A(7, 14), //22233
	FULL_HOUSE_K(7, 13), //22233
	FULL_HOUSE_Q(7, 12), //22233
	FULL_HOUSE_J(7, 11), //22233
	FULL_HOUSE_10(7, 10), //22233
	FULL_HOUSE_9(7, 9), //22233
	FULL_HOUSE_8(7, 8), //22233
	FULL_HOUSE_7(7, 7), //22233
	FULL_HOUSE_6(7, 6), //22233
	FULL_HOUSE_5(7, 5), //22233
	FULL_HOUSE_4(7, 4), //22233
	FULL_HOUSE_3(7, 3), //22233
	FULL_HOUSE(7, 2), //22233
	
	FLUSH_A(6, 14), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_K(6, 13), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_Q(6, 12), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_J(6, 11), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_10(6, 10), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_9(6, 9), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_8(6, 8), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_7(6, 7), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_6(6, 6), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_5(6, 5), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_4(6, 4), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH_3(6, 3), //XXXXX not card value dependent. If two players have flush, split.
	FLUSH(6, 2), //XXXXX not card value dependent. If two players have flush, split.
	
	STRAIGHT_A(5, 14),//A2345
	STRAIGHT_K(5, 13),//A2345
	STRAIGHT_Q(5, 12),//A2345
	STRAIGHT_J(5, 11),//A2345
	STRAIGHT_10(5, 10),//A2345
	STRAIGHT_9(5, 9),//A2345
	STRAIGHT_8(5, 8),//A2345
	STRAIGHT_7(5, 7),//A2345
	STRAIGHT_6(5, 6),//A2345
	STRAIGHT(5, 5),//A2345
	
	THREE_OF_A_KIND_A(4, 14),//222
	THREE_OF_A_KIND_K(4, 13),//222
	THREE_OF_A_KIND_Q(4, 12),//222
	THREE_OF_A_KIND_J(4, 11),//222
	THREE_OF_A_KIND_10(4, 10),//222
	THREE_OF_A_KIND_9(4, 9),//222
	THREE_OF_A_KIND_8(4, 8),//222
	THREE_OF_A_KIND_7(4, 7),//222
	THREE_OF_A_KIND_6(4, 6),//222
	THREE_OF_A_KIND_5(4, 5),//222
	THREE_OF_A_KIND_4(4, 4),//222
	THREE_OF_A_KIND_3(4, 3),//222
	THREE_OF_A_KIND(4, 2),//222
	
	TWO_PAIR_A(3, 14), //If top pair card is the same, cardValue takes the value of the second pair. Such as (4,2) and (4,3)
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
	
	FiveCardHand(int value, int cardValue) {
		this.value = value;
		this.cardValue = cardValue;
	}	
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getCardValue() {
		return cardValue;
	}
	
	public void setCardValue(int value) {
		this.cardValue = value;
	}
	
	public void setCard(int value, int cardValue) {
		this.value = value;
		this.cardValue = cardValue;
		//return 0;
	}
}
