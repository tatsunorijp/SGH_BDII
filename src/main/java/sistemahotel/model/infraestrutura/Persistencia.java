package sistemahotel.model.infraestrutura;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by marcelo on 02/10/17.
 */
public class Persistencia {

    private static Persistencia instancia = null;
    private static SessionFactory ssf;

    private Persistencia(){
    }

    public static Persistencia getInstancia(){
        if(instancia == null){
            instancia = new Persistencia();
        }
        return instancia;
    }

    public void startSsf(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        ssf = sessionFactory;
    }

    public void persistir(Object object){
        Session session = ssf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(object);
        tx.commit();
        session.close();
    }

    public void deletar(Object object){
        Session session = ssf.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(object);
        tx.commit();
        session.close();
    }

    public static SessionFactory getSsf() {
        return ssf;
    }
}
