package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import ConexionBD.ObtenerDatos;
import Ventas.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerVentas implements Initializable {

    @FXML
    private TableColumn<Venta, LocalDate> colFechaEntrega;

    @FXML
    private TableColumn<Venta, String> colHorario;

    @FXML
    private TableColumn<Venta, String> colDireccion;

    @FXML
    private TableColumn<Venta, LocalDate> colFechaRealizada;

    @FXML
    private TableColumn<Venta, Double> colPago;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnTerminarVenta;

    @FXML
    private TableView<Venta> tblVentas;

    @FXML
    private TableColumn<Venta, String> colCliente;

    @FXML
    private TableColumn<Venta, Double> colMontoTotal;
    
    private ObservableList<Venta> ventasPendientes;

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
    void onTerminarVentaClick(ActionEvent event) {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			ventasPendientes = FXCollections.observableArrayList();
			ventasPendientes = obtenerDatos.obtenerVentasPendientes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.tblVentas.setItems(ventasPendientes);
		
		this.colCliente.setCellValueFactory(new PropertyValueFactory<Venta, String>("datosCliente"));
		this.colFechaRealizada.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("fecha"));
		this.colFechaEntrega.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("FechaEntrega"));
		this.colMontoTotal.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_precioTotal"));
		this.colHorario.setCellValueFactory(new PropertyValueFactory<Venta, String>("Horario"));
		this.colDireccion.setCellValueFactory(new PropertyValueFactory<Venta, String>("DireccionCliente"));
		//this.colPago.setCellValueFactory(new PropertyValueFactory<Venta, String>("estaPagado"));
	}

}
