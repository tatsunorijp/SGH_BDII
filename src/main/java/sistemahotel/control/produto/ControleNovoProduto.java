package sistemahotel.control.produto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelaPrincipal;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.produto.Produto;
import sistemahotel.model.produto.ProdutoDAO;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tatsunori on 05/10/2017.
 */
public class ControleNovoProduto implements Initializable{

    @FXML
    private JFXButton btConfirmar;

    @FXML
    private JFXButton btCancelar;

    @FXML
    private JFXTextField tfNome;

    @FXML
    private JFXTextField tfPreco;

    @FXML
    private JFXTextField tfQuantidadeInicial;

    @FXML
    private JFXTextField tfAlertaDeEstoque;

    @FXML
    private JFXButton btLimpar;

    @FXML
    private AnchorPane anchorPane;



    ControleTelas controleTelas = ControleTelas.getInstancia();
    ProdutoDAO produtoDAO = ProdutoDAO.getInstancia();

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        controleTelas.newWindow("/sistemahotel/view/TelaPrincipal.fxml", event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        if (tfNome.getText().isEmpty() || tfPreco.getText().isEmpty()) {
            controleTelas.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {
            produtoDAO.Novo(tfNome.getText(), tfQuantidadeInicial.getText(), tfPreco.getText(), tfAlertaDeEstoque.getText());
            controleTelas.notificacao("Cadastro efetuado", "Novo produto adicionado ao banco de dados");
            controleTelas.newWindow("/sistemahotel/view/TelaPrincipal.fxml", event);
        }
    }

    @FXML
    void btLimparActionHandler(ActionEvent event) {
        tfNome.setText("");
        tfPreco.setText("");
        tfQuantidadeInicial.setText("");
        tfAlertaDeEstoque.setText("");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
