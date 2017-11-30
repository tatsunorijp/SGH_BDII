package JunitTestes;
import junit.framework.TestCase;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.local.LocalDAO;

import static org.junit.Assert.*;


public class TesteLocais{
    SessionFactory ssf = new Configuration().configure().buildSessionFactory();
    Session session = ssf.openSession();
    LocalDAO dao;
    @Test
    public void testeCriaEntidade(){
        Habitacao habteste = new Habitacao();
        habteste.setCamasDeCasal("3");
        habteste.setCamasDeSolteiro("2");
        habteste.setNumero("1");
        habteste.setPreco("29");
        assertNull(habteste.getId());

        Transaction tx = session.beginTransaction();
        session.save(habteste);
        tx.commit();

        System.out.println(habteste.getId());
    }

    @Test
    public void testeAddBd(){
        Habitacao habteste = new Habitacao();

        Transaction tx = session.beginTransaction();
        session.save(habteste);
        tx.commit();

        IdentifierLoadAccess<Habitacao> multiLoadAccess = session.byId(Habitacao.class);
        Habitacao habtestebd = multiLoadAccess.load(habteste.getId());
        System.out.println(habtestebd.getAtivo());
        System.out.println(habtestebd.getId());
    }

    @Test
    public void testeExcluiBd(){
        Habitacao habteste = new Habitacao();

        Transaction tx = session.beginTransaction();
        session.save(habteste);
        tx.commit();

        tx = session.beginTransaction();
        habteste.setAtivo(false);
        session.update(habteste);
        tx.commit();


        IdentifierLoadAccess<Habitacao> multiLoadAccess = session.byId(Habitacao.class);
        Habitacao habtestebd = multiLoadAccess.load(habteste.getId());

        System.out.println(habtestebd.getAtivo());
    }

}
