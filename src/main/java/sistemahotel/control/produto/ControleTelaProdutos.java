package sistemahotel.control.produto;

import com.jfoenix.controls.JFXTreeTableView;
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
public class ControleTelaProdutos implements Initializable {
    @FXML
    private JFXTreeTableView<?> ttvProdutos;
    ControleTelas controleTelas = new ControleTelas();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btNovoProdutoActionHandler(ActionEvent event) throws IOException{
        controleTelas.newWindow("/sistemahotel/view/produto/NovoProduto.fxml",event);
    }
}
