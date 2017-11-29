package sistemahotel.control.produto;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.produto.Produto;
import sistemahotel.model.produto.ProdutoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControleTelaProdutos implements Initializable {
    @FXML
    TableView tvProdutos;
    @FXML
    TableColumn tcNome;
    @FXML
    TableColumn tcPreco;
    @FXML
    JFXTextField tfFiltro;
    @FXML
    private JFXTextField tfNome;

    @FXML
    private JFXTextField tfPreco;

    @FXML
    private JFXTextField tfEstoque;

    @FXML
    private JFXTextField tfAlerta;
    boolean a;
    Produto produtoMain;
    ProdutoDAO produtoDAO = ProdutoDAO.getInstancia();
    RetornaListas pegaListas;
    ObservableList list;
    ControleTelas controleTelas = ControleTelas.getInstancia();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableList(pegaListas.listProduto());
        tcNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tvProdutos.setItems(FXCollections.observableList(list));

        tvProdutos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoDeItens((Produto) newValue)
        );
        FilteredList<Produto> filteredData = new FilteredList<>(list, p -> true);
        tfFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(produto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (produto.getNome().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Produto> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvProdutos.comparatorProperty());
        tvProdutos.setItems(sortedData);

    }

    public void btNovoProdutoActionHandler(ActionEvent event) throws IOException{
        controleTelas.novaJanelaSobreposta("/sistemahotel/view/produto/NovoProduto.fxml",event);
    }

    public void btRefreshActionHandler(ActionEvent e) throws IOException {
        list = FXCollections.observableList(pegaListas.listProduto());
        tvProdutos.setItems(FXCollections.observableList(list));
    }

    public void btAlterarProdutoActionHandler(ActionEvent event) throws IOException{
        if (tfNome.getText().isEmpty() || tfPreco.getText().isEmpty()) {
            controleTelas.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {

            list.add(produtoDAO.alterar(tfNome.getText(), tfEstoque.getText(), tfPreco.getText(), tfAlerta.getText(),
                    produtoMain.getId()));
            list.remove(produtoMain);
            controleTelas.notificacao("Alteração efetuada", "Alteração do produto concluída no banco de dados");
            tvProdutos.refresh();
        }

    }
    public void btDeletarProdutoActionHandler(ActionEvent event) throws IOException {
        a = controleTelas.continuarOuCancelar("Menssagem de confirmação",
                "Você está excluindo um produto!",
                "Você realmente deseja excluir o produto?");
        if (a) {
            produtoDAO.deletar(produtoMain);
            list.remove(produtoMain);
            tvProdutos.refresh();
        }
    }
    public void selecaoDeItens(Produto produto){
        produtoMain = produto;
        tfNome.setText(produto.getNome());
        tfPreco.setText(produto.getPreco());
        tfEstoque.setText(produto.getQuantidade());
        tfAlerta.setText(produto.getAlertaEstoque());
    }
}
