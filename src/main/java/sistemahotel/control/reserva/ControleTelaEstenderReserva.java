package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.awt.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControleTelaEstenderReserva implements Initializable{

    @FXML
    JFXTextField tfCheckInAtual;
    @FXML
    JFXTextField tfCheckOutAtual;
    @FXML
    JFXDatePicker dpNovoCheckIn;
    @FXML
    JFXDatePicker dpNovoCheckOut;

    private ReservaDAO InstanciaReservaDAO = ReservaDAO.getInstancia();
    private ControleTelas janela = ControleTelas.getInstancia();
    private Reserva reservaMain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            tfCheckInAtual.setText(reservaMain.getDataCheckIn().format(formatter));
            tfCheckOutAtual.setText(reservaMain.getDataCheckOut().format(formatter));
        });

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
                janela.fechaJanela(event);
        }
    }

}
