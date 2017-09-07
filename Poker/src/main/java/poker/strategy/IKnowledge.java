package poker.strategy;

import java.util.List;

import poker.framework.Game;

public interface IKnowledge {

	public void addToKnowledge(Game game);
	
	public Game getGameHistory(Object object); 
	
	public List<Game> getGameHistories(Object object);
}
