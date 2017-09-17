package poker.framework;

public enum DrawingHands {

	INSIDE_STRAIGHT_DRAW(),
	OPEN_ENDED_STRAIGHT_DRAW(),
	FLUSH_DRAW();
	
	private boolean inside_straight_draw;
	private boolean open_ended_straight_draw;
	private boolean flush_draw;
	
	/**
	 * Constructor sets all possible draws initially to false.
	 */
	private DrawingHands() {
		this.setInside_straight_draw(false);
		this.setOpen_ended_straight_draw(false);
		this.setFlush_draw(false);
	}
	
	/**
	 * Check status of possible inside straight draw.
	 * @return
	 */
	public boolean isInside_straight_draw() {
		return inside_straight_draw;
	}
	
	/**
	 * Set the status of inside straight draw.
	 * @param inside_straight_draw
	 */
	public void setInside_straight_draw(boolean inside_straight_draw) {
		this.inside_straight_draw = inside_straight_draw;
	}

	/**
	 * Check the status of open ended straight draw.
	 * @return
	 */
	public boolean isOpen_ended_straight_draw() {
		return open_ended_straight_draw;
	}
	
	/**
	 * Set the status of open ended straight draw.
	 * @param open_ended_straight_draw
	 */
	public void setOpen_ended_straight_draw(boolean open_ended_straight_draw) {
		this.open_ended_straight_draw = open_ended_straight_draw;
	}

	/**
	 * Check the status of it being a flush draw.
	 * @return
	 */
	public boolean isFlush_draw() {
		return flush_draw;
	}
	
	/**
	 * Set the status of flush draw.
	 * @param flush_draw
	 */
	public void setFlush_draw(boolean flush_draw) {
		this.flush_draw = flush_draw;
	}
}
