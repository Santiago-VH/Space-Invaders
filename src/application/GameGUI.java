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
import model.PlayerShip;
import model.Projectile;

public class GameGUI implements Initializable{

	@FXML
	Canvas canvas1;
	
	@FXML
	Label highscore;
	
	
	private Main main;
	
	private GraphicsContext gc;
	
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
			main.getBoard().getProjectiles().forEach(projectile -> {
				gc.fillRect(projectile.getX()-3, projectile.getY()-6, 6, 12);
			});
		});
	}
	
	public void start() {
		Thread hilo = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(50/3);
					main.getBoard().getProjectiles().forEach(projectile -> {
						projectile.moveUp();
					});
					main.getBoard().updateProjectiles();
					main.getBoard().updatePlayer();
					paint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		hilo.start();
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
			Projectile projectile = new Projectile(main.getBoard().getPlayerShip().getX(), main.getBoard().getPlayerShip().getY(),1);
			main.getBoard().getProjectiles().add(projectile);
			break;
		
		default:
			break;
		}
	}
}
