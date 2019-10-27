package model;

public class Game {
	
	private Frame[] frames;
	private int currentFrameIndex;
	
	public Game() {
		frames = new Frame[10];
		
		//create new frames, starting from the last one
		for (int i = 9; i >= 0; i--) {
			
			//the last frame doesn't have a reference to the next frame
			if(i == 9) {
				frames[i] = new Frame10th();
			}
			//any frame but the last has a reference to the next frame
			else {
				frames[i] = new Frame1stTo9th(frames[i+1]);
			}
		}
		
		currentFrameIndex = 0;
	}
	
	public Frame[] getFrames(){
		return frames;
	}
	
	public boolean isGameFinished() {
		return getCurrentFrame() == null;
	}
	
	public String play(int pinsHit) {
		
		return getCurrentFrame().hitPins(pinsHit);
	}

	//returns the game score up to the frame with the given index
	public int getScore(int toFrameIndex) {
		int gameScore = 0;
		
		for(int i = 0; i <= toFrameIndex; i++) {
			gameScore += frames[i].getScore();
		}
		
		return gameScore;
	}
	
	private Frame getCurrentFrame() {
		
		//if the frame with currentFrameIndex is finished (a strike or all attempts taken)
		//then try to take the next one, unless it is the last frame - then return null
		if(frames[currentFrameIndex].isFramePlayFinished()) {
			if(currentFrameIndex == 9) {
				return null;
			}
			else {
				currentFrameIndex++;
			}
		}
		
		return frames[currentFrameIndex];
	};
}
