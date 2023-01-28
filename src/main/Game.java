package main;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import entities.Missile;
import entities.Player;
import managers.GlobeManager;

public class Game implements Runnable {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private boolean isGaming = true;
	private int time = 0;
	private boolean isPlaying = true;

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
	public final static boolean DEBUG = true;

	public Game() {
		initClasses();

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				isPlaying = false;
				JOptionPane.showConfirmDialog(
					gamePanel, 
					"Your Points are:" + player.getGoals(), 
					"Swing Tester",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			}

		};
		timer.schedule(task, 10000);
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
				if (isPlaying) {
					gamePanel.repaint();
				}
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				time++;
			}
		}

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
