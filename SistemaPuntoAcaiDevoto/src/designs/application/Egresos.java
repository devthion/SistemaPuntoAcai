package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import Egresos.Egreso;
import Gastos.GastosGenerales;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Egresos implements Initializable{

    @FXML
    private TableColumn<Egreso, LocalDate> colFecha;

    @FXML
    private MenuItem slipOctubre;

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
    private Button btnEditarEgreso;

    @FXML
    private MenuItem slipJulio;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Egreso, String> colDetalle;

    @FXML
    private SplitMenuButton slipMenuMes;

    @FXML
    private TableView<Egreso> tblEgresos;

    @FXML
    private MenuItem slipAbril;

    @FXML
    private Label lblGastosTotal;

    @FXML
    private TextField txtAnio;

    @FXML
    private MenuItem slipAgosto;

    @FXML
    private MenuItem slipJunio;

    @FXML
    private Button btnNuevoEgreso;

    @FXML
    private MenuItem slipNoviembre;

    @FXML
    private TableColumn<Egreso, Double> colCantidad;

    @FXML
    private Button btnEliminarEgreso;

    @FXML
    private MenuItem slipDiciembre;
    
    private ObservableList<Egreso> egresos;
    private ObservableList<Egreso> egresosPorMes;

    @FXML
    void onEneroClick(ActionEvent event) {
    	mostrarEgresosPorMes(1);
    }

    @FXML
    void onFebreroClick(ActionEvent event) {
    	mostrarEgresosPorMes(2);
    }

    @FXML
    void onMarzoClick(ActionEvent event) {
    	mostrarEgresosPorMes(3);
    }

    @FXML
    void onAbrilClick(ActionEvent event) {
    	mostrarEgresosPorMes(4);
    }	
    
    @FXML
    void onMayoClick(ActionEvent event) {
    	mostrarEgresosPorMes(5);
    }

    @FXML
    void onJunioClick(ActionEvent event) {
    	mostrarEgresosPorMes(6);
    }

    @FXML
    void onJulioClick(ActionEvent event) {
    	mostrarEgresosPorMes(7); 	
    }

    @FXML
    void onAgostoClick(ActionEvent event) {
    	mostrarEgresosPorMes(8);
    }

    @FXML
    void onSeptiembreClick(ActionEvent event) {
    	mostrarEgresosPorMes(9);
    }

    @FXML
    void onOctubreClick(ActionEvent event) {
    	mostrarEgresosPorMes(10);
    }

    @FXML
    void onNoviembreClick(ActionEvent event) {
    	mostrarEgresosPorMes(11);
    }

    @FXML
    void onDiciembreClick(ActionEvent event) {
    	mostrarEgresosPorMes(12);
    }
    
    public void mostrarEgresosPorMes(int mes) {
    	if(Validaciones.validarCajaNumerica(txtAnio)) {
    		 new Alerta().errorAlert("Debe ingresar un año valido", "Error de Datos");
    	}else{
    		egresosPorMes =egresos.filtered(unGasto-> unGasto.getFecha().getMonthValue()==mes && unGasto.getFecha().getYear()==Integer.parseInt(txtAnio.getText()));
        	this.tblEgresos.setItems(egresosPorMes);
        	lblGastosMes.setText(egresosPorMes.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
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
    void onEditarGastoClick(ActionEvent event) {
    	Egreso egreso = this.tblEgresos.getSelectionModel().getSelectedItem();
    	
    	if(egreso==null) {
    		new Alerta().errorAlert("Debe seleccionar un Gasto", "Editar Gasto");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("EditarGasto.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			EditarEgreso controller = loader.getController();
    			controller.initEditarEgresos(egreso);
    			
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Egresos");
    			stage.showAndWait();
    			
    			
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		ObtenerDatos obtenerDatos = new ObtenerDatos();
			obtenerDatos = new ObtenerDatos();
			egresos = FXCollections.observableArrayList();
			egresos = obtenerDatos.obtenerEgresos();
			
			this.tblEgresos.setItems(egresos);
			this.tblEgresos.refresh();
			mostrarEgresosPorMes(LocalDate.now().getMonthValue());
	    	lblGastosTotal.setText(egresos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
    	}
    }

    @FXML
    void onEliminarGastoClick(ActionEvent event) throws SQLException {
    	Egreso egreso = this.tblEgresos.getSelectionModel().getSelectedItem();
    	
    	if(egreso==null) {
    		new Alerta().errorAlert("Debe seleccionar un Egreso", "Editar Egreso");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("¿Estas seguro que desea elminar el egreso?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
	    		try {
	    			
	    			egreso.eliminarEgreso();
	    			new Alerta().errorAlert("Egreso Eliminado", "Eliminar Egreso");
	    			
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    		}
	    		ObtenerDatos obtenerDatos = new ObtenerDatos();
				obtenerDatos = new ObtenerDatos();
				egresos = FXCollections.observableArrayList();
				egresos = obtenerDatos.obtenerEgresos();
				
				this.tblEgresos.setItems(egresos);
				this.tblEgresos.refresh();
				mostrarEgresosPorMes(LocalDate.now().getMonthValue());
		    	lblGastosTotal.setText(egresos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
        	}
    	}
    }

    @FXML
    void onNuevoGastoClick(ActionEvent event) throws SQLException {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("EgresoNuevo.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();

    		NuevoEgreso controller = loader.getController();
    		controller.initGuardarEgreso();

    		Scene scene = new Scene(root,1300,650);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.resizableProperty().setValue(Boolean.FALSE);
    		stage.setResizable(false);
    		stage.setTitle("Egresos");
    		stage.showAndWait();


    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	ObtenerDatos obtenerDatos = new ObtenerDatos();
    	obtenerDatos = new ObtenerDatos();
    	egresos = FXCollections.observableArrayList();
    	egresos = obtenerDatos.obtenerEgresos();

    	this.tblEgresos.setItems(egresos);
    	this.tblEgresos.refresh();
    	mostrarEgresosPorMes(LocalDate.now().getMonthValue());
    	lblGastosTotal.setText(egresos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		txtAnio.setText(""+LocalDate.now().getYear());
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			egresos = FXCollections.observableArrayList();
			egresos = obtenerDatos.obtenerEgresos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.tblEgresos.setItems(egresos);
		
		this.colDetalle.setCellValueFactory(new PropertyValueFactory<Egreso, String>("detalle"));
		this.colFecha.setCellValueFactory(new PropertyValueFactory<Egreso, LocalDate>("fecha"));
		this.colCantidad.setCellValueFactory(new PropertyValueFactory<Egreso, Double>("monto"));
		
		mostrarEgresosPorMes(LocalDate.now().getMonthValue());
    	lblGastosTotal.setText(egresos.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum()+" $");
		
	}

}
