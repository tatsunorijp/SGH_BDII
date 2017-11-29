package sistemahotel.control.usuarios;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.pessoa.UsuarioDAO;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControleNovoUsuario implements Initializable {
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXComboBox cbTipo;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfRG;
    @FXML
    private JFXTextField tfCPF;
    @FXML
    private DatePicker dtDataDeNascimento;
    @FXML
    private JFXTextField tfLogin;
    @FXML
    private JFXTextField tfSenha;
    @FXML
    private JFXButton btLimpar;
    @FXML
    private AnchorPane anchorPane;



    ControleTelas controleTelas = ControleTelas.getInstancia();
    UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        controleTelas.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        if (tfNome.getText().isEmpty() || tfRG.getText().isEmpty() || tfCPF.getText().isEmpty()
            || dtDataDeNascimento.toString().isEmpty() || tfLogin.getText().isEmpty() || tfSenha.getText().isEmpty() || cbTipo.getValue().toString().isEmpty()) {
            controleTelas.popupAviso("Campos inválidos",
                                     "Campos com * são obrigatórios");
        } else {
            usuarioDAO.Novo(tfNome.getText(), tfLogin.getText(), tfSenha.getText(), tfCPF.getText(), tfRG.getText(),
                            cbTipo.getValue().toString(), dtDataDeNascimento.getValue());
            controleTelas.notificacao("Cadastro efetuado",
                                      "novo usuario adicionado ao banco de dados");
        }
    }

    @FXML
    void btLimparActionHandler(ActionEvent event) {
        tfNome.setText("");
        tfCPF.setText("");
        tfRG.setText("");
        tfLogin.setText("");
        tfSenha.setText("");
        dtDataDeNascimento.setValue(LocalDate.parse("1900-01-01"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = FXCollections.observableArrayList(
                "Gerente",
                "Recepcionista",
                "Admin"
        );
        cbTipo.setItems(options);
    }
}
