package JunitTestes;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.local.LocalDAO;
import sistemahotel.model.local.SalaoFestas;

import static org.junit.Assert.*;


public class TesteLocais{
    SessionFactory ssf = new Configuration().configure().buildSessionFactory();
    Session session = ssf.openSession();
    LocalDAO dao;
    @Test
    public void testeCriaHab(){
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
    public void testeAddHabBd(){
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
    public void testeExcluiHabBd(){
        Habitacao habteste = new Habitacao();

        Transaction tx = session.beginTransaction();
        session.save(habteste);
        tx.commit();

        IdentifierLoadAccess<Habitacao> multiLoadAccess = session.byId(Habitacao.class);
        Habitacao habtestebd = multiLoadAccess.load(habteste.getId());

        System.out.println(habtestebd.getAtivo());

        tx = session.beginTransaction();
        habteste.setAtivo(false);
        session.update(habteste);
        tx.commit();


        multiLoadAccess = session.byId(Habitacao.class);
        habtestebd = multiLoadAccess.load(habteste.getId());

        System.out.println(habtestebd.getAtivo());
    }

    @Test
    public void testeCriaSalao(){
        SalaoFestas salaoteste = new SalaoFestas();
        salaoteste.setMaximoPessoas("20");
        salaoteste.setNumero("1");
        salaoteste.setPreco("200");
        assertNull(salaoteste.getId());

        Transaction tx = session.beginTransaction();
        session.save(salaoteste);
        tx.commit();

        System.out.println(salaoteste.getId());
    }

    @Test
    public void testeAddSalaoBd(){
        SalaoFestas salaoteste = new SalaoFestas();

        Transaction tx = session.beginTransaction();
        session.save(salaoteste);
        tx.commit();

        IdentifierLoadAccess<SalaoFestas> multiLoadAccess = session.byId(SalaoFestas.class);
        SalaoFestas salaotestebd = multiLoadAccess.load(salaoteste.getId());
        System.out.println(salaotestebd.getAtivo());
        System.out.println(salaotestebd.getId());
    }

    @Test
    public void testeExcluiSalaoBd(){
        SalaoFestas salaoteste = new SalaoFestas();

        Transaction tx = session.beginTransaction();
        session.save(salaoteste);
        tx.commit();

        IdentifierLoadAccess<SalaoFestas> multiLoadAccess = session.byId(SalaoFestas.class);
        SalaoFestas salaotestebd = multiLoadAccess.load(salaoteste.getId());
        System.out.println(salaotestebd.getAtivo());

        tx = session.beginTransaction();
        salaoteste.setAtivo(false);
        session.update(salaoteste);
        tx.commit();


        multiLoadAccess = session.byId(SalaoFestas.class);
        salaotestebd = multiLoadAccess.load(salaoteste.getId());

        System.out.println(salaotestebd.getAtivo());
    }
}
