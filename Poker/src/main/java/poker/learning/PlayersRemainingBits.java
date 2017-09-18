package poker.learning;

public class PlayersRemainingBits extends InfoBits {

	public PlayersRemainingBits(int num) {
		super(10);
		update(true, num-1);
	}
}
