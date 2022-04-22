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

	private static final int RANKING_LENGTH = 50;
	
    private List<Pair<String,Integer>> ranking = new ArrayList<>();
    private final File saveFile;
	
    /**
     * Builds a new object of class Leaderboard.
     * @param savePath
     */
	public Leaderboard(final String savePath) {
		this.saveFile = new File(savePath);
		try {
			if (saveFile.createNewFile()) {
				this.save();
			}
		} catch (IOException e) {
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
	 * Adds to the ranking the player, if the score is in the top scores
	 * @param playerName
	 * @param score
	 */
	public void addToRanking(final String playerName, final int score) {
		//System.out.println(playerName);
		final var entry = new Pair<String,Integer>(playerName, score);
		if (this.ranking.isEmpty()) {
			this.ranking.add(entry);
		} else {
			//check if already present
//			for (int i = 0; i < this.ranking.size(); i++) {
//				//System.out.println(this.ranking.get(i).get1());
//				if (this.ranking.get(i).get1().equals(playerName)) {
//					if (this.ranking.get(i).get2() < score) {
//						//removes the lowest score
//						this.ranking.remove(i);
//						break;
//					} else {
//						return;
//					}	
//				}
//			}
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
	/**
	 * Get the rank position of the player playerName
	 * @param playerName - the player name
	 * @return the rank
	 */
	public int getRank(final String playerName, final int score) {
		for (int i = 0; i < this.ranking.size(); i++) {
			if (this.ranking.get(i).get1().equals(playerName) && 
			    this.ranking.get(i).get2().equals(score)) {
				return i + 1;
			}
		}
		return this.ranking.size();
	}
	
	/**
	 * Loads the ranking saved in the savefile.
	 */
	@SuppressWarnings("unchecked")
	public void load() {
		try (var ois = new ObjectInputStream(new FileInputStream(saveFile))) {	
			this.ranking = (ArrayList<Pair<String,Integer>>)ois.readObject();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves the current ranking in the savefile.
	 */
	public final void save() {
		try (var oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
			oos.writeObject(this.ranking);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

