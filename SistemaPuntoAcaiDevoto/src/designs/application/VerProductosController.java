package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import ModelosClientes.Cliente;
import Productos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VerProductosController implements Initializable {

    @FXML
    private Button btnActualizarStock;

    @FXML
    private Button btnAgregarNuevoProducto;

    @FXML
    private Button btnActualizarPrecio;
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private TableView<Producto> tblProductos;

    @FXML
    private TableColumn<Producto, Double> colCosto;

    @FXML
    private TableColumn<Producto, Double> colPrecioMayor;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Double> colPrecioUnitario;

    @FXML
    private TableColumn<Producto, Integer> colTotalVendidos;
    
    private ObservableList<Producto> productos;

    
    @FXML
    void onAgregarNuevoProductoClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("AgregarProducto.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			AgregarProductoController controller = loader.getController();
			
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Nuevo Producto");
			stage.showAndWait();
			
			this.productos.add(controller.getNuevoProducto());
			this.tblProductos.refresh();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onActualizarStockClick(ActionEvent event) {
    	Producto producto = this.tblProductos.getSelectionModel().getSelectedItem();
    	
    	if(producto==null) {
    		Alerta.errorAlert("Debe seleccionar un Producto", "Actualizar Stock");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("ActualizarStock.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			ActualizarStock controller = loader.getController();
    			controller.initActualizar(producto.getNombre());
    			
    			Scene scene = new Scene(root,700,300);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.initModality(Modality.APPLICATION_MODAL);
    			stage.setTitle("Productos");
    			stage.showAndWait();
    			
    			ObtenerDatos obtenerDatos = new ObtenerDatos();
    			productos = FXCollections.observableArrayList();
    			productos = obtenerDatos.obtenerProductos();
    			
    			this.tblProductos.setItems(productos);
    			this.tblProductos.refresh();
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}

    }
    
    @FXML
    void onActualizarPrecioClick(ActionEvent event) {
    	Producto producto = this.tblProductos.getSelectionModel().getSelectedItem();
    	
    	if(producto==null) {
    		Alerta.errorAlert("Debe seleccionar un Producto", "Actualizar Precio");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("ActualizarPrecio.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			
    			ActualizarPrecio controller = loader.getController();
    			controller.setProductoActualizar(producto);;
    			
    			Scene scene = new Scene(root,700,300);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.initModality(Modality.APPLICATION_MODAL);
    			stage.setTitle("Editar Cliente");
    			stage.showAndWait();
    			
    			ObtenerDatos obtenerDatos = new ObtenerDatos();
    			productos = FXCollections.observableArrayList();
    			productos = obtenerDatos.obtenerProductos();
    			
    			this.tblProductos.setItems(productos);
    			this.tblProductos.refresh();
    			
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Menu Principal");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//OBTENER LOS DATOS DE LOS PRODUCTOS
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			productos = FXCollections.observableArrayList();
			productos = obtenerDatos.obtenerProductos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.tblProductos.setItems(productos);
		
		this.colNombre.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
		this.colCosto.setCellValueFactory(new PropertyValueFactory<Producto, Double>("costo"));
		this.colPrecioMayor.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioMayor"));
		this.colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<Producto, Double>("precioUnitario"));
		this.colTotalVendidos.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("cantidadVendidos"));
		
	}

}
