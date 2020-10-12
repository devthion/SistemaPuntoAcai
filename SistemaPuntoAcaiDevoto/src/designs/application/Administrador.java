package application;

import java.sql.SQLException;

import Alertas.Alerta;
import ConexionBD.CrearArchivoH2;
import ConexionBD.CrearTabla;
import ConexionBD.DropTable;
import ConexionBD.ModificarDatos;
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
    private Button btnModificarDirecciones;

    @FXML
    private Button btnAgregarInversiones;

    @FXML
    private Button btnAgregarDescripcion;

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Ingreso.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
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
		CrearTabla.crearTablaGastoDiario();
		CrearTabla.crearTablaGastoGeneral();
		CrearTabla.crearTablaGastoProducto();
		CrearTabla.crearTablaCajaCerrada();
		CrearTabla.crearTablaInversion();
		CrearTabla.crearTablaPropina();
		CrearTabla.crearTablaDispositivo();
		CrearTabla.crearTablaEgreso();
		CrearTabla.crearTablaIngresoDiario();
		CrearTabla.crearTablaCombo();
    	new Alerta().informationAlert("Las tablas de la BD han sido creadas con Exito", "Creacion Tablas");
    }
    
    @FXML
    void onEliminarTablas(ActionEvent event) {
    	DropTable.dropTablaGasto();;
    	new Alerta().informationAlert("Las tabla GASTO ha sido eliminada con Exito", "Eliminacion Tablas");
    }
    
    @FXML
    void onTraerDatosClick(ActionEvent event) {
    	//DragAndDrop;
    }
    
    @FXML
    void onModificarDireccionesClick(ActionEvent event) {
		try {	
			new ModificarDatos().cambiarDirecciones();
			new Alerta().informationAlert("Direcciones Modificadas", "EXITO!");
		} catch (Exception e) {
		// TODO: handle exception
		System.out.println("ERROR");
	}
    }

    @FXML
    void onAgregarInversionesClick(ActionEvent event) {
    	CrearTabla.crearTablaInversion();
    }

    @FXML
    void onAgregarDescripcionClick(ActionEvent event) {
		try {
			new ModificarDatos().agregarDpto();
			new ModificarDatos().agregarDeudaCliente();
			new ModificarDatos().inicializarDeudaCliente();
			new ModificarDatos().agregarTipoDeVenta();
			new ModificarDatos().agregarCalleEnvio();
			new ModificarDatos().agregarNumeroEnvio();
			new ModificarDatos().agregarDptoEnvio();
			new ModificarDatos().agregarBarrioEnvio();
			new ModificarDatos().cambiarNumeroTelefonico();
			new Alerta().informationAlert("Cambios en BD", "EXITO!");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR");
		}
    }

}
