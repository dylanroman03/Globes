package entities;

import static utilities.Constants.MISSILE_PATH;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class Missile extends Entity {
  BufferedImage image;

  public Missile(float x, float y) {
    super(x, y, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2);
    image = LoadSave.getImage(MISSILE_PATH);
		initHitBox(x, y, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2);
  }

  public void render(Graphics g, int x, int y) {
    g.drawImage(image, x, y, (int) getHitBox().width, (int) getHitBox().height, null);
		updatePosition((float) x, (float) y);

    if (Game.DEBUG) {
      showHitBox(g);
    }
  }

  private void updatePosition(float x, float y) {
		hitBox.x = x;
		hitBox.y = y;
  }
  
}
