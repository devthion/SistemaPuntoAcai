package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import Productos.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ActualizarStock{


    @FXML
    private Button btnVolver;

    @FXML
    private Label lblNombreProd;

    @FXML
    private TextField txtCantidad;

    @FXML
    private Button btnActualizar;

    
    private int contador;
    private Producto productoActualizar;


    @FXML
    void onActualizarClick(ActionEvent event) throws SQLException {
    	if(new Validaciones().validarCajaNumerica(txtCantidad)) {
    		new Alerta().errorAlert("Ingreso una cantidad no valida", "Error de datos");
    	}else{
    		contador = Integer.parseInt(txtCantidad.getText().toString());
	    	productoActualizar.actualizarStock(contador);
	    	new Alerta().informationAlert("Se ha actualizado el stock", "Actualizar Stock");
	    	Stage stage = (Stage) btnVolver.getScene().getWindow();
	    	stage.close();
    		
    	}
	    
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

	
	public void initActualizar(String producto) {
		lblNombreProd.setText(producto);
	}
	
    public Producto getProductoActualizar() {
		return productoActualizar;
	}

	public void setProductoActualizar(Producto productoActualizar) {
		this.productoActualizar = productoActualizar;
	}

}
