package application;

import java.sql.SQLException;
import java.time.LocalDate;

import Alertas.Alerta;
import ConexionBD.ModificarDatos;
import Gastos.Gasto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditarGastoController {

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtDetalle;

    @FXML
    private Button btnEditarGastoGeneral;

    @FXML
    private Button btnVolver;

    @FXML
    private DatePicker dateCosto;

    @FXML
    private Button btnEditarGastoDiario;

    @FXML
    private Button btnNuevoGastoProductos;
    
    private Gasto gastoEditable;
    private Gasto nuevoGasto;

    @FXML
    void onEditarGastoProductosClick(ActionEvent event) {

    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void onEditarGastoDiarioCick(ActionEvent event) {

    }

    @FXML
    void onEditarGastoGeneralClick(ActionEvent event) {
    	nuevoGasto = generarGasto();
		try {
			new ModificarDatos().editarGasto(gastoEditable, nuevoGasto);
			new Alerta().informationAlert("Se ha editado el gasto", "Informacion");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new Alerta().errorAlert("Error al modificar el gasto", "Error en la modificacion");
		}
    	
    	Stage stage = (Stage) btnEditarGastoGeneral.getScene().getWindow();
    	stage.close();
    }
    
    public void initEditarProductos(Gasto gasto) {
    	this.txtDetalle.setText(gasto.getDetalle());
    	this.txtMonto.setText(""+gasto.getMonto());
    	this.dateCosto.setValue(gasto.getFecha());
    	
    	btnEditarGastoDiario.setVisible(false);
    	btnEditarGastoGeneral.setVisible(false);
    	btnNuevoGastoProductos.setVisible(true);
    	 	
    	gastoEditable = gasto;
    	
    }
    
    public void initEditarDiarios(Gasto gasto) {
    	this.txtDetalle.setText(gasto.getDetalle());
    	this.txtMonto.setText(""+gasto.getMonto());
    	this.dateCosto.setValue(gasto.getFecha());
    	
    	btnEditarGastoDiario.setVisible(true);
    	btnEditarGastoGeneral.setVisible(false);
    	btnNuevoGastoProductos.setVisible(false);

    	gastoEditable = gasto;
    	
    }
    
    public void initEditarGenerales(Gasto gasto) {
    	this.txtDetalle.setText(gasto.getDetalle());
    	this.txtMonto.setText(""+gasto.getMonto());
    	this.dateCosto.setValue(gasto.getFecha());
    	
    	btnEditarGastoDiario.setVisible(false);
    	btnEditarGastoGeneral.setVisible(true);
    	btnNuevoGastoProductos.setVisible(false);

    	gastoEditable = gasto;
    	
    }
    
    public Gasto generarGasto() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	LocalDate gastoFecha = dateCosto.getValue();
    	Gasto gasto = new Gasto(detalle, monto);
    	gasto.setFecha(gastoFecha);
    	return gasto;
    }

}
