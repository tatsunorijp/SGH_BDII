package sistemahotel.control.pessoa;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.pessoa.ClienteDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControleNovoCliente implements Initializable {
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfRG;
    @FXML
    private JFXTextField tfCPF;
    @FXML
    private DatePicker dtDataDeNascimento;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXTextField tfEndereco;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfCidade;
    @FXML
    private JFXTextField tfNacionalidade;
    @FXML
    private JFXTextField tfPlacaDoCarro;
    @FXML
    private JFXTextField tfInformacoesAdicionais;
    @FXML
    private JFXButton btLimpar;
    @FXML
    private AnchorPane anchorPane;



    ControleTelas controleTelas = ControleTelas.getInstancia();
    ClienteDAO clienteDAO = ClienteDAO.getInstancia();

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        controleTelas.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        if (tfNome.getText().isEmpty() || tfRG.getText().isEmpty() || tfCPF.getText().isEmpty()
            || dtDataDeNascimento.toString().isEmpty() || tfTelefone.getText().isEmpty()
            || tfEndereco.getText().isEmpty() || tfCidade.getText().isEmpty()
            || tfNacionalidade.getText().isEmpty()) {
            controleTelas.popupAviso("Campos inválidos",
                                     "Campos com * são obrigatórios");
        } else {
            clienteDAO.Novo(tfNome.getText(), tfEndereco.getText(), tfEmail.getText(), tfCidade.getText(),
                            tfNacionalidade.getText(), tfPlacaDoCarro.getText(),
                            tfInformacoesAdicionais.getText(), tfCPF.getText(), tfRG.getText(),
                            dtDataDeNascimento.getValue());
            controleTelas.notificacao("Cadastro efetuado",
                                      "novo cliente adicionado ao banco de dados");
            controleTelas.novaJanela("/sistemahotel/view/Clientes.fxml", event);
        }
    }

    @FXML
    void btLimparActionHandler(ActionEvent event) {
        tfNome.setText("");
        tfNacionalidade.setText("");
        tfCidade.setText("");
        tfEndereco.setText("");
        tfTelefone.setText("");
        tfCPF.setText("");
        tfRG.setText("");
        tfEmail.setText("");
        tfInformacoesAdicionais.setText("");
        tfPlacaDoCarro.setText("");
        dtDataDeNascimento.setValue(LocalDate.parse("1900-01-01"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
