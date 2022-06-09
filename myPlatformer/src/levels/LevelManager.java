package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class LevelManager {
	
	private Game game;
	private BufferedImage[] levelSprite;
	private Level levelOne;
	
	public LevelManager(Game game) { //constructor
		this.game = game;
		importOutsideSpriteS();
		levelOne = new Level(LoadSave.GetLevelData());
	}
	
	private void importOutsideSpriteS() { //imports outside level sprites
		BufferedImage image = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for(int j = 0; j < 4; j++) //rows
			for(int i = 0; i < 12; i++) { //columns
				int index = j*12 + i;
				levelSprite[index] = image.getSubimage(i*32, j*32, 32, 32);
			}
	}

	public void draw(Graphics g) { //draws the level
		for(int j = 0; j < Game.TILES_IN_HEIGHT; j++) //y
			for(int i = 0; i < Game.TILES_IN_WIDTH; i++) { //x
				int index = levelOne.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], Game.TILES_SIZE*i, Game.TILES_SIZE*j, Game.TILES_SIZE, Game.TILES_SIZE, null); //index, x, y, width, Height of each tile
			}
	}
	
	public void update() {
		
	}
	
	public Level getCurrentLevel() { //returns the current level data
		return levelOne;
	}
	
}
