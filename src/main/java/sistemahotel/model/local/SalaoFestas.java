package sistemahotel.model.local;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import sistemahotel.model.local.Local;

import javax.persistence.Entity;

@Entity
public class SalaoFestas extends Local {
    private String maximoPessoas;
    private String informacoesAdicionais;

    public SalaoFestas() {
        this.tipo = "Salao de Festas";
        this.status = "Livre";
    }

    public String getMaximoPessoas() {
        return maximoPessoas;
    }

    public void setMaximoPessoas(String maximoPessoas) {
        this.maximoPessoas = maximoPessoas;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }
}
