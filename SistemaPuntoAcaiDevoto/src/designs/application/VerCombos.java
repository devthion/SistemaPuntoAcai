package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerCombos {

    @FXML
    private Button btnEliminarCombo;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnNuevoCombo;

    @FXML
    private TableColumn<?, ?> colPrecio;

    @FXML
    private Button btnEditarCombo;

    @FXML
    private TableView<?> tblCombos;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("VerClientes.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Clientes");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onNuevoComboClick(ActionEvent event) {

    }

    @FXML
    void onEditarComboClick(ActionEvent event) {

    }

    @FXML
    void onEliminarComboClick(ActionEvent event) {

    }

}
