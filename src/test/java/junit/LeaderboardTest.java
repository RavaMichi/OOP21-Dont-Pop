package junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.util.Leaderboard;
import game.util.Pair;

import java.util.List;

/**
 * This class tests the leaderboard with JUnit.
 */
public class LeaderboardTest {

	private Leaderboard leaderboard;
	//private List<Pair<String, Integer>> list;
	
	private void init() {
		this.leaderboard = new Leaderboard("/.save-test");
		//this.list = new ArrayList<Pair<String, Integer>>();
	}
	/*
	@Test
	public void testAddToRanking1() {
		init();
		this.leaderboard.addToRanking("Test1", 5);
//		this.leaderboard.save();
//		this.leaderboard.load();
		this.list.add(new Pair<>("Test1", 5));
		assertEquals(this.list, this.leaderboard.getRanking());
	}
	
	@Test
	public void testAddToRanking2() {
		init();
		this.leaderboard.addToRanking("Test2", 10);
		this.list.add(0, new Pair<>("Test2", 10));
		assertEquals(this.list, this.leaderboard.getRanking());
	}
	
	@Test
	public void testAddToRanking3() {
		init();
	    this.leaderboard.addToRanking("Test3", 1);
	    this.list.add(new Pair<>("Test3", 1));
	    assertEquals(this.list, this.leaderboard.getRanking());
	}
	*/
	@Test
	public void testAddToRanking() {
		init();
		
	    assertEquals(List.of(), this.leaderboard.getRanking());
	    
	    this.leaderboard.addToRanking("Test1", 5);
	    this.leaderboard.addToRanking("Test2", 10);
	    this.leaderboard.addToRanking("Test3", 1);
	    
	    List<Pair<String, Integer>> control = List.of(new Pair<>("Test2", 10),new Pair<>("Test1", 5),new Pair<>("Test3", 1));
	   
	    assertEquals(control, this.leaderboard.getRanking());
	}
	
	@Test
	public void testGetRank() {
		init();
		
		this.leaderboard.addToRanking("Test1", 5);
	    this.leaderboard.addToRanking("Test2", 10);
	    this.leaderboard.addToRanking("Test3", 1);
	    
	    assertEquals(1, this.leaderboard.getRank("Test2", 10));
	    assertEquals(2, this.leaderboard.getRank("Test1", 5));
	    assertEquals(3, this.leaderboard.getRank("Test3", 1));
	}
}
