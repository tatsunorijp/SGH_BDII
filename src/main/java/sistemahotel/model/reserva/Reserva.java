package sistemahotel.model.reserva;

import sistemahotel.model.local.Local;
import sistemahotel.model.pessoa.Cliente;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Reserva {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "fk_cliente", nullable=false)
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "fk_local", nullable=false)
    private Local local;
    private String status; // Agendada, Em andamento, Finalizada, Cancelada //
    private LocalDateTime dataReserva;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private String qtdhospede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDate getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(LocalDate dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public LocalDate getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(LocalDate dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public String getQtdhospede() { return qtdhospede; }

    public void setQtdhospede(String qtdhospede) {
        this.qtdhospede = qtdhospede;
    }
}
