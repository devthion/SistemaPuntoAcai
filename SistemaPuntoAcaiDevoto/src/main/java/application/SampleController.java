package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {

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

    @FXML
    void onIngresarClick(ActionEvent event) {
    	System.out.println("Se precionio el boton ingresar"+etNombreUsuario.getText());
    }

}