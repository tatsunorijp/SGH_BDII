package JunitTestes;
import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import sistemahotel.model.infraestrutura.ListasBD;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.produto.Produto;


public class TesteLocais extends TestCase{
    Persistencia persistencia = Persistencia.getInstancia();
    Session session = persistencia.getSsf().openSession();

    @Test
    public void testeCria(){
        Habitacao habteste = new Habitacao();
        habteste.setCamasDeCasal("3");
        habteste.setCamasDeSolteiro("2");
        habteste.setNumero("1");
        habteste.setPreco("29");
//        assertNull(habteste.getId());
        persistencia.persistir(habteste);
        assertNotNull(habteste.getId());
    }

    @Test
    public void testeRead(){

    }
}
