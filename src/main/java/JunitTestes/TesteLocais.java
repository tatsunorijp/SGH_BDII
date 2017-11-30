package JunitTestes;
import junit.framework.TestCase;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.local.Habitacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class TesteLocais{
    Persistencia persistencia = Persistencia.getInstancia();
    Session session = persistencia.getSsf().openSession();

    /*@Test
    public void testeCriaEntidade(){
        Habitacao habteste = new Habitacao();
        habteste.setCamasDeCasal("3");
        habteste.setCamasDeSolteiro("2");
        habteste.setNumero("1");
        habteste.setPreco("29");
        assertNull(habteste.getId());
        persistencia.persistir(habteste);
        assertNotNull(habteste.getId());
    }*/

    @Test
    public void testeAddBd(){
        Habitacao habteste = new Habitacao();
        persistencia.persistir(habteste);
        IdentifierLoadAccess<Habitacao> multiLoadAccess = session.byId(Habitacao.class);
        Habitacao lhabteste = multiLoadAccess.load(habteste.getId());
        assertNotNull(lhabteste);
    }
}
