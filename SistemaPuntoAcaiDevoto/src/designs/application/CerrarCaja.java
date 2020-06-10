package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CerrarCaja {

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TextField txtMontoReal;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtMontoIdeal;

    @FXML
    private Button btnCerrarCaja;

    @FXML
    private TableColumn<?, ?> colGanancia;

    @FXML
    private TableView<?> tblVentas;

    @FXML
    private TableColumn<?, ?> colCliente;

    @FXML
    private TableColumn<?, ?> colMontoTotal;

    @FXML
    void onVolverClick(ActionEvent event) {

    }

    @FXML
    void onCerrarCajaClick(ActionEvent event) {

    }

}
