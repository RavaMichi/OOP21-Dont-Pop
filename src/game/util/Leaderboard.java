package game.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Class used to store the scores of the best players
 */

//prima salva la lunghezza della classifica, poi gli elementi
public class Leaderboard {

	private static final int RANKING_LENGTH = 5;
	
	private List<Pair<String,Integer>> ranking = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public void load(final String savePath) {
		ranking.clear();
		try (var ois = new ObjectInputStream(new FileInputStream(new File(savePath)))) {
			int rankingLenth = ois.readInt();
			
			for (int i = 0; i < rankingLenth; i++) {
				ranking.add((Pair<String, Integer>)ois.readObject());
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
