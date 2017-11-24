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
import sistemahotel.model.local.Habitacao;
import sistemahotel.model.local.Local;
import sistemahotel.model.local.LocalDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControleTelaHabitacao implements Initializable {

    @FXML
    TableView tvHabitacao;
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
    private JFXTextField tfCamaSolteiro;
    @FXML
    private JFXTextField tfCamaCasal;
    @FXML
    private JFXTextField tfInfo;

    boolean a;
    Habitacao habitacaoMain;
    ControleTelas window = ControleTelas.getInstancia();
    LocalDAO localdao = new LocalDAO();
    RetornaListas pegaListas;
    ObservableList list;


    public void btDeletarHabitacaoActionHandler(ActionEvent event) throws IOException {
        a = window.continuarOuCancelar("Menssagem de confirmação",
                "Você está excluindo uma habitação!",
                "Você realmente deseja excluir a habitação?");
        if (a){
            localdao.DeletarLocal(habitacaoMain);
            list.remove(habitacaoMain);
            tvHabitacao.refresh();
        }
    }

    public void btNovaHabitacaoActionHandler(ActionEvent e) throws IOException {
        window.novaJanelaSobreposta("/sistemahotel/view/locais/NovaHabitacao.fxml",e);
    }

    public void btAlterarHabitacaoActionHandler(ActionEvent e){
        if (tfNumero.getText().isEmpty() || tfPreco.getText().isEmpty()) {
            window.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {
            list.add(localdao.AlterarHabitacao(habitacaoMain, tfNumero.getText(), tfPreco.getText(), tfInfo.getText(),
                    tfCamaSolteiro.getText(), tfCamaCasal.getText()));
            list.remove(habitacaoMain);
            window.notificacao("Alteração efetuada", "Alteração do produto concluída no banco de dados");
            tvHabitacao.refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableList(pegaListas.listHabitacao());
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tvHabitacao.setItems(FXCollections.observableList(list));

        tvHabitacao.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoDeItens((Habitacao) newValue)
        );

        FilteredList<Habitacao> filteredData = new FilteredList<>(list, p -> true);
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
        SortedList<Habitacao> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvHabitacao.comparatorProperty());
        tvHabitacao.setItems(sortedData);
    }

    public void selecaoDeItens(Habitacao habitacao){
        habitacaoMain = habitacao;
        tfNumero.setText(habitacao.getNumero());
        tfPreco.setText(habitacao.getPreco());
        tfCamaCasal.setText(habitacao.getCamasDeCasal());
        tfCamaSolteiro.setText(habitacao.getCamasDeSolteiro());
        tfInfo.setText(habitacao.getInformacoesAdicionais());
    }
}
