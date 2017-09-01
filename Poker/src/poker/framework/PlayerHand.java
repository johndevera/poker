package poker.framework;

public class PlayerHand {

	private final Player player;
	private final Hand hand;
	public PlayerHand(Player player, Hand hand) {
		super();
		this.player = player;
		this.hand = hand;
	}
	public Player getPlayer() {
		return player;
	}
	public Hand getHand() {
		return hand;
	}
	
	@Override
	public String toString() {
		return player + " has " + hand;
	}
}
