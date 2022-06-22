package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

public class MenuButton { //not a menu button but a button in the menu
	private int xPos, yPos, rowIndex, index;
	private int xOffsetCenter = B_WIDTH /2; //x center
	private int yOffsetCenter = B_HEIGHT /2; //y center
	private Gamestate state;
	private BufferedImage[] imgs; //images for the buttons
	private boolean mouseOver, mousePressed;
	private Rectangle bounds; //the hitbox of the buttons
	
	public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) { //constructor
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex = rowIndex;
		this.state = state;
		loadImgs();
		initBounds();
	}

	private void initBounds() { //draws the button hitbox
		bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
		
	}

	private void loadImgs() { //loads the button images
		imgs = new BufferedImage[3]; 
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS); //temporary
		for(int i = 0; i < imgs.length; i++) {
			imgs[i] = temp.getSubimage(i * B_WD, rowIndex * B_HD, B_WD, B_HD); //x, y, width, height
		}
	}
	
	public void draw(Graphics g) { //draws the buttons
		g.drawImage(imgs[index], xPos- xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}
	
	public void update() { //updates the buttons on screen
		index = 0; //sets to zero right away
		if(mouseOver) { //if mouse hovers over
			index = 1;
		}
		if(mousePressed) { //if mouse clicks the button
			index = 2;
		}
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public void applyGamestate() { //applies the game state when mouse is pressed
		Gamestate.state = state;
		
	}
	
	public void resetBools() { //makes them set to a default false
		mouseOver = false;
		mousePressed = false;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
}
