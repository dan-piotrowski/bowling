package model;

import common.Constants;
import exceptions.IncorrectPinsNumberException;

public abstract class Frame {

	protected int pinsHit1, pinsHit2, pinsHit3, attempts;
	protected String firstScoreDisplay, secondScoreDisplay, thirdScoreDisplay;
	protected FrameType frameType;
	protected Frame nextFrame;
	
	public Frame() {
		//pins hit in attempts 1-3 of a frame
		//if a frame had less attempts, it's OK to leave the others equal 0
		pinsHit1 = 0;
		pinsHit2 = 0;
		pinsHit3 = 0;
		attempts = 0;
		
		//used to display X, /, - on a scoring boeard
		firstScoreDisplay = Constants.EMPTY_SIGN;
		secondScoreDisplay = Constants.EMPTY_SIGN;
		thirdScoreDisplay = Constants.EMPTY_SIGN;
		
		//frame can be ordinary, a strike or a a spare
		//used for computing score for current frame
		frameType = FrameType.ORDINARY;
		
		//reference to the next frame, the last frame has no next frame
		nextFrame = null;
	}
	
	public int getPinsHit1() {
		return pinsHit1;
	}

	public int getPinsHit2() {
		return pinsHit2;
	}

	public int getPinsHit3() {
		return pinsHit3;
	}
	
	public int getAttepmts() {
		return attempts;
	}
	
	public String getFirstScoreDisplay() {
		return firstScoreDisplay;
	};
	
	public String getSecondScoreDisplay() {
		return secondScoreDisplay;
	};
	
	public String getThirdScoreDisplay() {
		return thirdScoreDisplay;
	};
	
	public FrameType getFrameType() {
		return frameType;
	}
	
	public Frame getNextFrame() {
		return nextFrame;
	}

	//A brand new hit - there are 10 pins to hit and a strike to achieve
	//Changes frame type to strike if needed and assigns a sign for attempt to display on the board
	protected String freshHit(int pinsHit) throws IncorrectPinsNumberException {
		
		if(pinsHit > 10 || pinsHit < 0) {
			throw new IncorrectPinsNumberException(10);
		}
		
		if (pinsHit == 10){
			frameType = FrameType.STRIKE;
			return Constants.STRIKE_SIGN;
		}
		else if (pinsHit == 0) {
			return Constants.MISS_SIGN;
		}
		else {
			return String.valueOf(pinsHit);
		}
	}
	
	//A continuation of a hit - there are only 10-previousHit pins to hit
	//Changes frame type to spare if needed and assigss a sign for attempt to display on the board
	protected String continuousHit(int pinsHit, int previousHit) throws IncorrectPinsNumberException {
		
		if(pinsHit > (10 - previousHit) || pinsHit < 0) {
			throw new IncorrectPinsNumberException(10 - previousHit);
		}
		
		if (previousHit + pinsHit == 10){
			frameType = FrameType.SPARE;
			return Constants.SPARE_SIGN;
		}
		else if (pinsHit == 0) {
			return Constants.MISS_SIGN;
		}
		else {
			return String.valueOf(pinsHit);
		}
	}
	
	//It stores pinsHit for current frame
	public String hitPins(int pinsHit) {
		
		try {
			hitPinsInternal(pinsHit);
			
			attempts++;
			return pinsHit + Constants.MSG_PINS_HIT;
		} 
		catch (IncorrectPinsNumberException e) {
			return Constants.INCORRECT_PIN_NUMBER + e.getPinsLeft();
		}
	}

	//This method assigns in child classes pinsHit to pinsHit1, pinsHit2 or pinsHit3
	//based on specific logic for frames 1-9 and frame 10
	//and changes frame type using either freshHit or continuousHit
	protected abstract void hitPinsInternal(int pinsHit) throws IncorrectPinsNumberException;

	//Tells if there are no more attempts left to play in this frame
	public abstract boolean isFramePlayFinished();
	
	//Tells if the score for this frame is already known
	//In case of spare or strike we have to wait for next frames to be played
	public abstract boolean isFrameScoreFinished();
	
	public abstract int getScore();
}
