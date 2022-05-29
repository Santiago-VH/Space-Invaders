package model;

import java.util.ArrayList;

public class Board {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int E_SHIP_AMOUNT = 1;
	
	private PlayerShip fShip;
	
	private EnemyShip[] eShip;
	private ArrayList<Projectile> projectiles;
	
	public Board() {
		setPlayerShip(new PlayerShip(250, 250));
		fShip.setDeltaX(2);
		eShip= new EnemyShip[E_SHIP_AMOUNT];
		projectiles=new ArrayList<Projectile>();
	}

	public PlayerShip getPlayerShip() {
		return fShip;
	}

	public void setPlayerShip(PlayerShip fShip) {
		this.fShip = fShip;
	}
	
	public void updatePlayer() {
		
		if (fShip.getDeltaX() > 0 && fShip.getX() + fShip.getDeltaX() > WIDTH) {
			fShip.setDeltaX(-1*fShip.getDeltaX());
		}
		
		if (fShip.getDeltaX() < 0 && fShip.getX() + fShip.getDeltaX() < 0) {
			fShip.setDeltaX(-1*fShip.getDeltaX());
		}
		
	}

	public PlayerShip getfShip() {
		return fShip;
	}

	public void setfShip(PlayerShip fShip) {
		this.fShip = fShip;
	}

	public EnemyShip[] geteShip() {
		return eShip;
	}

	public void seteShip(EnemyShip[] eShip) {
		this.eShip = eShip;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}
	
}
