package model;

public class PlayerShip {

	private double x;
	private double y;	
	
	private int deltaX;
	
	public PlayerShip(double x, double y) {
		this.setX(x);
		this.setY(y);
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

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public void moveRight() {
		x += deltaX*2;
	}
	
	public void moveLeft() {
		x -= deltaX*2;
	}
	
}
