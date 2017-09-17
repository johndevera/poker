package poker.framework;

public class ActionValidator{
	
	
	/**
	 * Returns 0, because that is how much player will be spending. 
	 * @return
	 */
	public static int getFoldAmount() {
		return 0;
	}
	
	/**
	 * Returns 0, because check implies current bet is 0.
	 * @return
	 */
	public static int getCheckAmount() {
		return 0;
	}

	/**
	 * Returns the difference of how much the player is in for and the current table bet.
	 * @param player
	 * @param game
	 * @return
	 */
	public static int getCallAmount(Player player, Game game) {
		return game.getCurrentBet() - player.getMyBet();
	}

	/**
	 * Returns 2x what the current bet is at.
	 * @param game
	 * @return
	 */
	public static int getMinRaiseAmount(Game game) {
		return 2*game.getCurrentBet();
	}
	
	/**
	 * Equivalent to an All In. Useful if wanting to compare max and min.
	 * @param player
	 * @return
	 */
	public static int getMaxRaiseAmount(Player player) {
		return player.getStack();
	}
	
	/**
	 * Returns the player's total stack.
	 * @param player
	 * @return
	 */
	public static int getAllInAmount(Player player) {
		return player.getStack();
	}
	
	/**
	 * Checks if the player can raise, based on the scaling value mult and their hand.
	 * @param mult
	 * @param amount
	 * @param player
	 * @param game
	 * @return
	 */
	public static boolean canRaise(double mult, int amount, Player player, Game game) {
		double value = mult*amount;
		return (value >= getMinRaiseAmount(game) && value < getMaxRaiseAmount(player));
	}
	
	/**
	 * Checks if the player can call given their stack, their bet and the currentBet.
	 * @param player
	 * @param game
	 * @return
	 */
	public static boolean canCall(Player player, Game game) {
		return (getCallAmount(player, game) < player.getStack() && getCallAmount(player, game) > 0);
	}
	
	/**
	 * Returns true if the current bet is equal to what player has put in. 
	 * @param player
	 * @param game
	 * @return
	 */
	public static boolean canCheck(Player player, Game game) {
		return game.getCurrentBet() == player.getMyBet();
	}

}


