package poker.learning;

import poker.framework.Street;

public class StreetBits extends InfoBits {

	public StreetBits(Street street) {
		super(Street.values().length);
		update(true, street.getValue());
	}
}
