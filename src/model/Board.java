package model;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.control.Label;

public class Board {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int MAX_EROW=8;
	
	private int eShipAmount;
	private PlayerShip fShip;
	private CopyOnWriteArrayList<EnemyShip> enemies;
	private CopyOnWriteArrayList<Projectile> projectiles;
	
	public Board() {
		setPlayerShip(new PlayerShip(300, 500));
		fShip.setDeltaX(2);
		eShipAmount=4;
		enemies=new CopyOnWriteArrayList<EnemyShip>();
		projectiles=new CopyOnWriteArrayList<Projectile>();
		createEnemies();
	}
	
	public void updatePlayer() {
		
		if (fShip.getX() > WIDTH-15) {
			fShip.setX(585);
		}
		
		if (fShip.getX() < 15) {
			fShip.setX(15);
		}
	}
	
	public void updateProjectiles() {
		for(Projectile projectile:projectiles) {
			if (projectile.getDeltaY() > 0 && projectile.getY() + projectile.getDeltaY() > HEIGHT) {
				projectiles.remove(projectile);
			}
		}
		
	}
	
	public PlayerShip getPlayerShip() {
		return fShip;
	}

	public void setPlayerShip(PlayerShip fShip) {
		this.fShip = fShip;
	}

	public CopyOnWriteArrayList<EnemyShip> getEnemies() {
		return enemies;
	}

	public void setEnemies(CopyOnWriteArrayList<EnemyShip> enemies) {
		this.enemies = enemies;
	}

	public CopyOnWriteArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(CopyOnWriteArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	public int geteShipAmount() {
		return eShipAmount;
	}

	public void seteShipAmount(int eShipAmount) {
		this.eShipAmount = eShipAmount;
	}
	
	
	public void createEnemies() {
		int a = eShipAmount/MAX_EROW;
		int b = eShipAmount%MAX_EROW;
		int y = 100;
		
		for(int i=0;i<a;i++) {
			placeRow(MAX_EROW,y+30*i);
		}
		placeRow(b,y+30*a);
	}
	
	public void placeRow(int enemyAmount, int yPosition) {
		int counter=enemyAmount;
		int center=WIDTH/2;
		
		if(counter%2!=0) {
			placeEnemy(center,yPosition);
			counter--;
		}
		for(int i=1;i<=counter/2;i++) {
			placeEnemy(center-80*i,yPosition);
		}
		for(int i=1;i<=counter/2;i++) {
			placeEnemy(center+80*i,yPosition);
		}
	}
	
	public void placeEnemy(int xPosition, int yPosition) {
		
		EnemyShip ship = new EnemyShip(xPosition,yPosition, 1);
		enemies.add(ship);
	}
	
	public void checkEnemyProjectileCollission(Label highscore, int scoreValue, String currentScore) {
		
		for(int i=0;i<enemies.size();i++) {
			EnemyShip enemy=enemies.get(i);
			
			for(int j=0;j<projectiles.size();j++) {
				
				Projectile projectile=projectiles.get(j);
				if(projectile.getX()==enemy.getX()&&projectile.getX()==enemy.getX()) {
					enemies.remove(i);
					projectiles.remove(j);
					scoreValue += 10;
					currentScore= ""+scoreValue;
					Integer.parseInt(currentScore);
					highscore.setText(currentScore);
				}
			}
		}
	}
	
	public boolean betweenXProjectileToEnemy(double enemyLeftSide, double enemyRightSide, double projectileLeftSide, double projectileRightSide) {
		if(enemyLeftSide >= projectileLeftSide &&  enemyRightSide > projectileLeftSide && enemyRightSide <= projectileRightSide &&  enemyLeftSide < projectileRightSide) {
			return true;
		}else {
			return false;
		}	
	}
	
	public boolean betweenYProjectileToEnemy(double enemyBottom, double enemyTop, double projectileTop) {
		if(enemyBottom <= projectileTop && enemyTop < projectileTop) {
			return true;
		}else {
			return false;
		}	
	}
	
	
	/*public void checkEnemyProjectileCollission(Label highscore, int scoreValue, String currentScore) {
		for(int i=0;i<enemies.size();i++) {
			EnemyShip enemy=enemies.get(i);
			
			for(int j=0;j<projectiles.size();j++) {
				Projectile projectile=projectiles.get(j);
				
				
				if(betweenXProjectileToEnemy(enemy.getX()-20,enemy.getX()+20,projectile.getX()-2, projectile.getX())==true &&
					betweenYProjectileToEnemy(enemy.getY()+10, enemy.getY()-10, projectile.getY()-5)==true) {
					enemies.remove(i);
					projectiles.remove(j);
					scoreValue += 10;
					currentScore= ""+scoreValue;
					Integer.parseInt(currentScore);
					highscore.setText(currentScore);
				}
			}
		}
	}*/
}
