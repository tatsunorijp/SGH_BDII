package sistemahotel.control.produto;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.Listas;
import sistemahotel.model.produto.Produto;

import java.io.IOException;
import java.net.URL;
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

    @FXML
    private JFXTextField tfNome;

    @FXML
    private JFXTextField tfPreco;

    @FXML
    private JFXTextField tfEstoque;

    @FXML
    private JFXTextField tfAlerta;

    Listas pegaListas;
    ObservableList list;
    ControleTelas controleTelas = new ControleTelas();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableList(pegaListas.listProduto());
        tcNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tvProdutos.setItems(FXCollections.observableList(list));

        tvProdutos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoDeItens((Produto) newValue)
        );

    }

    public void btNovoProdutoActionHandler(ActionEvent event) throws IOException{
        controleTelas.newWindow("/sistemahotel/view/produto/NovoProduto.fxml",event);
    }

    public void selecaoDeItens(Produto produto){
        tfNome.setText(produto.getNome());
        tfPreco.setText(produto.getPreco());
        tfEstoque.setText(produto.getQuantidade());
        tfAlerta.setText(produto.getAlertaEstoque());

    }
}
