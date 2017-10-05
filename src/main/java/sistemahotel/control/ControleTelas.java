package sistemahotel.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        stage.setScene(new Scene(root));
        ((Node) event.getSource()).getParent().getScene().getWindow().hide();
        stage.show();
    }

    public void setFragment(String path, AnchorPane pane) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource(path));
        pane.getChildren().setAll(a);
    }
}
