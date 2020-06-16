package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import Ventas.CajaCerrada;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerCajas implements Initializable{

    @FXML
    private TableView<CajaCerrada> tblCajas;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<CajaCerrada, Double> colMontoReal;

    @FXML
    private TableColumn<CajaCerrada, Double> colMontoIdeal;

    @FXML
    private Button btnEliminarCaja;

    @FXML
    private TableColumn<CajaCerrada, LocalDate> colFechaCaja;
    
    ObtenerDatos obtenerDatos;
    private ObservableList<CajaCerrada> cajas;

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
    void onEliminarCaja(ActionEvent event) throws SQLException {
    	CajaCerrada caja = this.tblCajas.getSelectionModel().getSelectedItem();
    	
    	if(caja==null) {
    		new Alerta().errorAlert("Debe seleccionar una Caja", "Cancelar Caja");
    	}else {
    		
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea eliminar la Caja ?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
        		caja.eliminarCaja();
        		new Alerta().informationAlert("Se ha eliminado la caja con exito", "Cancelar Caja");
        		try {
        			obtenerDatos = new ObtenerDatos();
        			cajas = FXCollections.observableArrayList();
        			cajas = obtenerDatos.obtenerCajasObs();
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}

        		
        		this.tblCajas.setItems(cajas);
        	}
    		
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			obtenerDatos = new ObtenerDatos();
			cajas = FXCollections.observableArrayList();
			cajas = obtenerDatos.obtenerCajasObs();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.tblCajas.setItems(cajas);
		
		this.colFechaCaja.setCellValueFactory(new PropertyValueFactory<CajaCerrada, LocalDate>("fecha"));
		this.colMontoIdeal.setCellValueFactory(new PropertyValueFactory<CajaCerrada, Double>("venta_ganancia"));
		this.colMontoReal.setCellValueFactory(new PropertyValueFactory<CajaCerrada, Double>("venta_precioTotal"));
		
	}
	


}
