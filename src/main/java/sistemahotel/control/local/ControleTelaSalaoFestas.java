package sistemahotel.control.local;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.local.LocalDAO;
import sistemahotel.model.local.SalaoFestas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaSalaoFestas implements Initializable{
    @FXML
    TableView tvSalao;
    @FXML
    TableColumn tcPreco;
    @FXML
    TableColumn tcNumero;
    @FXML
    JFXTextField tfFiltro;
    @FXML
    JFXButton btExcluir;

    @FXML
    private JFXTextField tfNumero;
    @FXML
    private JFXTextField tfPreco;
    @FXML
    private JFXTextField tfMaxPessoas;
    @FXML
    private JFXTextField tfInfo;

    Boolean a;
    ControleTelas window = ControleTelas.getInstancia();
    LocalDAO localdao = new LocalDAO();
    RetornaListas pegaListas;
    ObservableList list;
    SalaoFestas salaoMain;


    public void btDeletarSalaoActionHandler(ActionEvent event) throws IOException {
        a = window.continuarOuCancelar("Menssagem de confirmação",
                "Você está excluindo um salaão de eventos!",
                "Você realmente deseja excluir o salão");
        if (a) {
            localdao.DeletarLocal(salaoMain);
            list.remove(salaoMain);
            tvSalao.refresh();
        }
    }

    public void btNovoSalaoActionHandler(ActionEvent e) throws IOException{
        window.novaJanelaSobreposta("/sistemahotel/view/locais/NovoSalao.fxml",e);
    }

    public void btAlterarSalaoActionHandler(ActionEvent e){
        if (tfNumero.getText().isEmpty() || tfPreco.getText().isEmpty()) {
            window.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {
            list.add(localdao.AlterarSalao(salaoMain, tfNumero.getText(), tfPreco.getText(), tfInfo.getText(), tfMaxPessoas.getText()));
            list.remove(salaoMain);
            window.notificacao("Alteração efetuada", "Alteração do produto concluída no banco de dados");
            tvSalao.refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableList(pegaListas.listSalaoFestas());
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tvSalao.setItems(FXCollections.observableList(list));

        tvSalao.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoDeItens((SalaoFestas) newValue)
        );

        FilteredList<SalaoFestas> filteredData = new FilteredList<>(list, p -> true);
        tfFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(local -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (local.getNumero().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<SalaoFestas> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvSalao.comparatorProperty());
        tvSalao.setItems(sortedData);

    }

    public void selecaoDeItens(SalaoFestas salao){
        salaoMain = salao;
        tfNumero.setText(salao.getNumero());
        tfPreco.setText(salao.getPreco());
        tfMaxPessoas.setText(salao.getMaximoPessoas());
        tfInfo.setText(salao.getInformacoesAdicionais());
    }
}
