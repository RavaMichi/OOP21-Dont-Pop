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
		list.add(new Pair<>("Test1", 5));
		assertEquals(list, leaderboard.getRanking());
	}
	
	@Test
	public void testAddToRanking2() {
		leaderboard.addToRanking("Test2", 10);
		
	}
}
