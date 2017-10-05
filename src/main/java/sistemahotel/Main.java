package sistemahotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sistemahotel.model.infraestrutura.Persistencia;

/**
 * Created by marcelo on 19/09/17.
 */
public class Main extends  Application{
    public static void main(String[] args){
        Persistencia.getInstancia().startSsf();
        launch(args);


    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sistemahotel/view/Login.fxml"));
        Parent root = loader.load();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
