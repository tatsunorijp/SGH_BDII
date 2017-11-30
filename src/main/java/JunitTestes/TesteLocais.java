package JunitTestes;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.junit.Test;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.local.Habitacao;

import java.util.List;

import static org.junit.Assert.*;


public class TesteLocais{
    Persistencia persistencia = Persistencia.getInstancia();
    Session session = persistencia.getSsf().openSession();

    /*@Test
    public void testeCria(){
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
    public void testeRead(){
        Habitacao habteste = new Habitacao();
        Long idhab = habteste.getId();
        persistencia.persistir(habteste);
        System.out.println(idhab);
        List lhabteste = session.createQuery("FROM Habitacao WHERE id = :idhab").list();
        assertNotNull(lhabteste);
    }
}
