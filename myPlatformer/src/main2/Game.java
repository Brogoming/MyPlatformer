package main2;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread; //a separate thread that is ran
	private final int FPS_SET = 120; //max frames per second
	private final int UPS_SET = 200; //max updates per second
	private Player player; //the player 
	private LevelManager  levelManager; //has all things for the levels
	
	public final static int TILES_DEFAULT_SIZE = 32; //default size of our tiles
	public final static float SCALE = 1.5f; //how much we should scale 
	public final static int TILES_IN_WIDTH = 26; //width of the screen
	public final static int TILES_IN_HEIGHT = 14; //height of the screen
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE); //tile size is 48
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

	public Game() { //constructor - head method of the class
		initClasses(); //Initializes classes like players or enemies
		gamePanel = new GamePanel(this); //needs to create the game panel before the window
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus(); //gets input focus
		startGameLoop(); //needs to be the last thing
	}

	private void initClasses() { //initializes the classes
		levelManager = new LevelManager(this);
		player = new Player(200, 200, (int) (64 * SCALE), (int) (40*SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
	}

	private void startGameLoop() { //starts the separate thread
		gameThread = new Thread(this);
		gameThread.start(); //starts the thread
	}

	public void update() { //where every update goes
		levelManager.update();
		player.update();
	}
	
	public void render(Graphics g) { //where every render goes
		levelManager.draw(g); //level rendered first, so the level sprite with be behind the player
		player.render(g); //the player render
	}

	@Override
	public void run() { //runs a separate thread

		double timePerFrame = 1000000000.0 / FPS_SET; //how long each frame will last in nano-seconds
		double timePerUpdate = 1000000000.0 / UPS_SET; //the time in between updates

		long previousTime = System.nanoTime(); //past time

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) { //an infinite loop that always runs
			long currentTime = System.nanoTime(); //our time

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) { //time to update the game, deltaU will be 1.0 or more WHEN the duration since last update is equal OR more than time per update, 
				//percentage of total duration between updates. Example 1.05 = 105% after update 0.05 or 5%
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

//			if (System.currentTimeMillis() - lastCheck >= 1000) { //fps counter and ups counter
//				lastCheck = System.currentTimeMillis();
//				System.out.println("FPS: " + frames + " | UPS: " + updates);
//				frames = 0;
//				updates = 0;
//
//			}
		}

	}
	public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() { //acts when you click out of the window
		player.resetDirBooleans(); //resets the movement booleans
		
	}

}