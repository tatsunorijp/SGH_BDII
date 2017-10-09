package sistemahotel.model.infraestrutura;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import sistemahotel.model.produto.Produto;
import sistemahotel.model.infraestrutura.Persistencia;


import java.util.List;

/**
 * Created by tatsunori on 08/10/17.
 */
public class RetornaListas {
    Persistencia persistencia = Persistencia.getInstancia();
    public static SessionFactory ssf;
    public static void startSessionFactory() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        ssf = sessionFactory;
    }

    public static List<Produto> listProduto() {
        startSessionFactory();
        Session session = ssf.openSession();
        Transaction transaction = null;

        List produto = null;

        try {
            transaction = session.beginTransaction();
            produto = session.createQuery("FROM Produto").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return produto;
    }
}
