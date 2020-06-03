package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Alertas.Alerta;
import Alertas.Validaciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IngresoController {
	
    @FXML
    private TextField etClave;

    @FXML
    private TextField etNombreUsuario;

    @FXML
    private Label txtNombreUsuario;

    @FXML
    private Button btnIngresar;

    @FXML
    private Label txtClave;
    
    List<TextField> txtfiles = new ArrayList<>();

    @FXML
    void onIngresarClick(ActionEvent event) {
    	txtfiles.add(etClave);
    	txtfiles.add(etNombreUsuario);
    	
    	if (Validaciones.validarCajasDeTextos(txtfiles)){
    		new Alerta().errorAlert("Debe ingresar un nombre y una contraseña", "Error de Ingreso");
    	}else {
    		if(etNombreUsuario.getText().equalsIgnoreCase("acaidevoto") && etClave.getText().equalsIgnoreCase("123456")) {
    			verMenuPrincipal("MenuPrincipal","Menu Principal");      	
    			Stage stage = (Stage) btnIngresar.getScene().getWindow();
    			stage.close();
    		}else if(etNombreUsuario.getText().equalsIgnoreCase("admin") && etClave.getText().equalsIgnoreCase("admin")) {
    			verMenuPrincipal("Administrador","Administrador");    
    			Stage stage = (Stage) btnIngresar.getScene().getWindow();
            	stage.close();
    		}else {
    			new Alerta().errorAlert("Verifique los datos ingresados", "Error datos ingresados");
    		}    		
    	}
    }
	
	public void verMenuPrincipal(String ventana,String title) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(""+ventana+".fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle(title);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    @FXML
    void onSalirClick(ActionEvent event) {
    	Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("¿Estas seguro que desea salir del sistema?", "Confirmación");
    	if (action.get() == ButtonType.OK) {
    			System.exit(0);
    	}
    }
}