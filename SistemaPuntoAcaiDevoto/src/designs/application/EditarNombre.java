package application;

import Alertas.Alerta;
import Productos.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarNombre {

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblNombreProd;

    @FXML
    private Button btnActualizar;
    
    private Producto productoActualizar;

    @FXML
    void onActualizarClick(ActionEvent event) {
    	productoActualizar.actualizarNombre(productoActualizar.getNombre());
    	new Alerta().informationAlert("El nombre ha sido actualizado.", "Editar Nombre");
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
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
		txtNombre.setText(productoActualizar.getNombre());
	}

}
