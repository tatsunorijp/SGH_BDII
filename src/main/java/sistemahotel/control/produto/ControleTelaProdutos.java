package sistemahotel.control.produto;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemahotel.model.infraestrutura.ListasBD;
import sistemahotel.model.produto.Produto;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Tatsunori on 04/10/2017.
 */
public class ControleTelaProdutos implements Initializable {
    @FXML
    TableView tvProdutos;
    @FXML
    TableColumn tcNome;
    @FXML
    TableColumn tcPreco;

    ObservableList<Produto> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lista = FXCollections.observableList(ListasBD.listaProduto());
        tcNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
    }
}
