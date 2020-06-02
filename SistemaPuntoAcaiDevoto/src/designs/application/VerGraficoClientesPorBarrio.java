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


public class VerGraficoClientesPorBarrio implements Initializable{
	
    @FXML
    private BarChart<?, ?> barchart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    private List<ClientesPorBarrio> clientesPorBarrioList = new ArrayList<ClientesPorBarrio>();
    ObtenerDatos obtenerDatos;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		graficoClientesPorBarrio();
		
	}
	
	public void graficoClientesPorBarrio() {
		XYChart.Series set1= new XYChart.Series<>();
		try {
			obtenerDatos=new ObtenerDatos();
			clientesPorBarrioList = obtenerDatos.obtenerClientesPorBarrio();
			
			clientesPorBarrioList.stream().forEach(unBarrioConClientes -> set1.getData().add(new XYChart.Data(unBarrioConClientes.getBarrio(), unBarrioConClientes.getCantidadClientes())));
			barchart.getData().add(set1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
