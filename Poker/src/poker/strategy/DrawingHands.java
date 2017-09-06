package poker.strategy;

public enum DrawingHands {

	INSIDE_STRAIGHT_DRAW(),
	OPEN_ENDED_STRAIGHT_DRAW(),
	FLUSH_DRAW();
	
	private boolean inside_straight_draw;
	private boolean open_ended_straight_draw;
	private boolean flush_draw;
	
	private DrawingHands() {
		this.setInside_straight_draw(false);
		this.setOpen_ended_straight_draw(false);
		this.setFlush_draw(false);
	}

	public boolean isInside_straight_draw() {
		return inside_straight_draw;
	}

	public void setInside_straight_draw(boolean inside_straight_draw) {
		this.inside_straight_draw = inside_straight_draw;
	}

	public boolean isOpen_ended_straight_draw() {
		return open_ended_straight_draw;
	}

	public void setOpen_ended_straight_draw(boolean open_ended_straight_draw) {
		this.open_ended_straight_draw = open_ended_straight_draw;
	}

	public boolean isFlush_draw() {
		return flush_draw;
	}

	public void setFlush_draw(boolean flush_draw) {
		this.flush_draw = flush_draw;
	}
}
