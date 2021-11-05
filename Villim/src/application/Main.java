package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {			
			 		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Start_Layout.fxml"));
			Font.loadFont(getClass().getResourceAsStream("/resources/KoPubWorld_Dotum_Bold.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("/resources/KoPubWorld_Dotum_Light.ttf"), 10);
			Font.loadFont(getClass().getResourceAsStream("/resources/KoPubWorld_Dotum_Medium.ttf"), 10);
			AnchorPane mainLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(mainLayout);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setTitle("Villim");
			primaryStage.setScene(scene);
			primaryStage.setMaxHeight(900);
			primaryStage.setMaxWidth(1600);
			primaryStage.setMinHeight(900);
			primaryStage.setMinWidth(1600);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public static void main(String[] args) {
		launch(args);
	}
}
