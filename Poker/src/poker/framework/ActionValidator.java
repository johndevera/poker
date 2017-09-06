package poker.framework;

public class ActionValidator{
	
	//private Decision decision = new Decision(null, 0);
	
	public static int getFoldAmount() {
		return 0;
	}
	public static int getCheckAmount() {
		return 0;
	}
	public static int getCallAmount(Player player, Game game) {
		return game.getCurrentBet() - player.getMyBet();
	}
	public static int getMinRaiseAmount(Game game) {
		return 2*game.getCurrentBet();
	}
	public static int getMaxRaiseAmount(Player player) {
		return player.getStack();
	}
	public static int getAllInAmount(Player player) {
		return player.getStack();
	}
	
	
	public static boolean canRaise(double mult, int amount, Player player, Game game) {
		double value = mult*amount;
		return (value >= getMinRaiseAmount(game) && value < getMaxRaiseAmount(player));
	}
	
	public static boolean canCall(Player player, Game game) {
		return (getCallAmount(player, game) < player.getStack() && getCallAmount(player, game) > 0);
	}
		
	public static boolean canCheck(Player player, Game game) {
		return game.getCurrentBet() == player.getMyBet();
	}

}


