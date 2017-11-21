package sistemahotel.model.pessoa;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends Pessoa {
    @Column(unique = true)
    private String login;
    private String senha;

    public Usuario() {
        this.tipo = "Usuario";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
