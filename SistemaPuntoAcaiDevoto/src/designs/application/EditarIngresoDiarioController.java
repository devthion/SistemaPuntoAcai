package application;

import java.sql.SQLException;
import java.time.LocalDate;

import Alertas.Alerta;
import ModeloInversion.IngresoDiario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarIngresoDiarioController {

    @FXML
    private TextField txtMonto;

    @FXML
    private Button btnEditarIngreso;

    @FXML
    private TextField txtDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private DatePicker dateInversion;
    
    private IngresoDiario ingresoEditable;
    private IngresoDiario ingresoNuevo;

    @FXML
    void onVolverClick(ActionEvent event) {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onEditarIngresoClick(ActionEvent event) {
    	ingresoNuevo = generarIngreso();
		try {
			ingresoEditable.modificarIngreso(ingresoNuevo);
			new Alerta().informationAlert("Se ha editado la inversion con Exito", "Informacion");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new Alerta().errorAlert("Error al modificar la inversion", "Error en la modificacion");
		}
    	
    	Stage stage = (Stage) btnEditarIngreso.getScene().getWindow();
    	stage.close();
    }

	public void initEditar(IngresoDiario ingreso) {
    	this.txtDetalle.setText(ingreso.getDetalle());
    	this.txtMonto.setText(""+ingreso.getMonto());
    	this.dateInversion.setValue(ingreso.getFecha());

    	ingresoEditable = ingreso;
		
	}
	
    public IngresoDiario generarIngreso() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	LocalDate gastoFecha = dateInversion.getValue();
    	IngresoDiario ingreso = new IngresoDiario(detalle, monto);
    	ingreso.setFecha(gastoFecha);
    	return ingreso;
    }

}
