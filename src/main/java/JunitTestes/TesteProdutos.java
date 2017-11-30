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
import sistemahotel.model.produto.ProdutoDAO;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertNull;

/**
 * Created by tatsunori on 29/11/17.
 */
public class TesteProdutos extends TestCase{
    SessionFactory ssf = new Configuration().configure().buildSessionFactory();
    Session session = ssf.openSession();
    ProdutoDAO produtoDAO = ProdutoDAO.getInstancia();

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
        Persistencia.getInstancia().startSsf();

        Produto produto = null;

        produto = produtoDAO.novo(null,null,null,null);

        IdentifierLoadAccess<Produto> multiLoadAccess = session.byId(Produto.class);
        Produto produtoBd = multiLoadAccess.load(produto.getId());
        Assert.assertNotNull(produtoBd);
    }

    @Test
    public void testeExcluiProdutoBd(){
        Persistencia.getInstancia().startSsf();

        Produto produto = null;

        produto = produtoDAO.novo(null,null,null,null);
        produtoDAO.deletar(produto);

        IdentifierLoadAccess<Produto> multiLoadAccess = session.byId(Produto.class);
        Produto produtoBd = multiLoadAccess.load(produto.getId());
        Assert.assertNull(produtoBd);
    }

}
