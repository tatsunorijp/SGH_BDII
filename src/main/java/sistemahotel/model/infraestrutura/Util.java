package sistemahotel.model.infraestrutura;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;

public class Util {
    private static Util instancia = null;

    private Util(){
    }

    public static Util getInstancia(){
        if(instancia == null){
            instancia = new Util();
        }
        return instancia;
    }

    public static <T> void setUpFilter(ObservableList<T> ol, JFXTextField tf, TableView<T> tv) {
        FilteredList<T> filteredData = new FilteredList<>(ol, p -> true);
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(object -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                System.out.println(object.toString());
                if (object.toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<T> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tv.comparatorProperty());
        tv.setItems(sortedData);
    }

    public static boolean apenasNumeros(String string){
        if(string.equals("")){
            return true;
        }

        try{
            float num = Float.parseFloat(string);
//            if(num < 0){
//                return false;
//            }
        }catch(NumberFormatException ex){
            return false;
        }

        return true;
    }
}
