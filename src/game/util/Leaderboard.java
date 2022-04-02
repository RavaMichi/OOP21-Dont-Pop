package game.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Class used to store the scores of the best players
 */
public class Leaderboard implements Serializable {

	private static final long serialVersionUID = 6432415329682133181L;

	private List<Pair<String,Integer>> ranking = new ArrayList<>();
	
	public void load(final String savePath) throws IOException {
		
	}
}
