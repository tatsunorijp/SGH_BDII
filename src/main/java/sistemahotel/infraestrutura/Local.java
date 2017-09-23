package sistemahotel.infraestrutura;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by marcelo on 23/09/17.
 */
@Entity
public abstract class Local {
    @Id
    @GeneratedValue
    protected Long id;
    private String numero;
    protected String tipo;
    protected String status;
    protected String informacoesAdicionais;
    protected String preco;
}
