package ManejoArchivos;

import java.sql.SQLException;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;

import ConexionBD.InsertarDatos;
import ConexionBD.ObtenerDatos;

public class ConfigurarImpresora {
	
	
	public void  settearImpresora() {
		try {
			ObtenerDatos obtenerDatos = new ObtenerDatos();
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
		      	  //System.out.println(obtenerDatos.obtenerDispositivo(1));
		      	  if(obtenerDatos.obtenerDispositivo(1)==-1){//le paso el valor de dispositivo que quiero (1=impresora)
		      		insertarDatos.insertarDispositivo(1, i);
		      		System.out.println("SE INSERTO EL DISPOSITIVO" + i);
		      	  }else {
		      		insertarDatos.actualizarDispositivo(1, i);
		      		System.out.println("SE ACTUALIZO EL DISPOSITIVO" + i);
		      	  }
		      	  //aca me gustaria que haya una query de sql que en caso de clave duplicada, actualice el valor. Pero por ahora	
		      	  //no me quedo otra que hacer ese if que devuelve 0 si traigo un valor de dispositivo con un id que no existe
		      	  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //TODO TIRAR ALERTA SI NO HAY CONEXION
		}
		
		
      	  
	}

}
