package sistemahotel.model.reserva;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.local.Local;
import sistemahotel.model.pessoa.Cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReservaDAO {

    private static ReservaDAO instancia = null;
    private Persistencia persistencia = Persistencia.getInstancia();

    private ReservaDAO(){

    }

    public static ReservaDAO getInstancia(){
        if(instancia == null){
            instancia = new ReservaDAO();
        }
        return instancia;
    }

    public void novaReserva(Cliente cliente, Local local, LocalDate dataCheckIn, LocalDate dataCheckOut, String qtdhospede){
        Reserva reserva = new Reserva();

        reserva.setCliente(cliente);
        reserva.setLocal(local);
        reserva.setStatus("Agendada");
        reserva.setDataReserva(LocalDateTime.now());
        reserva.setDataCheckIn(dataCheckIn);
        reserva.setDataCheckOut(dataCheckOut);
        reserva.setQtdhospede(qtdhospede);

        persistencia.persistir(reserva);
    }

    public boolean checarIndisponibilidade(Local local, LocalDate dataCheckIn, LocalDate dataCheckOut){ // true = INDISPONIVEL
        List<Reserva> lista = RetornaListas.listReservaPorLocal(local);
        if(lista.isEmpty()) return false; // caso a lista de reservas esteja vazia;
        for (Reserva aux : lista) {
            if (aux.getDataCheckOut().compareTo(dataCheckIn) < 0) { // se a data de checkout de aux for menor que a data de checkin da nova reserva, entao OK
                return false;
            } else if (aux.getDataCheckIn().compareTo(dataCheckOut) > 0) { // caso contrario, OK se a data de checkin de aux for maior que a data de checkout da nova reserva
                return false;
            }
        }
        return true;
    }

    public void cancelarReserva(Reserva reserva){
        reserva.setStatus("Cancelada");
        persistencia.alterar(reserva);
    }

    public void estenderReserva(Reserva reserva, LocalDateTime novaDataCheckOut){

    }

}