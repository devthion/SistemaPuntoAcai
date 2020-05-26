package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ConexionBD.ObtenerDatos;
import ModelosGraficos.ClientesPorBarrio;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


public class VerGraficoClientesPorBarrio implements Initializable {
	
	@FXML
    private BarChart<?, ?> barchart;
	
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    private List<ClientesPorBarrio> clientesPorBarrioList = new ArrayList<ClientesPorBarrio>();
    ObtenerDatos obtenerDatos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
