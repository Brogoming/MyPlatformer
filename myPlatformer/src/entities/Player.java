package entities;

import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;
import utilz.LoadSave;

public class Player extends Entity{

	private BufferedImage[][] animations; //animation array
	private int aniTick, aniIndex, aniSpeed = 25; //the ticks per animation, the index of the animation, the speed of each animation image
	private int playerAction = IDLE; //the default animation
	private boolean left, up, right, down; //if the player direction
	private boolean moving = false, attacking = false; //if the player is moving or not, if the player is attacking
	private float playerSpeed = 2.0f; //speed of the player
	private int[][] lvlData; //temporary 
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitBox(x, y, 20 * Game.SCALE,  28 * Game.SCALE); //players hit box
	}
	
	public void update() { //update the player
		updatePos();
		updateAnimationTick();
		setAnimation();
	}
	
	public void render(Graphics g) { //render the player
		g.drawImage(animations[playerAction][aniIndex], (int)(hitBox.x - xDrawOffset), (int)(hitBox.y - yDrawOffset), (int) width, (int) height, null);
		drawHitBox(g);
	}

	private void updateAnimationTick() { //cycles through the animation
		aniTick++;
		if (aniTick >= aniSpeed) { //if the animation tick is greater than or equal to the animation speed
			aniTick = 0; //resets the tick
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction)) { //if the index is greater than or equal to animation index
				aniIndex = 0; //resets the index
				attacking = false; //sets attacking false, stops attacking
			}
		}
	}

	private void setAnimation() { //sets animation based on the players action
		int startAni = playerAction;
		
		if (moving) { //movement
			playerAction = RUNNING;
		}
		else {
			playerAction = IDLE;
		}
		
		if(attacking) { //attack
			playerAction = ATTACK_1;
		}
		
		if(startAni != playerAction) { //reset animation
			resetAniTick();
		}
	}

	private void resetAniTick() { //resets animation
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() { //allows us to change position
		moving = false; //sets moving to false as default
		if(!left && !right && !up && !down) {
			return;
		}
		
		float xSpeed = 0, ySpeed =0;
		
		if(left && !right) {
			xSpeed = -playerSpeed;
		} else if(right && !left) {
			xSpeed = playerSpeed;
		}
		
		if(up && !down) {
			ySpeed = -playerSpeed;
		} else if(down && !up) {
			ySpeed = playerSpeed;
		}
		
//		if(CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
//			this.x += xSpeed;
//			this.y += ySpeed;
//			moving = true;
//		}
		
		if(CanMoveHere(hitBox.x + xSpeed, hitBox.y + ySpeed, hitBox.width, hitBox.height, lvlData)) {
			hitBox.x += xSpeed;
			hitBox.y += ySpeed;
			moving = true;
		}
	}
	
	private void loadAnimations() { //loads our animations
			BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
			
			animations = new BufferedImage[9][6]; //9 rows and 6 columns of animation
			for (int j = 0; j < animations.length; j++) //row
				for (int i = 0; i < animations[j].length; i++)
					animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40); //gets the image from each column on row j
	
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
	public void resetDirBooleans() { //resets all directions as false so the player doesn't move when you are off screen
		left = false;
		right = false;
		up = false;
		down = false;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	
}
