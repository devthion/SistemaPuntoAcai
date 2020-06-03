package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ResourceBundle;

import Alertas.Alerta;
import Alertas.Validaciones;
import ConexionBD.ObtenerDatos;
import Ventas.Venta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VerVentasController implements Initializable{

	@FXML
    private MenuItem slipOctubre;

	@FXML
	private MenuItem slipFebrero;

	@FXML
	private MenuItem slipMarzo;
	
    @FXML
    private Label lblDineroTotal;

	@FXML
	private MenuItem slipEnero;

	@FXML
	private MenuItem slipSeptiembre;
	
    @FXML
    private TextField txtAnio;

	@FXML
	private MenuItem slipJulio;

	@FXML
	private MenuItem slipAbril;

	@FXML
	private MenuItem slipAgosto;

	@FXML
	private MenuItem slipJunio;

	@FXML
	private MenuItem slipNoviembre;

	@FXML
	private MenuItem slipDiciembre;

    @FXML
    private TableColumn<Venta, LocalDate> colFecha;
    
    @FXML
    private MenuItem slipMayo;
    
    @FXML
    private SplitMenuButton slipMenuMes;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Venta, Double> colGanancia;

    @FXML
    private TableView<Venta> tblVentas;

    @FXML
    private TableColumn<Venta, String> colCliente;

    @FXML
    private TableColumn<Venta, Double> colMontoTotal;
    
    @FXML
    private Label lblGananciasVentas;
    
    private ObservableList<Venta> ventas;
    private ObservableList<Venta> ventasPorMes;

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setResizable(false);
			stage.setTitle("Menu Principal");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtAnio.setText(""+LocalDate.now().getYear());
		ObtenerDatos obtenerDatos;
		try {
			obtenerDatos = new ObtenerDatos();
			ventas = FXCollections.observableArrayList();
			ventas = obtenerDatos.obtenerVentas();
			System.out.println(ventas.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.tblVentas.setItems(ventas);
		
		this.colCliente.setCellValueFactory(new PropertyValueFactory<Venta, String>("datosCliente"));
		this.colFecha.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("fecha"));
		this.colGanancia.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_ganancia"));
		this.colMontoTotal.setCellValueFactory(new PropertyValueFactory<Venta, Double>("venta_precioTotal"));
		
		//HAY QUE RESTARLE LOS COSTOS TOTALES
		lblGananciasVentas.setText(ventas.stream().mapToDouble(unaVenta-> unaVenta.getVenta_ganancia()).sum()+" $");
    	lblDineroTotal.setText(ventas.stream().mapToDouble(unaVenta-> unaVenta.getVenta_precioTotal()).sum()+" $");
	}
	

    @FXML
    void onEneroClick(ActionEvent event) {
    	mostrarVentasPorMes(1);
    }

    @FXML
    void onFebreroClick(ActionEvent event) {
    	mostrarVentasPorMes(2);
    }

    @FXML
    void onMarzoClick(ActionEvent event) {
    	mostrarVentasPorMes(3);
    }

    @FXML
    void onAbrilClick(ActionEvent event) {
    	mostrarVentasPorMes(4);
    }	
    
    @FXML
    void onMayoClick(ActionEvent event) {
    	mostrarVentasPorMes(5);
    }

    @FXML
    void onJunioClick(ActionEvent event) {
    	mostrarVentasPorMes(6);
    }

    @FXML
    void onJulioClick(ActionEvent event) {
    	mostrarVentasPorMes(7); 	
    }

    @FXML
    void onAgostoClick(ActionEvent event) {
    	mostrarVentasPorMes(8);
    }

    @FXML
    void onSeptiembreClick(ActionEvent event) {
    	mostrarVentasPorMes(9);
    }

    @FXML
    void onOctubreClick(ActionEvent event) {
    	mostrarVentasPorMes(10);
    }

    @FXML
    void onNoviembreClick(ActionEvent event) {
    	mostrarVentasPorMes(11);
    }

    @FXML
    void onDiciembreClick(ActionEvent event) {
    	mostrarVentasPorMes(12);
    }
    
    public void mostrarVentasPorMes(int mes) {
    	if(Validaciones.validarCajaNumerica(txtAnio)) {
   		 new Alerta().errorAlert("Debe ingresar un año valido", "Error de Datos");
	   	}else{
	    	ventasPorMes =ventas.filtered(unaVenta-> unaVenta.getMes()==mes && unaVenta.getAnio()==Integer.parseInt(txtAnio.getText()));
	    	this.tblVentas.setItems(ventasPorMes);
	    	lblGananciasVentas.setText(ventasPorMes.stream().mapToDouble(unaVenta-> unaVenta.getVenta_ganancia()).sum()+" $");
	    	lblDineroTotal.setText(ventasPorMes.stream().mapToDouble(unaVenta-> unaVenta.getVenta_precioTotal()).sum()+" $");
	   	}
    }
}
