package game.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
/**
 * Class used to store the scores of the best players.
 */

//prima salva la lunghezza della classifica, poi gli elementi
public class Leaderboard {

	private static final int RANKING_LENGTH = 5;
	
	private List<Pair<String,Integer>> ranking = new ArrayList<>();
	private final String savePath;
	
	public Leaderboard(final String savePath) {
		this.savePath = savePath;
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
	public void addToRanking(final String playerName, final int score) {
		final var entry = new Pair<String,Integer>(playerName, score);
		if (this.ranking.isEmpty()) {
			this.ranking.add(entry);
		} else {
			//sorting during insertion
			int index = 0;
			while (index < this.ranking.size()) {
				if (score > this.ranking.get(index).get2()) {
					break;
				}
				index++;
			}
			this.ranking.add(index, entry);
			
			if (this.ranking.size() > RANKING_LENGTH) {
				this.ranking.remove(this.ranking.size() - 1);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Loads the ranking saved in the savefile.
	 */
	public void load() {
		try (var ois = new ObjectInputStream(new FileInputStream(new File(this.savePath)))) {
			this.ranking = (ArrayList<Pair<String,Integer>>)ois.readObject();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		}
	}
	/**
	 * Saves the current ranking in the savefile.
	 */
	public void save() {
		try (var oos = new ObjectOutputStream(new FileOutputStream(new File(this.savePath)))) {
			oos.writeObject(this.ranking);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}
