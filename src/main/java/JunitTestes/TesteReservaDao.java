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
import sistemahotel.model.pessoa.Cliente;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TesteReservaDao{

    SessionFactory ssf = new Configuration().configure().buildSessionFactory();
    Session session = ssf.openSession();
    ReservaDAO reservadao = ReservaDAO.getInstancia();


    @Test
    public void novaReserva() throws Exception {

        Persistencia.getInstancia().startSsf();

        Cliente cliente = new Cliente();
        Habitacao hab = new Habitacao();
        LocalDate in = LocalDate.now();
        LocalDate out = LocalDate.now().plusDays(1);

        Reserva reservahab = reservadao.novaReserva(cliente, hab, in, out,"5");

        IdentifierLoadAccess<Reserva> multiLoadAccess = session.byId(Reserva.class);
        Reserva reservahabBd = multiLoadAccess.load(reservahab.getId());
        Assert.assertNotNull(reservahabBd);

    }

    @Test
    public void checarIndisponibilidade() throws Exception {

        Persistencia.getInstancia().startSsf();

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

        Cliente cliente = new Cliente();
        Habitacao hab = new Habitacao();
        LocalDate in = LocalDate.now();
        LocalDate out = LocalDate.now().plusDays(1);

        Reserva reservahab = reservadao.novaReserva(cliente, hab, in, out,"5");
        reservadao.cancelarReserva(reservahab);

        IdentifierLoadAccess<Reserva> multiLoadAccess = session.byId(Reserva.class);
        Reserva reservahabBd = multiLoadAccess.load(reservahab.getId());
        assertNull(reservahabBd);

    }

    @Test
    public void fazerCheckIn() throws Exception {

        Cliente cliente = new Cliente();
        Habitacao hab = new Habitacao();
        LocalDate in = LocalDate.now();
        LocalDate out = LocalDate.now().plusDays(1);

        Reserva reservahab = reservadao.novaReserva(cliente, hab, in, out,"5");
        reservadao.fazerCheckIn(reservahab);

        IdentifierLoadAccess<Reserva> multiLoadAccess = session.byId(Reserva.class);
        Reserva reservahabBd = multiLoadAccess.load(reservahab.getId());
        assertEquals("Em andamento", reservahabBd.getStatus());

    }

    @Test
    public void fazerCheckOut() throws Exception {

        Cliente cliente = new Cliente();
        Habitacao hab = new Habitacao();
        LocalDate in = LocalDate.now();
        LocalDate out = LocalDate.now().plusDays(1);

        Reserva reservahab = reservadao.novaReserva(cliente, hab, in, out,"5");
        reservadao.fazerCheckOut(reservahab);

        IdentifierLoadAccess<Reserva> multiLoadAccess = session.byId(Reserva.class);
        Reserva reservahabBd = multiLoadAccess.load(reservahab.getId());
        assertEquals("Finalizada", reservahabBd.getStatus());

    }

    @Test
    public void estenderReserva() throws Exception {

        Cliente cliente = new Cliente();
        Habitacao hab = new Habitacao();
        LocalDate in = LocalDate.now();
        LocalDate out = LocalDate.now().plusDays(1);

        Reserva reservahab = reservadao.novaReserva(cliente, hab, in, out,"5");
        reservadao.estenderReserva(reservahab, in.plusDays(5), out.plusDays(5));

        IdentifierLoadAccess<Reserva> multiLoadAccess = session.byId(Reserva.class);
        Reserva reservahabBd = multiLoadAccess.load(reservahab.getId());

        assertEquals(reservahabBd.getDataCheckIn(), in.plusDays(5));
        assertEquals(reservahabBd.getDataCheckOut(), out.plusDays(5));

    }


}
