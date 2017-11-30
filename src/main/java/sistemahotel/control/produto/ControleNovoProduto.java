package sistemahotel.control.produto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.Util;
import sistemahotel.model.produto.ProdutoDAO;

import java.net.URL;
import java.util.ResourceBundle;

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
    Util util = Util.getInstancia();

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        controleTelas.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        if (tfNome.getText().isEmpty() || tfPreco.getText().isEmpty() || tfQuantidadeInicial.getText().isEmpty() || tfAlertaDeEstoque.getText().isEmpty()) {
            controleTelas.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {
            if( (!util.apenasNumeros(tfPreco.getText())) || (!util.apenasNumeros(tfQuantidadeInicial.getText())) ||
                    (!util.apenasNumeros(tfAlertaDeEstoque.getText()))){
                controleTelas.popupAviso("Campos inválidos", "Verifique campos numéricos");
            }else{
                if((Float.parseFloat(tfPreco.getText())) < 0 || (Float.parseFloat(tfPreco.getText()) > 1000001) ||
                   (Float.parseFloat(tfQuantidadeInicial.getText()) < 0) || (Float.parseFloat(tfQuantidadeInicial.getText()) > 1000001)
                 || (Float.parseFloat(tfAlertaDeEstoque.getText()) < 0) || (Float.parseFloat(tfAlertaDeEstoque.getText()) > 1000001)){
                    controleTelas.popupAviso("Valores inválidos", "Os valores devem de estar entre [0,1000000]");
                }else{

                produtoDAO.novo(tfNome.getText(), tfQuantidadeInicial.getText(), tfPreco.getText(), tfAlertaDeEstoque.getText());
                controleTelas.notificacao("Cadastro efetuado", "novo produto adicionado ao banco de dados");
                controleTelas.fechaJanela(event);
                }
            }
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
