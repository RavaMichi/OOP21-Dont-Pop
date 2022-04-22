package test.junit;

import org.junit.Before;
import org.junit.Test;

import game.util.Leaderboard;
import game.util.Pair;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardTest {

	private Leaderboard leaderboard;
	private List<Pair<String,Integer>> list;
	
	@Before
	public void init() {
		this.leaderboard = new Leaderboard("src/test/junit/.save-test");
		this.list = new ArrayList<Pair<String,Integer>>();
	}
	
	@Test
	public void testAddToRanking1() {
		this.leaderboard.addToRanking("Test1", 5);
//		this.leaderboard.save();
//		this.leaderboard.load();
		this.list.add(new Pair<>("Test1", 5));
		assertEquals(this.list, this.leaderboard.getRanking());
	}
	
	@Test
	public void testAddToRanking2() {
		this.leaderboard.addToRanking("Test2", 10);
		this.list.add(0, new Pair<>("Test2", 10));
		assertEquals(this.list, this.leaderboard.getRanking());
	}
	
	@Test
	public void testAddToRanking3() {
	    this.leaderboard.addToRanking("Test3", 1);
	    this.list.add(new Pair<>("Test3", 1));
	    assertEquals(this.list, this.leaderboard.getRanking());
	}
	
	@Test
	public void testGetRanking() {
	    assertEquals(this.list, this.leaderboard.getRanking());
	}
	
	@Test
	public void testGetRank() {
	    assertEquals(1, this.leaderboard.getRank("Test2", 10));
	    assertEquals(2, this.leaderboard.getRank("Test1", 5));
	    assertEquals(3, this.leaderboard.getRank("Test3", 1));
	}
}
