package Alertas;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
	
	public static Optional<ButtonType> preguntaConfirmacion(String mensaje, String titulo) {
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setHeaderText(null);
    	alert.setTitle(titulo);
    	alert.setContentText(mensaje);
    	Optional<ButtonType> action = alert.showAndWait();
    	
    	return action;
	}
}
