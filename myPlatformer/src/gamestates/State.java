package gamestates;

import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class State {

	protected Game game;
	public State(Game game) { //sets the game state
		this.game = game;
	}
	
	public Game getGame() { //returns the game state
		return game;
	}
	
	public boolean isIn(MouseEvent e, MenuButton mb) {
		return mb.getBounds().contains(e.getX(), e.getY()); //returns true if our mouse is over the button
	}
}
