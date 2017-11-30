package sistemahotel.control.pessoa;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.pessoa.ClienteDAO;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import sistemahotel.model.pessoa.ClienteDAO;

public class ControleNovoCliente implements Initializable {
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
    private JFXTextField tfBairro;
    @FXML
    private JFXTextField tfEmail;
    @FXML
    private JFXTextField tfCidade;
    @FXML
    private JFXTextField tfEstado;
    @FXML
    private JFXTextField tfNacionalidade;
    @FXML
    private JFXTextField tfPlacaDoCarro;
    @FXML
    private JFXTextField tfInformacoesAdicionais;
    @FXML
    private JFXTextField tfCEP;

    ControleTelas controleTelas = ControleTelas.getInstancia();
    ClienteDAO clienteDAO = ClienteDAO.getInstancia();

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        controleTelas.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        boolean ok = true;

        if (tfNome.getText().isEmpty() || tfRG.getText().isEmpty() || tfCPF.getText().isEmpty()
            || dtDataDeNascimento.toString().isEmpty() || tfTelefone.getText().isEmpty()
            || tfEndereco.getText().isEmpty() || tfCidade.getText().isEmpty()
            || tfNacionalidade.getText().isEmpty() || tfBairro.getText().isEmpty()
            || tfCEP.getText().isEmpty() || tfEstado.getText().isEmpty()) {
            controleTelas.popupAviso("Campos inválidos",
                                     "Campos com * são obrigatórios");
        } else {
            if (!tfRG.getText().matches("[0-9]+") ) {
                controleTelas.popupAviso("Campo inválido!",
                        "O RG deve conter apenas números");
                ok = false;
            }
            if (!ClienteDAO.verificaCPF(tfCPF.getText())) {
                controleTelas.popupAviso("Campo inválido!",
                        "Esse CPF não é válido");
                ok = false;
            }
            if (tfEmail.getText().length() > 0
                    && !tfEmail.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                controleTelas.popupAviso("Campo inválido!",
                        "Esse email não é válido");
                ok = false;
            }
            LocalDate data = dtDataDeNascimento.getValue();
            if ((data.getYear() < 1899) || (data.getYear() > LocalDate.now().getYear() - 18)) {
                controleTelas.popupAviso("Campo inválido",
                            "A data esta fora do intervalo aceito. O usuário"
                        + " deve ter ao menos 18 anos de idade e não pode ter nascido antes de 1899");
                ok = false;
            }
            if (ok) {
                clienteDAO.Novo(tfNome.getText(), tfEndereco.getText(), tfEmail.getText(), tfCidade.getText(),
                        tfNacionalidade.getText(), tfPlacaDoCarro.getText(), tfTelefone.getText(),
                        tfInformacoesAdicionais.getText(), tfCPF.getText(), tfRG.getText(),
                        dtDataDeNascimento.getValue(), tfBairro.getText(),
                        tfCEP.getText(), tfEstado.getText());
                controleTelas.notificacao("Cadastro efetuado",
                        "novo cliente adicionado ao banco de dados");
                controleTelas.novaJanela("/sistemahotel/view/Clientes.fxml", event);
            }
        }
    }

    @FXML
    void btLimparActionHandler(ActionEvent event) {
        tfNome.setText("");
        tfEstado.setText("");
        tfNacionalidade.setText("");
        tfCidade.setText("");
        tfEndereco.setText("");
        tfTelefone.setText("");
        tfCPF.setText("");
        tfRG.setText("");
        tfEmail.setText("");
        tfInformacoesAdicionais.setText("");
        tfPlacaDoCarro.setText("");
        tfCEP.setText("");
        tfBairro.setText("");
        dtDataDeNascimento.setValue(LocalDate.parse("1900-01-01"));
    }

    public void usaCEP(ActionEvent event) {
        String cep = tfCEP.getText();
        if (cep.length() == 8 && cep.matches("[0-9]+")) {
            BuscaCEP busca = new BuscaCEP();
            try {
                String endereco = busca.getEndereco(cep);
                String cidade = busca.getCidade(cep);
                String uf = busca.getUF(cep);
                String bairro = busca.getBairro(cep);

                tfBairro.setText(bairro);
                tfEstado.setText(uf);
                tfEndereco.setText(endereco);
                tfCidade.setText(cidade);
            } catch (java.io.IOException e) {
            }
        } else {
            controleTelas.popupAviso("CEP inválido",
                    "Digite apenas números");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
