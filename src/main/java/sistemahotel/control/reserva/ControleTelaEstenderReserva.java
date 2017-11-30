package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaEstenderReserva implements Initializable{

    @FXML
    JFXDatePicker dpCheckInAtual;
    @FXML
    JFXDatePicker dpCheckOutAtual;
    @FXML
    JFXDatePicker dpNovoCheckIn;
    @FXML
    JFXDatePicker dpNovoCheckOut;

    private ReservaDAO InstanciaReservaDAO = ReservaDAO.getInstancia();
    private ControleTelas janela = ControleTelas.getInstancia();
    private Reserva reservaMain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*dpCheckInAtual.setValue(reservaMain.getDataCheckIn());
        dpCheckOutAtual.setValue(reservaMain.getDataCheckOut());*/

    }

    public void setReserva(Reserva reserva) {
        this.reservaMain = reserva;
    }

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        janela.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {
        if ( ReservaDAO.checarEstenderReserva(reservaMain, reservaMain.getLocal(), dpNovoCheckIn.getValue(), dpNovoCheckOut.getValue()) ) {
                janela.popupAviso("Data inválida", "Esta habitação está ocupada nesta data");
        } else {
                InstanciaReservaDAO.estenderReserva(reservaMain, dpNovoCheckIn.getValue(), dpNovoCheckOut.getValue());
                janela.notificacao("Reserva alterada", "Voce alterou o período desta reserva");
                janela.novaJanela("/sistemahotel/view/telaprincipal/TelaPrincipal.fxml", event);
        }
    }

}
