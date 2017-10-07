package sistemahotel.infraestrutura;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by marcelo on 23/09/17.
 */
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
    private String status;
    private LocalDate checkIn;
    private LocalDate checkOut;
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

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public String getQtdhospede() {
        return qtdhospede;
    }

    public void setQtdhospede(String qtdhospede) {
        this.qtdhospede = qtdhospede;
    }
}
