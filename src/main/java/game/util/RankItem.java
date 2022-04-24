package game.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Useful class to keep all infos about a rank entry in the ScoreScene table.
 */
public class RankItem {
	private final SimpleStringProperty rank;
	private final SimpleStringProperty name;
	private final SimpleIntegerProperty score;
	
	/**
	 * Builds a new object of this class.
	 * @param rank
	 * @param name
	 * @param score
	 */
	public RankItem(final String rank, final String name, final int score) {
		this.rank = new SimpleStringProperty(rank);
		this.name = new SimpleStringProperty(name);
		this.score = new SimpleIntegerProperty(score);
	}

	/**
	 * Gets rank of current entry.
	 * @return rank
	 */
	public String getRank() {
		return this.rank.get();
	}

	/**
	 * Gets name of current entry.
	 * @return name
	 */
	public String getName() {
		return this.name.get();
	}

	/**
	 * Gets score of current entry.
	 * @return score
	 */
	public int getScore() {
		return this.score.get();
	}
}
