package entities;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import utilz.LoadSave;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Player extends Entity{
	private BufferedImage[][] animations; //animation array
	private int aniTick, aniIndex, aniSpeed = 25; //the ticks per animation, the index of the animation, the speed of each animation image
	private int playerAction = IDLE; //the default animation
	private boolean left, up, right, down, jump; //if the player direction
	private boolean moving = false, attacking = false; //if the player is moving or not, if the player is attacking
	private float playerSpeed = 2.0f; //speed of the player
	private int[][] lvlData; //temporary 
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	
	//jumping  / gravity
	private float airSpeed = 0f; //the speed we travel through the air
	private float gravity = 0.04f * Game.SCALE; //how fast we want the player to fall, lower the value the higher the jump
	private float jumpSpeed = -2.25f * Game.SCALE; //how fast we rise above the ground
	private float fSAC = 0.5f * Game.SCALE;//fall speed after collision when hitting the roof or wall
	private boolean inAir = false;
	
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitBox(x, y, 20 * Game.SCALE,  27 * Game.SCALE); //players hit box
		
	}
	
	public void update() { //update the player
		updatePos();
		updateAnimationTick();
		setAnimation();
	}
	
	public void render(Graphics g) { //render the player
		g.drawImage(animations[playerAction][aniIndex], (int)(hitBox.x - xDrawOffset), (int)(hitBox.y - yDrawOffset), width, height, null);
		//drawHitBox(g);
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
		
		if(inAir) {
			if(airSpeed < 0) {
				playerAction = JUMP;
			} else {
				playerAction = FALLING;
			}
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
		if(jump) {
			jump();
		}
		if(!left && !right && !inAir) { //no moving we are standing still
			return; 
		}
		float xSpeed = 0;
		if(left) { //goes left
			xSpeed -= playerSpeed;
		}
		if(right) { //goes right
			xSpeed += playerSpeed;
		}
		if(!inAir) {
			if(!IsEntityOnFloor(hitBox, lvlData)) {
				inAir = true;
			}
		}
		if(inAir) {
			if(CanMoveHere(hitBox.x, hitBox.y + airSpeed, hitBox.width, hitBox.height, lvlData)) {
				hitBox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitBox.y = GetEnitityYPosSurface(hitBox, airSpeed);
				if(airSpeed > 0) {
					resetInAir();
				} else {
					airSpeed = fSAC;
				}
				updateXPos(xSpeed);
			}
		} else {
			updateXPos(xSpeed);
		}	
		moving = true;
	}
	
	private void jump() {
		if(inAir) { // no double jumping
			return;
		}
		inAir = true;
		airSpeed = jumpSpeed;
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}

	private void updateXPos(float xSpeed) {

		if(CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
			hitBox.x += xSpeed;
		} else {
			hitBox.x = GetEnitityXPosNextToWall(hitBox, xSpeed);
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
		if(!IsEntityOnFloor(hitBox, lvlData)) { //should fall down as we start the game
			inAir = true;
		}
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
	
	public void setJump(boolean jump) {
		this.jump = jump;
	}
	
}
