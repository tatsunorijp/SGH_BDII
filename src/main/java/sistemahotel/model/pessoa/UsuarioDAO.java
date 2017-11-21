package sistemahotel.model.pessoa;

import sistemahotel.model.infraestrutura.Persistencia;

import java.time.LocalDate;

public class UsuarioDAO {
    Persistencia persistencia = Persistencia.getInstancia();
    Usuario usuario;

    private static UsuarioDAO instancia = null;

    private UsuarioDAO() {}

    public static UsuarioDAO getInstancia() {
        if (instancia == null){
            instancia = new UsuarioDAO();
        }
        return instancia;
    }

    public void Novo(String nome, String login, String senha,
                     String cpf, String rg, LocalDate dataDeNascimento) {
        usuario = new Usuario();
        //usuario.setTipo(tipo);
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setCPF(cpf);
        usuario.setRG(rg);
        usuario.setDataDeNascimento(dataDeNascimento);
        persistencia.persistir(usuario);
    }

    public Usuario Alterar(String nome, String login, String senha,
                           String cpf, String rg, LocalDate dataDeNascimento, Long id) {
        usuario = new Usuario();
        //usuario.setTipo(tipo);
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setCPF(cpf);
        usuario.setRG(rg);
        usuario.setDataDeNascimento(dataDeNascimento);
        persistencia.persistir(usuario);
        return usuario;
    }

    public void Deletar(Usuario usuario) {
        persistencia.deletar(usuario);
    }
}
