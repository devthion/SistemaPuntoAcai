package application;

import java.sql.SQLException;
import java.time.LocalDate;

import Alertas.Alerta;
import Egresos.Egreso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarEgreso {

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtDetalle;

    @FXML
    private Button btnEditarEgreso;

    @FXML
    private Button btnVolver;

    @FXML
    private DatePicker dateInversion;
    
    private Egreso egresoEditable;
    private Egreso egresoNuevo;

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onEditarEgresoClick(ActionEvent event) {
    	egresoNuevo = generarEgreso();
		try {
			egresoEditable.editarEgreso(egresoNuevo);
			new Alerta().informationAlert("Se ha editado la inversion con Exito", "Informacion");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new Alerta().errorAlert("Error al modificar la inversion", "Error en la modificacion");
		}
    	
    	Stage stage = (Stage) btnEditarEgreso.getScene().getWindow();
    	stage.close();
    }
    
    public void initEditar(Egreso egreso){
    	this.txtDetalle.setText(egreso.getDetalle());
    	this.txtMonto.setText(""+egreso.getMonto());
    	this.dateInversion.setValue(egreso.getFecha());

    	egresoEditable = egreso;
    	
    }
    
    public Egreso generarEgreso() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	LocalDate gastoFecha = dateInversion.getValue();
    	Egreso egreso= new Egreso(detalle, monto);
    	egreso.setFecha(gastoFecha);
    	return egreso;
    }

}
