package application;

import java.sql.SQLException;

import Alertas.Alerta;
import ConexionBD.CrearArchivoH2;
import ConexionBD.CrearTabla;
import ConexionBD.DropTable;
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
    private Button btnEliminarTablas;
    
    @FXML
    private Button btnTraerDatos;

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
    	new Alerta().informationAlert("La BD ha sido creado con Exito", "Creacion BD");
    }

    @FXML
    void onCrearTablasClcik(ActionEvent event) {
    	CrearTabla.crearTablaProducto();
    	CrearTabla.crearTablaCliente();
    	CrearTabla.crearTablaVenta();
    	CrearTabla.crearTablaItem_Venta();
    	CrearTabla.crearTablaGasto();
    	
    	new Alerta().informationAlert("Las tablas de la BD han sido creadas con Exito", "Creacion Tablas");
    }
    
    @FXML
    void onEliminarTablas(ActionEvent event) {
    	DropTable.main();
    	new Alerta().informationAlert("Las tablas de la BD han sido eliminadas con Exito", "Eliminacion Tablas");
    }
    
    @FXML
    void onTraerDatosClick(ActionEvent event) {
    	//DragAndDrop;
    }

}
