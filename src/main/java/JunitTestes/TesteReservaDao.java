package JunitTestes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.pessoa.Cliente;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TesteReservaDao {

    SessionFactory ssf = new Configuration().configure().buildSessionFactory();
    Session session = ssf.openSession();
    ReservaDAO reservadao = ReservaDAO.getInstancia();

    @Test
    public void novaReserva() throws Exception {

       /* Cliente cliente = new Cliente();
        Habitacao hab = new Habitacao();
        LocalDate in = LocalDate.now();
        LocalDate out = LocalDate.now().plusDays(1);
        Reserva reserva = new Reserva();

        reservadao.novaReserva(cliente, hab, in, out,"5");

        IdentifierLoadAccess<Reserva> multiLoadAccess = session.byId(Reserva.class);
        Reserva reservahabBd = multiLoadAccess.load(reservahab.getId());
        Assert.assertNotNull(reservahabBd);*/

    }

    @Test
    public void checarIndisponibilidade() throws Exception {

        Cliente cliente = new Cliente();
        Habitacao hab = new Habitacao();
        LocalDate in = LocalDate.now();
        LocalDate out = LocalDate.now().plusDays(5);
        reservadao.novaReserva(cliente, hab, in, out,"5");

        Reserva reserva = new Reserva();
        reserva.setLocal(hab);
        reserva.setDataCheckIn(in.plusDays(3));
        reserva.setDataCheckOut(out.plusDays(3));

        assertTrue(ReservaDAO.checarIndisponibilidade(hab, in.plusDays(3), out.plusDays(3)));

    }

    @Test
    public void cancelarReserva() throws Exception {
    }

    @Test
    public void fazerCheckIn() throws Exception {
    }

    @Test
    public void fazerCheckOut() throws Exception {
    }

    @Test
    public void estenderReserva() throws Exception {
    }


}
