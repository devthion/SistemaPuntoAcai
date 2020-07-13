package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerInversiones {

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private MenuItem slipOctubre;

    @FXML
    private Label lblInversionesTotal;

    @FXML
    private TableView<?> tblInversiones;

    @FXML
    private Label lblInversionesMes;

    @FXML
    private MenuItem slipFebrero;

    @FXML
    private MenuItem slipMarzo;

    @FXML
    private MenuItem slipMayo;

    @FXML
    private Button btnEditarInversion;

    @FXML
    private MenuItem slipEnero;

    @FXML
    private MenuItem slipSeptiembre;

    @FXML
    private TableColumn<?, ?> colDetalle;

    @FXML
    private Button btnVolver;

    @FXML
    private MenuItem slipJulio;

    @FXML
    private SplitMenuButton slipMenuMes;

    @FXML
    private MenuItem slipAbril;

    @FXML
    private MenuItem slipAgosto;

    @FXML
    private TextField txtAnio;

    @FXML
    private MenuItem slipJunio;

    @FXML
    private TableColumn<?, ?> colCantidad;

    @FXML
    private MenuItem slipNoviembre;

    @FXML
    private MenuItem slipDiciembre;

    @FXML
    void onEditarInversionClick(ActionEvent event) {

    }

    @FXML
    void onVolverClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("Ingresos.fxml"));
    		AnchorPane root = (AnchorPane) loader.load();
    		Scene scene = new Scene(root,1300,650);
    		Stage stage = new Stage();
    		stage.setScene(scene);
    		stage.resizableProperty().setValue(Boolean.FALSE);
    		stage.setResizable(false);
    		stage.setTitle("Inversiones");
    		stage.show();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onEneroClick(ActionEvent event) {

    }

    @FXML
    void onFebreroClick(ActionEvent event) {

    }

    @FXML
    void onMarzoClick(ActionEvent event) {

    }

    @FXML
    void onAbrilClick(ActionEvent event) {

    }

    @FXML
    void onMayoClick(ActionEvent event) {

    }

    @FXML
    void onJunioClick(ActionEvent event) {

    }

    @FXML
    void onJulioClick(ActionEvent event) {

    }

    @FXML
    void onAgostoClick(ActionEvent event) {

    }

    @FXML
    void onSeptiembreClick(ActionEvent event) {

    }

    @FXML
    void onOctubreClick(ActionEvent event) {

    }

    @FXML
    void onNoviembreClick(ActionEvent event) {

    }

    @FXML
    void onDiciembreClick(ActionEvent event) {

    }

}
