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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerClientesConsumidoresFinales implements Initializable {

    @FXML
    private TableColumn<Cliente, String> colBarrio;
    
    @FXML
    private Label lblCantidadClientes;
    
    @FXML
    private TableColumn<Cliente, Double> colDeuda;

    @FXML
    private TableColumn<Cliente, Integer> colNumero;

    @FXML
    private TableColumn<Cliente, Double> colIngresos;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TextField txtBusqueda;

    @FXML
    private TableColumn<Cliente, String> colApellido;

    @FXML
    private Button btnEditarCliente;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Cliente, String> colCalle;

    @FXML
    private TableColumn<Cliente, Integer> colTelefono;

    @FXML
    private TextField txtNombreABuscar;

    
    private ObservableList<Cliente> clientes;

    @FXML
    void editarCliente(ActionEvent event) throws SQLException {
    	Cliente cliente = this.tblClientes.getSelectionModel().getSelectedItem();
    	
    	if(cliente==null) {
    		new Alerta().errorAlert("Debe seleccionar un cliente", "Editar Cliente");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarCliente.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			AgregarCliente controller = loader.getController();
    			controller.initEditar(cliente);
    			
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Editar Cliente");
    			stage.showAndWait();
    			
    			
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		ObtenerDatos obtenerDatos = new ObtenerDatos();
			clientes = FXCollections.observableArrayList();
			clientes = obtenerDatos.obtenerClientes(new Querys().queryClientesConsumidoresFinales());
			
			this.tblClientes.setItems(clientes);
			this.tblClientes.refresh();
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
			//OBTENER DATOS CLIENTES CONSUMIDORES FINALES
			clientes = obtenerDatos.obtenerClientes(new Querys().queryClientesConsumidoresFinales());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.tblClientes.setItems(clientes);
		
		this.colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
		this.colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
		this.colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("telefono"));
		this.colCalle.setCellValueFactory(new PropertyValueFactory<Cliente, String>("DireccionCompleta"));
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
		
    	int cantidad = clientes.size();
    	System.out.println(cantidad);
    	lblCantidadClientes.setText("Cantidad de clientes Finales : "+cantidad);
		
	}

}
