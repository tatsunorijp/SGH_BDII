package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.local.Local;
import sistemahotel.model.pessoa.Cliente;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaNovaReserva implements Initializable{

    @FXML
    JFXTextField tfFiltroCliente;
    @FXML
    JFXTextField tfFiltroLocal;
    @FXML
    JFXTextField tfQtdhospede;

    @FXML
    public TableView tvClientes;
    @FXML
    public TableView tvLocais;

    @FXML
    TableColumn tcClienteNome;
    @FXML
    TableColumn tcClienteCPF;
    @FXML
    TableColumn tcLocalNumero;
    @FXML
    TableColumn tcLocalTipo;
    @FXML
    TableColumn tcLocalPreco;
    @FXML
    DatePicker dpDataCheckIn;
    @FXML
    DatePicker dpDataCheckOut;

    private Cliente cliente = null;
    private Local local = null;
    private ObservableList olClientes;
    private ObservableList olLocais;
    private ReservaDAO InstanciaReservaDAO = ReservaDAO.getInstancia();
    private ControleTelas janela = ControleTelas.getInstancia();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        olClientes= FXCollections.observableList(RetornaListas.listClientes());
        tcClienteNome.setCellValueFactory( new PropertyValueFactory<>("nome"));
        tcClienteCPF.setCellValueFactory( new PropertyValueFactory<>("CPF"));
        tvClientes.setItems(FXCollections.observableList(olClientes));
        tvClientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoCliente((Cliente) newValue)
        );

        FilteredList<Cliente> filteredDataClientes = new FilteredList<>(olClientes, p -> true);
        tfFiltroCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataClientes.setPredicate(cliente -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (cliente.getNome().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Cliente> sortedDataClientes = new SortedList<>(filteredDataClientes);
        sortedDataClientes.comparatorProperty().bind(tvClientes.comparatorProperty());
        tvClientes.setItems(sortedDataClientes);

        olLocais= FXCollections.observableList(RetornaListas.listLocais());
        tcLocalNumero.setCellValueFactory( new PropertyValueFactory<>("numero"));
        tcLocalTipo.setCellValueFactory( new PropertyValueFactory<>("tipo"));
        tcLocalPreco.setCellValueFactory( new PropertyValueFactory<>("preco"));
        tvLocais.setItems(FXCollections.observableList(olLocais));
        tvLocais.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoLocal((Local) newValue)
        );

        FilteredList<Local> filteredDataLocais = new FilteredList<>(olLocais, p -> true);
        tfFiltroLocal.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataLocais.setPredicate(local -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (local.getTipo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Local> sortedDataLocal = new SortedList<>(filteredDataLocais);
        sortedDataLocal.comparatorProperty().bind(tvLocais.comparatorProperty());
        tvLocais.setItems(sortedDataLocal);

    }

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        janela.newWindow("/sistemahotel/view/TelaPrincipal.fxml", event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        if (cliente                   == null ||
            local                     == null ||
            tfQtdhospede.getText().isEmpty()  ||
            dpDataCheckIn.getValue()  == null ||
            dpDataCheckOut.getValue() == null)  { janela.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {
            if ( InstanciaReservaDAO.checarIndisponibilidade(local, dpDataCheckIn.getValue(), dpDataCheckOut.getValue()) ) {
                janela.popupAviso("Data inválida", "Esta habitação está ocupada nesta data");
            } else {
                InstanciaReservaDAO.novaReserva(cliente, local, dpDataCheckIn.getValue(), dpDataCheckOut.getValue(), tfQtdhospede.getText());
                janela.notificacao("Reserva efetuada", "Nova reserva agendada no banco de dados");
                janela.newWindow("/sistemahotel/view/TelaPrincipal.fxml", event);
            }
        }
    }

    public void selecaoCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void selecaoLocal(Local local){
        this.local = local;
    }

}
