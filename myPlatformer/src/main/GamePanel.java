package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Inputs.KeyBoardInputs; //allows the panel to access KeyBoardInputs
import Inputs.MouseInputs; //allows the panel to access MouseInputs

import static main.Game.GAME_HEIGHT; //the height of the game panel
import static main.Game.GAME_WIDTH; //the width of the game panel

public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private Game game;

	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;

		setPanelSize();
		addKeyListener(new KeyBoardInputs(this)); //looks(listens) for KeyBoardInputs
		addMouseListener(mouseInputs); //looks(listens) for MouseInputs	
		addMouseMotionListener(mouseInputs); //looks(listens) for MouseInputs motion
	}

	private void setPanelSize() { //sets the size of the panel we play in
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT); //the images are 32 px 
		setPreferredSize(size); //sets the size of the panel
		System.out.println("Size : " + GAME_WIDTH + ":" + GAME_HEIGHT);
	}

	public void updateGame() { //updates the animation, set animation, and position

	}

	public void paintComponent(Graphics g) { //graphics allows us to draw
		super.paintComponent(g);
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}

}