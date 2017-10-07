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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tatsunori on 04/10/2017.
 */
public class ControleTelaHabitacao implements Initializable {
    @FXML
    private JFXTextField tfNumero;
    @FXML
    private JFXTextField tfPreco;
    @FXML
    private JFXTextField tfCamaSolteiro;
    @FXML
    private JFXTextField tfCamaCasal;
    @FXML
    private JFXTextField tfInfo;
    @FXML
    private JFXTreeTableView<?> ttvProdutos;
    @FXML
    AnchorPane apPrincipal;
    ControleTelas window = new ControleTelas();
    LocalDAO localdao = new LocalDAO();

    public void btNovaHabitacaoActionHandler(ActionEvent e) throws IOException{
        String numero = tfNumero.getText();
        String preco = tfPreco.getText();
        String camasolteiro = tfCamaSolteiro.getText();
        String camacasal = tfCamaCasal.getText();
        String informacoes = tfInfo.getText();
        localdao.NovaHabitacao(numero, camasolteiro, camacasal, preco, informacoes);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operação realizada com sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Habitação Cadastrada");
        alert.showAndWait();
    }

    public void btVoltarActionHandler(ActionEvent event) throws IOException {
        window.newWindow("/sistemahotel/view/TelaPrincipal.fxml", event);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
