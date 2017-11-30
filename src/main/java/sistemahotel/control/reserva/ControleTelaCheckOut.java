package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.reserva.Consumacao;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ControleTelaCheckOut implements Initializable{

    @FXML
    TableView tvConsumacao;
    @FXML
    TableColumn tcProduto;
    @FXML
    TableColumn tcQtd;
    @FXML
    TableColumn tcPreco;
    @FXML
    Text txtDias;
    @FXML
    Text txtTotal;

    private ControleTelas janela = ControleTelas.getInstancia();
    private ReservaDAO InstanciaReservaDAO = ReservaDAO.getInstancia();
    private Reserva reservaMain;
    private ObservableList<Consumacao> olConsumacao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() ->{

            int valor = 0;
            int dias =(int) ChronoUnit.DAYS.between(reservaMain.getDataCheckIn(), reservaMain.getDataCheckOut());

            List<Consumacao> auxlist = RetornaListas.listConsumacao(reservaMain);
            Collections.sort(auxlist);
            olConsumacao = FXCollections.observableList(auxlist);
            tcProduto.setCellValueFactory( new PropertyValueFactory<>("produto"));
            tcQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            tcPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
            tvConsumacao.setItems(FXCollections.observableList(olConsumacao));

            for(Consumacao aux: auxlist){
                valor += (  Integer.parseInt(aux.getPreco()) * Integer.parseInt(aux.getQuantidade())  ) ;
            }
            valor += Integer.parseInt(reservaMain.getLocal().getPreco()) * dias;

            txtDias.setText("Qtd. dias: " + String.valueOf(dias));
            txtTotal.setText("Valor total: R$" + String.valueOf(valor));

        });

    }

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        janela.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {

        boolean msg = janela.continuarOuCancelar("Menssagem de confirmação",
                "Você está finalizando esta reserva",
                "Deseja continuar ?");
        if (msg) {
            InstanciaReservaDAO.fazerCheckOut(reservaMain);
            janela.fechaJanela(event);
        }

    }

    public void setReserva(Reserva reserva){
        this.reservaMain = reserva;
    }
}
