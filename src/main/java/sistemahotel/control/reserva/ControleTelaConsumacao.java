package sistemahotel.control.reserva;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
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
import sistemahotel.model.infraestrutura.Util;
import sistemahotel.model.produto.Produto;
import sistemahotel.model.produto.ProdutoDAO;
import sistemahotel.model.reserva.Consumacao;
import sistemahotel.model.reserva.ConsumacaoDAO;
import sistemahotel.model.reserva.Reserva;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by tatsunori on 30/11/17.
 */
public class ControleTelaConsumacao implements Initializable{
    @FXML
    private JFXButton btConfirmar;

    @FXML
    private JFXButton btCancelar;

    @FXML
    private JFXTextField tfQtd;

    @FXML
    private JFXTextField tfFiltroNomeProduto;

    @FXML
    private TableView tvProdutos;

    @FXML
    private TableView tvConsumidos;

    @FXML
    private TableColumn tcNomeProdutos;

    @FXML
    private TableColumn tcEstoqueProdutos;

    @FXML
    private TableColumn tcPrecoProdutos;

    @FXML
    private TableColumn tcNomeConsumidos;

    @FXML
    private TableColumn tcQuantidadeConsumidos;

    Boolean a;
    ControleTelas controleTelas = ControleTelas.getInstancia();
    ProdutoDAO produtoDAO = ProdutoDAO.getInstancia();
    ConsumacaoDAO consumacaoDAO = ConsumacaoDAO.getInstancia();
    Produto produtoMain = null;
    Reserva reservaMain = null;
    Util util = Util.getInstancia();
    ObservableList listProdutos;
    ObservableList listConsumacao;
    RetornaListas pegaListas;
    Consumacao consumacaoMain;


    public void setReserva(Reserva reserva){
        this.reservaMain = reserva;
    }

    @FXML
    void btCancelarActionHandler(ActionEvent event) {
        controleTelas.fechaJanela(event);
    }

    @FXML
    void btConfirmarActionHandler(ActionEvent event) {

        if(produtoMain == null) return;

        if( (!util.apenasNumeros(tfQtd.getText())) || (tfQtd.getText().equals("")) ){
            controleTelas.popupAviso("Campo inválido", "Digite corretamente a quantidade do produto");
        }else{
            a = controleTelas.continuarOuCancelar("Menssagem de confirmação",
                    "Você esta adicionando uma consumação!",
                    "Você realmente deseja adicionar o produto?");
            if (a) {


                if(consumacaoDAO.addConsumo(produtoMain, tfQtd.getText(), reservaMain)){
                    controleTelas.notificacao("Consumo salvo", "Novo consumo adicionado a reserva");
                    controleTelas.fechaJanela(event);
                }else{
                    controleTelas.popupAviso("Operação não efetuada", "Estoque insuficiente para a consumação");
                }
            }
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() ->{
            listProdutos = FXCollections.observableList(pegaListas.listProduto());

            tcNomeProdutos.setCellValueFactory( new PropertyValueFactory<>("nome"));
            tcPrecoProdutos.setCellValueFactory(new PropertyValueFactory<>("preco"));
            tcEstoqueProdutos.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            tvProdutos.setItems(FXCollections.observableList(listProdutos));

            tvProdutos.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldvalue, newValue) -> selecaoDeItens((Produto) newValue)
            );

            Util.setUpFilter(listProdutos,tfFiltroNomeProduto, tvProdutos );
            ////////////////////////////////////////////////////////////////////////////

            listConsumacao = FXCollections.observableList(pegaListas.listConsumacao(reservaMain));

            tcNomeConsumidos.setCellValueFactory( new PropertyValueFactory<>("produto"));
            tcQuantidadeConsumidos.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
            tvConsumidos.setItems(FXCollections.observableList(listConsumacao));
            tvProdutos.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldvalue, newValue) -> selecaoDeItensConsumo((Consumacao) newValue)
            );

        });

    }

    public void selecaoDeItens(Produto produto){
        produtoMain = produto;
    }
    public void selecaoDeItensConsumo(Consumacao consumacao){ consumacaoMain = consumacao;}
    @FXML
    public void btExcluirConsumacaoActionHandler(ActionEvent event){
        a = controleTelas.continuarOuCancelar("Menssagem de confirmação",
                "Você está excluindo uma consumação!",
                "Você realmente deseja excluir o item?");
        if (a) {
            consumacaoDAO.deleteConsumacao(consumacaoMain);
            listConsumacao.remove(consumacaoMain);
            tvConsumidos.refresh();
        }


    }
}
