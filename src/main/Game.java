package main;

import java.awt.Graphics;

import entities.Player;

// import entities.Player;
// import levels.BoxManager;
// import levels.BushManager;
// import levels.LevelManager;

public class Game implements Runnable {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private boolean isGaming = true;

	private Player player;
	// private LevelManager levelManager;
  // private BoxManager boxManager;
  // private BushManager bushManager;

	public final static int TILES_DEFAULT_SIZE = 16;
	public final static float SCALE = 3f;
	public final static int TILES_WIDTH = 20;
	public final static int TILES_HEIGTH = 8;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_HEIGTH;
	public final static boolean DEBUG = false;

	public Game() {
		initClasses();

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
		int xInit = 0;
		int yInit = GAME_HEIGHT - TILES_SIZE;

		player = new Player(xInit, yInit, TILES_SIZE + 30, TILES_SIZE + 30);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		player.update();
	}

	public void render(Graphics g) {
		player.render(g);
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();

		double deltaU = 0;
		double deltaF = 0;

		while (isGaming) {
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

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;

			}
		}

	}

	public void windowsFocusLost() {
		player.resetDirection();
	}

	public Player getPlayer() {
		return player;
	}
}
