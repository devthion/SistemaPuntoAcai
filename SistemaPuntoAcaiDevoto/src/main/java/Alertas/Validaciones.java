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
    				Double.parseDouble(unText.getText());
    			}catch (Exception e){
    				error = true;
    		}
    	}
    	return error;
    }
    
    public static boolean validarCajaNumerica(TextField txTextField) {
    	boolean error = false;
    	
       		try{
       				if(txTextField.getText().isEmpty()) {
       					error = true;
       				}
       				Double.parseDouble(txTextField.getText());
    			}catch (Exception e){
    				error = true;
    		}
    	return error;
    }
    
    public static void ponerVaciosTextsFiels(List<TextField> productosAValidar) {
    	for(TextField unText: productosAValidar) {
    		if(unText.getText().isEmpty()) {
    			unText.setText("");
    		}
    	}
    }
    
    public static void ponerVaciosTextsFielsNumericos(List<TextField> productosAValidar) {
    	for(TextField unText: productosAValidar) {
    		if(unText.getText().isEmpty()) {
    			unText.setText("0");
    		}
    	}
    }
}
