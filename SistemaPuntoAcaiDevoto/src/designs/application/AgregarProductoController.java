package application;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
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
    
    public void generarProducto() {
    	
    	String nombre = this.txtNombre.getText().toString();
    	String kilos = this.txtKilos.getText();
    	double costo = Integer.parseInt(txtCosto.getText());
    	double precioUnitario = Integer.parseInt(txtPrecioUnitario.getText());
    	double precioMayor = Integer.parseInt(this.txtPrecioMayor.getText());
    	int cantidadMayor = Integer.parseInt(this.txtCantidadMayor.getText());
    	
    	//return new Producto()
    	//return new Cliente(dni, nombre, apellido, telefono, email, direccion, tipo, comoLlego, 0);
    }

}
