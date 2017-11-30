package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.spreadsheet.*;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.local.Local;
import sistemahotel.model.reserva.Reserva;
import sistemahotel.model.reserva.ReservaDAO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;
import static sistemahotel.model.infraestrutura.RetornaListas.listLocais;
import static sistemahotel.model.infraestrutura.Util.setUpFilter;

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
    @FXML
    JFXDatePicker dpDataInicio;
    @FXML
    JFXDatePicker dpDataFim;
    @FXML
    SpreadsheetView svReservas;

    Reserva reservaMain;

    private ObservableList olReservas;
    private ReservaDAO InstanciaReservaDAO = ReservaDAO.getInstancia();
    private ControleTelas janela = ControleTelas.getInstancia();
    GridBase reservaGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dpDataInicio.setValue(LocalDate.now());
        dpDataFim.setValue(LocalDate.now().plusDays(15));
        setUpSpreadSheet();

        List<Reserva> auxlist = RetornaListas.listReserva();
        Collections.sort(auxlist);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        olReservas = FXCollections.observableList(auxlist);
        tcStatus.setCellValueFactory( new PropertyValueFactory<>("status"));
        tcCliente.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reserva, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getCliente().getNome()));
        tcLocal.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Reserva, String>, ObservableValue<String>>) r -> new SimpleStringProperty(r.getValue().getLocal().getNumero()));
        tcQtdhospede.setCellValueFactory( new PropertyValueFactory<>("qtdhospede"));
        tcDataCheckIn.setCellValueFactory(new PropertyValueFactory<>("dataCheckIn"));
        tcDataCheckOut.setCellValueFactory(new PropertyValueFactory<>("dataCheckOut"));
        tvReservas.setItems(FXCollections.observableList(olReservas));
        tvReservas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoReserva((Reserva) newValue)
        );
        setUpFilter(olReservas, tfFiltro, tvReservas);

    }

    public void setUpSpreadSheet(){

        int rowCount, columnCount;
        List<Local> listlocais = listLocais();
        Collections.sort(listlocais);

        rowCount = listlocais.size();
        columnCount = (int) ChronoUnit.DAYS.between(dpDataInicio.getValue(),dpDataFim.getValue()) + 1;
        reservaGrid = new GridBase(rowCount, columnCount);

        //criando a array de cells
        ObservableList<ObservableList<SpreadsheetCell>> listOfRows = observableArrayList();

        //povoando a array com as cells
        for(int i = 0; i<listlocais.size(); i++){
            ObservableList<SpreadsheetCell> auxlist = observableArrayList();
            for(int j = 0; j<columnCount; j++){
                auxlist.add(SpreadsheetCellType.INTEGER.createCell(i, j, 1, 1, null));
            }
            listOfRows.add(auxlist);
        }
        reservaGrid.setRows(listOfRows);

        //setando  os headers das colunas e linhas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

        List<String> columnheader = new ArrayList<>();
        for(int i = 0; i<columnCount; i++){
            columnheader.add(dpDataInicio.getValue().plusDays(i).format(formatter));
        }
        reservaGrid.getColumnHeaders().setAll(columnheader);

        List<String> rowheader = new ArrayList<>();
        for(int i = 0; i<rowCount; i++){
            rowheader.add(listlocais.get(i).toString());
        }
        reservaGrid.getRowHeaders().setAll(rowheader);

        //setando configs do spreadsheet
        svReservas.setRowHeaderWidth(50);
        svReservas.setEditable(false);
        svReservas.setGrid(reservaGrid);
        for(SpreadsheetColumn aux : svReservas.getColumns()){
            aux.setMinWidth(50);
            aux.setMaxWidth(50);
            aux.setResizable(false);
        }

        //marcando as células que representam datas ocupadas
        LocalDate celldate = dpDataInicio.getValue();
        for(int i = 0; i< reservaGrid.getRowCount(); i++){
            for(int j = 0; j< reservaGrid.getRows().get(i).size(); j++){

                celldate = dpDataInicio.getValue().plusDays(i+j);

                if(ReservaDAO.checarIndisponibilidade(listlocais.get(i), celldate, celldate)){
                    reservaGrid.getRows().get(i).get(j).setStyle("-fx-background-color: blue");
                }
            }
        }


    }

    public void btNovaReservaActionHandler(ActionEvent event) throws IOException{
        janela.novaJanelaSobreposta("/sistemahotel/view/reserva/TelaNovaReserva.fxml",event);
    }

    public void btNovaReservaSalaoActionHandler(ActionEvent event) throws IOException{
        janela.novaJanelaSobreposta("/sistemahotel/view/reserva/TelaNovaReservaSalao.fxml",event);
    }

    public void btCheckInActionHandler(ActionEvent event) throws IOException{

        System.out.println(reservaMain.getStatus());

        if (!Objects.equals(reservaMain.getStatus(), "Agendada"))  {
            janela.popupAviso("Ação inválida", "A reserva deve estar 'Agendada' para que se possa realizar esta ação.");
        } else {
            boolean msg = janela.continuarOuCancelar("Menssagem de confirmação",
                    "Você está colocando esta reserva em andamento",
                    "Deseja continuar ?");
            if (msg) {
                InstanciaReservaDAO.fazerCheckIn(reservaMain);
                tvReservas.refresh();
            }
        }
    }

    public void btCheckOutActionHandler(ActionEvent event) throws IOException{

        if (!Objects.equals(reservaMain.getStatus(), "Em andamento"))  {
            janela.popupAviso("Ação inválida", "A reserva deve estar 'Em andamento' para que se possa realizar esta ação.");
        } else {

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sistemahotel/view/reserva/TelaCheckOut.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Scene scene = new Scene(root);
            scene.getStylesheets().add("TableViewCSS.css");
            stage.setScene(scene);

            ControleTelaCheckOut controller = loader.getController();
            controller.setReserva(reservaMain);

            stage.show();

            //janela.novaJanelaSobreposta("/sistemahotel/view/reserva/TelaCheckOut.fxml",event);
        }
    }

    public void btEstenderReservaActionHandler(ActionEvent event) throws IOException{

        if (!Objects.equals(reservaMain.getStatus(), "Agendada") && !Objects.equals(reservaMain.getStatus(), "Em andamento"))  {
            janela.popupAviso("Ação inválida", "A reserva deve estar 'Agendada' ou 'Em andamento' para que se possa realizar esta ação.");
        } else {

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sistemahotel/view/reserva/TelaEstenderReserva.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Scene scene = new Scene(root);
            scene.getStylesheets().add("TableViewCSS.css");
            stage.setScene(scene);

            ControleTelaEstenderReserva controller = loader.getController();
            controller.setReserva(reservaMain);

            stage.show();

            //customNovaJanelaSobreposta("/sistemahotel/view/reserva/TelaEstenderReserva.fxml", reservaMain);
        }

    }

    public void customNovaJanelaSobreposta(String path, Reserva reserva){
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("TableViewCSS.css");
        stage.setScene(scene);

        ControleTelaEstenderReserva controller = loader.getController();
        controller.setReserva(reserva);

        stage.show();

    }

    public void btConsumacaoActionHandler(ActionEvent event) throws IOException{

        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sistemahotel/view/reserva/TelaConsumacao.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add("TableViewCSS.css");
        stage.setScene(scene);

        ControleTelaConsumacao controller = loader.getController();
        controller.setReserva(reservaMain);

        stage.show();
//        janela.novaJanelaSobreposta("/sistemahotel/view/reserva/TelaConsumacao.fxml",event);
    }

    public void btCancelaReservaActionHandler(ActionEvent event) throws IOException{
        boolean msg = janela.continuarOuCancelar("Menssagem de confirmação",
                "Você está cancelando uma reserva!",
                "Você realmente deseja cancelar esta reserva?");
        if (msg) {
            InstanciaReservaDAO.cancelarReserva(reservaMain);
            olReservas.remove(reservaMain);
            tvReservas.refresh();
        }
    }

    public void btRefreshSheetActionHandler(ActionEvent event) {
        if (dpDataInicio.getValue().compareTo(dpDataFim.getValue()) > 0)
            janela.popupAviso("Período inválido", "Selecione um período válido.");
        else setUpSpreadSheet();
    }

    public void selecaoReserva(Reserva reserva){
        reservaMain = reserva;
    }

}
