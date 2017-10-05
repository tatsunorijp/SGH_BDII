package sistemahotel.control.local;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Tatsunori on 04/10/2017.
 */
public class ControleTelaHabitacao implements Initializable {
    @FXML
    private JFXTreeTableView<?> ttvProdutos;
    @FXML
    AnchorPane apPrincipal;
    ControleTelas window = new ControleTelas();

    public void btVoltarActionHandler(ActionEvent event) throws IOException {
        window.newWindow("/sistemahotel/view/TelaPrincipal.fxml", event);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
