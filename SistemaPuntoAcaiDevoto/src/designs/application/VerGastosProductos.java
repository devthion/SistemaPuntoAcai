package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import Gastos.GastosProductos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerGastosProductos implements Initializable {

    @FXML
    private TableColumn<GastosProductos, LocalDate> colFecha;

    @FXML
    private MenuItem slipOctubre;

    @FXML
    private Button btnEliminarGasto;
    
    @FXML
    private Button btnNuevoGasto;

    @FXML
    private MenuItem slipFebrero;

    @FXML
    private MenuItem slipMarzo;

    @FXML
    private MenuItem slipMayo;

    @FXML
    private MenuItem slipEnero;

    @FXML
    private Label lblGastosMes;

    @FXML
    private MenuItem slipSeptiembre;

    @FXML
    private TableColumn<GastosProductos, String> colDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private MenuItem slipJulio;

    @FXML
    private TableView<GastosProductos> tblGastos;

    @FXML
    private SplitMenuButton slipMenuMes;

    @FXML
    private Label lblGastosTotal;

    @FXML
    private MenuItem slipAbril;

    @FXML
    private Button btnEditarGasto;

    @FXML
    private MenuItem slipAgosto;

    @FXML
    private TextField txtAnio;

    @FXML
    private MenuItem slipJunio;

    @FXML
    private TableColumn<GastosProductos, Double> colCantidad;

    @FXML
    private MenuItem slipNoviembre;

    @FXML
    private MenuItem slipDiciembre;
    
    private ObservableList<GastosProductos> gastos;
    private ObservableList<GastosProductos> gastosPorMes;

    @FXML
    void onEliminarGastoClick(ActionEvent event) throws SQLException {
    	GastosProductos gasto = this.tblGastos.getSelectionModel().getSelectedItem();
    	
    	if(gasto==null) {
    		new Alerta().errorAlert("Debe seleccionar un Gasto", "Editar Gasto");
    	}else {
    		gasto.eliminarGasto();
    		new Alerta().errorAlert("Gasto Eliminado", "Eliminar Gasto");
    		
    		ObtenerDatos obtenerDatos = new ObtenerDatos();
			obtenerDatos = new ObtenerDatos();
			gastos = FXCollections.observableArrayList();
			gastos = obtenerDatos.obtenerGastosProductos();
			
			this.tblGastos.setItems(gastos);
			this.tblGastos.refresh();
			mostrarGastosPorMes(LocalDate.now().getMonthValue());
	    	lblGastosTotal.setText(gastos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
    	}
    }
    
    @FXML
    void onNuevoGastoClick(ActionEvent event) throws SQLException {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("GastoNuevo.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();

    		NuevoGastoController controller = loader.getController();
    		controller.initGuardarProductos();

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
    	gastos = obtenerDatos.obtenerGastosProductos();

    	this.tblGastos.setItems(gastos);
    	this.tblGastos.refresh();
    	mostrarGastosPorMes(LocalDate.now().getMonthValue());
    	lblGastosTotal.setText(gastos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");

    }

    @FXML
    void onEditarGastoClick(ActionEvent event) throws SQLException {
    	GastosProductos gasto = this.tblGastos.getSelectionModel().getSelectedItem();
    	
    	if(gasto==null) {
    		new Alerta().errorAlert("Debe seleccionar un Gasto", "Editar Gasto");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("EditarGasto.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			EditarGastoController controller = loader.getController();
    			controller.initEditarProductos(gasto);
    			
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
			gastos = obtenerDatos.obtenerGastosProductos();
			
			this.tblGastos.setItems(gastos);
			this.tblGastos.refresh();
			mostrarGastosPorMes(LocalDate.now().getMonthValue());
	    	lblGastosTotal.setText(gastos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
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
    void onEneroClick(ActionEvent event) {
    	mostrarGastosPorMes(1);
    }

    @FXML
    void onFebreroClick(ActionEvent event) {
    	mostrarGastosPorMes(2);
    }

    @FXML
    void onMarzoClick(ActionEvent event) {
    	mostrarGastosPorMes(3);
    }

    @FXML
    void onAbrilClick(ActionEvent event) {
    	mostrarGastosPorMes(4);
    }	
    
    @FXML
    void onMayoClick(ActionEvent event) {
    	mostrarGastosPorMes(5);
    }

    @FXML
    void onJunioClick(ActionEvent event) {
    	mostrarGastosPorMes(6);
    }

    @FXML
    void onJulioClick(ActionEvent event) {
    	mostrarGastosPorMes(7); 	
    }

    @FXML
    void onAgostoClick(ActionEvent event) {
    	mostrarGastosPorMes(8);
    }

    @FXML
    void onSeptiembreClick(ActionEvent event) {
    	mostrarGastosPorMes(9);
    }

    @FXML
    void onOctubreClick(ActionEvent event) {
    	mostrarGastosPorMes(10);
    }

    @FXML
    void onNoviembreClick(ActionEvent event) {
    	mostrarGastosPorMes(11);
    }

    @FXML
    void onDiciembreClick(ActionEvent event) {
    	mostrarGastosPorMes(12);
    }
    
    public void mostrarGastosPorMes(int mes) {
    	if(Validaciones.validarCajaNumerica(txtAnio)) {
    		 new Alerta().errorAlert("Debe ingresar un año valido", "Error de Datos");
    	}else{
    		gastosPorMes =gastos.filtered(unGasto-> unGasto.getFecha().getMonthValue()==mes && unGasto.getFecha().getYear()==Integer.parseInt(txtAnio.getText()));
        	this.tblGastos.setItems(gastosPorMes);
        	lblGastosMes.setText(gastosPorMes.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
    	}
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtAnio.setText(""+LocalDate.now().getYear());
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			gastos = FXCollections.observableArrayList();
			gastos = obtenerDatos.obtenerGastosProductos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.tblGastos.setItems(gastos);
		
		this.colDetalle.setCellValueFactory(new PropertyValueFactory<GastosProductos, String>("detalle"));
		this.colFecha.setCellValueFactory(new PropertyValueFactory<GastosProductos, LocalDate>("fecha"));
		this.colCantidad.setCellValueFactory(new PropertyValueFactory<GastosProductos, Double>("monto"));
		
		mostrarGastosPorMes(LocalDate.now().getMonthValue());
    	lblGastosTotal.setText(gastos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
		
	}

}
