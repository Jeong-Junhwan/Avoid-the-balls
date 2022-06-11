package finalProject;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class GamePanel extends JPanel {

	private Player player;
	// make LinkedList for saving enemy fields
	private LinkedList<Enemy> enemys;

	// key check for moving
	private boolean keyUp = false;
	private boolean keyDown = false;
	private boolean keyLeft = false;
	private boolean keyRight = false;

	// two timer for displaying and respawning
	private Timer playerMoveTimer;
	private Timer enemyRespawnTimer;

	private Random random;
	private int[] enemyPos = { 0, 0 };
	private float score;
	private Font scoreFont;

	// boolean indicates game is end or not
	private boolean gameOver;

	/**
	 * Create the panel.
	 */
	public GamePanel() {
		gameOver = false;
		this.setDoubleBuffered(true); // use double buffering (not blinking)
		score = 0f;

		// initialize player at the center
		player = new Player(ProjectUtilities.SIZE_MAP / 2, ProjectUtilities.SIZE_MAP / 2, ProjectUtilities.SIZE_PLAYER,
				5);
		random = new Random();
		enemys = new LinkedList<Enemy>();
		setLayout(new BorderLayout(0, 0));
		scoreFont = new Font("Arial", Font.ITALIC, 50);

		// add key listener for user keyboard input
		addKeyListener(new KeyAdapter() {
			@Override
			// check if arrow keys are pressed
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					keyUp = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					keyDown = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					keyLeft = true;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					keyRight = true;
				}
			}

			@Override
			// check if arrow keys are released
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					keyUp = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					keyDown = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					keyLeft = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					keyRight = false;
				}
			}
		});

		// in every 10ms, execute below
		playerMoveTimer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// move player and enemy, repaint the panel
				playerMove();
				enemyMove();
				repaint();
				score += 0.1f;

				// if player and enemy get crashed, game end
				if (ProjectUtilities.checkCrash(player, enemys)) {
					gameOver = true;
					enemyRespawnTimer.stop();
					playerMoveTimer.stop();
				}

			}
		});

		// in every 800ms, execute below
		enemyRespawnTimer = new Timer(800, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// use synchronized block because there are so many remove and add
				synchronized (enemys) {
					// make enemy
					new enemyRespawnClass().randomPatternSelect();

					// remove enemy if they are already out of board
					enemys.removeIf(enemy -> (ProjectUtilities.checkBoarderContact(enemy)));
				}

			}
		});

		playerMoveTimer.start();
		enemyRespawnTimer.start();
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public float getScore() {
		return score;
	}

	// move player according to keyboard input
	public void playerMove() {
		if (keyUp) {
			player.moveUp();
		}
		if (keyDown) {
			player.moveDown();
		}
		if (keyLeft) {
			player.moveLeft();
		}
		if (keyRight) {
			player.moveRight();
		}
	}

	// enemy move according to given patterns
	public void enemyMove() {
		synchronized (enemys) {
			for (Enemy enemy : enemys) {
				enemy.setNextPos();
			}
		}
	}

	@Override
	// override paintComponent method for painting
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw background
		g.drawImage(new ImageIcon(getClass().getResource("/images/gameBackground.png")).getImage(), 0, 0, null);
		// draw player
		g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), null);
		// draw enemy
		synchronized (enemys) {
			for (Enemy enemy : enemys) {
				g.drawImage(enemy.getImage(), enemy.getPosX(), enemy.getPosY(), null);
			}
		}

		// draw score
		g.setFont(scoreFont);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("" + (int) score, 30, 50);
	}

	// due to many patterns, make another class
	class enemyRespawnClass {

		// make new enemy without specific direction (direct to player)
		public void enemyRespawn(int[] enemyPos, int randomSpeed) {

			int randomSize = random.nextInt(5);

			int randomRadius = 0;
			Image enemyImage = null;

			// set radius and image to enemy
			if (randomSize == 0) {
				randomRadius = ProjectUtilities.SIZE_YELLOW;
				enemyImage = new ImageIcon(getClass().getResource("/images/circleYellow.png")).getImage();
			} else if (randomSize == 1) {
				randomRadius = ProjectUtilities.SIZE_PURPLE;
				enemyImage = new ImageIcon(getClass().getResource("/images/circlePurple.png")).getImage();
			} else if (randomSize == 2) {
				randomRadius = ProjectUtilities.SIZE_BLACK;
				enemyImage = new ImageIcon(getClass().getResource("/images/circleBlack.png")).getImage();
			} else if (randomSize == 3) {
				randomRadius = ProjectUtilities.SIZE_BLUE;
				enemyImage = new ImageIcon(getClass().getResource("/images/circleBlue.png")).getImage();
			} else if (randomSize == 4) {
				randomRadius = ProjectUtilities.SIZE_RED;
				enemyImage = new ImageIcon(getClass().getResource("/images/circleRed.png")).getImage();
			}
			double[] enemyDirection = ProjectUtilities.calculateDirection(player.getPosX(), player.getPosY(),
					enemyPos[0], enemyPos[1]);

			// use synchronized block because there are so many remove and add
			synchronized (enemys) {
				enemys.add(new Enemy(enemyImage, enemyPos[0], enemyPos[1], randomRadius, randomSpeed, enemyDirection[0],
						enemyDirection[1]));
			}
		}

		// make new enemy with specific direction
		public void enemyRespawn(int[] enemyPos, int randomSpeed, double[] enemyDirection) {

			int randomSize = random.nextInt(5);

			int randomRadius = 0;
			Image enemyImage = null;

			// set radius and image to enemy
			if (randomSize == 0) {
				randomRadius = ProjectUtilities.SIZE_YELLOW;
				enemyImage = new ImageIcon(getClass().getResource("/images/circleYellow.png")).getImage();
			} else if (randomSize == 1) {
				randomRadius = ProjectUtilities.SIZE_PURPLE;
				enemyImage = new ImageIcon(getClass().getResource("/images/circlePurple.png")).getImage();
			} else if (randomSize == 2) {
				randomRadius = ProjectUtilities.SIZE_BLACK;
				enemyImage = new ImageIcon(getClass().getResource("/images/circleBlack.png")).getImage();
			} else if (randomSize == 3) {
				randomRadius = ProjectUtilities.SIZE_BLUE;
				enemyImage = new ImageIcon(getClass().getResource("/images/circleBlue.png")).getImage();
			} else if (randomSize == 4) {
				randomRadius = ProjectUtilities.SIZE_RED;
				enemyImage = new ImageIcon(getClass().getResource("/images/circleRed.png")).getImage();
			}

			// use synchronized block because there are so many remove and add
			synchronized (enemys) {
				enemys.add(new Enemy(enemyImage, enemyPos[0], enemyPos[1], randomRadius, randomSpeed, enemyDirection[0],
						enemyDirection[1]));
			}
		}

		// come 12 balls at once
		public void enemyRespawnPattern1() {
			enemyPos[0] = 0;
			enemyPos[1] = 0;
			{
				enemyRespawn(enemyPos, 4);
				enemyPos[0] += ProjectUtilities.SIZE_MAP / 3;
				enemyRespawn(enemyPos, 4);
				enemyPos[0] += ProjectUtilities.SIZE_MAP / 3;
				enemyRespawn(enemyPos, 4);
				enemyPos[0] = ProjectUtilities.SIZE_MAP;
				enemyRespawn(enemyPos, 4);
			}
			{
				enemyPos[1] += ProjectUtilities.SIZE_MAP / 3;
				enemyRespawn(enemyPos, 4);
				enemyPos[1] += ProjectUtilities.SIZE_MAP / 3;
				enemyRespawn(enemyPos, 4);
				enemyPos[1] = ProjectUtilities.SIZE_MAP;
				enemyRespawn(enemyPos, 4);
			}
			{
				enemyPos[0] -= ProjectUtilities.SIZE_MAP / 3;
				enemyRespawn(enemyPos, 4);
				enemyPos[0] -= ProjectUtilities.SIZE_MAP / 3;
				enemyRespawn(enemyPos, 4);
				enemyPos[0] = 0;
				enemyRespawn(enemyPos, 4);
			}
			{
				enemyPos[1] -= ProjectUtilities.SIZE_MAP / 3;
				enemyRespawn(enemyPos, 4);
				enemyPos[1] -= ProjectUtilities.SIZE_MAP / 3;
				enemyRespawn(enemyPos, 4);
			}
		}

		// come 15 balls randomly
		public void enemyRespawnPattern2() {
			for (int iter = 0; iter < 15; iter++) {
				enemyRespawn(ProjectUtilities.randomPositionGenerator(), 4);
			}
		}

		// horizontal (bottom > top)
		public void enemyRespawnPattern3() {
			enemyPos[0] = 0;
			enemyPos[1] = ProjectUtilities.SIZE_MAP;
			double[] enemyDirection = { 0, -1 };
			enemyRespawn(enemyPos, 4, enemyDirection);
			for (int iter = 0; iter < 10; iter++) {
				enemyPos[0] += ProjectUtilities.SIZE_MAP / 10;
				enemyRespawn(enemyPos, 4, enemyDirection);
			}
		}

		// horizontal (top > bottom)
		public void enemyRespawnPattern4() {
			enemyPos[0] = 0;
			enemyPos[1] = 0;
			double[] enemyDirection = { 0, 1 };
			enemyRespawn(enemyPos, 4, enemyDirection);
			for (int iter = 0; iter < 10; iter++) {
				enemyPos[0] += ProjectUtilities.SIZE_MAP / 10;
				enemyRespawn(enemyPos, 4, enemyDirection);
			}
		}

		// vertical (left > right)
		public void enemyRespawnPattern5() {
			enemyPos[0] = 0;
			enemyPos[1] = 0;
			double[] enemyDirection = { 1, 0 };
			enemyRespawn(enemyPos, 4, enemyDirection);
			for (int iter = 0; iter < 10; iter++) {
				enemyPos[1] += ProjectUtilities.SIZE_MAP / 10;
				enemyRespawn(enemyPos, 4, enemyDirection);
			}
		}

		// vertical (right > left)
		public void enemyRespawnPattern6() {
			enemyPos[0] = ProjectUtilities.SIZE_MAP;
			enemyPos[1] = 0;
			double[] enemyDirection = { -1, 0 };
			enemyRespawn(enemyPos, 4, enemyDirection);
			for (int iter = 0; iter < 10; iter++) {
				enemyPos[1] += ProjectUtilities.SIZE_MAP / 10;
				enemyRespawn(enemyPos, 4, enemyDirection);
			}
		}

		// bottom to player
		public void enemyRespawnPattern7() {
			enemyPos[0] = 0;
			enemyPos[1] = ProjectUtilities.SIZE_MAP;
			enemyRespawn(enemyPos, 4);
			for (int iter = 0; iter < 10; iter++) {
				enemyPos[0] += ProjectUtilities.SIZE_MAP / 10;
				enemyRespawn(enemyPos, 4);
			}
		}

		// top to player
		public void enemyRespawnPattern8() {
			enemyPos[0] = 0;
			enemyPos[1] = 0;
			enemyRespawn(enemyPos, 4);
			for (int iter = 0; iter < 10; iter++) {
				enemyPos[0] += ProjectUtilities.SIZE_MAP / 10;
				enemyRespawn(enemyPos, 4);
			}
		}

		// left to player
		public void enemyRespawnPattern9() {
			enemyPos[0] = 0;
			enemyPos[1] = 0;
			enemyRespawn(enemyPos, 4);
			for (int iter = 0; iter < 10; iter++) {
				enemyPos[1] += ProjectUtilities.SIZE_MAP / 10;
				enemyRespawn(enemyPos, 4);
			}
		}

		// right to player
		public void enemyRespawnPattern10() {
			enemyPos[0] = ProjectUtilities.SIZE_MAP;
			enemyPos[1] = 0;
			enemyRespawn(enemyPos, 4);
			for (int iter = 0; iter < 10; iter++) {
				enemyPos[1] += ProjectUtilities.SIZE_MAP / 10;
				enemyRespawn(enemyPos, 4);
			}
		}

		// execute one of patterns above
		public void randomPatternSelect() {
			int patternSelector = random.nextInt(10) + 1;
			if (patternSelector == 1) {
				enemyRespawnPattern1();
			} else if (patternSelector == 2) {
				enemyRespawnPattern2();
			} else if (patternSelector == 3) {
				enemyRespawnPattern3();
			} else if (patternSelector == 4) {
				enemyRespawnPattern4();
			} else if (patternSelector == 5) {
				enemyRespawnPattern5();
			} else if (patternSelector == 6) {
				enemyRespawnPattern6();
			} else if (patternSelector == 7) {
				enemyRespawnPattern7();
			} else if (patternSelector == 8) {
				enemyRespawnPattern8();
			} else if (patternSelector == 9) {
				enemyRespawnPattern9();
			} else if (patternSelector == 10) {
				enemyRespawnPattern10();
			}

		}

	}
}
