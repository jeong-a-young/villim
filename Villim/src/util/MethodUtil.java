package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MethodUtil {

	public void changeScene(String url, Button btn) {
		try {
			Parent main = FXMLLoader.load(getClass().getResource(url));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changePartScene(String url, BorderPane pane) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(url));
			pane.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void popUpScene(Button btn, Stage stage, String url, String title) {

		Stage mainStage = (Stage) btn.getScene().getWindow();
		stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(mainStage);

		try {
			Parent root = FXMLLoader.load(getClass().getResource(url));

			Scene sc = new Scene(root);
			sc.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage.setScene(sc);
			stage.setResizable(false);
			stage.setTitle(title);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
