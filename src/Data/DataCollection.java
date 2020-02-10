package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DataCollection {
    private ObservableList<DataModel> objToTV = FXCollections.observableArrayList();

   public void kobligTilTable(TableView table){
        table.setItems(objToTV);
    }
    public void leggTilEllement(DataModel obj){
        objToTV.add(obj);
    }
}
