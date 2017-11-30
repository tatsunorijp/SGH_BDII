package JunitTestes;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import sistemahotel.model.pessoa.Cliente;
import sistemahotel.model.pessoa.ClienteDAO;
import sistemahotel.control.pessoa.ControleNovoCliente;
import sistemahotel.control.pessoa.ControleTelaClientes;
import sistemahotel.control.pessoa.BuscaCEP;


public class TesteCliente extends TestCase {
    SessionFactory ssf = new Configuration().configure().buildSessionFactory();
    Session session = ssf.openSession();

    @Test
    public void testeVerificaCPF() {
        //C1: 781.278.407-00
        assert(ClienteDAO.verificaCPF("78127840700"));
        //C2: 373.625.455-50
        assert(ClienteDAO.verificaCPF("37362545550"));
        //C3: 138.960.691-01
        assert(ClienteDAO.verificaCPF("13896069101"));
        //C4: 881.384.724-64
        assert(ClienteDAO.verificaCPF("88138472464"));
    }

    @Test
    public void testeNovoCliente(){
        Cliente cliente = new Cliente();

        Transaction tx = session.beginTransaction();
        session.save(cliente);
        tx.commit();

        IdentifierLoadAccess<Cliente> multiLoadAccess = session.byId(Cliente.class);
        Cliente clienteBD = multiLoadAccess.load(cliente.getId());
        Assert.assertNotNull(clienteBD);
    }

    @Test
    public void testeDeletarCliente(){
        Cliente cliente = new Cliente();

        Transaction tx = session.beginTransaction();
        session.save(cliente);
        tx.commit();

        IdentifierLoadAccess<Cliente> multiLoadAccess = session.byId(Cliente.class);
        Cliente clienteBD = multiLoadAccess.load(cliente.getId());

        tx = session.beginTransaction();
        cliente.setAtivo(false);
        session.update(cliente);
        tx.commit();


        multiLoadAccess = session.byId(Cliente.class);
        clienteBD = multiLoadAccess.load(cliente.getId());

        assert(!clienteBD.getAtivo());
    }
}
