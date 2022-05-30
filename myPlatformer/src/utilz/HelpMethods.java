package utilz;

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
		} else {
			return false;
		}
		
	}
	
}
