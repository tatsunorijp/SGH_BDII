package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import org.controlsfx.control.spreadsheet.SpreadsheetView;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sistemahotel.model.infraestrutura.Util.setUpFilter;

public class Testeplanilha implements Initializable{


    @FXML
    SpreadsheetView svReservas;

    Reserva reservaMain;

    private ReservaDAO InstanciaReservaDAO = ReservaDAO.getInstancia();
    private ControleTelas janela = ControleTelas.getInstancia();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        svReservas.setEditable(false);


    }

    public void btNovaReservaActionHandler(ActionEvent event) throws IOException{
        //janela.novaJanelaSobreposta("/sistemahotel/view/reserva/TelaNovaReserva.fxml",event);
        janela.novaJanelaSobreposta("/sistemahotel/view/reserva/test.fxml",event);
    }

    public void btCancelaReservaActionHandler(ActionEvent event) throws IOException{
        /*boolean msg = janela.continuarOuCancelar("Menssagem de confirmação",
                "Você está cancelando uma reserva!",
                "Você realmente deseja cancelar esta reserva?");
        if (msg) {
            InstanciaReservaDAO.cancelarReserva(reservaMain);
            olReservas.remove(reservaMain);
            tvReservas.refresh();
        }*/
    }

    public void selecaoReserva(Reserva reserva){
        reservaMain = reserva;
    }

}
