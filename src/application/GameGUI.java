package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import model.Projectile;

public class GameGUI implements Initializable{

	@FXML
	Canvas canvas1;
	
	@FXML
	Label highscore;
	
	
	private Main main;
	
	private GraphicsContext gc;
	
	private boolean gameHasFinished;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		canvas1.setFocusTraversable(true);
		gc = canvas1.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
	}
	
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void paint() {		
		Platform.runLater(()->{
			gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
			gc.setFill(Color.YELLOW);
			double x = main.getBoard().getPlayerShip().getX();
			double y = main.getBoard().getPlayerShip().getY();

			gc.fillPolygon(new double[] {x,x-15,x,x+15}, new double[] {y-20,y+20,y+8,y+20}, 4);

			main.getBoard().getEnemies().forEach(enemy ->{
				gc.fillPolygon(new double[] {enemy.getX()-20,enemy.getX(),enemy.getX()+20}, new double[] {enemy.getY()-10,enemy.getY()+10,enemy.getY()-10}, 3);
			});
			
			main.getBoard().getProjectiles().forEach(projectile -> {
				gc.fillRect(projectile.getX()-2, projectile.getY()-5, 4, 10);		
			});
			
			highscore.setText(String.valueOf(main.getBoard().getScore()));
			if(main.getBoard().checkGameOverStates()) {
				gc.fillText("Game over! Your final score is "+getHighscore().getText(), 215, 300);
				
				}
		});
	}
	public void startP() {
		enemy();
		projectile();
		Runnable start=() -> {
			while (true) {
				
				try {
					Thread.sleep(30/3);
					main.getBoard().updatePlayer();
					main.getBoard().checkProjectileToEnemy();
					main.getBoard().checkGameOverStates();
					paint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		Thread startThread = new Thread(start); 
		startThread.start();
	}
	
	public void projectile() {
		Runnable projectiles=() -> {
			while(true) {
				try {
					Thread.sleep(10/3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			main.getBoard().getProjectiles().forEach(projectile -> {
				projectile.moveUp();
			});
			main.getBoard().updateProjectiles();
			}
		};
		Thread projectileThread = new Thread(projectiles);
		projectileThread.start();
	}
	
	public void enemy() {
		Runnable enemies = () -> {
			while(true) {
				
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				main.getBoard().getEnemies().forEach(enemy ->{
					//main.getBoard().randomShot();
					enemy.moveDown();
				});
			}
		};
		Thread enemyThread = new Thread(enemies);
		enemyThread.start();
	}
	
	@FXML
	public void movePlayer(KeyEvent e) {
		switch(e.getCode()) {
		case LEFT:
			main.getBoard().getPlayerShip().moveLeft();	
			break;
		
		case RIGHT:
			main.getBoard().getPlayerShip().moveRight();	
			break;
			
		case SPACE:
			Projectile projectile = new Projectile(main.getBoard().getPlayerShip().getX(), main.getBoard().getPlayerShip().getY()-30,1, true);
			main.getBoard().getProjectiles().add(projectile);
			break;
		
		default:
			break;
		}
	}

	public Label getHighscore() {
		return highscore;
	}
	
}
