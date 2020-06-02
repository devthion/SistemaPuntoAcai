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


public class VerGraficosVentas implements Initializable {
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private LineChart<?, ?> linechart;
    
    @FXML
    private Button btnVolver;
    
   
    ObtenerDatos obtenerDatos;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		graficoVentas();
		
	}
	
	public void graficoVentas() {
	XYChart.Series series = new XYChart.Series();
		
		series.getData().add(new XYChart.Data("1",23));
		series.getData().add(new XYChart.Data("2",4));
		series.getData().add(new XYChart.Data("3",43));
		series.getData().add(new XYChart.Data("1",16));
		
		linechart.getData().addAll(series);
		
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
