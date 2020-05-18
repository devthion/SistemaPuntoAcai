package application;

import Alertas.Alerta;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    	int numero = Integer.parseInt(txtNumero.getText());
    	String mail=this.txtEmail.getText();
    	
    	//EJEMPLOS DE PRUEBA
    	Direccion direccion = new Direccion("asd", 545, "asdasd", 5456);
    	Cliente c = new Cliente(dni, nombre, apellido, 41, "ads", direccion, "CONSUMIDROFINAL", "WPP", 4854);
    	
    	cliente=new Cliente(dni, nombre, apellido, numero, mail, direccion, "CONSUMIDORFINAL", "wpp", 0);
    	
    	if(!clientes.contains(c)) {
    		/*this.cliente = c;*/
    		cliente.almacenarCliente();
    		Alerta.informationAlert("Se ha a�adido correctamente", "Informacion");
			Stage stage = (Stage) btnGuardarCliente.getScene().getWindow();
	    	stage.close();
    	}else {
    		Alerta.errorAlert("El cliente ingresado ya existe en la base de datos", "Cliente Repetido");
    	}
    	

    }
    
    public void initAtributos(ObservableList<Cliente> clientes) {
    	this.clientes = clientes;
    }


	public Cliente getCliente() {
		return cliente;
	}
    

}
