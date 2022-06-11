package finalProject;
import javax.swing.ImageIcon;

// Player class is child class of MovingObject class.
public class Player extends MovingObject {

	public Player(int posX, int posY, int radius, int speed) {
		super(posX, posY, radius, speed);
		setImage(new ImageIcon(getClass().getResource("/images/player.png")).getImage());
	}

	// player can move only within the boundary (use Math.max or Math.min method)
	public void moveUp() {
		super.setPosY(Math.max(super.getPosY() - super.getSpeed(), 0));
	}

	public void moveDown() {
		super.setPosY(
				Math.min(super.getPosY() + super.getSpeed(), ProjectUtilities.SIZE_MAP - ProjectUtilities.SIZE_PLAYER));
	}

	public void moveRight() {
		super.setPosX(
				Math.min(super.getPosX() + super.getSpeed(), ProjectUtilities.SIZE_MAP - ProjectUtilities.SIZE_PLAYER));
	}

	public void moveLeft() {
		super.setPosX(Math.max(super.getPosX() - super.getSpeed(), 0));
	}
}
