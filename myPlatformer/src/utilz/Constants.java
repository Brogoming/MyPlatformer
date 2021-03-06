package utilz;

import main.Game;

public class Constants { //changes animation based on player action 
	
	public static class UI{
		public static class Buttons{ //menu buttons
			public static final int B_WD = 140; //b width default = 140 px
			public static final int B_HD = 56; //b height default = 56 px
			public static final int B_WIDTH = (int) (B_WD * Game.SCALE); //width scale
			public static final int B_HEIGHT = (int) (B_HD * Game.SCALE); //height scale
		}
		
		public static class PauseButtons{ //pause buttons
			public static final int SOUND_SIZE_DEFAULT = 42; //x and y are 42 by 42
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE); //SOUND scale

		}
		
		public static class URMButtons{
			public static final int URM_SIZE_DEFAULT = 56;
			public static final int URM_SIZE = (int)(URM_SIZE_DEFAULT * Game.SCALE);
		}
		
		public static class VolButtons{
			public static final int VOL_WIDTH_DEFAULT = 28;
			public static final int VOL_HEIGHT_DEFAULT = 44;
			public static final int SLIDER_WIDTH_DEFAULT = 215;
			public static final int VOL_WIDTH = (int)(VOL_WIDTH_DEFAULT * Game.SCALE);
			public static final int VOL_HEIGHT = (int)(VOL_HEIGHT_DEFAULT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int)(SLIDER_WIDTH_DEFAULT * Game.SCALE);
		}
	}
	
	public static class Directions { //determines what direction the player is moving based on their input
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants { //the constants, the rows of each animation
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int GROUND = 4;
		public static final int HIT = 5;
		public static final int ATTACK_1 = 6;
		public static final int ATTACK_JUMP_1 = 7;
		public static final int ATTACK_JUMP_2 = 8;

		public static int GetSpriteAmount(int player_action) { //returns the column of sprites used based on players action  
			switch (player_action) {
			case RUNNING: //there are 6 sprites in the running row when the player "runs"
				return 6;
			case IDLE:
				return 5;
			case HIT:
				return 4;
			case JUMP:
			case ATTACK_1:
			case ATTACK_JUMP_1:
			case ATTACK_JUMP_2:
				return 3;
			case GROUND:
				return 2;
			case FALLING:
			default:
				return 1;
			}
		}
	}

}
