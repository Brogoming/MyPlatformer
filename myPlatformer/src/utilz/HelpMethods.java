package utilz;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods { 
	
	public static boolean CanMoveHere(float x, float y,  float width, float height, int[][] lvlData) { //checking if the hit box touches the tile
		
		if(!IsSolid(x, y, lvlData)) { //top left
			if(!IsSolid(x+width, y+height, lvlData)) { //bottom right
				if(!IsSolid(x+width, y, lvlData)) { //top right
					if(!IsSolid(x, y+height, lvlData)) { //bottom left
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static boolean IsSolid(float x, float y, int[][] lvlData) { //checks if it is a tile and the position is inside the game window
		
		if(x < 0 || x >= Game.GAME_WIDTH) { //says the tile is a solid
			return true;
		}
		if(y < 0 || y >= Game.GAME_HEIGHT) { //says the tile is a solid
			return true;
		}
		
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = x / Game.TILES_SIZE;
		
		int value = lvlData[(int) yIndex][(int) xIndex];
		
		if(value >= 48 || value < 0 || value != 11) {
			return true;
		}
		return false;
	}
	
	public static float GetEnitityXPosNextToWall(Rectangle2D.Float hitBox, float xSpeed) { //helps position the entity next to the wall
		int currentTile = (int) (hitBox.x /Game.TILES_SIZE);
		
		if(xSpeed > 0) { //right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffSet = (int)(Game.TILES_SIZE - hitBox.width);
			return tileXPos + xOffSet -1;
		} else { //left
			return currentTile * Game.TILES_SIZE;
		}
	}
	
	public static float GetEnitityYPosSurface(Rectangle2D.Float hitBox, float airSpeed) { //helps with the collision of the roof and floor detection when touched
	int currentTile = (int) (hitBox.x /Game.TILES_SIZE);
		
		if(airSpeed > 0) { //falling
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffSet = (int)(Game.TILES_SIZE - hitBox.height);
			return tileYPos + yOffSet -1;
		} else { //jumping
			return currentTile * Game.TILES_SIZE;
		}
	}
	
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitBox, int[][] lvlData) {
		//check the pixel below bottom left and bottom right
		if(!IsSolid(hitBox.x, hitBox.y + hitBox.height + 1, lvlData)) {
			if(!IsSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, lvlData)) {
				return false;
			}
		}
		return true;
	}
	
}
