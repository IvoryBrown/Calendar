package application.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/Sample.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setWidth(1300);
			primaryStage.setHeight(700);
			primaryStage.setTitle("Naptár");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application/view/calendar.png")));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {

	}

	public static void main(String[] args) {
		launch(args);
	}
}
