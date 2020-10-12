package application;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import com.itextpdf.text.DocumentException;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import ManejoArchivos.ExportarPdf;
import ManejoArchivos.Ticket;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerVentas implements Initializable {

    @FXML
    private TableColumn<Venta, LocalDate> colFechaEntrega;

    @FXML
    private TableColumn<Venta, String> colHorario;
    
    @FXML
    private Button btnRealizarTodas;

    @FXML
    private TableColumn<Venta, String> colDireccion;

    @FXML
    private TableColumn<Venta, LocalDate> colFechaRealizada;
    
    @FXML
    private TableColumn<Venta, String> colTipoPago;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnTerminarVenta;
    
    @FXML
    private Button btnCancelarVenta;

    @FXML
    private TableView<Venta> tblVentas;
    
    @FXML
    private Button btnImprimirTicket;

    @FXML
    private TableColumn<Venta, String> colCliente;
    
    @FXML
    private TableColumn<Venta, Double> colEnvio;

    @FXML
    private TableColumn<Venta, Double> colMontoTotal;
    
    @FXML
    private Label lblItems;
    
    @FXML
    private Button btnGenerarRemito;
    
    private ObservableList<Venta> ventasPendientes;
    
    @FXML
    private Label lblObservacion;
    
    private ObtenerDatos obtenerDatos;
    
    String productos;

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
    void onTerminarVentaClick(ActionEvent event) throws SQLException {
    	Venta venta = this.tblVentas.getSelectionModel().getSelectedItem();
    	
    	if(venta==null) {
    		new Alerta().errorAlert("Debe seleccionar una Venta", "Terminar Venta");
    	}else {
    		venta.concretarVenta();
    		new Alerta().informationAlert("Se ha cambiado el estado de la venta con exito", "Terminar Venta");
    		RefrescarTabla();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		RefrescarTabla();
		
		this.colCliente.setCellValueFactory(new PropertyValueFactory<Venta, String>("datosCliente"));
		this.colFechaRealizada.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("fecha"));
		this.colFechaEntrega.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("FechaEntrega"));
		this.colMontoTotal.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_precioTotal"));
		this.colHorario.setCellValueFactory(new PropertyValueFactory<Venta, String>("Horario"));
		this.colDireccion.setCellValueFactory(new PropertyValueFactory<Venta, String>("DireccionCliente"));
		this.colEnvio.setCellValueFactory(new PropertyValueFactory<Venta, Double>("PrecioEnvio"));
		this.colTipoPago.setCellValueFactory(new PropertyValueFactory<Venta, String>("TipoDePago"));
		//this.colPago.setCellValueFactory(new PropertyValueFactory<Venta, String>("estaPagado"));
		
		lblItems.setMinWidth(600);
		lblItems.setMinHeight(5);
	    
		tblVentas.setOnMouseClicked((MouseEvent eve) ->{ 
			productos = "";
			Venta venta = this.tblVentas.getSelectionModel().getSelectedItem();
			venta.getItems().forEach(unItem -> productos += unItem.getNombreProducto()+" \nCantidad: "+unItem.getCantidad()+" \nPrecio: "+unItem.getItemPrecio()+" \n---------------------------------\n");
			lblItems.setText(productos);
			lblObservacion.setText(""+venta.getObservacion());
		});
	}
	
	public void RefrescarTabla() {
		try {
			obtenerDatos = new ObtenerDatos();
			ventasPendientes = FXCollections.observableArrayList();
			ventasPendientes = obtenerDatos.obtenerVentasPendientes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.tblVentas.setItems(ventasPendientes);
		
	}
	

    @FXML
    void onCancelarVentaClick(ActionEvent event) throws SQLException {
    	Venta venta = this.tblVentas.getSelectionModel().getSelectedItem();
    	
    	if(venta==null) {
    		new Alerta().errorAlert("Debe seleccionar una Venta", "Cancelar Venta");
    	}else {
    		
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea cancelar la Venta ?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
        		
        		venta.cancelarVenta();
        		new Alerta().informationAlert("Se ha cancelado la venta con exito", "Cancelar Venta");
        		RefrescarTabla();
        	}
    		
    	}
    }
    
    @FXML
    void onGenerarRemitoClick(ActionEvent event) throws SQLException {
    	Venta venta = this.tblVentas.getSelectionModel().getSelectedItem();
    	
    	if(venta==null) {
    		new Alerta().errorAlert("Debe seleccionar una Venta", "Generar Remito");
    	}else {
			ExportarPdf exportarPdf = new ExportarPdf();
			new Alerta().informationAlert("El remito fue guardado en la carpeta remitos", "Remito");
			try {
				exportarPdf.exportar(venta);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
    }
    
    @FXML
    void onRealizarTodasClick(ActionEvent event) {

    }
    

    @FXML
    void onImprimirTicketClick(ActionEvent event) {
    	Venta venta = this.tblVentas.getSelectionModel().getSelectedItem();
    	
    	if(venta==null) {
    		new Alerta().errorAlert("Debe seleccionar una Venta", "Generar Remito");
    	}else {
			Ticket ticket = new Ticket();
	        ticket.obtenerTicketVenta(venta);
			new Alerta().informationAlert("El Ticket ha sido impreso y exportado a la carpeta de Tickets", "Generar Ticket");
		}
    }

}
