package ejemplos;

import conf.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import modelos.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Consultas {
    private SessionFactory sesion;
    private Session session;
    private Clientes c;
    private Productos p;
    private Ventas v;
    private Scanner sc;

    public Consultas() {
        sc = new Scanner(System.in);
        sesion = NewHibernateUtil.getSessionFactory();
        session = sesion.openSession();
        
        select("Clientes");
        select("Productos");
        select("Ventas");

        c = new Clientes();
        p = new Productos();
        v = new Ventas();

        insert(c);
        insert(p);
        insert(v);

        delete(c);
        delete(p);
        delete(v);

        session.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        Consultas c = new Consultas();
    }

    public void select(String tabla) {
        Query q = session.createQuery("from " + tabla);

        switch (tabla) {
            case "Clientes":
                List<Clientes> lista = q.list();

                Iterator<Clientes> it = lista.iterator();

                System.out.println("=============================\n      DATOS DE CLIENTES\n=============================");
                while (it.hasNext()) {
                    Clientes c = (Clientes) it.next();
                    System.out.println("Nombre: " + c.getNombre() + "\nTelefono: " + c.getTelef());
                    System.out.println("---------------");
                }

                break;
            case "Productos":
                List<Productos> lista2 = q.list();

                Iterator<Productos> it2 = lista2.iterator();

                System.out.println("=============================\n      DATOS DE PRODUCTOS\n=============================");
                while (it2.hasNext()) {
                    Productos c = (Productos) it2.next();
                    System.out.println("ID: " + c.getId() + "\nDescripcion: " + c.getDescripcion());
                    System.out.println("---------------");
                }
                break;
            case "Ventas":
                List<Ventas> lista3 = q.list();

                Iterator<Ventas> it3 = lista3.iterator();

                System.out.println("=============================\n      DATOS DE VENTAS\n=============================");
                while (it3.hasNext()) {
                    Ventas c = (Ventas) it3.next();
                    System.out.println("ID: " + c.getIdventa() + "\nCantidad: " + c.getCantidad());
                    System.out.println("---------------");
                }
                break;
        }

    }

    private void insert(Clientes c) {
        Transaction tx = session.beginTransaction();

        String nombre, direccion;
        int id;

        /*
            en vez de preguntar al usuario puedes utilizar el objeto Cliente que se le pasa al metodo
        
            c.getDireccion();
            c.getNombre();
            c.getDireccion();            
         */
        System.out.println("Introduce el id: ");
        id = sc.nextInt();
        System.out.println("Introduce el nombre: ");
        nombre = sc.next();
        System.out.println("Introduce la direccion:");
        direccion = sc.next();

        String hql = "insert into CLIENTES (ID, NOMBRE, DIRECCION) values (" + id + ",'" + nombre + "','" + direccion + "')";

        Query q = session.createQuery(hql);
        int filas = q.executeUpdate();
        tx.commit();

        System.out.println("FILAS INSERTADAS: " + filas);
    }

    private void insert(Productos p) {
        Transaction tx = session.beginTransaction();

        String descripcion;
        int id;

        System.out.println("Introduce el id: ");
        id = sc.nextInt();
        System.out.println("Introduce la descripcion: ");
        descripcion = sc.next();

        String hql = "insert into PRODUCTOS (ID, DESCRIPCION) VALUES (" + id + ",'" + descripcion + ")";

        Query q = session.createQuery(hql);
        int filas = q.executeUpdate();
        tx.commit();

        System.out.println("FILAS INSERTADAS: " + filas);
    }

    private void insert(Ventas v) {
        Transaction tx = session.beginTransaction();

        String fecha;
        int id;

        System.out.println("Introduce el id: ");
        id = sc.nextInt();
        System.out.println("Introduce la fecha: ");
        fecha = sc.next();

        String hql = "insert into PRODUCTOS (ID, FECHAVENTA) VALUES (" + id + ",'" + fecha + ")";

        Query q = session.createQuery(hql);
        int filas = q.executeUpdate();
        tx.commit();

        System.out.println("FILAS INSERTADAS: " + filas);
    }

    private void delete(Clientes c) {
        Transaction tx = session.beginTransaction();

        int id; // id = c.getID;
        id = sc.nextInt();

        String hql = "delete CLIENTES c where c.ID=" + id;

        Query q = session.createQuery(hql);

        int filas = q.executeUpdate();
        System.out.println("Filas eliminadas: " + filas);
        tx.commit();
    }

    private void delete(Productos p) {
        Transaction tx = session.beginTransaction();

        int id; // id = c.getID;
        id = sc.nextInt();

        String hql = "delete PRODUCTOS c where c.ID=" + id;

        Query q = session.createQuery(hql);

        int filas = q.executeUpdate();
        System.out.println("Filas eliminadas: " + filas);
        tx.commit();
    }

    private void delete(Ventas v) {
        Transaction tx = session.beginTransaction();

        int id; // id = c.getID;
        id = sc.nextInt();

        String hql = "delete VENTAS c where c.IDVENTA=" + id;

        Query q = session.createQuery(hql);

        int filas = q.executeUpdate();
        System.out.println("Filas eliminadas: " + filas);
        tx.commit();
    }
}
