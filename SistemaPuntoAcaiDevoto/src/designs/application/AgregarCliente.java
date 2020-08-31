package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Alertas.Alerta;
import Alertas.Validaciones;
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
    private TextField txtDpto;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtComoLlego;
    
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
    private SplitMenuButton slipTipoCliente;
    
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
    	return clientes.stream().anyMatch(unCliente -> unCliente.tieneElMismoDni(-1));
    }

    @FXML
    void onGuardarClienteClick(ActionEvent event) throws SQLException {
    	Validaciones.ponerVaciosTextsFiels(generarListOpcionales());
    	Validaciones.ponerVaciosTextsFielsNumericos(generarListOpcionalesNumericos());
    	
    	if(Validaciones.validarCajasDeTextos(generarListObligatorios()) || Validaciones.validarCajasNumericas(generarListNumericos())) {
    		new Alerta().errorAlert("Puede que falten atributos llenar o esta ingresando mal los datos", "Error en el ingreso de Datos");
    	}else {
    		Cliente cliente = generarCliente();
    		if(!existeElUsuarioEnLaBd()) {
	    		this.nuevoCliente = cliente;
	    		cliente.almacenarCliente();
	    		new Alerta().informationAlert("Se ha añadido correctamente", "Informacion");
				Stage stage = (Stage) btnGuardarCliente.getScene().getWindow();
		    	stage.close();
	    	}else {
	    		new Alerta().errorAlert("El cliente ingresado ya existe en la base de datos", "Cliente Repetido");
	    	}
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
    	this.txtNumero.setText(""+clienteEditable.getNumero());
    	this.txtCalle.setText(clienteEditable.getCalle());
    	this.txtBarrio.setText(clienteEditable.getBarrio());
    	this.txtTelefono.setText(""+clienteEditable.getTelefono());
    	this.slipTipoCliente.setText(clienteEditable.getTipo().toLowerCase());
    	
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
    	int numero = Integer.parseInt(txtNumero.getText());
    	String calle = this.txtCalle.getText().toLowerCase();
    	String barrio = this.txtBarrio.getText().toLowerCase();
    	int telefono=Integer.parseInt(txtTelefono.getText());
    	String tipo = slipTipoCliente.getText().toLowerCase();
    	String rubro = txtRubro.getText().toLowerCase();
    	
    	int dni = 0;
		try {
			dni = new ObtenerDatos().obtenerMayorDni() + 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Direccion direccion = new Direccion(calle, numero, barrio, 0);
    	return new Cliente(dni, nombre, apellido, telefono, "", direccion, tipo, "Desconocido", rubro);
    }
    
    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }
    
    public List<TextField> generarListObligatorios() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtNombre);
    	productosAValidar.add(txtRubro);
    	
    	return productosAValidar;
    }
    
    public List<TextField> generarListNumericos() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtNumero);
    	productosAValidar.add(txtTelefono);
    	
    	return productosAValidar;
    }
    
    public List<TextField> generarListOpcionales() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtApellido);
    	productosAValidar.add(txtCalle);
    	productosAValidar.add(txtBarrio);
    	
    	return productosAValidar;
    }
    
    public List<TextField> generarListOpcionalesNumericos() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtNumero);
    	productosAValidar.add(txtTelefono);
    	
    	return productosAValidar;
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

    
	public Cliente getNuevoCliente() {
		return nuevoCliente;
	}

}
