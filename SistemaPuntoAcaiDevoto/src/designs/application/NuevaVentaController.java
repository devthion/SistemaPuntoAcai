package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class NuevaVentaController {

    @FXML
    private TableColumn<?, ?> colClieDireccion;

    @FXML
    private Label lblCantidadItem;

    @FXML
    private Button btnRealizarVenta;

    @FXML
    private Button btnNuevoCliente;

    @FXML
    private TableView<?> tblProductosVenta;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private TableView<?> tblClientes;

    @FXML
    private TableColumn<?, ?> colClieApellido;

    @FXML
    private TableColumn<?, ?> colProdVentaCantidad;

    @FXML
    private TableColumn<?, ?> colClieDni;

    @FXML
    private TableColumn<?, ?> colProdKilos;

    @FXML
    private TableColumn<?, ?> colProdVentaPrecioTotal;

    @FXML
    private TextField txtNombreABuscar;

    @FXML
    private TableColumn<?, ?> colClieNombre;

    @FXML
    private TableView<?> tblProductos;

    @FXML
    private Button btnMas;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<?, ?> colProdNombre;

    @FXML
    private TableColumn<?, ?> colProdVentaNombre;

    @FXML
    private Button btnAgregarAlCarrito;

    @FXML
    private Button btnMenos;

    @FXML
    private TextField txtPrecioTotal;

    @FXML
    private TableColumn<?, ?> colProdStock;

    @FXML
    void onAgregarAlCarritoClick(ActionEvent event) {

    }

    @FXML
    void btnNuevoClienteClick(ActionEvent event) {

    }

    @FXML
    void onBuscarClienteClick(ActionEvent event) {

    }

    @FXML
    void onMenosClick(ActionEvent event) {

    }

    @FXML
    void onMasClick(ActionEvent event) {

    }

    @FXML
    void onRealizarVentaClick(ActionEvent event) {

    }

    @FXML
    void onVolverClick(ActionEvent event) {

    }

}
