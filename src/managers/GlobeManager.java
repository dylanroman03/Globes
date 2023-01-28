package managers;

import static utilities.Constants.GetGlobePoints;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import entities.Globe;
import entities.Player;
import main.Game;

public class GlobeManager {
  private Globe[][] globes = new Globe[3][17];
  private Player player;

  public GlobeManager() {
    for (int i = 0; i < globes.length; i++) {
      for (int j = 0; j < globes[0].length; j++) {
        Random r = new Random();
        int type = r.nextInt(5-1) + 1;
        globes[i][j] = new Globe((Game.TILES_SIZE * j), (Game.TILES_SIZE * i), type);
      }
    }
  }

  public void render(Graphics g) {
    for (Globe[] globes : globes) {
      for (Globe globe : globes) {
          if (globe.visible) {
            globe.render(g);
          }
      } 
    }
  }

  public boolean intersectGlobe(Rectangle2D missile) {
    for (Globe[] globes2 : globes) {
      for (Globe globe : globes2) {
        if (globe.visible) {
          if(globe.getHitBox().intersects(missile)) {
            globe.visible = false;
            player.setGoals(GetGlobePoints(globe.type));
            return true;
          }
        }
      }
    }
    return false;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
  
}
