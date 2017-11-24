package sistemahotel.control.local;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.local.LocalDAO;
import sistemahotel.model.produto.ProdutoDAO;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by tatsunori on 23/11/17.
 */
public class ControleNovaHabitacao implements Initializable {
    @FXML
    private JFXButton btConfirmar;

    @FXML
    private JFXButton btCancelar;

    @FXML
    private JFXTextField tfNumero;

    @FXML
    private JFXTextField tfPreco;

    @FXML
    private JFXTextField tfSolteiro;

    @FXML
    private JFXTextField tfCasal;

    @FXML
    private JFXButton btLimpar;

    @FXML
    private JFXTextArea taAdicionais;

    @FXML
    private AnchorPane anchorPane;
    ControleTelas controleTelas = ControleTelas.getInstancia();
    LocalDAO localdao = new LocalDAO();



    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        if (tfNumero.getText().isEmpty() || tfPreco.getText().isEmpty()) {
            controleTelas.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {
            localdao.NovaHabitacao(tfNumero.getText(), tfSolteiro.getText(), tfCasal.getText(), tfPreco.getText(), taAdicionais.getText());
            controleTelas.notificacao("Cadastro efetuado", "Novo produto adicionado ao banco de dados");
            controleTelas.fechaJanela(event);
        }
    }

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        controleTelas.fechaJanela(event);
    }

    @FXML
    void btLimparActionHandler(ActionEvent event) {
        tfNumero.setText("");
        tfPreco.setText("");
        tfCasal.setText("");
        tfSolteiro.setText("");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
