package sistemahotel.model.pessoa;

import javax.persistence.Entity;

@Entity
public class Gerente extends Usuario{

    public Gerente() {
        this.tipo = "Gerente";
    }


    // implementar gerar relatorio
    public void gerarRelatorio(){

    }
}
