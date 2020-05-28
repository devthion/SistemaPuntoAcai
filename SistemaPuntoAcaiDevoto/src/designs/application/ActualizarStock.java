package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Alertas.Alerta;
import Productos.Producto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ActualizarStock implements Initializable{

    @FXML
    private Button btnMas;

    @FXML
    private Button btnVolver;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblNombreProd;

    @FXML
    private Button btnMenos;

    @FXML
    private Button btnActualizar;
    
    private int contador;
    private Producto productoActualizar;



	@FXML
    void onMasClick(ActionEvent event) {
    	contador++;
    	lblStock.setText(""+contador);
    }

    @FXML
    void onMenosClick(ActionEvent event) {
    	contador--;
    	lblStock.setText(""+contador);
    }

    @FXML
    void onActualizarClick(ActionEvent event) throws SQLException {
    	productoActualizar.actualizarStock(contador);
    	new Alerta().informationAlert("Se ha actualizado el stock", "Actualizar Stock");
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    
    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		contador = 0;
		lblStock.setText(""+contador);
	}
	
	public void initActualizar(String producto) {
		lblNombreProd.setText(producto);
	}
	
    public Producto getProductoActualizar() {
		return productoActualizar;
	}

	public void setProductoActualizar(Producto productoActualizar) {
		this.productoActualizar = productoActualizar;
	}

}
