package sistemahotel.control;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javax.management.Notification;
import java.io.IOException;

/**
 * Created by Tatsunori on 05/10/2017.
 */
public class ControleTelas {
    public void newWindow(String path, ActionEvent event){
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Scene scene;
        stage.setScene(new Scene(root));
        ((Node) event.getSource()).getParent().getScene().getWindow().hide();
        stage.show();
    }


    public void setFragment(String path, AnchorPane pane) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource(path));
        pane.getChildren().setAll(a);
    }

    public void popupAviso(String titulo, String menssagem){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(menssagem);

        alert.showAndWait();
    }

    public void notificacao(String titulo, String texto){
        Notifications oie = Notifications.create()
                .title(titulo)
                .text(texto)
                .graphic(null)
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        oie.darkStyle();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                oie.showInformation();
            }
        });
    }
}
