
/**
 * The Class RandomInt.
 */
public class RandomInt {

	/**
	 * Gets the random int between min and max.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the random int returned
	 */
	public int getRandomInt(int min, int max) {

		double randomN = Math.random() * (min - max);
		int randomInt = (int) randomN;

		return randomInt;

	}
}
