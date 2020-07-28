package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import Propina.Propina;
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

public class PropinasController implements Initializable {

    @FXML
    private TableColumn<Propina, LocalDate> colFecha;

    @FXML
    private MenuItem slipOctubre;

    @FXML
    private Label lblPropinasTotal;

    @FXML
    private MenuItem slipFebrero;

    @FXML
    private MenuItem slipMarzo;

    @FXML
    private MenuItem slipMayo;

    @FXML
    private MenuItem slipEnero;

    @FXML
    private MenuItem slipSeptiembre;

    @FXML
    private Button btnEliminarPropina;

    @FXML
    private MenuItem slipJulio;

    @FXML
    private Button btnVolver;

    @FXML
    private SplitMenuButton slipMenuMes;

    @FXML
    private MenuItem slipAbril;

    @FXML
    private TextField txtAnio;

    @FXML
    private MenuItem slipAgosto;

    @FXML
    private MenuItem slipJunio;

    @FXML
    private Label lblPropinasMes;

    @FXML
    private MenuItem slipNoviembre;

    @FXML
    private TableColumn<Propina, Double> colCantidad;

    @FXML
    private Button btnNuevaPropina;

    @FXML
    private MenuItem slipDiciembre;

    @FXML
    private TableView<Propina> tblPropinas;
    
    private ObservableList<Propina> propinas;
    private ObservableList<Propina> propinasPorMes;

    @FXML
    void onEliminarPropinaClick(ActionEvent event) throws SQLException {
    	Propina propina = this.tblPropinas.getSelectionModel().getSelectedItem();
    	
    	if(propina==null) {
    		new Alerta().errorAlert("Debe seleccionar una Propina", "Editar Inversion");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("¿Estas seguro que desea eliminar la propina?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
	    		propina.eliminarPropina();
	    		new Alerta().errorAlert("Gasto Eliminado", "Eliminar Gasto");
	    		
	    		ObtenerDatos obtenerDatos = new ObtenerDatos();
				obtenerDatos = new ObtenerDatos();
				propinas = FXCollections.observableArrayList();
				propinas = obtenerDatos.obtenerPropinas();
				
				this.tblPropinas.setItems(propinas);
				mostrarPropinaPorMes(LocalDate.now().getMonthValue());
				lblPropinasTotal.setText(propinas.stream().mapToDouble(unaInversion-> unaInversion.getMonto()).sum()+" $");
        	}
	    }
    }

    @FXML
    void onNuevaPropinaClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("NuevaPropina.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();
    		Scene scene = new Scene(root, 1300, 650);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.resizableProperty().setValue(Boolean.FALSE);
    		stage.setResizable(false);
    		stage.setTitle("Nueva Propina");
    		stage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	Stage stage = (Stage) btnNuevaPropina.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onEneroClick(ActionEvent event) {
    	mostrarPropinaPorMes(1);
    }

    @FXML
    void onFebreroClick(ActionEvent event) {
    	mostrarPropinaPorMes(2);
    }

    @FXML
    void onMarzoClick(ActionEvent event) {
    	mostrarPropinaPorMes(3);
    }

    @FXML
    void onAbrilClick(ActionEvent event) {
    	mostrarPropinaPorMes(4);
    }

    @FXML
    void onMayoClick(ActionEvent event) {
    	mostrarPropinaPorMes(5);
    }

    @FXML
    void onJunioClick(ActionEvent event) {
    	mostrarPropinaPorMes(6);
    }

    @FXML
    void onJulioClick(ActionEvent event) {
    	mostrarPropinaPorMes(7);
    }

    @FXML
    void onAgostoClick(ActionEvent event) {
    	mostrarPropinaPorMes(8);
    }

    @FXML
    void onSeptiembreClick(ActionEvent event) {
    	mostrarPropinaPorMes(9);
    }

    @FXML
    void onOctubreClick(ActionEvent event) {
    	mostrarPropinaPorMes(10);
    }

    @FXML
    void onNoviembreClick(ActionEvent event) {
    	mostrarPropinaPorMes(11);
    }

    @FXML
    void onDiciembreClick(ActionEvent event) {
    	mostrarPropinaPorMes(12);
    }
    
    public void mostrarPropinaPorMes(int mes) {
    	if(Validaciones.validarCajaNumerica(txtAnio)) {
    		 new Alerta().errorAlert("Debe ingresar un año valido", "Error de Datos");
    	}else{
    		propinasPorMes =propinas.filtered(unaInversion-> unaInversion.getFecha().getMonthValue()==mes && unaInversion.getFecha().getYear()==Integer.parseInt(txtAnio.getText()));
        	this.tblPropinas.setItems(propinasPorMes);
        	lblPropinasMes.setText(propinasPorMes.stream().mapToDouble(unaInversion-> unaInversion.getMonto()).sum()+" $");
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
    		stage.setTitle("Inversion");
    		stage.show();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtAnio.setText(""+LocalDate.now().getYear());
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			propinas = FXCollections.observableArrayList();
			propinas = obtenerDatos.obtenerPropinas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.tblPropinas.setItems(propinas);
		
		this.colFecha.setCellValueFactory(new PropertyValueFactory<Propina, LocalDate>("fecha"));
		this.colCantidad.setCellValueFactory(new PropertyValueFactory<Propina, Double>("monto"));
		
		mostrarPropinaPorMes(LocalDate.now().getMonthValue());
    	lblPropinasTotal.setText(propinas.stream().mapToDouble(unaInversion-> unaInversion.getMonto()).sum()+" $");
		
	}

}
