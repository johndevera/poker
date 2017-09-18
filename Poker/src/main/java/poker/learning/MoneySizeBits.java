package poker.learning;

public class MoneySizeBits extends InfoBits {

	public MoneySizeBits(Size size) {
		super(5);
		update(true, size.getValue());
	}
}
