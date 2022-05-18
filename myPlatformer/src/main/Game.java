package main;

public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread; //a separate thread that is ran
	private final int FPS_SET = 120; //max frames per second
	private final int UPS_SET = 200;

	public Game() { //constructor - head method of the class
		gamePanel = new GamePanel(); //needs to create the game panel before the window
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus(); //gets input focus
		startGameLoop();

	}

	private void startGameLoop() { //starts the separate thread
		gameThread = new Thread(this);
		gameThread.start(); //starts the thread
	}

	public void update() { 
		gamePanel.updateGame();
	}

	@Override
	public void run() { //runs a separate thread

		double timePerFrame = 1000000000.0 / FPS_SET; //how long each frame will last in nano-seconds
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (true) { //an infinite loop that always runs
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) { //fps counter and ups counter
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}

	}

}