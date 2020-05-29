package ConexionBD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ModelosClientes.Cliente;
import ModelosClientes.ClienteHibernate;
import ModelosClientes.Direccion;



public class PruebaInsertarMostrarHibernate {
	Transaction transaction = null;
    public static void main(String[] args) {

    	ClienteHibernate clie = new ClienteHibernate("Diego", "vivona", "mail@gmail.com");
   
    }
    
    public void almacenar(Object object) {
    	try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            session.beginTransaction();
            // save the student objects
            session.save(object);
            
            // commit transaction
            session.getTransaction().commit();
            //HibernateUtil.shutdown();
           
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public List<ClienteHibernate> obtenerClientes() {
    	 List<ClienteHibernate> lista = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	session.beginTransaction();
        	Query query = session.createQuery("from ClienteHibernate");
            lista = query.list();
            //clientes.forEach(s -> System.out.println(s.getNombre()));
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return lista;
    }
    
}