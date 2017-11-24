package sistemahotel.model.pessoa;

import javax.persistence.Entity;

@Entity
public class Recepcionista extends Usuario{
    public Recepcionista() {
        this.tipo = "Recepcionista";
    }
}
