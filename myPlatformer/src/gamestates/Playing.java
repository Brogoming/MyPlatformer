package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;

public class Playing extends State implements Statemethods{ //when we are playing the game

	private Player player; //the player 
	private LevelManager  levelManager; //has all things for the levels
	
	public Playing(Game game) {
		super(game);
		initClasses();
	}
	
	private void initClasses() { //initializes the classes
		levelManager = new LevelManager(game);
		player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
	}

	@Override
	public void update() {
		levelManager.update();
		player.update();
	}

	@Override
	public void draw(Graphics g) {
		levelManager.draw(g); //level rendered first, so the level sprite with be behind the player
		player.render(g); //the player render
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) { //BUTTON1 is left mouse button
			player.setAttacking(true);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) { //when we release from the key it stops the animation
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_BACK_SPACE:
			Gamestate.state = Gamestate.MENU;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) { //when we release from the key it stops the animation
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}
		
	}

	public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() { //acts when you click out of the window
		player.resetDirBooleans(); //resets the movement booleans
		
	}
}
