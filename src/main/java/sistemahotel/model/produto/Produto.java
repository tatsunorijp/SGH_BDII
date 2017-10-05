package sistemahotel.model.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by marcelo on 23/09/17.
 */
@Entity
public class Produto {
    @Id
    @GeneratedValue
    protected Long id;
    private String nome;
    private String quantidade;
    private String preco;
    private String alertaEstoque;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getAlertaEstoque() {
        return alertaEstoque;
    }

    public void setAlertaEstoque(String alertaEstoque) {
        this.alertaEstoque = alertaEstoque;
    }
}
