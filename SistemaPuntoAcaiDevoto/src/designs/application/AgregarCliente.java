package application;

import java.sql.SQLException;

import Alertas.Alerta;
import ConexionBD.ObtenerDatos;
import ConexionBD.Querys;
import ModelosClientes.Cliente;
import ModelosClientes.Direccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private TextField txtRubro;

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
    
    @FXML
    private Button btnVolver;
    
    @FXML
    private Label lblRubro;
    
    private Cliente nuevoCliente;
    private ObservableList<Cliente> clientes;
    
    
    public boolean existeElUsuarioEnLaBd() throws SQLException {
    	ObtenerDatos obtenerDatos = new ObtenerDatos();
		clientes = FXCollections.observableArrayList();
		clientes = obtenerDatos.obtenerClientes(new Querys().queryClientes());
    	return clientes.stream().anyMatch(unCliente -> unCliente.tieneElMismoDni(Integer.parseInt(txtDni.getText())));
    }

    @FXML
    void onGuardarClienteClick(ActionEvent event) throws SQLException {
    	if(!existeElUsuarioEnLaBd()) {
    		Cliente cliente = generarCliente();
    		this.nuevoCliente = cliente;
    		cliente.almacenarCliente();
    		new Alerta().informationAlert("Se ha añadido correctamente", "Informacion");
			Stage stage = (Stage) btnGuardarCliente.getScene().getWindow();
	    	stage.close();
    	}else {
    		new Alerta().errorAlert("El cliente ingresado ya existe en la base de datos", "Cliente Repetido");
    	}
    	
    }
    
    @FXML
    void editarCliente(ActionEvent event) throws SQLException {
    	Cliente cliente = generarCliente();
    	if(!existeElUsuarioEnLaBd()||(cliente.getDni()==nuevoCliente.getDni())) {
        	nuevoCliente.modificarCliente(cliente);
        	new Alerta().informationAlert("Se ha editado el cliente", "Informacion");
        	Stage stage = (Stage) btnGuardarCliente.getScene().getWindow();
        	stage.close();
    	}else {
    		new Alerta().errorAlert("Asigno un dni que ya existe en la base de datos", "Error en la modificacion");
    	}

    }
    
    
    public void initEditar(Cliente clienteEditable) {
    	
    	btnGuardarCliente.setVisible(false);
    	if(clienteEditable.getTipo().equalsIgnoreCase("mayorista")) {
    		this.txtRubro.setText(clienteEditable.getRubro());
    	}else {
    		lblRubro.setVisible(false);
    		txtRubro.setVisible(false);
    		txtRubro.setText(clienteEditable.getRubro());
    	}
    	
    	this.txtNombre.setText(clienteEditable.getNombre());
    	this.txtApellido.setText(clienteEditable.getApellido());
    	this.txtDni.setText((""+clienteEditable.getDni()));
    	this.txtNumero.setText(""+clienteEditable.getNumero());
    	this.txtCalle.setText(clienteEditable.getCalle());
    	this.txtBarrio.setText(clienteEditable.getBarrio());
    	this.txtCodigoPostal.setText(""+clienteEditable.getCodPostal());
    	this.txtEmail.setText(clienteEditable.getEmail());
    	this.txtTelefono.setText(""+clienteEditable.getTelefono());
    	this.slipTipoCliente.setText(clienteEditable.getTipo().toLowerCase());
    	this.slipComoLlego.setText(clienteEditable.getComoLlego());
    	
    	nuevoCliente = clienteEditable;
    	
    	
    }
    
    public void initAgregar() {
    	btnEditarCliente.setVisible(false);
    	lblRubro.setVisible(false);
		txtRubro.setVisible(false);
    }
    
    public Cliente generarCliente() {
    	String nombre = this.txtNombre.getText().toString().toLowerCase();
    	String apellido = this.txtApellido.getText().toLowerCase();
    	int dni = Integer.parseInt(txtDni.getText());
    	int numero = Integer.parseInt(txtNumero.getText());
    	String calle = this.txtCalle.getText().toLowerCase();
    	String barrio = this.txtBarrio.getText().toLowerCase();
    	int codPostal = Integer.parseInt(this.txtCodigoPostal.getText());
    	String email=this.txtEmail.getText().toLowerCase();
    	int telefono=Integer.parseInt(txtTelefono.getText());
    	String tipo = slipTipoCliente.getText().toLowerCase();
    	String comoLlego = slipComoLlego.getText().toLowerCase();
    	String rubro = txtRubro.getText().toLowerCase();
    	
    	Direccion direccion = new Direccion(calle, numero, barrio, codPostal);
    	return new Cliente(dni, nombre, apellido, telefono, email, direccion, tipo, comoLlego, rubro);
    }
    
    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void onConsumidorFinal(ActionEvent event) {
    	this.slipTipoCliente.setText("CONSUMIDOR FINAL");
    	txtRubro.setText("consumidor final");
    	lblRubro.setVisible(false);
		txtRubro.setVisible(false);
    }

    @FXML
    void onMayorista(ActionEvent event) {
    	this.slipTipoCliente.setText("MAYORISTA");
    	txtRubro.setText("");
    	lblRubro.setVisible(true);
		txtRubro.setVisible(true);
    }
    
    @FXML
    void onPorInstagram(ActionEvent event) {
    	this.slipComoLlego.setText("Por Instagram");
    }

    @FXML
    void onPorRecomendacion(ActionEvent event) {
    	this.slipComoLlego.setText("Por Recomendacion");
    }

    @FXML
    void onPorOtraPersona(ActionEvent event) {
    	this.slipComoLlego.setText("Por Otra Persona");
    }

    @FXML
    void onOtro(ActionEvent event) {
    	this.slipComoLlego.setText("Otro");
    }
    
	public Cliente getNuevoCliente() {
		return nuevoCliente;
	}

}
