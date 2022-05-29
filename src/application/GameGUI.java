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
			PlayerShip ship = main.getBoard().getPlayerShip();
			
			gc.setFill(Color.YELLOW);
			gc.fillPolygon(new double[] {300,285,300,315}, new double[] {402,442,430,442}, 4);
		});
	}
	
	public void start() {
		Thread hilo = new Thread(() -> {
			while (true) {
				try {
					Thread.sleep(1000/50);
					main.getBoard().updatePlayer();
					paint();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
			Projectile projectile = new Projectile(main.getBoard().getPlayerShip().getX(),
													main.getBoard().getPlayerShip().getY());
			
			break;
		
		default:
			break;
		}
	}
}
