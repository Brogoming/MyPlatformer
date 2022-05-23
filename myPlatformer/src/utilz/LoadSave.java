package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
	
	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	public static final String LEVEL_ONE_DATA = "level_one_data.png";

	public static BufferedImage GetSpriteAtlas(String fileName) { //returns the player's image
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		
		try { //catches any errors
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static int[][] GetLevelData(){ //gets level sprite data and corresponds a color with a sprite
		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		
		for(int j = 0; j < img.getHeight(); j++) { //y
			for(int i = 0; i < img.getWidth(); i++) { //x
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if(value  >= 48) {
					value = 0;
					lvlData[j][i] = value;
				}
			}
		}
		return lvlData;
	}
}
