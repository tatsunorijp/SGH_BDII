package sistemahotel.model.local;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@SQLDelete(sql = "UPDATE Local" +  "SET ativo = false " + "WHERE id = ?")
@Where(clause = "ativo = 1")
public abstract class Local {

    @Id
    @GeneratedValue
    protected Long id;
    protected String numero;
    protected String tipo; // "Habitacao" ou "Salao"
    protected String status;
    protected String informacoesAdicionais;
    protected String preco;
    protected Boolean ativo = true;

    @Override
    public String toString() {
        return getTipo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
