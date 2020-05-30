package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ConexionBD.ObtenerDatos;
import ModelosGraficos.ClientesPorBarrio;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class VerGraficos implements Initializable {
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private LineChart<?, ?> linechart;
    
    @FXML
    private BarChart<?, ?> barchart;
	
    @FXML
    private CategoryAxis xBarrios;

    @FXML
    private NumberAxis yBarrios;
    
    @FXML
    private Button btnVolver;
    
    private List<ClientesPorBarrio> clientesPorBarrioList = new ArrayList<ClientesPorBarrio>();
    ObtenerDatos obtenerDatos;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		graficoVentas();
		graficoClientesPorBarrio();
	}
	
	public void graficoVentas() {
	XYChart.Series series = new XYChart.Series();
		
		series.getData().add(new XYChart.Data("1",23));
		series.getData().add(new XYChart.Data("2",4));
		series.getData().add(new XYChart.Data("3",43));
		series.getData().add(new XYChart.Data("1",16));
		
		linechart.getData().addAll(series);
		
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
	
    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MenuPrincipal.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,1300,650);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Menu Principal");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    	Stage stage = (Stage) btnVolver.getScene().getWindow();
    	stage.close();
    }


    

}
