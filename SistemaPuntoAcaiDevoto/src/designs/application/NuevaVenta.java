package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class NuevaVenta {

    @FXML
    private Button btnMas;

    @FXML
    private Label lblStock;

    @FXML
    private Button btnMenos;

    @FXML
    void onMenosClick(ActionEvent event) {
    	lblStock.setText("Holis");
    }

    @FXML
    void onMasClick(ActionEvent event) {
    	lblStock.setText("Chau");

    }

}
