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
import Ventas.CajaCerrada;
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
    private Label lblIdealMes;

    @FXML
    private Label lblRealAnio;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblIdealDia;

    @FXML
    private Label lblRealDia;

    @FXML
    private TextField txtMes;

    @FXML
    private TextField txtAnio;

    @FXML
    private Label lblRealMes;

    @FXML
    private Label lblIdealAnio;

    @FXML
    private TextField txtDia;
    
    @FXML
    private Button btnVerIngresos;
    
    private List<CajaCerrada> cajas = new ArrayList<>();

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
    	
    	int dia=Integer.parseInt(txtDia.getText());
    	int mes=Integer.parseInt(txtMes.getText());
    	int anio=Integer.parseInt(txtAnio.getText());
    	
    	lblIdealDia.setText(""+cajas.stream().filter(unaCaja -> (unaCaja.getFecha().getDayOfMonth()== dia) &&
    			unaCaja.getFecha().getMonthValue() == mes && unaCaja.getFecha().getYear() == anio).mapToDouble(unaCaja -> unaCaja.getMonto_ideal()).sum());
		
    	lblRealDia.setText(""+cajas.stream().filter(unaCaja -> (unaCaja.getFecha().getDayOfMonth()== dia) &&
    			unaCaja.getFecha().getMonthValue() == mes && unaCaja.getFecha().getYear() == anio).mapToDouble(unaCaja -> unaCaja.getMonto_real()).sum());
    	
    	lblIdealMes.setText(""+cajas.stream().filter(unaCaja ->unaCaja.getFecha().getMonthValue() == mes 
    			&& unaCaja.getFecha().getYear() == anio).mapToDouble(unaCaja -> unaCaja.getMonto_ideal()).sum());
    	
    	lblRealMes.setText(""+cajas.stream().filter(unaCaja ->unaCaja.getFecha().getMonthValue() == mes 
    			&& unaCaja.getFecha().getYear() == anio).mapToDouble(unaCaja -> unaCaja.getMonto_real()).sum());
    
    	
    	lblIdealAnio.setText(""+cajas.stream().filter(unaCaja ->unaCaja.getFecha().getYear() == anio).mapToDouble(unaCaja -> unaCaja.getMonto_ideal()).sum());
    	
    	lblRealAnio.setText(""+cajas.stream().filter(unaCaja ->unaCaja.getFecha().getYear() == anio).mapToDouble(unaCaja -> unaCaja.getMonto_real()).sum());
		
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
