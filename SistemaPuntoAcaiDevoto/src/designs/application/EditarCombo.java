package application;

import java.sql.SQLException;

import Alertas.Alerta;
import Alertas.Validaciones;
import Productos.Combo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarCombo {

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnEditarCombo;
    
    private Combo combo;

    @FXML
    void onEditarComboClick(ActionEvent event) throws NumberFormatException, SQLException {
    	if(Validaciones.validarCajaNumerica(txtMonto)) {
    		new Alerta().informationAlert("El valor ingresado del combo es incorrecto", "Combo valor");
    	}else {
    		combo = new Combo(txtNombre.getText(), Double.parseDouble(txtMonto.getText()));
    		combo.actualizarPrecio(Double.parseDouble(txtMonto.getText()));
    		new Alerta().informationAlert("El combo fue almacenado", "Almacenar Combo");
        	Stage stage = (Stage) btnVolver.getScene().getWindow();
        	stage.close();
    	}
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

	public void initEditar(Combo combo) {
		txtMonto.setText(""+combo.getCombo_precio());
		txtNombre.setText(""+combo.getCombo_nombre());
		
	}

}
