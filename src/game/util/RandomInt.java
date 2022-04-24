package game.util;

/**
 * The Class RandomInt that provide a rand integer number
 */
public class RandomInt {

	/**
	 * Gets the random int between min and max.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the random int returned
	 */
	public int getRandomInt(final int min, final int max) {

		final double randomN = Math.random() * (max - min);
		return (int) Math.round(randomN) + min;

	}
}
