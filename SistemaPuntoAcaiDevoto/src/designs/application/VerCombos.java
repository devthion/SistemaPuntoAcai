package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import Gastos.GastosDiarios;
import Productos.Combo;
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

public class VerCombos implements Initializable {

    @FXML
    private Button btnEliminarCombo;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnNuevoCombo;

    @FXML
    private TableColumn<Combo, Double> colPrecio;

    @FXML
    private Button btnEditarCombo;

    @FXML
    private TableView<Combo> tblCombos;

    @FXML
    private TableColumn<Combo, String> colNombre;
    
    private ObservableList<Combo> combos;

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Productos.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Productos");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onNuevoComboClick(ActionEvent event) throws SQLException {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("NuevoCombo.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();

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
    	combos = FXCollections.observableArrayList();
    	combos = obtenerDatos.obtenerCombos();

    	this.tblCombos.setItems(combos);
    	this.tblCombos.refresh();
    }

    @FXML
    void onEditarComboClick(ActionEvent event) throws SQLException {
    	Combo combo = this.tblCombos.getSelectionModel().getSelectedItem();
    	
    	if(combo==null) {
    		new Alerta().errorAlert("Debe seleccionar un Combo", "Editar Combo");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("EditarCombo.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			EditarCombo controller = loader.getController();
    			controller.initEditar(combo);
    			
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Combos");
    			stage.showAndWait();
    			
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        	ObtenerDatos obtenerDatos = new ObtenerDatos();
        	obtenerDatos = new ObtenerDatos();
        	combos = FXCollections.observableArrayList();
        	combos = obtenerDatos.obtenerCombos();

        	this.tblCombos.setItems(combos);
        	this.tblCombos.refresh();

    	}
    }

    @FXML
    void onEliminarComboClick(ActionEvent event) throws SQLException {
    	Combo combo = this.tblCombos.getSelectionModel().getSelectedItem();
    	
    	if(combo==null) {
    		new Alerta().errorAlert("Debe seleccionar un Combo", "Eliminar Combo");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("¿Estas seguro que desea eliminar el combo?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
        		combo.eliminar();
	    		new Alerta().errorAlert("Combo Eliminado", "Eliminar Combo");
	    		
	        	ObtenerDatos obtenerDatos = new ObtenerDatos();
	        	obtenerDatos = new ObtenerDatos();
	        	combos = FXCollections.observableArrayList();
	        	combos = obtenerDatos.obtenerCombos();

	        	this.tblCombos.setItems(combos);
	        	this.tblCombos.refresh();
        	}
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			combos = FXCollections.observableArrayList();
			combos = obtenerDatos.obtenerCombos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		this.tblCombos.setItems(combos);

		this.colNombre.setCellValueFactory(new PropertyValueFactory<Combo, String>("nombre"));
		this.colPrecio.setCellValueFactory(new PropertyValueFactory<Combo, Double>("precio"));
		
	}

}
