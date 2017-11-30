package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.local.Local;
import sistemahotel.model.pessoa.Cliente;
import sistemahotel.model.reserva.ReservaDAO;

import java.net.URL;
import java.util.ResourceBundle;

import static sistemahotel.model.infraestrutura.Util.setUpFilter;

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
    TableColumn tcLocalCamasSolteiro;
    @FXML
    TableColumn tcLocalCamasCasal;
    @FXML
    TableColumn tcLocalPreco;
    @FXML
    JFXDatePicker dpDataCheckIn;
    @FXML
    JFXDatePicker dpDataCheckOut;

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
        setUpFilter(olClientes, tfFiltroCliente, tvClientes);


        olLocais= FXCollections.observableList(RetornaListas.listHabitacao());
        tcLocalNumero.setCellValueFactory( new PropertyValueFactory<>("numero"));
        tcLocalCamasSolteiro.setCellValueFactory( new PropertyValueFactory<>("camasDeSolteiro"));
        tcLocalCamasCasal.setCellValueFactory( new PropertyValueFactory<>("camasDeCasal"));
        tcLocalPreco.setCellValueFactory( new PropertyValueFactory<>("preco"));
        tvLocais.setItems(FXCollections.observableList(olLocais));
        tvLocais.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoLocal((Local) newValue)
        );
        setUpFilter(olLocais, tfFiltroLocal, tvLocais);

    }

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        janela.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        if (cliente                   == null ||
            local                     == null ||
            tfQtdhospede.getText().isEmpty()  ||
            dpDataCheckIn.getValue()  == null ||
            dpDataCheckOut.getValue() == null)  { janela.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {
            if ( ReservaDAO.checarIndisponibilidade(local, dpDataCheckIn.getValue(), dpDataCheckOut.getValue()) ) {
                janela.popupAviso("Data inválida", "Esta habitação está ocupada nesta data");
            } else {
                InstanciaReservaDAO.novaReserva(cliente, local, dpDataCheckIn.getValue(), dpDataCheckOut.getValue(), tfQtdhospede.getText());
                janela.notificacao("Reserva efetuada", "Nova reserva agendada no banco de dados");
                janela.novaJanela("/sistemahotel/view/telaprincipal/TelaPrincipal.fxml", event);
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
