package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import ModeloInversion.Inversion;
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

public class VerInversiones implements Initializable {

    @FXML
    private TableColumn<Inversion, LocalDate> colFecha;

    @FXML
    private MenuItem slipOctubre;

    @FXML
    private Label lblInversionesTotal;

    @FXML
    private TableView<Inversion> tblInversiones;

    @FXML
    private Label lblInversionesMes;

    @FXML
    private MenuItem slipFebrero;

    @FXML
    private MenuItem slipMarzo;

    @FXML
    private MenuItem slipMayo;

    @FXML
    private Button btnEditarInversion;

    @FXML
    private MenuItem slipEnero;

    @FXML
    private MenuItem slipSeptiembre;

    @FXML
    private TableColumn<Inversion, String> colDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private MenuItem slipJulio;

    @FXML
    private SplitMenuButton slipMenuMes;

    @FXML
    private MenuItem slipAbril;

    @FXML
    private MenuItem slipAgosto;

    @FXML
    private TextField txtAnio;

    @FXML
    private MenuItem slipJunio;

    @FXML
    private TableColumn<Inversion, Double> colCantidad;

    @FXML
    private MenuItem slipNoviembre;

    @FXML
    private MenuItem slipDiciembre;
    
    private ObservableList<Inversion> inversiones;
    private ObservableList<Inversion> inversionesPorMes;

    @FXML
    void onEditarInversionClick(ActionEvent event) throws SQLException {
    	Inversion inversion = this.tblInversiones.getSelectionModel().getSelectedItem();
    	
    	if(inversion==null) {
    		new Alerta().errorAlert("Debe seleccionar una Inversion", "Editar Inversion");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("EditarInversion.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			EditarInversionController controller = loader.getController();
    			controller.initEditar(inversion);
    			
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Inversion");
    			stage.showAndWait();
    			
    			
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		ObtenerDatos obtenerDatos = new ObtenerDatos();
			obtenerDatos = new ObtenerDatos();
			inversiones = FXCollections.observableArrayList();
			inversiones = obtenerDatos.obtenerInversiones();
			
			this.tblInversiones.setItems(inversiones);
			mostrarGastosPorMes(LocalDate.now().getMonthValue());
			lblInversionesTotal.setText(inversiones.stream().mapToDouble(unaInversion-> unaInversion.getMonto()).sum()+" $");
    	}
    }
    
	try {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("NuevaInversion.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root, 1300, 650);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.resizableProperty().setValue(Boolean.FALSE);
		stage.setResizable(false);
		stage.setTitle("Nueva Inversion");
		stage.show();
	} catch (Exception e) {
		e.printStackTrace();
	}
	Stage stage = (Stage) btnNuevoGasto.getScene().getWindow();
	stage.close();

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
    		stage.setTitle("Inversion");
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
    		inversionesPorMes =inversiones.filtered(unaInversion-> unaInversion.getFecha().getMonthValue()==mes && unaInversion.getFecha().getYear()==Integer.parseInt(txtAnio.getText()));
        	this.tblInversiones.setItems(inversionesPorMes);
        	lblInversionesMes.setText(inversionesPorMes.stream().mapToDouble(unaInversion-> unaInversion.getMonto()).sum()+" $");
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtAnio.setText(""+LocalDate.now().getYear());
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			inversiones = FXCollections.observableArrayList();
			inversiones = obtenerDatos.obtenerInversiones();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.tblInversiones.setItems(inversiones);
		
		this.colDetalle.setCellValueFactory(new PropertyValueFactory<Inversion, String>("detalle"));
		this.colFecha.setCellValueFactory(new PropertyValueFactory<Inversion, LocalDate>("fecha"));
		this.colCantidad.setCellValueFactory(new PropertyValueFactory<Inversion, Double>("monto"));
		
		mostrarGastosPorMes(LocalDate.now().getMonthValue());
    	lblInversionesTotal.setText(inversiones.stream().mapToDouble(unaInversion-> unaInversion.getMonto()).sum()+" $");
		
	}

}
