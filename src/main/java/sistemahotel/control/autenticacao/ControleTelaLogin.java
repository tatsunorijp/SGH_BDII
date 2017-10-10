package sistemahotel.control.autenticacao;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sistemahotel.control.ControleTelas;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tatsunori on 04/10/2017.
 */
public class ControleTelaLogin implements Initializable{
    @FXML
    private JFXTextField tfUser;
    @FXML
    private JFXPasswordField tfPassword;
    @FXML
    private JFXButton btLogin;
    @FXML
    private JFXButton btSair;
    ControleTelas controleTelas = new ControleTelas();


    public void iniciar(ActionEvent event) throws IOException {
        controleTelas.newWindow("/sistemahotel/view/TelaPrincipal.fxml", event);
    }
    public void sair(ActionEvent event){
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
