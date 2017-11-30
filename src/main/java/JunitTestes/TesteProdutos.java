package JunitTestes;

import junit.framework.TestCase;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.local.LocalDAO;
import sistemahotel.model.produto.Produto;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertNull;

/**
 * Created by tatsunori on 29/11/17.
 */
public class TesteProdutos extends TestCase{
    SessionFactory ssf = new Configuration().configure().buildSessionFactory();
    Session session = ssf.openSession();
    LocalDAO dao;
    @Test
    public void testeNome(){
        Produto produto = new Produto();
        produto.setNome("Bolachinha");
        assertEquals("Bolachinha", produto.getNome());
    }

    @Test
    public void testePreco(){
        Produto produto = new Produto();
        produto.setPreco("50,00");
        assertEquals("50,00", produto.getPreco());
    }


    @Test
    public void testeAddProdutoBd(){
        Produto produto = new Produto();

        Transaction tx = session.beginTransaction();
        session.save(produto);
        tx.commit();

        IdentifierLoadAccess<Produto> multiLoadAccess = session.byId(Produto.class);
        Produto produtoBd = multiLoadAccess.load(produto.getId());
        Assert.assertNotNull(produtoBd);
    }

    @Test
    public void testeExcluiProdutoBd(){
        Produto produto = new Produto();

        Transaction tx = session.beginTransaction();
        session.save(produto);
        tx.commit();

        IdentifierLoadAccess<Produto> multiLoadAccess = session.byId(Produto.class);
        Produto produtoBd = multiLoadAccess.load(produto.getId());

        tx = session.beginTransaction();
        produto.setAtivo(false);
        session.update(produto);
        tx.commit();


        multiLoadAccess = session.byId(Produto.class);
        produtoBd = multiLoadAccess.load(produto.getId());

        assertFalse(produtoBd.getAtivo());
    }

}
