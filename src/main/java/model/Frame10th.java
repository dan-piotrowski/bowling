package model;

import exceptions.IncorrectPinsNumberException;

public class Frame10th extends Frame{

	@Override
	public boolean isFramePlayFinished() {
		
		//In the last frame there are 3 attempts if there was a strike or spare in the first 2 attempts
		return (FrameType.ORDINARY == frameType && attempts == 2)
				|| attempts == 3;
	}
	
	@Override
	public boolean isFrameScoreFinished() {
		
		//In the last frame there are no bonus points for strikes or spares as there is no next frame
		return isFramePlayFinished();
	}
	
	@Override
	protected void hitPinsInternal(int pinsHit) throws IncorrectPinsNumberException {
		
		if (attempts == 0) {
			
			//The 1st attempt is always a fresh hit
			firstScoreDisplay = freshHit(pinsHit);
			pinsHit1 = pinsHit;
		} 
		else if (attempts == 1){
			
			//If there was no strike in the first attempt, then the 2nd is a continuation
			if(pinsHit1 < 10) {
				secondScoreDisplay = continuousHit(pinsHit, pinsHit1);
			}
			//If there was a strike in the first attempt, then the 2nd is a fresh hit
			else {
				secondScoreDisplay = freshHit(pinsHit);
			}
			
			pinsHit2 = pinsHit;
		}
		else {
			
			//If there was no strike or spare in the second attempt, then the 3rd is a continuation
			//This is only the case when the 1st attempt was the strike and the 2nd wasn't
			if(pinsHit1 == 10 && pinsHit2 < 10) {
				thirdScoreDisplay = continuousHit(pinsHit, pinsHit2);
			}
			//If there was a strike in the second attempt, then the 3rd is a fresh hit
			else {
				thirdScoreDisplay = freshHit(pinsHit);
			}
			
			pinsHit3 = pinsHit;
		}
	}
	
	@Override
	public int getScore() {
		return pinsHit1 + pinsHit2 + pinsHit3;
	}
}
