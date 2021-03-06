package application.disposition.main;

import application.controller.CalendarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DispositionMain {
	
	
	public void start() {
		
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/disposition/view/Disposition.fxml"));
			Parent root = (Parent) loader.load();
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setOnCloseRequest(evt -> {
		        // prevent window from closing
		        evt.consume();

		        // execute own shutdown procedure
		        shutdown(primaryStage);
		    });
			primaryStage.setWidth(1300);
			primaryStage.setHeight(800);
			primaryStage.setTitle("Beosztás");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/application/view/calendar.png")));
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void shutdown(Stage mainWindow) {
	   System.out.println("Bez");
	        mainWindow.close();
	    }
	

}
