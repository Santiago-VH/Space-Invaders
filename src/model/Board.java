package model;

import java.util.concurrent.CopyOnWriteArrayList;	

public class Board {

	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int MAX_EROW=8;
	
	private int score;
	private int eShipAmount;
	private PlayerShip fShip;
	private CopyOnWriteArrayList<EnemyShip> enemies;
	private CopyOnWriteArrayList<Projectile> projectiles;
	
	public Board() {
		setPlayerShip(new PlayerShip(300, 500));
		fShip.setDeltaX(2);
		eShipAmount=11;
		score=0;
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
	
	public void createEnemies() {
		int a = eShipAmount/MAX_EROW;
		int b = eShipAmount%MAX_EROW;
		int y = 100;
		
		for(int i=0;i<a;i++) {
			placeRow(MAX_EROW,y+40*i);
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
			placeEnemy(center-50*i,yPosition);
		}
		for(int i=1;i<=counter/2;i++) {
			placeEnemy(center+50*i,yPosition);
		}
	}
	
	public void placeEnemy(int xPosition, int yPosition) {
		
		EnemyShip ship = new EnemyShip(xPosition,yPosition, 1);
		enemies.add(ship);
	}
	
	public void checkProjectileToEnemy() {
        for (int i = 0; i < enemies.size(); i++) {
            double enemyTop = enemies.get(i).getY() - 10;
            double enemyBottom = enemies.get(i).getY() + 10;
            for (int j = 0; j < projectiles.size(); j++) {
                double projectileTop = projectiles.get(j).getY() - 5;
                double projectileBottom = projectiles.get(j).getY() + 5;

                boolean hasTopHit = projectileTop < enemyBottom && projectileTop > enemyTop;
                boolean hasBottomHit = projectileBottom < enemyBottom && projectileBottom > enemyTop;

                if ((hasTopHit || hasBottomHit)) {
                    double enemyLeft = enemies.get(i).getX() - 20;
                    double enemyRight = enemies.get(i).getX() + 20;
                    double projectileLeft = projectiles.get(j).getX() - 2;
                    double projectileRight = projectiles.get(j).getX() + 2;

                    boolean hasLeftHit = projectileLeft > enemyLeft && projectileLeft < enemyRight;
                    boolean hasRightHit = projectileRight > enemyLeft && projectileRight < enemyRight;

                    if (hasLeftHit || hasRightHit) {
                        enemies.remove(i);
                        projectiles.remove(j);
                        score+=100;
                    }

                }
            }
        }
	}
	
	public void checkGameOverStates() {
		double playerTop = getPlayerShip().getY()-20;
		double playerBottom = getPlayerShip().getY()+20;
		boolean playerShot=false;
		boolean playerReached=false;
		
			for(int index=0; index < projectiles.size();index++) {
				double projectileTop = projectiles.get(index).getY() - 5;
				double projectileBottom = projectiles.get(index).getY() + 5;
				
				boolean hasTopHitPlayer = projectileTop < playerBottom && projectileTop > playerTop;
                boolean hasBottomHitPlayer = projectileBottom < playerBottom && projectileBottom > playerTop;
                
                if ((hasTopHitPlayer || hasBottomHitPlayer)) {
                	double playerLeft = fShip.getX() - 15;
                    double playerRight = fShip.getX() + 15;
                    double projectileLeft = projectiles.get(index).getX() - 2;
    				double projectileRight = projectiles.get(index).getX() + 2;

                    boolean hasLeftHit = projectileLeft > playerLeft && projectileLeft < playerRight;
                    boolean hasRightHit = projectileRight > playerLeft && projectileRight < playerRight;
                    if(hasLeftHit || hasRightHit) {
            			playerShot=true;
            			projectiles.remove(index);
    				}
                }
			}
			
			for(int index = 0; index < enemies.size(); index++) {
			double enemyBottom = enemies.get(index).getY()+10;
			
			boolean hasEnemyReachedPlayer = playerTop <= enemyBottom;
				if(hasEnemyReachedPlayer) {
					playerReached=true;
				}
			}
			
			if(playerShot == true || playerReached == true) {
				System.out.println("reached");
				
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
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
