package JunitTestes;

import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.local.LocalDAO;
import sistemahotel.model.local.SalaoFestas;
import sistemahotel.model.pessoa.Usuario;

import static org.junit.Assert.assertNull;

public class TesteUsuarios {

    SessionFactory ssf = new Configuration().configure().buildSessionFactory();
    Session session = ssf.openSession();
    LocalDAO dao;
    @Test
    public void testeCriaUsuario(){
        Usuario usuarioteste = new Usuario();
        assertNull(usuarioteste.getId());

        Transaction tx = session.beginTransaction();
        session.save(usuarioteste);
        tx.commit();

        System.out.println(usuarioteste.getId());
    }

    @Test
    public void testeAddUsuarioBd(){
        Usuario usuarioteste = new Usuario();

        Transaction tx = session.beginTransaction();
        session.save(usuarioteste);
        tx.commit();

        IdentifierLoadAccess<Usuario> multiLoadAccess = session.byId(Usuario.class);
        Usuario usuariotestebd = multiLoadAccess.load(usuarioteste.getId());
        System.out.println(usuariotestebd.getAtivo());
        System.out.println(usuariotestebd.getId());
    }

    @Test
    public void testeExcluiUsuarioBd() {
        Usuario usuarioteste = new Usuario();

        Transaction tx = session.beginTransaction();
        session.save(usuarioteste);
        tx.commit();

        IdentifierLoadAccess<Usuario> multiLoadAccess = session.byId(Usuario.class);
        Usuario usuariotestebd = multiLoadAccess.load(usuarioteste.getId());

        System.out.println(usuariotestebd.getAtivo());

        tx = session.beginTransaction();
        usuarioteste.setAtivo(false);
        session.update(usuarioteste);
        tx.commit();

        multiLoadAccess = session.byId(Usuario.class);
        usuariotestebd = multiLoadAccess.load(usuarioteste.getId());

        System.out.println(usuariotestebd.getAtivo());
    }


}
