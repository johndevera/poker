package poker.learning;

public class PositionBits extends InfoBits {

	public PositionBits(Position position) {
		super(Position.values().length);
		update(true, position.getValue());
	}
}
