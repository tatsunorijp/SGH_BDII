package sistemahotel.control.local;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelaPrincipal;
import sistemahotel.control.ControleTelas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tatsunori on 04/10/2017.
 */
public class ControleTelaLocais implements Initializable {
    @FXML
    private JFXTreeTableView<?> ttvProdutos;

    @FXML
    private JFXButton btHabitacao;

    @FXML
    private JFXButton btSalaoFestas;

    ControleTelas window = new ControleTelas();

    public void btHabitacaoActionHandler(ActionEvent e) throws IOException {
        window.newWindow("/sistemahotel/view/locais/Habitacao.fxml", e);
    }

    public void btSalaoFestasActionHandler(ActionEvent e) throws IOException {
        window.newWindow("/sistemahotel/view/locais/SalaoFestas.fxml", e);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
