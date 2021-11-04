package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {

	public static void warningAlert(String contentText, String headerText) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Error");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);

		alert.show();
	}
	
	public static void informationAlert(String contentText, String headerText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("INFORMATION");
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);

		alert.show();
	}	
}