package application;

import java.sql.SQLException;

import ConexionBD.CrearArchivoH2;
import ConexionBD.CrearTabla;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Administrador {

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnCrearBd;

    @FXML
    private Button btnCrearTablas;

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Ingreso.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Ingreso al Sistema");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onCrearBDClick(ActionEvent event) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
    	CrearArchivoH2.crearArchivo();
    }

    @FXML
    void onCrearTablasClcik(ActionEvent event) {
    	CrearTabla.crearTablaProducto();
    	CrearTabla.crearTablaCliente();
    	CrearTabla.crearTablaVenta();
    	CrearTabla.crearTablaItem_Venta();
    	CrearTabla.crearTablaGasto();
    }

}
