package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VerCajas {

    @FXML
    private TableView<?> tblCajas;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<?, ?> colMontoReal;

    @FXML
    private TableColumn<?, ?> colMontoIdeal;

    @FXML
    private Button btnEliminarCaja;

    @FXML
    private TableColumn<?, ?> colFechaCaja;

    @FXML
    void onVolverClick(ActionEvent event) {

    }

    @FXML
    void onEliminarCaja(ActionEvent event) {

    }

}
