package sistemahotel.model.infraestrutura;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.local.Local;
import sistemahotel.model.local.SalaoFestas;
import sistemahotel.model.pessoa.Cliente;
import sistemahotel.model.pessoa.Pessoa;
import sistemahotel.model.pessoa.Usuario;
import sistemahotel.model.produto.Produto;
import sistemahotel.model.reserva.Reserva;


import java.util.List;

public class RetornaListas{
    public static Persistencia persistencia = Persistencia.getInstancia();
    public static SessionFactory ssf;



    public static List<Usuario> listUsuario() {
        ssf = persistencia.getSsf();
        Session session = ssf.openSession();
        Transaction transaction = null;

        List users = null;

        try {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM Pessoa WHERE Pessoa.tipo = 'Usuario'").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    public static List<Pessoa> listClientes() {
        ssf = persistencia.getSsf();
        Session session = ssf.openSession();
        Transaction transaction = null;

        List<Pessoa> clientes = null;

        try {
            transaction = session.beginTransaction();
            clientes = session.createQuery("FROM Pessoa WHERE Pessoa.tipo = 'Cliente'").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return clientes;
    }

    public static List<Produto> listProduto() {
        ssf = persistencia.getSsf();
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

    public static List<Local> listLocais() {
        ssf = persistencia.getSsf();
        Session session = ssf.openSession();
        Transaction transaction = null;

        List local = null;

        try {
            transaction = session.beginTransaction();
            local = session.createQuery("FROM Local").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return local;
    }

    public static List<Habitacao> listHabitacao() {
        ssf = persistencia.getSsf();
        Session session = ssf.openSession();
        Transaction transaction = null;

        List habitacao = null;

        try {
            transaction = session.beginTransaction();
            habitacao = session.createQuery("FROM Habitacao ").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return habitacao;
    }

    public static List<SalaoFestas> listSalaoFestas() {
        ssf = persistencia.getSsf();
        Session session = ssf.openSession();
        Transaction transaction = null;

        List salao = null;

        try {
            transaction = session.beginTransaction();
            salao = session.createQuery("FROM SalaoFestas ").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return salao;
    }

    public static List<Reserva> listReserva() {
        SessionFactory ssf = Persistencia.getInstancia().getSsf();
        Session session = ssf.openSession();
        Transaction transaction = null;

        //String query = "FROM Reserva" + restricao;
        List<Reserva> reserva = null;

        try {
            transaction = session.beginTransaction();
            reserva = session.createQuery("FROM Reserva").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return reserva;
    }

    public static List<Reserva> listReservaPorLocal(Local local) {
        SessionFactory ssf = Persistencia.getInstancia().getSsf();
        Session session = ssf.openSession();
        Transaction transaction = null;

        List<Reserva> reserva = null;

        try {
            transaction = session.beginTransaction();
            reserva = session.createQuery("FROM Reserva WHERE Reserva.local = local AND (Reserva.status = 'Em Andamento' OR Reserva.status = 'Agendada')").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return reserva;
    }
}
