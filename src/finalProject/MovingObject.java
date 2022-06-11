package finalProject;
import java.awt.Image;

// MovingObject is parent class of Enemy and Player.
public class MovingObject {
	// All moving objects (enemy or player) has following value.
	private int posX;
	private int posY;
	private int radius;
	private int speed;
	private Image image;

	// constructor
	public MovingObject(int posX, int posY, int radius, int speed) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.speed = speed;
	}

	// getters & setters
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
