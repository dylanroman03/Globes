package inputs;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import main.GamePanel;

public class MouseInputs implements  MouseMotionListener, MouseListener {
	private GamePanel gamePanel;
	int x = 0;

	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		Point location = MouseInfo.getPointerInfo().getLocation();
		x = (int) location.getX();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getX() < x) {
			gamePanel.getGame().getPlayer().setLeft(true);
		} else if (e.getX() > x) {
			gamePanel.getGame().getPlayer().setRight(true);
		} else {
			gamePanel.getGame().getPlayer().setRight(false);
			gamePanel.getGame().getPlayer().setLeft(false);
		}


		x = e.getX();

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				if (x == e.getX()) {
					gamePanel.getGame().getPlayer().setRight(false);
					gamePanel.getGame().getPlayer().setLeft(false);
				}
			}
		};
		timer.schedule(task, 100);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gamePanel.getGame().getMissile().setShooting(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
