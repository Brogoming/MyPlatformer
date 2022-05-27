package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity { //a class we can't make an object on

	protected float x, y; //only this class can use x and y, any thing that extends from Entity can use it
	protected int width, height;  //width and height of the player
	protected Rectangle hitBox; //entity hit box
	
	public Entity(float x, float y, int width, int height) { //constructor
		this.x = x; 
		this.y = y;
		this.width = width;
		this.height = height;
		initHitBox(); //initializes the hit box
	}
	
	protected void drawHitBox(Graphics g) { //draws the hit box around the entity
		g.setColor(Color.PINK);
		g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}
	
	private void initHitBox() { //sets the hit box
		hitBox = new Rectangle((int) x, (int)y, width, height);
	}
	
	protected void updateHitBox() { //updates the position
		hitBox.x = (int)x;
		hitBox.y = (int)y;
	}
	
	public Rectangle getHitBox() {
		return hitBox;
	}
	
}
