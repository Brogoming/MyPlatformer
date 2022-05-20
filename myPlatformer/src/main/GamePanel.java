package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import Inputs.KeyBoardInputs; //allows the panel to access KeyBoardInputs
import Inputs.MouseInputs; //allows the panel to access MouseInputs

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
		Dimension size = new Dimension(1280, 800); //the images are 32 px big so its 40 images by 25 images
		setPreferredSize(size); //sets the size of the panel
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