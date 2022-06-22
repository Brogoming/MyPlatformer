package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.Constants.UI.PauseButtons.*;

public class SoundButton extends PauseButton{

	private BufferedImage[][] soundImgs;
	private boolean mouseOver, mousePressed;
	private boolean muted;
	private int rowIndex, colIndex;
	
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
	public boolean isMuted() {
		return muted;
	}
	public void setMuted(boolean muted) {
		this.muted = muted;
	}
	public SoundButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		loadSoundImgs();
	}
	private void loadSoundImgs() {
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.SOUND_BUTTONS); //Temporally stores the sound buttons
		soundImgs = new BufferedImage[2][3]; //row, column
		for(int j = 0; j < soundImgs.length; j++) { //row
			for(int i = 0; i < soundImgs[j].length; i++) { //column
				soundImgs[j][i] = temp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
			}
		}
	}
	
	public void update() { //updates the sound button state
		if(muted) {
			rowIndex = 1;
		} else {
			rowIndex = 0;
		}
		
		colIndex = 0;
		if(mouseOver) {
			colIndex = 1;
		} 
		if(mousePressed){
			colIndex = 2;
		}
	}
	
	public void resetBools() {//sets the booleans to false
		mouseOver = false;
		mousePressed = false;
	}
	
	public void draw(Graphics g) { //draws the sound buttons
		g.drawImage(soundImgs[rowIndex][colIndex], x, y, width, height, null);
	}

}
