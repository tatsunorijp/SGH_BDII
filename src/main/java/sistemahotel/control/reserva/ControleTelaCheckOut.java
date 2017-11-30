package sistemahotel.control.reserva;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ControleTelaCheckOut implements Initializable{

    private ControleTelas janela = ControleTelas.getInstancia();
    private ReservaDAO InstanciaReservaDAO = ReservaDAO.getInstancia();
    private Reserva reservaMain;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(() ->{

            int dias =(int) ChronoUnit.DAYS.between(reservaMain.getDataCheckIn(), reservaMain.getDataCheckOut()) + 1;



        });

    }

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        janela.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {

        boolean msg = janela.continuarOuCancelar("Menssagem de confirmação",
                "Você está finalizando uma reserva",
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
