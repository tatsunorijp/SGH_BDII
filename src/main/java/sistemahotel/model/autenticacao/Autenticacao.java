package sistemahotel.model.autenticacao;

import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.pessoa.Usuario;

public class Autenticacao {

    Usuario user;

    public Usuario Autenticar (String usuario, String senha){
        user = null;
        for (Usuario autenticar: RetornaListas.listUsuario()){
            if (autenticar.getLogin().equals(usuario) && autenticar.getSenha().equals(senha)){
                user = autenticar;
            }
        }

        return user;
    }

}
