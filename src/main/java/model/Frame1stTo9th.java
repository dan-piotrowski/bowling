package model;

import exceptions.IncorrectPinsNumberException;

public class Frame1stTo9th extends Frame{
	

	public Frame1stTo9th(Frame nextFrame) {
		this.nextFrame = nextFrame;
	}
	
	@Override
	public boolean isFramePlayFinished() {
		
		//A strike or 2 attempts means the frame is finished
		return (FrameType.STRIKE == frameType) || (attempts == 2);
	}
	
	@Override
	public boolean isFrameScoreFinished() {
		
		/*
		for a frame 1-9, a frame score is finished when:
		1. frame was a strike, and:
			- the next frame had already 2 attempts
			- the next frame was also a strike and its next frame had at least 1 attempt (the null check is for the 9th frame, because the 10th frame won't have its next)
		2. frame was a spare and the next frame had at least 1 attempt
		3. frame was neither a stike nor a spare and had already 2 attempts
		*/
		return (FrameType.STRIKE == frameType 
					&& (nextFrame.getAttepmts() >= 2 
							|| (FrameType.STRIKE == nextFrame.getFrameType() && (nextFrame.getNextFrame() != null ? nextFrame.getNextFrame().getAttepmts() >= 1 : false))
					)
				)
				|| (FrameType.SPARE == frameType && nextFrame.getAttepmts() >= 1)
				|| (FrameType.ORDINARY == frameType && attempts == 2);
	}

	@Override
	protected void hitPinsInternal(int pinsHit) throws IncorrectPinsNumberException {
		if (attempts == 0) {
			
			firstScoreDisplay = freshHit(pinsHit);
			pinsHit1 = pinsHit;
		} else {
			
			secondScoreDisplay = continuousHit(pinsHit, pinsHit1);
			pinsHit2 = pinsHit;
		}
	}
	
	@Override
	public int getScore() {
		int score = pinsHit1 + pinsHit2;
		
		//In case of spare or strike points from next frames need to be added to the current score
		if(FrameType.SPARE == frameType) {
			score += nextFrame.getPinsHit1();
		}
		else if(FrameType.STRIKE == frameType) {
			score += nextFrame.getPinsHit1(); 
			if(nextFrame.getAttepmts() >= 2 ) {
				score += nextFrame.getPinsHit2();
			}
			else {
				//isFrameScoreFinished() makes sure that this is safe (nextFrame.getNextFrame() exists)
				score += nextFrame.getNextFrame().getPinsHit1();
			}
		}
		
		return score;
	};
}
