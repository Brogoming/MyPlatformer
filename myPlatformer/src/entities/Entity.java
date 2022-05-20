package entities;

public abstract class Entity { //a class we can't make an object on

	protected float x, y; //only this class can use x and y, any thing that extends from Entity can use it
	public Entity(float x, float y) { //constructor
		this.x = x; 
		this.y = y;
		
	}
	
}
