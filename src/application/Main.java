package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Board;

public class Main extends Application {

	private Board board;

	@Override
	public void start(Stage primaryStage) throws Exception {
		setBoard(new Board());
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/gameView.fxml"));
			BorderPane root = (BorderPane)loader.load();
			GameGUI controller = loader.getController();
			controller.setMain(this);
			controller.paint();
			Scene scene = new Scene(root);
			scene.setFill(Color.BLACK);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Space Invaders");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("PNGs/titleIcon.png")));
			primaryStage.show();
			controller.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
