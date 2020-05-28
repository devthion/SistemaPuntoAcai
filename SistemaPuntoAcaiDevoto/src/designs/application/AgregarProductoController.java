package application;

import java.sql.SQLException;

import Alertas.Alerta;
import Productos.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    
    private Producto nuevoProducto;


	@FXML
    void onAgregarProductoClick(ActionEvent event) throws SQLException {
    	Producto producto = generarProducto();
    	
    	this.nuevoProducto = producto;
		producto.almacenarProducto();
		new Alerta().informationAlert("Se ha añadido correctamente", "Informacion");
		Stage stage = (Stage) btnAgregarProducto.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onVolverClick(ActionEvent event) {
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
    
    public Producto getNuevoProducto() {
		return nuevoProducto;
	}

}
