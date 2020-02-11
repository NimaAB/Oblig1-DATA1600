package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DataCollection {
    private ObservableList<PersonDataModel> objToTV = FXCollections.observableArrayList();

   public void kobligTilTable(TableView table){
        table.setItems(objToTV);
    }
    public void leggTilEllement(PersonDataModel obj){
        objToTV.add(obj);
    }
}
