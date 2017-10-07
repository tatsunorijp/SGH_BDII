package sistemahotel.control.produto;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sistemahotel.control.ControleTelas;
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
    private JFXButton btResetar;

    ProdutoDAO produto = ProdutoDAO.getInstancia();
    ControleTelas controleTelas;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btConfirmarActionHandler(ActionEvent event){
        produto.NovoProduto(tfNome.getText(),tfQuantidadeInicial.getText(),
                             tfPreco.getText(),tfAlertaDeEstoque.getText());
    }
}
