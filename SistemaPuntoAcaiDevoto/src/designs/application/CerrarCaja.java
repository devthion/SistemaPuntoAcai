package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import Gastos.GastosDiarios;
import ManejoArchivos.ExportarExcel;
import ModeloInversion.IngresoDiario;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CerrarCaja implements Initializable{

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
    private Button btnEliminarVenta;

    @FXML
    private TableColumn<Venta, Double> colMontoTotal;
    
    @FXML
    private TableColumn<Venta, String> colDireccionCliente;
    
    @FXML
    private TableView<GastosDiarios> tblGastos;
    
    @FXML
    private TableColumn<GastosDiarios, String> colDetalle;
    
    @FXML
    private TableColumn<GastosDiarios, Double> colCantidad;

    @FXML
    private TableColumn<GastosDiarios, LocalDate> colFechaGasto;
    
    @FXML
    private TableView<IngresoDiario> tblIngresos;
    
    @FXML
    private TableColumn<IngresoDiario, Double> colCantidadIng;
    
    @FXML
    private TableColumn<IngresoDiario, LocalDate> colFechaIng;
    
    @FXML
    private TableColumn<IngresoDiario, String> colDetalleIng;
    
    private ObservableList<Venta> ventas;
    private ObservableList<GastosDiarios> gastos;
    private ObservableList<GastosDiarios> gastosPorDia;
    private ObservableList<IngresoDiario> ingresos;
    private ObservableList<IngresoDiario> ingresosPorDia;
    
    private CajaCerrada cajaDelDia;
    
	ObtenerDatos obtenerDatos;
    

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

    @FXML
    void onCerrarCajaClick(ActionEvent event) throws SQLException, IOException {
    	
    	if (Validaciones.validarCajaNumerica(txtMontoReal)) {
    		new Alerta().errorAlert("Ingreso un montoReal no Valido", "Error en el ingreso de Datos");
    	}else {
    		cajaDelDia = new CajaCerrada(LocalDate.now(),Double.parseDouble(txtMontoReal.getText()) ,Double.parseDouble(txtMontoIdeal.getText()));
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
        	
        	ExportarExcel.exportar();
        	new Alerta().informationAlert("Se ha exportar el Excel en la Carpeta Sistema Acai con Exito", "Exportar Excel");
        	
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			obtenerDatos = new ObtenerDatos();
			ventas = FXCollections.observableArrayList();
			ventas = obtenerDatos.obtenerVentasDeldia(LocalDate.now());
			gastos = FXCollections.observableArrayList();
			gastos = obtenerDatos.obtenerGastosDiarios();
			ingresos = FXCollections.observableArrayList();
			ingresos = obtenerDatos.obtenerIngresosDiarios();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.tblVentas.setItems(ventas);
		
		this.colCliente.setCellValueFactory(new PropertyValueFactory<Venta, String>("datosCliente"));
		this.colFecha.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("fecha"));
		this.colGanancia.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_ganancia"));
		this.colMontoTotal.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_precioTotal"));
		this.colDireccionCliente.setCellValueFactory(new PropertyValueFactory<Venta, String>("direccionCliente"));
		
		gastosPorDia =gastos.filtered(unGasto-> unGasto.getFecha().equals(LocalDate.now()));
        this.tblGastos.setItems(gastosPorDia);
		
		this.colDetalle.setCellValueFactory(new PropertyValueFactory<GastosDiarios, String>("detalle"));
		this.colFechaGasto.setCellValueFactory(new PropertyValueFactory<GastosDiarios, LocalDate>("fecha"));
		this.colCantidad.setCellValueFactory(new PropertyValueFactory<GastosDiarios, Double>("monto"));
		
		ingresosPorDia =ingresos.filtered(unGasto-> unGasto.getFecha().equals(LocalDate.now()));
        this.tblIngresos.setItems(ingresosPorDia);
		
		this.colDetalleIng.setCellValueFactory(new PropertyValueFactory<IngresoDiario, String>("detalle"));
		this.colFechaIng.setCellValueFactory(new PropertyValueFactory<IngresoDiario, LocalDate>("fecha"));
		this.colCantidadIng.setCellValueFactory(new PropertyValueFactory<IngresoDiario, Double>("monto"));
		
		txtMontoIdeal.setText(""+(ventas.stream().mapToDouble(unaVenta-> unaVenta.getVenta_precioTotal()).sum() 
				- (gastosPorDia.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()) + 
				ingresosPorDia.stream().mapToDouble(unIngreso-> unIngreso.getMonto()).sum()) );
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
        			ventas = obtenerDatos.obtenerVentasDeldia(LocalDate.now());
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}		
        		this.tblVentas.setItems(ventas);
        	}
    		
    	}
    }

}
