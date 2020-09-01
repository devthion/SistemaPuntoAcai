package application;

import Alertas.Alerta;
import Alertas.Validaciones;
import ModelosClientes.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SaldarDeuda {

    @FXML
    private Button btnSaldarDeuda;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblNombreProd;

    @FXML
    private TextField txtCantidad;
    
    private Cliente cliente;

    @FXML
    void onSaldarDeudaClick(ActionEvent event) {
    	if (Validaciones.validarCajaNumerica(txtCantidad)) {
    		new Alerta().errorAlert("Ingreso un monto no valido", "Error");
    	}else {
    		cliente.saldarDeuda(Double.parseDouble(txtCantidad.getText().toString()));
    		new Alerta().informationAlert("Se ha almacenado el pago de la deuda", "Deuda Pagada");
    	}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    	
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }
    
    public void setCliente(Cliente cliente) {
    	this.cliente = cliente;
    }

}
