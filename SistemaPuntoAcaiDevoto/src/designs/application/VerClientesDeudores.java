package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import ConexionBD.Querys;
import ModelosClientes.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerClientesDeudores implements Initializable {

    @FXML
    private TableColumn<Cliente, Double> colDeuda;

    @FXML
    private TextField txtNombreABuscar;

    @FXML
    private TableColumn<Cliente, String> colApellido;

    @FXML
    private Button btnSaldarDeuda;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Cliente, String> colCalle;

    @FXML
    private TableColumn<Cliente, Double> colIngresos;

    @FXML
    private TableColumn<Cliente, String> colTipo;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TableColumn<Cliente, Integer> colTelefono;
    
    private ObservableList<Cliente> clientes;

    @FXML
    void onSaldarDeuda(ActionEvent event) {
    	Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();
    	
    	if(cliente==null) {
    		new Alerta().errorAlert("Debe seleccionar un cliente", "Editar Cliente");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("SaldarDeuda.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			SaldarDeuda controller = loader.getController();
    			controller.setCliente(cliente);
    			
    			Scene scene = new Scene(root,700,300);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Editar Cliente");
    			stage.showAndWait();
    			
    			ObtenerDatos obtenerDatos = new ObtenerDatos();
    			clientes = FXCollections.observableArrayList();
    			clientes = obtenerDatos.obtenerClientes(new Querys().queryClientesDeudores());
    			
    			this.tblClientes.setItems(clientes);
    			this.tblClientes.refresh();
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("VerClientes.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Clientes");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			clientes = FXCollections.observableArrayList();
			//OBTENER DATOS CLIENTES MAYORISTAS
			clientes = obtenerDatos.obtenerClientes(new Querys().queryClientesDeudores());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.tblClientes.setItems(clientes);
		
		this.colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		this.colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
		this.colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("telefono"));
		this.colCalle.setCellValueFactory(new PropertyValueFactory<Cliente, String>("DireccionCompleta"));
		this.colTipo.setCellValueFactory(new PropertyValueFactory<Cliente, String>("tipo"));
		this.colIngresos.setCellValueFactory(new PropertyValueFactory<Cliente, Double>("ingresos"));	
		this.colDeuda.setCellValueFactory(new PropertyValueFactory<Cliente, Double>("deuda"));	
		
		
		FilteredList<Cliente> filteredData = new FilteredList<>(clientes, p -> true);
    	tblClientes.setItems(filteredData);
    	
    	txtNombreABuscar.setPromptText("Buscar...");
    	txtNombreABuscar.textProperty().addListener((prop, old, text) -> {
    	    filteredData.setPredicate(unCliente -> {
    	        if(text == null || text.isEmpty()) return true;
    	        
    	        String name = unCliente.getApellido().toLowerCase();  
    	        return name.contains(text.toLowerCase());
    	    });
    	});
		
	}

}
