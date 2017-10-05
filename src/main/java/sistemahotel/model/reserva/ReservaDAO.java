package sistemahotel.model.reserva;

import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.local.Local;
import sistemahotel.model.pessoa.Cliente;

import java.time.LocalDateTime;

public class ReservaDAO {

    Persistencia persistencia = Persistencia.getInstancia();

    public void novaReserva(Cliente cliente, Local local,LocalDateTime dataCheckIn, LocalDateTime dataCheckOut, String qtdhospede){
        Reserva reserva = new Reserva();

        reserva.setCliente(cliente);
        reserva.setLocal(local);
        reserva.setStatus("Agendada");
        reserva.setDataReserva(LocalDateTime.now());
        reserva.setDataCheckIn(dataCheckIn);
        reserva.setDataCheckOut(dataCheckOut);

        persistencia.persistir(reserva);
    }

    public void cancelarReserva(Reserva reserva){
        reserva.setStatus("Cancelada");
    }

    public void estenderReserva(Reserva reserva, LocalDateTime novaDataCheckOut){

    }

}