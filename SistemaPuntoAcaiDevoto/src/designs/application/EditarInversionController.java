package application;

import java.sql.SQLException;
import java.time.LocalDate;

import Alertas.Alerta;
import ConexionBD.ModificarDatos;
import ModeloInversion.Inversion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditarInversionController {

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private DatePicker dateInversion;

    @FXML
    private Button btnEditarInversion;
    
    private Inversion inversionEditable;
    private Inversion inversionNuevo;

    @FXML
    void onVolverClick(ActionEvent event) {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }
    
    public void initEditar(Inversion inversion) {
    	this.txtDetalle.setText(inversion.getDetalle());
    	this.txtMonto.setText(""+inversion.getMonto());
    	this.dateInversion.setValue(inversion.getFecha());

    	inversionEditable = inversion;
    	
    }
    
    public Inversion generarGasto() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	LocalDate gastoFecha = dateInversion.getValue();
    	Inversion inversion = new Inversion(detalle, monto);
    	inversion.setFecha(gastoFecha);
    	return inversion;
    }

    @FXML
    void onEditarInversionClick(ActionEvent event) {
    	inversionNuevo = generarGasto();
		try {
			new ModificarDatos().editarInversion(inversionEditable, inversionNuevo);
			new Alerta().informationAlert("Se ha editado la inversion con Exito", "Informacion");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new Alerta().errorAlert("Error al modificar la inversion", "Error en la modificacion");
		}
    	
    	Stage stage = (Stage) btnEditarInversion.getScene().getWindow();
    	stage.close();
    }

}
