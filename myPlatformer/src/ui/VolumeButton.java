package ui;

import static utilz.Constants.UI.VolButtons.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;

public class VolumeButton extends PauseButton{
	
	private BufferedImage[] imgs;
	private BufferedImage slider;
	private int index = 0;
	private boolean mouseOver, mousePressed;
	private int buttonX, minX, maxX;

	public VolumeButton(int x, int y, int width, int height) {
		super(x + width/2, y, VOL_WIDTH, height); // the button starts in the center of the slider
		bounds.x -= VOL_WIDTH/2;
		buttonX = x + width/2;
		this.x = x;
		this.width = width;
		minX = x + VOL_WIDTH/2;
		maxX = x + width - VOL_WIDTH/2;
		loadImgs();
	}
	
	private void loadImgs() { //loads the images of the VOLUME_BUTTONS
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_BUTTONS);
		imgs = new BufferedImage[3];
		for(int i = 0; i < imgs.length; i++) {
			imgs[i] = temp.getSubimage(i * VOL_WIDTH_DEFAULT, 0, VOL_WIDTH_DEFAULT, VOL_HEIGHT_DEFAULT);
		}
		
		slider = temp.getSubimage(3 * VOL_WIDTH_DEFAULT, 0, SLIDER_WIDTH_DEFAULT, VOL_HEIGHT_DEFAULT);
		
	}

	public void update() { //updates the buttons
		index = 0;
		if(mouseOver) {
			index = 1;
		}
		if(mousePressed) {
			index = 2;
		}
	}
	
	public void draw(Graphics g) { //draws the buttons
		g.drawImage(slider, x, y, width, height, null);
		g.drawImage(imgs[index], buttonX - VOL_WIDTH /2, y, VOL_WIDTH, height, null);
	}
	
	public void changeX(int x) { //changes the x position of the slider button
		if(x < minX) {
			buttonX = minX;
		} else if(x > maxX) {
			buttonX = maxX;
		} else {
			buttonX = x;
		}
		
		bounds.x = buttonX - VOL_WIDTH / 2;
	}
	
	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
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

	
}
