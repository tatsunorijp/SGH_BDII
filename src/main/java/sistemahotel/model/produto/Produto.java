package sistemahotel.model.produto;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
//@SQLDelete(sql = "update Produto set ativo = 0 where id = ?")
@Where(clause = "ativo = 1")
public class Produto {
    @Id
    @GeneratedValue
    protected Long id;
    private String nome;
    private String quantidade;
    private String preco;
    private String alertaEstoque;
    private Boolean ativo = true;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

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
