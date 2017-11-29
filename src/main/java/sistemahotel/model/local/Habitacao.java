package sistemahotel.model.local;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.Entity;



@Entity
public class Habitacao extends Local {

    private String camasDeSolteiro;
    private String camasDeCasal;
    public Habitacao (){
        this.tipo = "Habitacao";
        this.status = "Livre";
    }

    public String getCamasDeSolteiro() {
        return camasDeSolteiro;
    }

    public void setCamasDeSolteiro(String camasDeSolteiro) {
        this.camasDeSolteiro = camasDeSolteiro;
    }

    public String getCamasDeCasal() {
        return camasDeCasal;
    }

    public void setCamasDeCasal(String camasDeCasal) {
        this.camasDeCasal = camasDeCasal;
    }
}
