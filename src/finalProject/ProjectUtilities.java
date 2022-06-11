package finalProject;
import java.util.LinkedList;
import java.util.Random;

// useful fields and method for this project
public class ProjectUtilities {

	// size of map, player, and enemy
	public static final int SIZE_MAP = 800;

	public static final int SIZE_PLAYER = 30;

	public static final int SIZE_YELLOW = 12;
	public static final int SIZE_PURPLE = 14;
	public static final int SIZE_BLACK = 16;
	public static final int SIZE_BLUE = 18;
	public static final int SIZE_RED = 20;

	// return true player get crashed with any of enemy
	// else, return false
	public static boolean checkCrash(Player player, LinkedList<Enemy> enemys) {
		// get player's position, radius
		int playerPosX = player.getPosX();
		int playerPosY = player.getPosY();

		// check with every enemy in the game
		for (Enemy enemy : enemys) {
			int enemyPosX = enemy.getPosX();
			int enemyPosY = enemy.getPosY();
			int enemyRadius = enemy.getRadius();

			// distance between player and enemy
			double distance = Math.hypot(playerPosX - enemyPosX, playerPosY - enemyPosY);

			// maximum safe distance (sum of radius because their shape are both circle)
			// give some tolerance
			int maxSafeDistance = (int) ((SIZE_PLAYER + enemyRadius) * 0.25);

			if (distance <= maxSafeDistance) {
				return true;
			}
		}
		return false;
	}

	// return true if given enemy is far away enough
	public static boolean checkBoarderContact(Enemy enemy) {

		int enemyPosX = enemy.getPosX();
		int enemyPosY = enemy.getPosY();

		if (enemyPosX < -10 || enemyPosX > ProjectUtilities.SIZE_MAP + 10 || enemyPosY < -10
				|| enemyPosY > ProjectUtilities.SIZE_MAP + 10) {
			return true;
		}
		return false;
	}

	// calculate direction from (x2, y2) to (x1, y1) (use for enemy direction)
	public static double[] calculateDirection(int x1, int y1, int x2, int y2) {
		double[] direction = { 0, 0 };
		double magnitude = Math.hypot(x1 - x2, y1 - y2);

		direction[0] = (x1 - x2) / magnitude;
		direction[1] = (y1 - y2) / magnitude;

		return direction;

	}

	// return random position on the boarder line
	public static int[] randomPositionGenerator() {
		Random random = new Random();
		int[] position = { 0, 0 };

		int randomLine = random.nextInt(4);
		if (randomLine == 0) {
			position[0] = random.nextInt(SIZE_MAP);
			position[1] = 0;
		} else if (randomLine == 1) {
			position[0] = SIZE_MAP;
			position[1] = random.nextInt(SIZE_MAP);
		} else if (randomLine == 2) {
			position[0] = random.nextInt(SIZE_MAP);
			position[1] = SIZE_MAP;
		} else if (randomLine == 3) {
			position[0] = 0;
			position[1] = random.nextInt(SIZE_MAP);
		}

		return position;

	}
}
