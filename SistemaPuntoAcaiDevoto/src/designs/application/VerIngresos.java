package application;

import java.util.ArrayList;
import java.util.List;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerIngresos {

    @FXML
    private Label lblIdealMes;

    @FXML
    private Label lblRealAnio;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblIdelaDia;

    @FXML
    private Label lblRealDia;

    @FXML
    private TextField txtMes;

    @FXML
    private TextField txtAnio;

    @FXML
    private Label lblRealMes;

    @FXML
    private Label lblIdealAnio;

    @FXML
    private TextField txtDia;
    
    @FXML
    private Button btnVerIngresos;
    
    private List<Venta> cajas

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Menu Principal");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }
    

    @FXML
    void onVerIngresosClick(ActionEvent event) {
    	
    	if (Validaciones.validarCajasNumericas(generarListNumericos())) {
    		new Alerta().errorAlert("Puede este ingresando mal los datos", "Error en el ingreso de Datos");
    	}else {
    		mostrarIngresos();
    	}
    	
    	
    }
    
    public void mostrarIngresos() {
		ObtenerDatos obtenerDatos = new ObtenerDatos();
		productos = FXCollections.observableArrayList();
		productos = obtenerDatos.obtenerProductos();
    }
    
    public List<TextField> generarListNumericos() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtMes);
    	productosAValidar.add(txtAnio);
    	productosAValidar.add(txtDia);
    	
    	return productosAValidar;
    }

}
