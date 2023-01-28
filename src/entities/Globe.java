package entities;

import static utilities.Constants.GetGlobePath;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class Globe extends Entity {
  int type;
  BufferedImage image;

  public int getType() {
    return type;
  }

  public Globe(float x, float y, int type) {
    super(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    this.type = type;

    initHitBox(x, y, Game.TILES_SIZE, Game.TILES_SIZE);
    getImage();
  }

  private void getImage() {
    String path = GetGlobePath(type);
    image  = LoadSave.getImage(path);
  }

  public void render(Graphics g) {
    g.drawImage(image, (int) getHitBox().x, (int) getHitBox().y, Game.TILES_SIZE, Game.TILES_SIZE, null);

    if (Game.DEBUG) {
      showHitBox(g);
    }
  }
  
}
