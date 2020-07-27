package application;

import java.sql.SQLException;
import java.time.LocalDate;

import Alertas.Alerta;
import Gastos.GastosDiarios;
import Gastos.GastosGenerales;
import Gastos.GastosProductos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    
    private GastosProductos gastoEditableProd;
    private GastosProductos nuevoGastoProducto;
    
    private GastosDiarios gastoEditableDiario;
    private GastosDiarios nuevoGastoDiario;
    
    private GastosGenerales gastoEditableGral;
    private GastosGenerales nuevoGastoGeneral;

    @FXML
    void onEditarGastoProductosClick(ActionEvent event) {
    	nuevoGastoProducto = generarGastoProducto();
		try {
			gastoEditableProd.editarGasto(nuevoGastoProducto);
			new Alerta().informationAlert("Se ha editado el gasto", "Informacion");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new Alerta().errorAlert("Error al modificar el gasto", "Error en la modificacion");
		}
    	
    	Stage stage = (Stage) btnEditarGastoGeneral.getScene().getWindow();
    	stage.close();
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
//    	nuevoGastoGeneral = generarGastoGeneral();
//		try {
//			new ModificarDatos().editarGastoProducto(gastoEditableGral, nuevoGastoGeneral);
//			new Alerta().informationAlert("Se ha editado el gasto", "Informacion");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			new Alerta().errorAlert("Error al modificar el gasto", "Error en la modificacion");
//		}
//    	
//    	Stage stage = (Stage) btnEditarGastoGeneral.getScene().getWindow();
//    	stage.close();
    }
    
    public void initEditarProductos(GastosProductos gasto) {
    	this.txtDetalle.setText(gasto.getDetalle());
    	this.txtMonto.setText(""+gasto.getMonto());
    	this.dateCosto.setValue(gasto.getFecha());
    	
    	btnEditarGastoDiario.setVisible(false);
    	btnEditarGastoGeneral.setVisible(false);
    	btnNuevoGastoProductos.setVisible(true);
    	 	
    	gastoEditableProd = gasto;
    	
    }
    
    public void initEditarDiarios(GastosDiarios gasto) {
    	this.txtDetalle.setText(gasto.getDetalle());
    	this.txtMonto.setText(""+gasto.getMonto());
    	this.dateCosto.setValue(gasto.getFecha());
    	
    	btnEditarGastoDiario.setVisible(true);
    	btnEditarGastoGeneral.setVisible(false);
    	btnNuevoGastoProductos.setVisible(false);

    	gastoEditableDiario = gasto;
    	
    }
    
    public void initEditarGenerales(GastosGenerales gasto) {
    	this.txtDetalle.setText(gasto.getDetalle());
    	this.txtMonto.setText(""+gasto.getMonto());
    	this.dateCosto.setValue(gasto.getFecha());
    	
    	btnEditarGastoDiario.setVisible(false);
    	btnEditarGastoGeneral.setVisible(true);
    	btnNuevoGastoProductos.setVisible(false);

    	gastoEditableGral = gasto;
    	
    }
    
    public GastosProductos generarGastoProducto() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	LocalDate gastoFecha = dateCosto.getValue();
    	GastosProductos gasto = new GastosProductos(detalle, monto);
    	gasto.setFecha(gastoFecha);
    	return gasto;
    }
    
    public GastosDiarios generarGastoDiario() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	LocalDate gastoFecha = dateCosto.getValue();
    	GastosDiarios gasto = new GastosDiarios(detalle, monto);
    	gasto.setFecha(gastoFecha);
    	return gasto;
    }
    
    public GastosGenerales generarGastoGeneral() {
    	String detalle = this.txtDetalle.getText().toString();
    	double monto = Double.parseDouble(this.txtMonto.getText().toString());
    	LocalDate gastoFecha = dateCosto.getValue();
    	GastosGenerales gasto = new GastosGenerales(detalle, monto);
    	gasto.setFecha(gastoFecha);
    	return gasto;
    }

}
