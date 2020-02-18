package Håntering;

import Data.PersonDataModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TVInputHåntering {
    public boolean nameCExceptable(TableView table, TableColumn<PersonDataModel,String> columnID){
        int row = table.getSelectionModel().getFocusedIndex();
        String name = columnID.getCellObservableValue(row).getValue();
        if(name.isEmpty()){
            return false;
        }
        return AvviksHåntering.isValidateName(name);
    }
    public boolean birthdateCExceptable(TableView table, TableColumn<PersonDataModel,String> columnID){
        int row = table.getSelectionModel().getFocusedIndex();
        String date = columnID.getCellObservableValue(row).getValue();
        return AvviksHåntering.isValidDate(date) && date.isEmpty();
    }
    public boolean epostCExceptable(TableView table, TableColumn<PersonDataModel,String> columnID){
        int row = table.getSelectionModel().getFocusedIndex();
        String epost = columnID.getCellObservableValue(row).getValue();
        return AvviksHåntering.isValidEpost(epost) && epost.isEmpty();
    }
    public boolean tlfNrCExeptable(TableView table, TableColumn<PersonDataModel,String> columnID){
        int row = table.getSelectionModel().getFocusedIndex();
        String tlfNr = columnID.getCellObservableValue(row).getValue();
        return AvviksHåntering.isValidTlfnr(tlfNr) && tlfNr.isEmpty();
    }
}
