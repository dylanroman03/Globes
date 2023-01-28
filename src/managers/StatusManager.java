package managers;

import static utilities.Constants.GetTimePath;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class StatusManager {
  public static void renderTime(Graphics g, int time) {
    int x =  Game.TILES_SIZE * 18;

    String number = String.valueOf(time);
    char[] digits1 = number.toCharArray();

    for (char c : digits1) {
      String path = GetTimePath(Character.getNumericValue(c));
      BufferedImage image = LoadSave.getImage(path);

      g.drawImage(image, x, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2, null);
      x += Game.TILES_SIZE;
    }
  }

  public static void renderGoals(Graphics g, int goals) {
    int x =  Game.TILES_SIZE * 18;

    String number = String.valueOf(goals);
    char[] digits1 = number.toCharArray();

    for (char c : digits1) {
      String path = GetTimePath(Character.getNumericValue(c));
      BufferedImage image = LoadSave.getImage(path);

      g.drawImage(image, x, Game.TILES_SIZE * 2, Game.TILES_SIZE / 2, Game.TILES_SIZE / 2, null);
      x += Game.TILES_SIZE;
    }
  }
  
}
