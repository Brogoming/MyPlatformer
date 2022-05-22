package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class LevelManager {
	
	private Game game;
	private BufferedImage[] levelSprite;
	
	public LevelManager(Game game) { //constructor
		this.game = game;
		//levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		importOutsideSpriteS();
	}
	
	private void importOutsideSpriteS() {
		BufferedImage image = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for(int j = 0; j < 4; j++) { //rows
			for(int i = 0; i < 12; i++) { //columns
				int index = j*12 + i;
				levelSprite[index] = image.getSubimage(i*32, j*32, 32, 32);
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(levelSprite[2], 0, 0, null);
	}
	
	public void update() {
		
	}

}
