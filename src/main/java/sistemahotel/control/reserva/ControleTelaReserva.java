package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.Persistencia;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.produto.Produto;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ControleTelaReserva implements Initializable{

    @FXML
    public TableView tvReservas;
    @FXML
    TableColumn tcStatus;
    @FXML
    TableColumn tcCliente;
    @FXML
    TableColumn tcLocal;
    @FXML
    TableColumn tcQtdhospede;
    @FXML
    TableColumn tcDataCheckIn;
    @FXML
    TableColumn tcDataCheckOut;
    @FXML
    JFXTextField tfFiltro;

    Reserva reservaMain;

    private ObservableList olReservas;
    private ReservaDAO InstanciaReservaDAO = ReservaDAO.getInstancia();
    private ControleTelas janela = ControleTelas.getInstancia();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        olReservas = FXCollections.observableList(RetornaListas.listReserva());
        tcStatus.setCellValueFactory( new PropertyValueFactory<>("status"));
        tcCliente.setCellValueFactory( new PropertyValueFactory<>("fk_cliente"));
        tcLocal.setCellValueFactory( new PropertyValueFactory<>("fk_local"));
        tcQtdhospede.setCellValueFactory( new PropertyValueFactory<>("qtdhospede"));
        tcDataCheckIn.setCellValueFactory(new PropertyValueFactory<>("dataCheckIn"));
        tcDataCheckOut.setCellValueFactory(new PropertyValueFactory<>("dataCheckOut"));
        tvReservas.setItems(FXCollections.observableList(olReservas));
        tvReservas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoReserva((Reserva) newValue)
        );

        FilteredList<Reserva> filteredData = new FilteredList<>(olReservas, p -> true);
        tfFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reserva -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (reserva.getCliente().getNome().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Reserva> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvReservas.comparatorProperty());
        tvReservas.setItems(sortedData);

    }

    public void btNovaReservaActionHandler(ActionEvent event) throws IOException{
        janela.newWindow("/sistemahotel/view/reserva/TelaNovaReserva.fxml",event);
    }

    public void btCancelaReservaActionHandler(ActionEvent event) throws IOException{


        InstanciaReservaDAO.cancelarReserva(reservaMain);
        tvReservas.refresh();
    }

    public void selecaoReserva(Reserva reserva){
        reservaMain = reserva;
    }

}
