package utilities;

import static utilities.Constants.PlayerConstants.IDLE_DOWN;
import static utilities.Constants.PlayerConstants.IDLE_UP;
import static utilities.Constants.PlayerConstants.RUNNING_LEFT;
import static utilities.Constants.PlayerConstants.RUNNING_RIGHT;

import entities.Player;
import main.Game;

public class Helpers {

  public static boolean canMove(Player player, float x, float y) {
    float height = player.getHitBox().height;
    float width = player.getHitBox().width;

    switch (player.getPlayerAction()) {
      case RUNNING_LEFT:
        if (!isSolid(x, y))
          if (!isSolid(x, y + height))
            return true;
        break;
      case RUNNING_RIGHT:
        if (!isSolid(x + width, y))
          if (!isSolid(x + width, y + height))
            return true;
        break;
      case IDLE_UP:
        if (!isSolid(x, y))
          if (!isSolid(x + width, y))
            return true;
        break;
      case IDLE_DOWN:
        if (!isSolid(x, y + height))
          if (!isSolid(x + width, y + height))
            return true;
        break;
    }

    return false;
  }

  public static boolean isSolid(float x, float y) {
    if (x < 0 || x >= Game.GAME_WIDTH)
			return true;
		if (y < 0 || y >= Game.GAME_HEIGHT)
			return true;

    return false;
  }
}
