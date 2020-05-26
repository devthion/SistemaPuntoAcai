package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class VerGraficoVentas implements Initializable {
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private LineChart<?, ?> linechart;

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		XYChart.Series series = new XYChart.Series();
		
		series.getData().add(new XYChart.Data("1",23));
		series.getData().add(new XYChart.Data("2",4));
		series.getData().add(new XYChart.Data("3",43));
		series.getData().add(new XYChart.Data("1",16));
		
		linechart.getData().addAll(series);
	}

    

}
