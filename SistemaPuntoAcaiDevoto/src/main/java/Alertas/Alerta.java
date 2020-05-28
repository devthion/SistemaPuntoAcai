package Alertas;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

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
	
	public Optional<ButtonType> preguntaConfirmacion(String mensaje, String titulo) {
		
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setHeaderText(null);
    	alert.setTitle(titulo);
    	alert.setContentText(mensaje);
    	Optional<ButtonType> action = alert.showAndWait();
    	
    	DialogPane dialogPane = alert.getDialogPane();
    	dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
    	dialogPane.getStyleClass().add("myDialog");
    	
    	return action;
	}
}
