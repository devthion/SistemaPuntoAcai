package application;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import Productos.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AgregarProductoController {

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtCosto;

    @FXML
    private TextField txtKilos;

    @FXML
    private TextField txtCantidadMayor;

    @FXML
    private TextField txtPrecioMayor;

    @FXML
    private TextField txtPrecioUnitario;

    @FXML
    void onAgregarProductoClick(ActionEvent event) {
    	Producto producto = generarProducto();
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("VerProductos.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Productos");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }
    
    public Producto generarProducto() {
    	
    	String nombre = this.txtNombre.getText().toString();
    	int kilos = Integer.parseInt(this.txtKilos.getText());
    	double costo = Double.parseDouble(txtCosto.getText());
    	double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText());
    	double precioMayor = Double.parseDouble(this.txtPrecioMayor.getText());
    	int cantidadMayor = Integer.parseInt(this.txtCantidadMayor.getText());
    	
    	return new Producto(nombre, kilos, 0, precioUnitario, precioMayor, costo, cantidadMayor);
    
    }

}
