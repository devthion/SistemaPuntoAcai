package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import ModeloInversion.IngresoDiario;
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

public class IngresosDiarios implements Initializable {

    @FXML
    private Button btnEditarIngreso;

    @FXML
    private TableColumn<IngresoDiario, LocalDate> colFecha;

    @FXML
    private Button btnNuevoIngreso;

    @FXML
    private TableColumn<IngresoDiario, String> colDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblIngresosTotales;

    @FXML
    private Button btnEliminarIngreso;

    @FXML
    private TableView<IngresoDiario> tblIngreso;

    @FXML
    private Label lblIngresosDiarios;

    @FXML
    private DatePicker dateGastoDiario;

    @FXML
    private TableColumn<IngresoDiario, Double> colCantidad;
    
    private ObservableList<IngresoDiario> ingresos;
    private ObservableList<IngresoDiario> ingresosPorDia;

    @FXML
    void onEliminarIngresoClick(ActionEvent event) throws SQLException {
    	IngresoDiario ingreso = this.tblIngreso.getSelectionModel().getSelectedItem();
    	
    	if(ingreso==null) {
    		new Alerta().errorAlert("Debe seleccionar un Ingreso", "Eliminar Ingreso Diario");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("¿Estas seguro que desea eliminar el Ingreso?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
	    		ingreso.eliminarIngreso();
	    		new Alerta().errorAlert("Ingreso Eliminado", "Eliminar Ingreso");
	    		
	    		ObtenerDatos obtenerDatos = new ObtenerDatos();
				obtenerDatos = new ObtenerDatos();
				ingresos = FXCollections.observableArrayList();
				ingresos = obtenerDatos.obtenerIngresosDiarios();
				
				this.tblIngreso.setItems(ingresos);
				this.tblIngreso.refresh();
				mostrarIngresosPorDia(LocalDate.now());
		    	lblIngresosTotales.setText(ingresos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
        	}
    	}
    }

    @FXML
    void onEditarIngresoClick(ActionEvent event) throws SQLException {
    	IngresoDiario ingreso = this.tblIngreso.getSelectionModel().getSelectedItem();
    	
    	if(ingreso==null) {
    		new Alerta().errorAlert("Debe seleccionar un Ingreso Diario", "Editar Ingreso");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("EditarIngresoDiario.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			EditarIngresoDiarioController controller = loader.getController();
    			controller.initEditar(ingreso);
    			
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Ingreso Diario");
    			stage.showAndWait();
    			
    			
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		ObtenerDatos obtenerDatos = new ObtenerDatos();
			obtenerDatos = new ObtenerDatos();
			ingresos = FXCollections.observableArrayList();
			ingresos = obtenerDatos.obtenerIngresosDiarios();
			
			this.tblIngreso.setItems(ingresos);
			mostrarIngresosPorDia(LocalDate.now());
			lblIngresosTotales.setText(ingresos.stream().mapToDouble(unaInversion-> unaInversion.getMonto()).sum()+" $");
    	}
    }

    @FXML
    void onVolverClick(ActionEvent event) {
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
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onSeleccionarDiaClick(ActionEvent event) {
    	mostrarIngresosPorDia(dateGastoDiario.getValue());
    }

    @FXML
    void onNuevoIngresoClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("NuevoIngresoDiario.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();
    		Scene scene = new Scene(root, 1300, 650);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.resizableProperty().setValue(Boolean.FALSE);
    		stage.setResizable(false);
    		stage.setTitle("Nuevo Ingreso");
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	Stage stage = (Stage) btnNuevoIngreso.getScene().getWindow();
    	stage.close();
    }
    
    public void mostrarIngresosPorDia(LocalDate date) {
    	ingresosPorDia =ingresos.filtered(unGasto-> unGasto.getFecha().equals(date) );
        this.tblIngreso.setItems(ingresosPorDia);
        lblIngresosDiarios.setText(ingresosPorDia.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			ingresos = FXCollections.observableArrayList();
			ingresos = obtenerDatos.obtenerIngresosDiarios();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.tblIngreso.setItems(ingresos);
		
		this.colDetalle.setCellValueFactory(new PropertyValueFactory<IngresoDiario, String>("detalle"));
		this.colFecha.setCellValueFactory(new PropertyValueFactory<IngresoDiario, LocalDate>("fecha"));
		this.colCantidad.setCellValueFactory(new PropertyValueFactory<IngresoDiario, Double>("monto"));
		
		mostrarIngresosPorDia(LocalDate.now());
    	lblIngresosTotales.setText(ingresos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
		
	}

}
