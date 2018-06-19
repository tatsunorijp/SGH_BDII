package sistemahotel.control.autenticacao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.autenticacao.Autenticacao;
import sistemahotel.model.pessoa.Usuario;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaLogin implements Initializable{
    @FXML
    private JFXTextField tfUser;
    @FXML
    private JFXPasswordField tfPassword;
    @FXML
    private JFXButton btLogin;
    @FXML
    private JFXButton btSair;
    ControleTelas controleTelas = ControleTelas.getInstancia();
    Usuario user;


    public void iniciar(ActionEvent event) throws IOException {
        String login = tfUser.getText();
        String password = tfPassword.getText();
        Autenticacao autenticacao = new Autenticacao();
        /*user = autenticacao.Autenticar(login, password);
        if (user == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Usuario invalido");
            alert.showAndWait();
        }
        else if(user.getTipo().equals("Admin")){*/
            controleTelas.novaJanela("/sistemahotel/view/telaprincipal/TelaPrincipalAdmin.fxml", event);
        /*}
        else if(user.getTipo().equals("Gerente")){
            controleTelas.novaJanela("/sistemahotel/view/telaprincipal/TelaPrincipalGerente.fxml", event);
        }
        else if(user.getTipo().equals("Recepcionista")){
            controleTelas.novaJanela("/sistemahotel/view/telaprincipal/TelaPrincipal.fxml", event);
        }*/
    }
    public void sair(ActionEvent event){
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
