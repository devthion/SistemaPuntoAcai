package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.mockito.internal.matchers.And;

import ConexionBD.ObtenerDatos;
import ModelosGraficos.ClientesPorBarrio;
import ModelosGraficos.ComoLlegoUnCliente;
import Ventas.Venta;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


public class VerGraficoComoLlegaron implements Initializable{
	
    @FXML
    private BarChart<?, ?> barchart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    private List<ComoLlegoUnCliente> comoLlegoUnCliente = new ArrayList<ComoLlegoUnCliente>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		graficoComoLlegaron();
		
	}
	
	public void graficoComoLlegaron() {
		XYChart.Series set1= new XYChart.Series<>();
		
		try {
			ObtenerDatos obtenerDatos = new ObtenerDatos();
			comoLlegoUnCliente = obtenerDatos.obtenerComoLlegaronLosClientes();
			
			comoLlegoUnCliente.stream().forEach(llegadaDeUnCliente -> set1.getData().add(new XYChart.Data(llegadaDeUnCliente.getComoLlego(),llegadaDeUnCliente.getCantidadClientes())));
			barchart.getData().add(set1);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}


}