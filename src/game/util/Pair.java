package game.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * A standard generic Pair<E1, E2> with getters, hashCode, equals, and toString well implemented.
 * @param <E1>
 * @param <E2>
 */
public class Pair<E1, E2> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final E1 e1;
	private final E2 e2;
	
	/**
	 * Builds a pair of two elements.
	 * @param e1
	 * @param e2
	 */
	public Pair(final E1 e1, final E2 e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}
	
	/**
	 * Gets the first element.
	 * @return e1
	 */
	public E1 get1() {
		return this.e1;
	}
	
	/**
	 * Gets the second element.
	 * @return e2
	 */
	public E2 get2() {
		return this.e2;
	}
	
	/**
	 * Returns a hash code value for the object.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(e1, e2);
	}
	
	/**
	 * Checks if two pairs are equal to one another.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if(getClass() != obj.getClass()) {
			return false;
		}
		@SuppressWarnings("rawtypes")
		final Pair other = (Pair) obj;
		return Objects.equals(e1, other.e1) 
		        && Objects.equals(e2, other.e2);
	}
	
	/**
	 * Creates a string representation for this object.
	 */
	@Override
	public String toString() {
		return "Pair [e1=" + this.e1 + ", e2=" + this.e2 + "]";
	}
}
