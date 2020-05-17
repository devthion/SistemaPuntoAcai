package Alertas;

import javafx.scene.control.Alert;

public class Alerta {
	
	public static void informationAlert(String mensaje, String titulo) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle(titulo);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
	
	public static void errorAlert(String mensaje, String titulo) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setTitle(titulo);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}
