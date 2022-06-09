package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity { //a class we can't make an object on

	protected float x, y; //only this class can use x and y, any thing that extends from Entity can use it
	protected int width, height;  //width and height of the player
	protected Rectangle2D.Float hitBox; //entity hit box
	
	public Entity(float x, float y, int width, int height) { //constructor
		this.x = x; 
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	protected void drawHitBox(Graphics g) { //draws the hit box around the entity
		g.setColor(Color.PINK); // For debugging the hitbox
		g.drawRect((int)hitBox.x, (int)hitBox.y, (int)hitBox.width, (int)hitBox.height);
	}
	
	protected void initHitBox(float x, float y, float width, float height) { //sets the hit box
		hitBox = new Rectangle2D.Float(x, y, width, height);
	}
	
//	protected void updateHitBox() { //updates the position
//		hitBox.x = (int)x;
//		hitBox.y = (int)y;
//	}
	
	public Rectangle2D.Float getHitBox() {
		return hitBox;
	}
	
}
