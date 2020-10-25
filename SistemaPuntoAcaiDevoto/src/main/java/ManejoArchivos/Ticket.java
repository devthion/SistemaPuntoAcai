package ManejoArchivos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.SQLException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import ConexionBD.ObtenerDatos;
import Ventas.Item;
import Ventas.Venta;

public class Ticket {

	
	public void obtenerTicketVenta(Venta unaVenta) {

		
		try {
			ObtenerDatos obtenerDatos = new ObtenerDatos();
			try {
				String nombreTicketString = generarTicket(unaVenta);
				imprimirTicket(nombreTicketString, obtenerDatos.obtenerDispositivo(1));
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); //TIRAR ALERTA DE ERROR EN GENERACION DE TICKET
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//TIRAR ALERTA DE ERROR DE CONEXION 
		}
	}
	
	public String generarTicket(Venta unaVenta) throws IOException {
		String separador = "------------------------------------------------ \n";
    	String formatoTitulo ="%-13s %-20s %-13s%n";
    	String titulo = String.format(formatoTitulo, "", "  ACAI M CABA","");
    	
    	String listadoItems = "";
    	String formatoListaItemString = "%-40s %-10s%n%n";
    	for(Item item : unaVenta.getItems()) {
    		String detalleProducto =item.getNombreProducto()+" X"+ String.valueOf(item.getCantidad());
    		listadoItems +=String.format(formatoListaItemString, detalleProducto, item.getPrecioFinal());
    		
    	}
    	
    	String precioTotalString = String.format(formatoListaItemString, "Total:", String.valueOf(unaVenta.getVenta_precioTotal()));
    	
    	
    	
    	String formatStr = "%-8s %-20s%n";
    	String format2Str = "%-20s %n";
    	String DireccionEmpresa = String.format(format2Str,"Arregui 6600, Versalles, CABA");
    	String TelEmpresa = String.format(format2Str,"Tel: 11 3165-8780");
    	String MailEmpresa = String.format(format2Str,"maracaibocaba@gmail.com");
    	
    	String cliente = String.format(formatStr,"Cliente: ", unaVenta.getDatosCliente());
    	
    	String clienteTel = String.format(formatStr,"Tel: ", unaVenta.getClienteContacto());
    	
    	String direccionString = String.format(formatStr, "Direccion: ", unaVenta.getDireccionCliente());
    	
    	String observacionString = String.format(formatStr, "Observacion: ", unaVenta.getObservacion());
    	
    	String costoEnvioString = String.format(formatoListaItemString, "Envio: ", unaVenta.getEnvioPrecio());
    	
    	String formatoListaItem2String = "%-10s %-35s%n";
    	String tipoDePagoString = String.format(formatoListaItem2String, "Tipo de Pago: ", unaVenta.getTipoDePago());
    	
    	
    	
    	
    	
    	String ticket = titulo+"\n"+DireccionEmpresa+TelEmpresa+MailEmpresa+"\n\n"+separador+ cliente +clienteTel
    			+ direccionString + observacionString + separador + listadoItems 
    			+ separador + costoEnvioString + precioTotalString +"\n"+ tipoDePagoString ;
    	
    	byte[] buff = ticket.getBytes();
    	
		RandomAccessFile ref = new RandomAccessFile("Tickets/"+unaVenta.getFechaEntrega().toString()+unaVenta.getDatosCliente().toUpperCase()+".txt", "rw");
		ref.write(buff);
		return String.valueOf(unaVenta.getFechaEntrega().toString()+unaVenta.getDatosCliente().toUpperCase());	

	}
	
	public void imprimirTicket(String ticket, int dispositivo) throws FileNotFoundException {
	
  	  DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE ;
  	  PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
  	  //aset.add(MediaSizeName.NA_LETTER);
  	  PrintService[] pservices = PrintServiceLookup.lookupPrintServices(
  	      null, null);
  	  
  	  DocPrintJob pj = pservices[dispositivo].createPrintJob();
  	  InputStream contenido = new FileInputStream("Tickets/"+ticket+".txt");//mi texto generado
  	  Doc doc = new SimpleDoc(contenido, flavor, null);
  	  try {
			pj.print(doc, null);
		} catch (PrintException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
}
