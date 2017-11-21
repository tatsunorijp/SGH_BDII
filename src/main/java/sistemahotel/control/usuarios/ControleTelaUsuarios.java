package sistemahotel.control.usuarios;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemahotel.control.ControleTelas;
import sistemahotel.model.infraestrutura.RetornaListas;
import sistemahotel.model.pessoa.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControleTelaUsuarios implements Initializable {


    @FXML
    TableView tvUsuarios;
    @FXML
    TableColumn tcLogin;
    @FXML
    TableColumn tcTipo;
    @FXML
    JFXTextField tfFiltro;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfRG;
    @FXML
    private JFXTextField tfCPF;
    @FXML
    private DatePicker dtDataDeNascimento;
    @FXML
    private JFXTextField tfLogin;
    @FXML
    private JFXTextField tfSenha;

    Usuario usuarioMain;
    UsuarioDAO usuarioDAO = UsuarioDAO.getInstancia();
    RetornaListas pegaListas;
    ObservableList list;
    ControleTelas controleTelas = ControleTelas.getInstancia();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = FXCollections.observableList(pegaListas.listUsuario());
        tcLogin.setCellValueFactory(new PropertyValueFactory<>("Login"));
        tcTipo.setCellValueFactory(new PropertyValueFactory<>("Tipo"));
        tvUsuarios.setItems(FXCollections.observableList(list));

        tvUsuarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldvalue, newValue) -> selecaoDeItens((Usuario) newValue)
        );
        FilteredList<Usuario> filteredData = new FilteredList<>(list, p -> true);
        tfFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(usuario -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (usuario.getLogin().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Usuario> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvUsuarios.comparatorProperty());
        tvUsuarios.setItems(sortedData);
    }

    public void btNovoUsuarioActionHandler(ActionEvent event) throws IOException {
        //controleTelas.novaJanelaSobreposta("/sistemahotel/view/pessoa/NovoUsuario.fxml",event);
    }

    public void btAlterarUsuarioActionHandler(ActionEvent event) throws IOException {
        if (tfNome.getText().isEmpty() || tfRG.getText().isEmpty()) {
            controleTelas.popupAviso("Campos inválidos", "Campos com * são obrigatórios");
        } else {
            list.add(usuarioDAO.Alterar(tfNome.getText(),tfLogin.getText(), tfSenha.getText(), tfCPF.getText(), tfRG.getText(),
                    dtDataDeNascimento.getValue(), usuarioMain.getId()));
            list.remove(usuarioMain);
            controleTelas.notificacao("Alteração efetuada", "Alteração do usuario concluída no banco de dados");
            tvUsuarios.refresh();
        }
    }

    public void btDeletarUsuarioActionHandler(ActionEvent event) throws IOException {
        boolean conf;

        conf = controleTelas.continuarOuCancelar("Menssgem de confirmação",
                "Você está excluindo um usuario!",
                "Você realmente deseja excluir o usuario?");
        if (conf) {
            usuarioDAO.Deletar(usuarioMain);
            list.remove(usuarioMain);
            tvUsuarios.refresh();
        }
    }

    public void selecaoDeItens(Usuario usuario) {
        usuarioMain = usuario;
        tfNome.setText(usuario.getNome());
        tfRG.setText(usuario.getRG());
        tfCPF.setText(usuario.getCPF());
        dtDataDeNascimento.setValue(usuario.getDataDeNascimento());
        tfLogin.setText(usuario.getLogin());
        tfSenha.setText(usuario.getSenha());
    }
}
