package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Inputs.KeyBoardInputs; //allows the panel to access KeyBoardInputs
import Inputs.MouseInputs; //allows the panel to access MouseInputs

import static utilz.Constants.PlayerConstants.*; //imports all the player animations
import static utilz.Constants.Directions.*; //imports the directions of movement

public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private float xDelta = 100, yDelta = 100; //changes the x and y value
	private BufferedImage img; //used to create an image
	private BufferedImage[][] animations; //animation array
	private int aniTick, aniIndex, aniSpeed = 25; //the ticks per animation, the index of the animation, the speed of each animation image
	private int playerAction = IDLE; //the default animation
	private int playerDir = -1; //not moving
	private boolean moving = false;

	public GamePanel() {
		mouseInputs = new MouseInputs(this);
		importImg();
		loadAnimations();

		setPanelSize();
		addKeyListener(new KeyBoardInputs(this)); //looks(listens) for KeyBoardInputs
		addMouseListener(mouseInputs); //looks(listens) for MouseInputs	
		addMouseMotionListener(mouseInputs); //looks(listens) for MouseInputs motion
	}

	private void loadAnimations() { //loads our animations
		animations = new BufferedImage[9][6]; //9 rows and 6 columns of animation
		for (int j = 0; j < animations.length; j++) //row
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40); //gets the image from each column on row j
	}

	private void importImg() { //imports the image to use
		InputStream is = getClass().getResourceAsStream("/player_sprites.png");
		try {
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
	}

	private void setPanelSize() { //sets the size of the panel we play in
		Dimension size = new Dimension(1280, 800); //the images are 32 px big so its 40 images by 25 images
		setPreferredSize(size); //sets the size of the panel
	}

	public void setDirection(int direction) { //if the player is moving set moving to true
		this.playerDir = direction;
		moving = true;
	}

	public void setMoving(boolean moving) { // moving = moving to whatever calls this sets moving to true or false
		this.moving = moving;
	}

	private void updateAnimationTick() { //cycles through the animation
		aniTick++;
		if (aniTick >= aniSpeed) { //if the animation tick is greater than or equal to the animation speed
			aniTick = 0; //resets the tick
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) //if the index is greater than or equal to 6
				aniIndex = 0; //resets the index
		}

	}

	private void setAnimation() { //if moving is true it switches the animation to running if not it goes back to idle
		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;
	}

	private void updatePos() { //allows us to change position
		if (moving) {
			switch (playerDir) {
			case LEFT:
				xDelta -= 5;
				break;
			case UP:
				yDelta -= 5;
				break;
			case RIGHT:
				xDelta += 2;
				break;
			case DOWN:
				yDelta += 5;
				break;
			}
		}
	}

	public void updateGame() {

		updateAnimationTick();
		setAnimation();
		updatePos();
	}

	public void paintComponent(Graphics g) { //graphics allows us to draw
		super.paintComponent(g);

		g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta, 256, 160, null);
	}

}