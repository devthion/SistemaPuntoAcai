package ManejoArchivos;

import java.sql.SQLException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;

import ConexionBD.InsertarDatos;

public class ConfigurarImpresora {
	
	
	public void  settearImpresora() {
		try {
			InsertarDatos insertarDatos = new InsertarDatos();
			
			PrintService[] pservices = PrintServiceLookup.lookupPrintServices(
		      	      null, null);
		      	  int i;
		      	  switch(pservices.length) {
		      	  case 0:
		      	    System.out.println(
		      	        "Error: No PrintService Found"); //TODO AGREGAR ALERT
		      	    return;
		      	  case 1:
		      	    i = 1;//SETTEA LA PRIMERA POR DEFAULT
		      	    break;
		      	  default:
		      	    i = JOptionPane.showOptionDialog(null, //este null creo que es lo que le da diseño
		      	        "Pick a printer", "Choice",
		      	        JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE,
		      	        null, pservices, pservices[0]);
		      	    break;
		      	  }
		      	  if (i < 0) {
		      	    return;
		      	  }
		      	  
		      	  insertarDatos.insertarDispositivo(1, i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //TODO TIRAR ALERTA SI NO HAY CONEXION
		}
		
		
      	  
	}

}
