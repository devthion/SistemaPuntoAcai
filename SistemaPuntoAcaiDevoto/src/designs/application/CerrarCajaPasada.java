package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import Ventas.CajaCerrada;
import Ventas.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CerrarCajaPasada implements Initializable {

    @FXML
    private TableColumn<Venta, LocalDate> colFecha;

    @FXML
    private TextField txtMontoReal;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtMontoIdeal;

    @FXML
    private Button btnCerrarCaja;

    @FXML
    private TableColumn<Venta, Double> colGanancia;

    @FXML
    private TableView<Venta> tblVentas;

    @FXML
    private TableColumn<Venta, String> colCliente;

    @FXML
    private TableColumn<Venta, Double> colMontoTotal;

    @FXML
    private DatePicker dateFechaCaja;

    @FXML
    private Button btnBuscarVentas;
    
    @FXML
    private Button btnEliminarVenta;
    
    private ObservableList<Venta> ventas;
    
    private CajaCerrada cajaDelDia;
    
	ObtenerDatos obtenerDatos;

    @FXML
    void onCerrarCajaClick(ActionEvent event) throws SQLException {
    	if (Validaciones.validarCajaNumerica(txtMontoReal)) {
    		new Alerta().errorAlert("Ingreso un montoReal no Valido", "Error en el ingreso de Datos");
    	}else {
    		cajaDelDia = new CajaCerrada(dateFechaCaja.getValue(),Double.parseDouble(txtMontoReal.getText()) ,Double.parseDouble(txtMontoIdeal.getText()));
    		cajaDelDia.almacenarCajaCerrada();
    		
    		new Alerta().informationAlert("Caja Cerrada Correctamente","Cierre de Caja");
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
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("CerrarCajaMenu.fxml"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

		try {
			obtenerDatos = new ObtenerDatos();
			ventas = FXCollections.observableArrayList();
			dateFechaCaja.setValue(LocalDate.now());
			ventas = obtenerDatos.obtenerVentasDeldia(dateFechaCaja.getValue());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.tblVentas.setItems(ventas);
		
		this.colCliente.setCellValueFactory(new PropertyValueFactory<Venta, String>("datosCliente"));
		this.colFecha.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("fecha"));
		this.colGanancia.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_ganancia"));
		this.colMontoTotal.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_precioTotal"));
		
		txtMontoIdeal.setText(""+ventas.stream().mapToDouble(unaVenta-> unaVenta.getVenta_precioTotal()).sum());
		
	}
	
    @FXML
    void onBuscarVentasClick(ActionEvent event) throws SQLException {
    	
    	List<CajaCerrada> cajas = new ArrayList<>();
    	
    	ObtenerDatos obtenerDatos = new ObtenerDatos();
		cajas = obtenerDatos.obtenerCajasCerradas();
		
		
    	if(cajas.stream().anyMatch(unaCaja -> unaCaja.getFecha().equals(dateFechaCaja.getValue()))) {
    		new Alerta().errorAlert("Ya se cerro la caja del dia: "+dateFechaCaja.getValue(), "Caja Cerrada");
    	}else {
    		try {
    			obtenerDatos = new ObtenerDatos();
    			ventas = FXCollections.observableArrayList();
    			ventas = obtenerDatos.obtenerVentasDeldia(dateFechaCaja.getValue());
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		this.tblVentas.setItems(ventas);
    		
    		this.colCliente.setCellValueFactory(new PropertyValueFactory<Venta, String>("datosCliente"));
    		this.colFecha.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("fecha"));
    		this.colGanancia.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_ganancia"));
    		this.colMontoTotal.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_precioTotal"));
    		
    		txtMontoIdeal.setText(""+ventas.stream().mapToDouble(unaVenta-> unaVenta.getVenta_precioTotal()).sum());
	    }
    }
    
    @FXML
    void onEliminarVentaClick(ActionEvent event) throws SQLException {
    	Venta venta = this.tblVentas.getSelectionModel().getSelectedItem();
    	
    	if(venta==null) {
    		new Alerta().errorAlert("Debe seleccionar una Venta", "Cancelar Venta");
    	}else {
    		
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea cancelar la Venta ?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
        		System.out.println(venta.getVenta_id());
        		venta.cancelarVenta();
        		new Alerta().informationAlert("Se ha cancelado la venta con exito", "Cancelar Venta");
        		try {
        			obtenerDatos = new ObtenerDatos();
        			ventas = FXCollections.observableArrayList();
        			ventas = obtenerDatos.obtenerVentasDeldia(dateFechaCaja.getValue());
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}

        		
        		this.tblVentas.setItems(ventas);
        	}
    		
    	}
    }

}
