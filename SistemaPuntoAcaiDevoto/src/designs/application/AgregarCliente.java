package application;

import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarCliente {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCalle;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtComoLlego;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCodigoPostal;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtBarrio;

    @FXML
    private Button btnGuardarCliente;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtDni;

    @FXML
    private SplitMenuButton slipTipoCliente;
    
    private Cliente cliente;
    private ObservableList<Cliente> clientes;


    @FXML
    void onGuardarClienteClick(ActionEvent event) {
    	
    	String nombre = this.txtNombre.getText();
    	String apellido = this.txtApellido.getText();
    	int dni = Integer.parseInt(txtDni.getText());
    	
    	Direccion direccion = new Direccion("asd", 545, "asdasd", 5456);
    	Cliente c = new Cliente(dni, nombre, apellido, 41, "ads", direccion, true, "WPP");
    	
    	if(!clientes.contains(c)) {
    		this.cliente = c;
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Informacion");
			alert.setContentText("Se ha añadido correctamente");
			alert.showAndWait();
			Stage stage = (Stage) btnGuardarCliente.getScene().getWindow();
	    	stage.close();
    	}else {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Cliente Repetido");
			alert.setTitle("Error");
			alert.setContentText("El cliente ingresado ya existe en la base de datos");
			alert.showAndWait();
    	}
    	
    	
    	

    }
    
    public void initAtributos(ObservableList<Cliente> clientes) {
    	this.clientes = clientes;
    }


	public Cliente getCliente() {
		return cliente;
	}
    

}
