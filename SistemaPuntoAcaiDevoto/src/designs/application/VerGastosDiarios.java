package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import Gastos.GastosDiarios;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerGastosDiarios implements Initializable{

    @FXML
    private TableColumn<GastosDiarios, LocalDate> colFecha;

    @FXML
    private Label lblGastosTotales;

    @FXML
    private TableColumn<GastosDiarios, String> colDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnEliminarGasto;

    @FXML
    private Label lblGastosDiarios;

    @FXML
    private TableView<GastosDiarios> tblGastos;

    @FXML
    private Button btnEditarGasto;

    @FXML
    private DatePicker dateGastoDiario;

    @FXML
    private TableColumn<GastosDiarios, Double> colCantidad;
    
    @FXML
    private Button btnNuevoGasto;
    
    private ObservableList<GastosDiarios> gastos;
    private ObservableList<GastosDiarios> gastosPorDia;

    @FXML
    void onEliminarGastoClick(ActionEvent event) throws SQLException {
    	GastosDiarios gasto = this.tblGastos.getSelectionModel().getSelectedItem();
    	
    	if(gasto==null) {
    		new Alerta().errorAlert("Debe seleccionar un Gasto", "Editar Gasto");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("¿Estas seguro que desea eliminar el gasto?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
	    		gasto.eliminarGasto();
	    		new Alerta().errorAlert("Gasto Eliminado", "Eliminar Gasto");
	    		
	    		ObtenerDatos obtenerDatos = new ObtenerDatos();
				obtenerDatos = new ObtenerDatos();
				gastos = FXCollections.observableArrayList();
				gastos = obtenerDatos.obtenerGastosDiarios();
				
				this.tblGastos.setItems(gastos);
				this.tblGastos.refresh();
				mostrarGastosPorDia(LocalDate.now());
		    	lblGastosTotales.setText(gastos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
        	}
    	}
    }

    @FXML
    void onEditarGastoClick(ActionEvent event) throws SQLException {
    	GastosDiarios gasto = this.tblGastos.getSelectionModel().getSelectedItem();
    	
    	if(gasto==null) {
    		new Alerta().errorAlert("Debe seleccionar un Gasto", "Editar Gasto");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("EditarGasto.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			EditarGastoController controller = loader.getController();
    			controller.initEditarDiarios(gasto);
    			
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Gastos");
    			stage.showAndWait();
    			
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		ObtenerDatos obtenerDatos = new ObtenerDatos();
			obtenerDatos = new ObtenerDatos();
			gastos = FXCollections.observableArrayList();
			gastos = obtenerDatos.obtenerGastosDiarios();
			
			this.tblGastos.setItems(gastos);
			this.tblGastos.refresh();
			mostrarGastosPorDia(LocalDate.now());
	    	lblGastosTotales.setText(gastos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
    	}
    }

    @FXML
    void onVolverClick(ActionEvent event) {
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
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onSeleccionarDiaClick(ActionEvent event) {
    	mostrarGastosPorDia(dateGastoDiario.getValue());
    }
    
    public void mostrarGastosPorDia(LocalDate date) {
    	gastosPorDia =gastos.filtered(unGasto-> unGasto.getFecha().equals(date) );
        this.tblGastos.setItems(gastosPorDia);
        lblGastosDiarios.setText(gastosPorDia.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");	
    }
    
    @FXML
    void onNuevoGastoClick(ActionEvent event) throws SQLException {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("GastoNuevo.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();

    		NuevoGastoController controller = loader.getController();
    		controller.initGuardarDiarios();

    		Scene scene = new Scene(root,1300,650);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.resizableProperty().setValue(Boolean.FALSE);
    		stage.setResizable(false);
    		stage.setTitle("Gastos");
    		stage.showAndWait();


    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	ObtenerDatos obtenerDatos = new ObtenerDatos();
    	obtenerDatos = new ObtenerDatos();
    	gastos = FXCollections.observableArrayList();
    	gastos = obtenerDatos.obtenerGastosDiarios();

    	this.tblGastos.setItems(gastos);
    	this.tblGastos.refresh();
		mostrarGastosPorDia(LocalDate.now());
    	lblGastosTotales.setText(gastos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			gastos = FXCollections.observableArrayList();
			gastos = obtenerDatos.obtenerGastosDiarios();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.tblGastos.setItems(gastos);
		
		this.colDetalle.setCellValueFactory(new PropertyValueFactory<GastosDiarios, String>("detalle"));
		this.colFecha.setCellValueFactory(new PropertyValueFactory<GastosDiarios, LocalDate>("fecha"));
		this.colCantidad.setCellValueFactory(new PropertyValueFactory<GastosDiarios, Double>("monto"));
		
		mostrarGastosPorDia(LocalDate.now());
    	lblGastosTotales.setText(gastos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
	}

}
