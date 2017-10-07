package sistemahotel.control.local;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.local.LocalDAO;
import sistemahotel.model.local.SalaoFestas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaSalaoFestas implements Initializable{
    @FXML
    private JFXTextField tfNumero;
    @FXML
    private JFXTextField tfPreco;
    @FXML
    private JFXTextField tfMaxPessoas;
    @FXML
    private JFXTextField tfInfo;
    @FXML
    private JFXTreeTableView<?> ttvProdutos;
    @FXML
    AnchorPane apPrincipal;

    ControleTelas window = new ControleTelas();
    LocalDAO localdao = new LocalDAO();

    public void btNovoSalaoActionHandler(ActionEvent e) throws IOException{
        String numero = tfNumero.getText();
        String preco = tfPreco.getText();
        String maxpessoas = tfMaxPessoas.getText();
        String informacoes = tfInfo.getText();
        localdao.NovoSalao(numero, maxpessoas, informacoes, preco);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operação realizada com sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Salao de Festas cadastrado");
        alert.showAndWait();
    }

    public void btVoltarActionHandler(ActionEvent event) throws IOException {
        window.newWindow("/sistemahotel/view/TelaPrincipal.fxml", event);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
