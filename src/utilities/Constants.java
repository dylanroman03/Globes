package utilities;

public class Constants {
	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
	}

	public static class PlayerConstants {
		public static final int IDLE_UP = 0;
		public static final int IDLE_DOWN = 3;
		public static final int RUNNING_LEFT = 1;
		public static final int RUNNING_RIGHT = 2;
	}

	public static final String[] PATH_WARRIOR_LIST = {
			"res/Warrior_animations/Front/PNG Sequences/Warrior_clothes_1/",
			"res/Warrior_animations/Left_Side/PNG Sequences/Warrior_clothes_1/",
			"res/Warrior_animations/Right_Side/PNG Sequences/Warrior_clothes_1/",
			"res/Warrior_animations/Front/PNG Sequences/Warrior_clothes_1/"
	};


	public static final int BLUE_GLOBE = 1;
	public static final int GREEN_GLOBE = 2;
	public static final int RED_GLOBE = 3;
	public static final int YELLOW_GLOBE = 4;

	public static String GetGlobePath (int globe) {
		switch (globe) {
			case BLUE_GLOBE:
				return 	"res/Globes/blue.png";
			case GREEN_GLOBE:
				return 	"res/Globes/green.png";
			case RED_GLOBE:
				return 	"res/Globes/red.png";
			default: // 3 
				return 	"res/Globes/yellow.png";
		}
	}

}
