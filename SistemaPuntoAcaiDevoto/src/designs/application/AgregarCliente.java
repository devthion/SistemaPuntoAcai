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
    private Button btnEditarCliente;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtDni;

    @FXML
    private SplitMenuButton slipTipoCliente;
    
    @FXML
    private SplitMenuButton slipComoLlego;
    
    private Cliente nuevoCliente;
    private ObservableList<Cliente> clientes;

    @FXML
    void onGuardarClienteClick(ActionEvent event) {	
    	Cliente cliente = generarCliente();
	
    	//if(!clientes.contains(cliente)) {
    		this.nuevoCliente = cliente;
    		cliente.almacenarCliente();
    		Alerta.informationAlert("Se ha añadido correctamente", "Informacion");
			Stage stage = (Stage) btnGuardarCliente.getScene().getWindow();
	    	stage.close();
    	//}else {
    		//Alerta.errorAlert("El cliente ingresado ya existe en la base de datos", "Cliente Repetido");
    	//}
    	
    }
    
    @FXML
    void editarCliente(ActionEvent event) {
    	Cliente cliente = generarCliente();
    	nuevoCliente.modificarCliente(cliente);
    	Alerta.informationAlert("Se ha editado el cliente", "Informacion");
	
    	Stage stage = (Stage) btnGuardarCliente.getScene().getWindow();
    	stage.close();
    	

    }
    
    public void initEditar(Cliente clienteEditable) {
    	
    	btnGuardarCliente.setVisible(false);
    	
    	this.txtNombre.setText(clienteEditable.getNombre());
    	this.txtApellido.setText(clienteEditable.getApellido());
    	this.txtDni.setText((""+clienteEditable.getDni()));
    	this.txtNumero.setText(""+clienteEditable.getNumero());
    	this.txtCalle.setText(clienteEditable.getCalle());
    	this.txtBarrio.setText(clienteEditable.getBarrio());
    	this.txtCodigoPostal.setText(""+clienteEditable.getCodPostal());
    	this.txtEmail.setText(clienteEditable.getEmail());
    	this.txtTelefono.setText(""+clienteEditable.getTelefono());
    	this.slipTipoCliente.setText(clienteEditable.getTipo());
    	this.slipComoLlego.setText(clienteEditable.getComoLlego());
    	
    	nuevoCliente = clienteEditable;
    	
    	
    }
    
    public void initAgregar() {
    	btnEditarCliente.setVisible(false);
    }
    
    public Cliente generarCliente() {
    	String nombre = this.txtNombre.getText().toString();
    	String apellido = this.txtApellido.getText();
    	int dni = Integer.parseInt(txtDni.getText());
    	int numero = Integer.parseInt(txtNumero.getText());
    	String calle = this.txtCalle.getText();
    	String barrio = this.txtBarrio.getText();
    	int codPostal = Integer.parseInt(this.txtCodigoPostal.getText());
    	String email=this.txtEmail.getText();
    	int telefono=Integer.parseInt(txtTelefono.getText());
    	String tipo = slipTipoCliente.getText();
    	String comoLlego = slipComoLlego.getText();
    	
    	Direccion direccion = new Direccion(calle, numero, barrio, codPostal);
    	return new Cliente(dni, nombre, apellido, telefono, email, direccion, tipo, comoLlego, 0);
    }
    
    @FXML
    void onConsumidorFinal(ActionEvent event) {
    	this.slipTipoCliente.setText("CONSUMIDOR FINAL");
    }

    @FXML
    void onMayorista(ActionEvent event) {
    	this.slipTipoCliente.setText("MAYORISTA");
    }
    
    @FXML
    void onInstagram(ActionEvent event) {
    	this.slipComoLlego.setText("Instagram");
    }

    @FXML
    void onUnConocido(ActionEvent event) {
    	this.slipComoLlego.setText("Un Conocido");
    }

    @FXML
    void onFolleto(ActionEvent event) {
    	this.slipComoLlego.setText("Folleto");
    }

    @FXML
    void onOtro(ActionEvent event) {
    	this.slipComoLlego.setText("Otro");
    }


	public Cliente getNuevoCliente() {
		return nuevoCliente;
	}
    

}
