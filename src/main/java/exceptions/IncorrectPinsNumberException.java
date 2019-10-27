package exceptions;

public class IncorrectPinsNumberException extends Exception{
	
	private int pinsLeft;

	public IncorrectPinsNumberException(int availablePins) {
		this.pinsLeft = availablePins;
	}
	
	public int getPinsLeft() {
		return pinsLeft;
	}
}
