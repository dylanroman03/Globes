package main;

import static utilities.Constants.BACKGROUND_PATH;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import entities.Missile;
import entities.Player;
import managers.GlobeManager;
import utilities.LoadSave;

public class Game implements Runnable {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private boolean isGaming = true;
	private int time = 0;
	private boolean isPlaying = true;
	private BufferedImage background;

	private Player player;
	private Missile missile;
	private GlobeManager globeManager;

	public final static int TILES_DEFAULT_SIZE = 16;
	public final static float SCALE = 3f;
	public final static int TILES_WIDTH = 20;
	public final static int TILES_HEIGTH = 8;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_HEIGTH;
	public final static boolean DEBUG = false;

	public Game() {
		background = LoadSave.getImage(BACKGROUND_PATH);
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
		globeManager = new GlobeManager();
		missile = new Missile(player.getHitBox().x, player.getHitBox().y - 25);
		missile.setGlobeManager(globeManager);
		globeManager.setPlayer(player);

		time = 0;
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				isPlaying = false;
				callDialog();
			}
		};
		timer.schedule(task, 10000);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		player.update();
		missile.update((int) player.getHitBox().x, (int) (player.getHitBox().y - 25));
	}

	public void render(Graphics g) {
    g.drawImage(background, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		globeManager.render(g);
		player.render(g);
		missile.render(g, (int) player.getHitBox().x, (int) (player.getHitBox().y - 25));
		g.drawString("Time: " + time, TILES_SIZE * 18, TILES_SIZE);
		g.drawString("Points: " + player.getGoals(), TILES_SIZE * 18, TILES_SIZE / 2);
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long previousTime = System.nanoTime();

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
				deltaU--;
			}

			if (deltaF >= 1) {
				if (isPlaying) {
					gamePanel.repaint();
				}
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				time++;
			}
		}

	}

	protected void callDialog() {
		new Dialog(gamePanel, this);
	}

	public void playAgain() {
		initClasses();
		isPlaying = true;
	}

	public void windowsFocusLost() {
		player.resetDirection();
	}

	public Player getPlayer() {
		return player;
	}

	public Missile getMissile() {
		return missile;
	}
}
