package sistemahotel.control.local;

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
    private JFXTextField tfNumero;
    @FXML
    private JFXTextField tfPreco;
    @FXML
    private JFXTextField tfMaxPessoas;
    @FXML
    private JFXTextField tfInfo;


    ControleTelas window = ControleTelas.getInstancia();
    LocalDAO localdao = new LocalDAO();
    RetornaListas pegaListas;
    ObservableList list;
    SalaoFestas salaoMain;

    public void btNovoSalaoActionHandler(ActionEvent e) throws IOException{
        window.novaJanelaSobreposta("/sistemahotel/view/locais/NovaSalao.fxml",e);
    }

    public void btAlterarSalaoActionHandler(ActionEvent e){
        localdao.AlterarSalao(salaoMain, tfNumero.getText(), tfPreco.getText(), tfInfo.getText(), tfMaxPessoas.getText());
    }

    public void btVoltarActionHandler(ActionEvent event) throws IOException {
        window.novaJanela("/sistemahotel/view/telaprincipal/TelaPrincipal.fxml", event);
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
