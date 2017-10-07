package sistemahotel.model.infraestrutura;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sistemahotel.model.produto.Produto;

import java.util.List;

/**
 * Created by Tatsunori on 07/10/2017.
 */
public class ListasBD {
    private static ListasBD instancia = null;
    private static SessionFactory ssf;

    private ListasBD(){
    }

    public static ListasBD getInstancia(){
        if(instancia == null){
            instancia = new ListasBD();
        }
        return instancia;
    }



    public static List<Produto> listaProduto() {
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
