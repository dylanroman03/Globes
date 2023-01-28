package managers;

// import static utilities.Constants.GetGlobePath;

import java.awt.Graphics;
import java.util.Random;

import entities.Globe;
import main.Game;

public class GlobeManager {
  private Globe[][] globes = new Globe[3][17];

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
          globe.render(g);
      } 
    }
  }
  
}