package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import Ventas.CajaCerrada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CerrarCajaMenu {

    @FXML
    private Button btnCerrarCajaDiaria;

    @FXML
    private Button btnCerrarCajaPasada;

    @FXML
    private Button btnVolver;

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
    void onCerrarDiaria(ActionEvent event) throws SQLException {
    	List<CajaCerrada> cajas = new ArrayList<>();
    	
    	ObtenerDatos obtenerDatos = new ObtenerDatos();
		cajas = obtenerDatos.obtenerCajasCerradas();
		
		
    	if(cajas.stream().anyMatch(unaCaja -> unaCaja.getFecha().equals(LocalDate.now()))) {
    		new Alerta().errorAlert("Ya se cerro la caja del dia de hoy", "Caja Cerrada");
    	}else {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			loader.setLocation(getClass().getResource("CerrarCaja.fxml"));
    			AnchorPane root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root,1300,650);
    			Stage stage = new Stage();
    			stage.setScene(scene);
    			stage.resizableProperty().setValue(Boolean.FALSE);
    			stage.setResizable(false);
    			stage.setTitle("Cerrar Caja");
    			stage.show();
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
        	Stage stage = (Stage) btnCerrarCajaDiaria.getScene().getWindow();
        	stage.close();
    	}
    }

    @FXML
    void onCerrarPasada(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("CerrarCajaPasada.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Cerrar Caja");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnCerrarCajaDiaria.getScene().getWindow();
    	stage.close();
    }

}
