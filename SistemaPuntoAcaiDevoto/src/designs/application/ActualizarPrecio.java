package application;

import java.sql.SQLException;

import Alertas.Alerta;
import Productos.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ActualizarPrecio {

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtCosto;

    @FXML
    private Button btnActualizarPrecio;

    @FXML
    private TextField txtPrecioUnitario;

    @FXML
    private TextField txtPrecioPorMayor;
    
    private Producto productoActualizar;



	@FXML
    void onActualizarPrecioClick(ActionEvent event) throws SQLException {
		double costo = Double.parseDouble(txtCosto.getText());
		double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText());
		double precioMayor = Double.parseDouble(txtPrecioPorMayor.getText());
		
		productoActualizar.actualizarPrecios(precioUnitario, precioMayor, costo);
		Alerta.informationAlert("Se ha actualizado el preio del producto", "Actualizar Precio");
		
		Stage stage = (Stage) btnActualizarPrecio.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }
    
    public Producto getProductoActualizar() {
		return productoActualizar;
	}

	public void setProductoActualizar(Producto productoActualizar) {
		this.productoActualizar = productoActualizar;
	}
    
    

}
