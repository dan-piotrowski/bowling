package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import common.Constants;

public class GameTestDisplay {

	private Game game;
	
	@Before
	public void initialize(){
		game = new Game();
	}
	
	@Test
	public void play_open_frame_get_score_display() {
		
		game.play(7);
		assertEquals("7", game.getFrames()[0].getFirstScoreDisplay());
	}
	
	@Test
	public void play_strike_get_score_display() {
		
		game.play(10);
		game.play(7);
		assertEquals(Constants.STRIKE_SIGN, game.getFrames()[0].getFirstScoreDisplay());
		assertEquals(Constants.EMPTY_SIGN, game.getFrames()[0].getSecondScoreDisplay());
		assertEquals("7", game.getFrames()[1].getFirstScoreDisplay());
	}
	
	@Test
	public void play_spare_get_score_display() {
		
		game.play(7);
		game.play(3);
		game.play(5);
		assertEquals("7", game.getFrames()[0].getFirstScoreDisplay());
		assertEquals(Constants.SPARE_SIGN, game.getFrames()[0].getSecondScoreDisplay());
		assertEquals("5", game.getFrames()[1].getFirstScoreDisplay());
	}
	
	@Test
	public void play_miss_get_score_display() {
		
		game.play(0);
		game.play(7);
		game.play(5);
		game.play(0);
		assertEquals(Constants.MISS_SIGN, game.getFrames()[0].getFirstScoreDisplay());
		assertEquals("7", game.getFrames()[0].getSecondScoreDisplay());
		assertEquals("5", game.getFrames()[1].getFirstScoreDisplay());
		assertEquals(Constants.MISS_SIGN, game.getFrames()[1].getSecondScoreDisplay());
	}
}
