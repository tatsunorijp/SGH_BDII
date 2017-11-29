package JunitTestes;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.produto.Produto;

import java.lang.annotation.Annotation;

/**
 * Created by tatsunori on 29/11/17.
 */
public class TesteProdutos extends TestCase{
    Persistencia persistencia = Persistencia.getInstancia();
    Session session = persistencia.getSsf().openSession();
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
    public void testeBD(){

        Produto produto = new Produto();
        produto.setPreco("50,00");
        produto.setNome("Bolachinha");

        assertEquals(session.save(produto),produto.getId());
    }

}
