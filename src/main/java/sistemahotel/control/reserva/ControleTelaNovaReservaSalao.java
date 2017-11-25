package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.local.Local;
import sistemahotel.model.local.SalaoFestas;
import sistemahotel.model.pessoa.Cliente;
import sistemahotel.model.reserva.ReservaDAO;

import java.net.URL;
import java.util.ResourceBundle;

import static sistemahotel.model.infraestrutura.Util.setUpFilter;

public class ControleTelaNovaReservaSalao implements Initializable{

    @FXML
    JFXTextField tfFiltroCliente;
    @FXML
    JFXTextField tfFiltroLocal;
    @FXML
    JFXTextField tfNomeDoEvento;

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
    TableColumn tcLocalLotacaoMax;
    @FXML
    TableColumn tcLocalPreco;
    @FXML
    DatePicker dpDataCheckIn;
    @FXML
    DatePicker dpDataCheckOut;

    private Cliente cliente = null;
    private SalaoFestas local = null;
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


        olLocais= FXCollections.observableList(RetornaListas.listSalaoFestas());
        tcLocalNumero.setCellValueFactory( new PropertyValueFactory<>("numero"));
        tcLocalLotacaoMax.setCellValueFactory( new PropertyValueFactory<>("maximoPessoas"));
        tcLocalPreco.setCellValueFactory( new PropertyValueFactory<>("preco"));
        tvLocais.setItems(FXCollections.observableList(olLocais));
        tvLocais.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoLocal((SalaoFestas) newValue)
        );
        setUpFilter(olLocais, tfFiltroLocal, tvLocais);

    }

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        janela.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        if (    cliente                   == null ||
                local                     == null ||
                dpDataCheckIn.getValue()  == null ||
                dpDataCheckOut.getValue() == null)  { janela.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {
            if ( InstanciaReservaDAO.checarIndisponibilidade(local, dpDataCheckIn.getValue(), dpDataCheckOut.getValue()) ) {
                janela.popupAviso("Data inválida", "Este salão está ocupado nesta data");
            } else {
                InstanciaReservaDAO.novaReservaSalao(cliente, local, dpDataCheckIn.getValue(), dpDataCheckOut.getValue(), tfNomeDoEvento.getText());
                janela.notificacao("Reserva de Salao efetuada", "Nova reserva agendada no banco de dados");
                janela.novaJanela("/sistemahotel/view/telaprincipal/TelaPrincipal.fxml", event);
            }
        }
    }

    public void selecaoCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public void selecaoLocal(SalaoFestas local){
        this.local = local;
    }
}
