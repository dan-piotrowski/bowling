package model;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class GameTestScore {
	
	private Game game;
	private Integer[] hits;
	private Integer frameIndex, expectedScore;
	
	public GameTestScore(Integer[] hits, Integer frameIndex, Integer expectedScore) {
		
		this.hits = hits;
		this.frameIndex = frameIndex;
		this.expectedScore = expectedScore;
	}

	@Before
	public void initialize(){
		game = new Game();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> setHitsAndScores() {
	      
		
		return Arrays.asList(new Object[][] {
			 { new Integer[]{4, 5, 5}, 0, 9 }, // open frame in the first frame
			 { new Integer[]{5, 5, 3}, 0, 13 }, // spare in the first frame
			 { new Integer[]{10, 4, 5}, 0, 19 }, // strike in the first frame
			 { new Integer[]{10, 10, 9}, 0, 29 }, // strike in the first and second frame
			 { new Integer[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, 9, 300 }, // perfect game
			 { new Integer[]{10, 3, 7, 8, 0}, 2, 46 },
	         { new Integer[]{10, 10, 4, 2}, 2, 46 },
	         { new Integer[]{1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6}, 9, 133 },
	         { new Integer[]{10, 7, 3, 9, 0, 10, 0, 8, 8, 2, 0, 6, 10, 10, 10, 8, 1}, 9, 167 }
	      });
	 }
	
	@Test
	// Tests the Game.play(int pinsHit) method - consecutive hits (as an array), frame index to test 
	// and expected score in that frame are provided as parameters.
	// Each test case is a row in a form of: new Integer[]{hits...}, frameIndex, expectedScore }
	// Example: new Integer[]{10, 3, 7, 8, 0}, 2, 46 } means that after hitting 10, 3, 7, 8, 0 pins,
	// it is tested that the score up to the 3rd frame (frameIndex==2) should be equal 46
	public void play_sample_game_get_score() {
		
		for (int i = 0; i < hits.length; i++) {
			game.play(hits[i]);
		}
		assertEquals((Integer) expectedScore, (Integer) game.getScore(frameIndex));
	}
}
