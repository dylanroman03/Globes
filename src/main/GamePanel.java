package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
	private Game game;
	private String label = "";

	public void setLabel(String label) {
		this.label = label;
	}

	public GamePanel(Game game) {
		MouseInputs mouseInputs;
		mouseInputs = new MouseInputs(this);
		this.game = game;

		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		// Dimension size = label.getPreferredSize();
		// label.setBounds(200, 200, size.width, size.height);
		// label.setBounds(Game.GAME_WIDTH - Game.TILES_SIZE, Game.GAME_HEIGHT, Game.TILES_SIZE, Game.TILES_SIZE);
		// add(label);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(Game.GAME_WIDTH, Game.GAME_HEIGHT);
		System.out.println("Dimesion:" + size.width + " " + size.height);
		System.out.println("Tile:" + Game.TILES_SIZE);
		setPreferredSize(size);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.white);
		for (int i = 0; i < 64; i++)
			for (int j = 0; j < 40; j++)
				g.fillRect(i * 20, j * 20, 20, 20);

		game.render(g);

	}

	public Game getGame() {
		return game;
	}

}
