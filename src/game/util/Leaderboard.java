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
	private final String savePath;
	
	public Leaderboard(final String savePath) {
		this.savePath = savePath;
	}
	@SuppressWarnings("unchecked")
	/**
	 * Loads the ranking saved in the savefile
	 */
	public void load() {
		try (var ois = new ObjectInputStream(new FileInputStream(new File(this.savePath)))) {
			this.ranking = (ArrayList<Pair<String,Integer>>)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Saves the current ranking in the savefile
	 */
	public void save() {
		try (var oos = new ObjectOutputStream(new FileOutputStream(new File(this.savePath)))) {
			oos.writeObject(ranking);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
