package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char w = "w".charAt(0);
		char wUppercase = "W".charAt(0);

		if (e.getKeyChar() == w || e.getKeyChar() == wUppercase) {
			gamePanel.getGame().getMissile().setShooting(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				gamePanel.getGame().getPlayer().setLeft(false);
				break;
			case KeyEvent.VK_D:
				gamePanel.getGame().getPlayer().setRight(false);
				break;
			default:
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				gamePanel.getGame().getPlayer().setLeft(true);
				break;
			case KeyEvent.VK_D:
				gamePanel.getGame().getPlayer().setRight(true);
				break;
			default:
				break;
		}
	}
}
