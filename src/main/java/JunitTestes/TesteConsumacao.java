package JunitTestes;

import junit.framework.TestCase;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.local.LocalDAO;
import sistemahotel.model.pessoa.Cliente;
import sistemahotel.model.pessoa.ClienteDAO;
import sistemahotel.model.produto.Produto;
import sistemahotel.model.produto.ProdutoDAO;
import sistemahotel.model.reserva.Consumacao;
import sistemahotel.model.reserva.ConsumacaoDAO;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.time.LocalDate;

/**
 * Created by tatsunori on 30/11/17.
 */
public class TesteConsumacao extends TestCase{
    SessionFactory ssf = new Configuration().configure().buildSessionFactory();
    Session session = ssf.openSession();

    ConsumacaoDAO consumacaoDAO = ConsumacaoDAO.getInstancia();
    LocalDAO localDAO = new LocalDAO();
    ProdutoDAO produtoDAO = ProdutoDAO.getInstancia();
    ReservaDAO reservaDAO = ReservaDAO.getInstancia();
    ClienteDAO clienteDAO = ClienteDAO.getInstancia();

//    @Test
//    public void testeAdicionarConsumacao(){
//        Persistencia.getInstancia().startSsf();
//        Consumacao consumo = null;
//
//        Cliente cliente = new Cliente();
//        Habitacao hab = new Habitacao();
//        Persistencia.getInstancia().persistir(cliente);
//        Persistencia.getInstancia().persistir(hab);
//        Reserva reserva = null;
//        LocalDate in = LocalDate.now();
//        LocalDate out = LocalDate.now().plusDays(1);
//
//
//        reserva = reservaDAO.novaReserva(cliente, hab, in, out,"5");;
//        Produto produto = produtoDAO.novo(null,"5",null,null);
//
//
//        consumo = consumacaoDAO.addConsumo(produto,"1",reserva);
//
//        IdentifierLoadAccess<Consumacao> multiLoadAccess = session.byId(Consumacao.class);
//        Consumacao consumoBD = multiLoadAccess.load(consumo.getId());
//        Assert.assertNotNull(consumoBD);
//    }
//
//    @Test
//    public void testeExcluirConsumacao(){
//        Persistencia.getInstancia().startSsf();
//        Consumacao consumo = null;
//
//        Cliente cliente = new Cliente();
//        Habitacao hab = new Habitacao();
//        Persistencia.getInstancia().persistir(cliente);
//        Persistencia.getInstancia().persistir(hab);
//        Reserva reserva = null;
//        LocalDate in = LocalDate.now();
//        LocalDate out = LocalDate.now().plusDays(1);
//
//
//        reserva = reservaDAO.novaReserva(cliente, hab, in, out,"5");;
//        Produto produto = produtoDAO.novo(null,"5",null,null);
//
//
//        consumo = consumacaoDAO.addConsumo(produto,"1",reserva);
//        consumacaoDAO.deleteConsumacao(consumo);
//
//        IdentifierLoadAccess<Consumacao> multiLoadAccess = session.byId(Consumacao.class);
//        Consumacao consumoBD = multiLoadAccess.load(consumo.getId());
//        Assert.assertNull(consumoBD);
//    }
}
