package application;

import Alertas.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VerGastosDiarios {

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private Label lblGastosTotales;

    @FXML
    private TableColumn<?, ?> colDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnEliminarGasto;

    @FXML
    private Label lblGastosDiarios;

    @FXML
    private TableView<?> tblGastos;

    @FXML
    private Button btnEditarGasto;

    @FXML
    private DatePicker dateGastoDiario;

    @FXML
    private TableColumn<?, ?> colCantidad;

    @FXML
    void onEliminarGastoClick(ActionEvent event) {

    }

    @FXML
    void onEditarGastoClick(ActionEvent event) {

    }

    @FXML
    void onVolverClick(ActionEvent event) {

    }

    @FXML
    void onSeleccionarDiaClick(ActionEvent event) {
    	new Alerta().informationAlert("FUNCIONA", "SISI FUNCIONA");
    }

}
