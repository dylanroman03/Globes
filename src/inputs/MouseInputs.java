package inputs;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
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
		// if (e.getX() < x) {
		// 	System.out.println("1");
		// 	gamePanel.getGame().getPlayer().setLeft(true);
		// } else if (e.getX() > x) {
		// 	System.out.println("2");
		// 	gamePanel.getGame().getPlayer().setRight(true);
		// } else {
		// 	gamePanel.getGame().getPlayer().setRight(false);
		// 	gamePanel.getGame().getPlayer().setLeft(false);
		// }

		// x = e.getX();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked");
		gamePanel.getGame().getMissile().setShooting(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
