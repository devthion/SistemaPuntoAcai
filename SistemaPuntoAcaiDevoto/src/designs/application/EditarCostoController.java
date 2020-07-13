package application;

import java.sql.SQLException;
import java.time.LocalDate;

import Alertas.Alerta;
import ConexionBD.ModificarDatos;
import ModeloGasto.Gasto;
import ModelosClientes.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditarCostoController {

    @FXML
    private Button btnNuevoGasto;

    @FXML
    private TextField txtMonto;

    @FXML
    private TextField txtDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private DatePicker dateCosto;
    
    private Gasto gastoEditable;
    private Gasto gastoNuevo;

    @FXML
    void onGuardarGastoClick(ActionEvent event) {
    	gastoNuevo = generarGasto();
    	//IF YA EXISTE EN LA BD
    		try {
				new ModificarDatos().editarGasto(gastoEditable, gastoNuevo);
				new Alerta().informationAlert("Se ha editado el gasto", "Informacion");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				new Alerta().errorAlert("Error al modificar el gasto", "Error en la modificacion");
			}
        	
        	Stage stage = (Stage) btnNuevoGasto.getScene().getWindow();
        	stage.close();
    }
    
    public void initEditar(Gasto gasto) {
    	this.txtDetalle.setText(gasto.getDetalle());
    	this.txtMonto.setText(""+gasto.getMonto());
    	this.dateCosto.setValue(gasto.getFecha());

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

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("VerGastos.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Gastos");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

}
