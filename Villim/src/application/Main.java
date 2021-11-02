package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {			
			 		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Main_Layout.fxml"));

			AnchorPane mainLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(mainLayout);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setTitle("Villim");
			primaryStage.setScene(scene);
			primaryStage.setMaxHeight(700);
			primaryStage.setMaxWidth(1200);
			primaryStage.setMinHeight(700);
			primaryStage.setMinWidth(1200);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
