package sistemahotel.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.ImageView;

/**
 * Created by Tatsunori on 02/10/2017.
 */
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

    ControleTelas window = new ControleTelas();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void showClientes(ActionEvent event) throws IOException {
        window.setFragment("/sistemahotel/view/pessoa/Clientes.fxml",apPrincipal);
    }
    public void showReservas(ActionEvent event){

    }
    public void showProdutos(ActionEvent event) throws IOException {
        window.setFragment("/sistemahotel/view/produto/Produtos.fxml",apPrincipal);
    }
    public void showLocais(ActionEvent event) throws IOException {
        window.setFragment("/sistemahotel/view/locais/Locais.fxml",apPrincipal);
    }
    public void exit(ActionEvent event){
        window.newWindow("/sistemahotel/view/Login.fxml",event);
    }


}
