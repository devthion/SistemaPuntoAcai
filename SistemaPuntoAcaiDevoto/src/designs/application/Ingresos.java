package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Ingresos {

	@FXML
	private Button btnVolver;

	@FXML
	private Button btnVerIngresos;
	
    @FXML
    private Button btnPropinas;

	@FXML
	void onVerIngresosClick(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("VerInversiones.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root, 1300, 650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Inversiones");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) btnVolver.getScene().getWindow();
		stage.close();
	}
	

    @FXML
    void onPropinasClick(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Propinas.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root, 1300, 650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Propinas");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) btnPropinas.getScene().getWindow();
		stage.close();
    }

	@FXML
	void onVolverClick(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root, 1300, 650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Menu Principal");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Stage stage = (Stage) btnVolver.getScene().getWindow();
		stage.close();
	}

}
