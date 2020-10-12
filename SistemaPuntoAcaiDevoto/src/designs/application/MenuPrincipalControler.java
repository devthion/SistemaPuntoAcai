package application;

import java.sql.SQLException;
import java.util.Optional;

import Alertas.Alerta;
import ManejoArchivos.ConfigurarImpresora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuPrincipalControler {

    @FXML
    private Button btnVerClientes;
    
    @FXML
    private Button btnVerProductos;
    
    @FXML
    private Button btnVerVentas;
    
    @FXML
    private Button btnAjustes;

    @FXML
    private Button btnNuevaVenta;
    
    @FXML
    private Button btnIngresados;
    
    @FXML
    private Button btnSalir;
    
    @FXML
    private Button btnGenerarExcel;

    @FXML
    private Button btnEstadisticas;
    
    @FXML
    private Button btnCostos;
    
    @FXML
    private Button btnIngresos;
    
    @FXML
    private Button btnCerraCaja;

    @FXML
    void verClientes(ActionEvent event) {
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
    	Stage stage = (Stage) btnVerClientes.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void onVerProductosClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Productos.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Producto");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVerProductos.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void onVerVentasClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("VerVentas.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Ventas");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVerVentas.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onNuevaVentaClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("NuevaVenta.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Venta");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnNuevaVenta.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void onEstadisticasClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Estadisticas.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Estadisticas");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnEstadisticas.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void onCostosClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Gastos.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Gastos");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnCostos.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onSalirClick(ActionEvent event) {
    	Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("¿Estas seguro que desea salir del sistema?", "Confirmación");
    	if (action.get() == ButtonType.OK) {
    			System.exit(0);
    		
    	}
    	
    }
    

    @FXML
    void onCerrarCajaClick(ActionEvent event) throws SQLException {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("CerrarCajaMenu.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Cerrar Caja");
    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        	Stage stage = (Stage) btnCostos.getScene().getWindow();
        	stage.close();	
    
    }
    
    @FXML
    void onIngresosClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("VerIngresos.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Ingresos");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnCostos.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void onInversionClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Ingresos.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Ingresos");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnCostos.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void onAjustesClick(ActionEvent event) {
    	ConfigurarImpresora configurarImpresora = new ConfigurarImpresora();
    	configurarImpresora.settearImpresora();
    }
}
