package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import Egresos.Egreso;
import Gastos.GastosDiarios;
import Gastos.GastosGenerales;
import Gastos.GastosProductos;
import ModeloInversion.Inversion;
import Propina.Propina;
import Ventas.CajaCerrada;
import Ventas.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerIngresos implements Initializable {


    @FXML
    private Button btnVolver;

    @FXML
    private Label lblIdealDia;

    @FXML
    private Label lblDineroEnMano;

    @FXML
    private Label lblRealDia;

    @FXML
    private TextField txtMes;

    @FXML
    private TextField txtAnio;

    @FXML
    private TextField txtDia;
    
    @FXML
    private Button btnVerIngresos;

    @FXML
    private Label lblGananciaDia;

    @FXML
    private Label lblMercaderia;
    
    @FXML
    private Label lblGananciaGeneral;

    @FXML
    private Label lblGastos;
    
    private List<CajaCerrada> cajas = new ArrayList<>();
    private List<GastosProductos> mercaderia = new ArrayList<>();
    private List<Propina> propinas = new ArrayList<>();
    private List<Egreso> egresos = new ArrayList<>();
    private List<Inversion> inversiones = new ArrayList<>();
    private ObservableList<Venta> ventas;
    private ObservableList<Venta> ventasPorDia;
    private ObservableList<GastosGenerales> gastos;
    private ObservableList<GastosDiarios> gastosDiarios;
    private ObservableList<GastosDiarios> gastosPorDia;

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Menu Principal");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }
    

    @FXML
    void onVerIngresosClick(ActionEvent event) throws SQLException {
    	
    	if (Validaciones.validarCajasNumericas(generarListNumericos())) {
    		new Alerta().errorAlert("Puede este ingresando mal los datos", "Error en el ingreso de Datos");
    	}else {
    		mostrarIngresos();
    	}
    	
    	
    }
    
    public void mostrarIngresos() throws SQLException {
		ObtenerDatos obtenerDatos = new ObtenerDatos();
		cajas = obtenerDatos.obtenerCajasCerradas();
		mercaderia = obtenerDatos.obtenerGastosProductos();
		inversiones = obtenerDatos.obtenerInversiones();
		egresos = obtenerDatos.obtenerEgresos();
		
    	
    	int dia=Integer.parseInt(txtDia.getText());
    	int mes=Integer.parseInt(txtMes.getText());
    	int anio=Integer.parseInt(txtAnio.getText());
    	
    	//MOSTRAR INGRESOS DIARIOS
    	lblIdealDia.setText(""+cajas.stream().filter(unaCaja -> (unaCaja.getFecha().getDayOfMonth()== dia) &&
    			unaCaja.getFecha().getMonthValue() == mes && unaCaja.getFecha().getYear() == anio).mapToDouble(unaCaja -> unaCaja.getMonto_ideal()).sum());
		
    	lblRealDia.setText(""+cajas.stream().filter(unaCaja -> (unaCaja.getFecha().getDayOfMonth()== dia) &&
    			unaCaja.getFecha().getMonthValue() == mes && unaCaja.getFecha().getYear() == anio).mapToDouble(unaCaja -> unaCaja.getMonto_real()).sum());
    	
    	ventasPorDia = ventas.filtered(unaVenta -> unaVenta.getUnEnvio().getFechaEntrega().getDayOfMonth()==dia
    			&& unaVenta.getUnEnvio().getFechaEntrega().getMonthValue()==mes
    			&& unaVenta.getUnEnvio().getFechaEntrega().getYear() ==anio
    			&& unaVenta.getUnEnvio().getEstado()==true);
    	
    	gastosPorDia = gastosDiarios.filtered(unGasto-> (unGasto.getFecha().getDayOfMonth()== dia) &&
    			unGasto.getFecha().getMonthValue() == mes && unGasto.getFecha().getYear() == anio);
    	
    	Double diferenciaDia = Double.parseDouble(lblRealDia.getText()) - Double.parseDouble(lblIdealDia.getText());
    	lblGananciaDia.setText((ventasPorDia.stream().mapToDouble(unaVenta -> unaVenta.getVenta_ganancia()).sum() + diferenciaDia - gastosPorDia.stream().mapToDouble(unGasto-> unGasto.getMonto()).sum())+" $");
    	
    	//MOSTRAR INGRESOS TOTALES
    	
    	lblMercaderia.setText(cajas.stream().mapToDouble(unaCaja -> unaCaja.getMonto_real()).sum() + "");
    	lblGastos.setText(gastos.stream().mapToDouble(unGasto -> unGasto.getMonto()).sum() + "");
    	
    	
//    	Double ventasCostoDouble2 = ventas.stream().mapToDouble(unaVenta -> 
//    	unaVenta.getItems().stream().mapToDouble(unItem -> unItem.getProducto().getCosto() *unItem.getCantidad() ).sum()).sum();
    	
    	Double ventasCostoDouble = ventas.stream().mapToDouble(unaVenta -> 
    	unaVenta.getVenta_precioTotal() - unaVenta.getVenta_ganancia()).sum();
    	Double egresosTotales = egresos.stream().mapToDouble(unEgreso -> unEgreso.getMonto()).sum();
    		
    	
    	lblGananciaGeneral.setText(" "+ (Double.parseDouble(lblMercaderia.getText()) - Double.parseDouble(lblGastos.getText())
    			- ventasCostoDouble - egresosTotales));
    	
    	//Cierres de caja - mercadería - gastos + inversiones + propinas
    	Double mercaderiaTotal = mercaderia.stream().mapToDouble(unaMercaderia -> unaMercaderia.getMonto()).sum();
    	Double inversionesTotales = inversiones.stream().mapToDouble(unaInversion -> unaInversion.getMonto()).sum();
    	Double propinasTotales = propinas.stream().mapToDouble(unaPropina -> unaPropina.getMonto()).sum();
    	
    	lblDineroEnMano.setText(" "+ (Double.parseDouble(lblMercaderia.getText()) - Double.parseDouble(lblGastos.getText())
    			- mercaderiaTotal + inversionesTotales + propinasTotales - egresosTotales));
    
		
    }
    
    public List<TextField> generarListNumericos() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtMes);
    	productosAValidar.add(txtAnio);
    	productosAValidar.add(txtDia);
    	
    	return productosAValidar;
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			ventas = FXCollections.observableArrayList();
			ventas = obtenerDatos.obtenerVentas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			obtenerDatos = new ObtenerDatos();
			gastos = FXCollections.observableArrayList();
			gastos = obtenerDatos.obtenerGastosGenerales();
			gastosDiarios = FXCollections.observableArrayList();
			gastosDiarios = obtenerDatos.obtenerGastosDiarios();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		txtDia.setText(""+LocalDate.now().getDayOfMonth());
		txtMes.setText(""+LocalDate.now().getMonthValue());
		txtAnio.setText(""+LocalDate.now().getYear());
		
		try {
			mostrarIngresos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
