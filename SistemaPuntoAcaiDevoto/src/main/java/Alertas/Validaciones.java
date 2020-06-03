package Alertas;

import java.util.List;

import javafx.scene.control.TextField;

public class Validaciones {
	
    public static boolean validarCajasDeTextos(List<TextField> productosAValidar) {
    	return productosAValidar.stream().anyMatch(unTxt -> unTxt.getText().isEmpty());
    }
    
    public static boolean validarCajasNumericas(List<TextField> productosAValidar) {
    	boolean error = false;
    	for(TextField unText: productosAValidar) {
       		try{
    				Integer.parseInt(unText.getText());
    			}catch (Exception e){
    				error = true;
    		}
    	}
    	return error;
    }
}