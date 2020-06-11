package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Alertas.Alerta;
import Alertas.Validaciones;
import Productos.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarProductoController {

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtCosto;

    @FXML
    private TextField txtKilos;

    @FXML
    private TextField txtCantidadMayor;

    @FXML
    private TextField txtPrecioMayor;

    @FXML
    private TextField txtPrecioUnitario;
    
    @FXML
    private TextField txtSabor;
    
    private Producto nuevoProducto;


	@FXML
    void onAgregarProductoClick(ActionEvent event) throws SQLException {
    	
    	if(Validaciones.validarCajasDeTextos(generarListTxt()) || Validaciones.validarCajasNumericas(generarListNumericos()) ) {
    		new Alerta().errorAlert("Los datos ingresados son erroneos o faltan completar algunos atributos","Error en el ingreso de Datos");
    	}else {
    		Optional<ButtonType> action =  new Alerta().preguntaConfirmacion("Desea confirmar el ingreso del producto: "+txtNombre.getText()+
    				" sabor "+ txtSabor.getText()+ " ?", "Confirmación");
        	if (action.get() == ButtonType.OK) {
	    		Producto producto = generarProducto();
	    		this.nuevoProducto = producto;
				producto.almacenarProducto();
				new Alerta().informationAlert("Se ha añadido correctamente", "Informacion");
				Stage stage = (Stage) btnAgregarProducto.getScene().getWindow();
		    	stage.close();
        	}
		}
	    	
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }
    
    public Producto generarProducto() {
    	String nombre = this.txtNombre.getText().toString().toLowerCase()+" "+this.txtSabor.getText().toString().toLowerCase();
    	double kilos = Double.parseDouble(this.txtKilos.getText());
    	double costo = Double.parseDouble(txtCosto.getText());
    	double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText());
    	double precioMayor = Double.parseDouble(this.txtPrecioMayor.getText());
    	int cantidadMayor = Integer.parseInt(this.txtCantidadMayor.getText());
    	
    	return new Producto(nombre, kilos, 0, precioUnitario, precioMayor, costo, cantidadMayor);
    }
    
    public Producto getNuevoProducto() {
		return nuevoProducto;
	}
    
    public List<TextField> generarListTxt() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtCantidadMayor);
    	productosAValidar.add(txtNombre);
    	productosAValidar.add(txtKilos);
    	productosAValidar.add(txtCosto);
    	productosAValidar.add(txtPrecioUnitario);
    	productosAValidar.add(txtPrecioMayor);
    	
    	return productosAValidar;
    }
    
    public List<TextField> generarListNumericos() {
    	
    	List<TextField> productosAValidar = new ArrayList<>();
    	productosAValidar.add(txtCantidadMayor);
    	productosAValidar.add(txtKilos);
    	productosAValidar.add(txtCosto);
    	productosAValidar.add(txtPrecioUnitario);
    	productosAValidar.add(txtPrecioMayor);
    	
    	return productosAValidar;
    }
    
    
    


}
