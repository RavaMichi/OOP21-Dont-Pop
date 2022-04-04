package game.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to store the scores of the best players
 */

//prima salva la lunghezza della classifica, poi gli elementi
public class Leaderboard {

	private static final int RANKING_LENGTH = 5;
	
	private ArrayList<Pair<String,Integer>> ranking = new ArrayList<>();
	private final File saveFile;
	
	public Leaderboard(final String savePath) {
		this.saveFile = new File(savePath);
		try {
			if (saveFile.createNewFile()) {
				save();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return the current ranking
	 */
	public List<Pair<String,Integer>> getRanking() {
		return List.copyOf(this.ranking);
	}
	/**
	 * @param playerName
	 * @param score
	 * Adds to the ranking the player, if the score is in the top 5 scores
	 */
	public void addToRanking(String playerName, int score) {
		var entry = new Pair<String,Integer>(playerName, score);
		if (this.ranking.isEmpty()) {
			this.ranking.add(entry);
		} else {
			//sorting during insertion
			int index = 0;
			for (; index < this.ranking.size(); index++) {
				if (score > this.ranking.get(index).get2()) {
					break;
				}
			}
			this.ranking.add(index, entry);
			
			if (this.ranking.size() > RANKING_LENGTH) {
				this.ranking.remove(this.ranking.size() - 1);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Loads the ranking saved in the savefile
	 */
	public void load() {
		try (var ois = new ObjectInputStream(new FileInputStream(saveFile))) {	
			this.ranking = (ArrayList<Pair<String,Integer>>)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Saves the current ranking in the savefile
	 */
	public void save() {
		try (var oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
			oos.writeObject(this.ranking);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

