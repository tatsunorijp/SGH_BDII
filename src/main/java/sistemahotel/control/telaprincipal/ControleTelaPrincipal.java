package sistemahotel.control.telaprincipal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import sistemahotel.control.ControleTelas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaPrincipal implements Initializable{
    @FXML
    private JFXButton btClientes;
    @FXML
    private JFXButton btReservas;
    @FXML
    private JFXButton btProdutos;
    @FXML
    private JFXButton btLocais;
    @FXML
    public AnchorPane apPrincipal;
    @FXML
    private JFXDrawer drawerMenu;

    ControleTelas window = ControleTelas.getInstancia();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void showClientes(ActionEvent event) throws IOException {
        window.setFragment("/sistemahotel/view/pessoa/Clientes.fxml",apPrincipal);
    }
    public void showReservas(ActionEvent event) throws  IOException{
        window.setFragment("/sistemahotel/view/reserva/TelaReservas.fxml",apPrincipal);
    }
    public void showProdutos(ActionEvent event) throws IOException {
        window.setFragment("/sistemahotel/view/produto/Produtos.fxml",apPrincipal);
    }
    public void showHabitacoes(ActionEvent event) throws IOException {
        window.setFragment("/sistemahotel/view/locais/Habitacao.fxml",apPrincipal);
    }
    public void showSalaoEventos(ActionEvent event) throws IOException {
        window.setFragment("/sistemahotel/view/locais/SalaoEventos.fxml",apPrincipal);
    }
    public void exit(ActionEvent event){
        window.novaJanela("/sistemahotel/view/Login.fxml",event);
    }


}
