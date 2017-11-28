package sistemahotel.model.pessoa;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Where(clause = "ativo = 1")
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @NotNull
    protected String tipo; // "Cliente" ou "Usuario"
    @Column(unique = true)
    private String CPF;
    @NotNull
    private String RG;
    private String nome;
    private LocalDate dataDeNascimento;
    protected Boolean ativo = true;

    @Override
    public String toString() {
        return getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
