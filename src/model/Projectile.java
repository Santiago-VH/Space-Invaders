package model;

public class Projectile {

	private double x;
	private double y;
	
	private int deltaY;
	
	public Projectile(double x, double y, int deltaY) {
		this.setX(x);
		this.setY(y);
		this.deltaY=deltaY;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	public void moveUp() {
		this.y -= deltaY;
	}
}
