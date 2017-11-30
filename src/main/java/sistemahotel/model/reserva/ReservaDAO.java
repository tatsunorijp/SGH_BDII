package sistemahotel.model.reserva;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.local.Local;
import sistemahotel.model.local.SalaoFestas;
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

    public void novaReservaSalao(Cliente cliente, SalaoFestas local, LocalDate dataCheckIn, LocalDate dataCheckOut, String nomeDoEvento){
        Reserva reserva = new Reserva();

        reserva.setCliente(cliente);
        reserva.setLocal(local);
        reserva.setStatus("Agendada");
        reserva.setDataReserva(LocalDateTime.now());
        reserva.setDataCheckIn(dataCheckIn);
        reserva.setDataCheckOut(dataCheckOut);
        reserva.setQtdhospede(local.getMaximoPessoas());
        reserva.setNomeDoEvento(nomeDoEvento);

        persistencia.persistir(reserva);

    }

    public static boolean checarIndisponibilidade(Local local, LocalDate dataCheckIn, LocalDate dataCheckOut){ // true = INDISPONIVEL

        if(dataCheckIn.compareTo(dataCheckOut) > 0) return true; // período inválido

        List<Reserva> lista = RetornaListas.listReservaPorLocal(local);
        if(lista.isEmpty()) return false; // caso a lista de reservas esteja vazia;
        for (Reserva aux : lista) {
            if (aux.getDataCheckOut().compareTo(dataCheckIn) < 0) { // se a data de checkout de aux for menor que a data de checkin da nova reserva, entao OK
                continue;
            } else if (aux.getDataCheckIn().compareTo(dataCheckOut) > 0) { // caso contrario, OK se a data de checkin de aux for maior que a data de checkout da nova reserva
                continue;
            } else return true;
        }
        return false;
    }

    public static boolean checarEstenderReserva(Reserva reserva, Local local, LocalDate dataCheckIn, LocalDate dataCheckOut){ // true = INDISPONIVEL

        if(dataCheckIn.compareTo(dataCheckOut) > 0) return true; // período inválido

        List<Reserva> lista = RetornaListas.listReservaPorLocal(local);
        if(lista.isEmpty()) return false; // caso a lista de reservas esteja vazia;
        for (Reserva aux : lista) {
            if (aux.getDataCheckOut().compareTo(dataCheckIn) < 0) { // se a data de checkout de aux for menor que a data de checkin da nova reserva, entao OK
                continue;
            } else if (aux.getDataCheckIn().compareTo(dataCheckOut) > 0) { // caso contrario, OK se a data de checkin de aux for maior que a data de checkout da nova reserva
                continue;
            } else {
                if(aux.getId() == reserva.getId()){
                    continue;
                } else return true;
            }
        }
        return false;
    }

    public void cancelarReserva(Reserva reserva){
        reserva.setStatus("Cancelada");
        persistencia.alterar(reserva);
    }

    public void fazerCheckIn(Reserva reserva){
        reserva.setStatus("Em andamento");
        persistencia.alterar(reserva);
    }

    public void fazerCheckOut(Reserva reserva){
        reserva.setStatus("Finalizada");
        persistencia.alterar(reserva);
    }

    public void estenderReserva(Reserva reserva, LocalDate novaDataCheckIn, LocalDate novaDataCheckOut){
        reserva.setDataCheckIn(novaDataCheckIn);
        reserva.setDataCheckOut(novaDataCheckOut);
        persistencia.alterar(reserva);
    }

    public void adicionarConsumacao(Reserva reserva){

    }

}