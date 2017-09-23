package sistemahotel.infraestrutura;

import javax.persistence.Entity;

/**
 * Created by marcelo on 23/09/17.
 */
@Entity
public class SalaoFestas extends Local{
    String maximoPessoas;
    String informacoesAdicionais;

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
