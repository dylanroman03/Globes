package entities;

import static utilities.Constants.MISSILE_PATH;
import static utilities.Helpers.isLimit;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import managers.GlobeManager;
import utilities.LoadSave;

public class Missile extends Entity {
  private BufferedImage image;
  private boolean shooting = false;
  private GlobeManager globeManager;


  public Missile(float x, float y) {
    super(x, y, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2);
    image = LoadSave.getImage(MISSILE_PATH);
		initHitBox(x, y, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2);
  }

  public void render(Graphics g, int x, int y) {
    g.drawImage(image, (int) hitBox.x, (int) hitBox.y, (int) getHitBox().width, (int) getHitBox().height, null);

    if (Game.DEBUG) {
      showHitBox(g);
    }
  }

  public void update(int x, int y) {
    if (shooting) {
			hitBox.y -= 2;
      rebootShooting();
    } else {
      updatePosition((float) x, (float) y);
    }
  };

  private void rebootShooting() {
    if(globeManager.intersectGlobe(hitBox)) {
      shooting = false;
    }

    if (isLimit(x, hitBox.y)) {
      shooting = false;
    }
  }

  private void updatePosition(float x, float y) {
		hitBox.x = x;
		hitBox.y = y;
  }

  public void setShooting(boolean b) {
    shooting = true;
  }

  public void setGlobeManager(GlobeManager globeManager) {
    this.globeManager = globeManager;
  }
  
}
