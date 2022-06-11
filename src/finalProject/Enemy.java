package finalProject;

import java.awt.Image;

// Enemy class is child class of MovingObject class.
public class Enemy extends MovingObject {
	// enemy moves with certain direction (straight)
	// need field indicate direction
	private double directionX;
	private double directionY;

	public Enemy(Image image, int posX, int posY, int radius, int speed, double directionX, double directionY) {
		super(posX, posY, radius, speed);
		this.directionX = directionX;
		this.directionY = directionY;
		super.setImage(image);
	}

	// set enemy's next position (current + direction * speed)
	public void setNextPos() {
		super.setPosX((int) (super.getPosX() + directionX * super.getSpeed()));
		super.setPosY((int) (super.getPosY() + directionY * super.getSpeed()));
	}
}
