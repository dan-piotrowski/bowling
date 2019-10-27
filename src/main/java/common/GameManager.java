package common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Game;

@ManagedBean
@SessionScoped
public class GameManager {
	
	private Game game;
	private int pinsHit;
	private String message;

	public GameManager() {
		newGame();
	}
	
	public String newGame() {
		game = new Game();
		message = Constants.MSG_WELCOME_MESSAGE;
		return "";
	}
	
	public String submit() {
		message = game.play(pinsHit);
		return "";
	}

	public int getPinsHit() {
		return pinsHit;
	}

	public void setPinsHit(int pinsHit) {
		this.pinsHit = pinsHit;
	}
	
	public Game getGame() {
		return game;
	}
	
	public String getMessage() {
		return message;
	}
}
