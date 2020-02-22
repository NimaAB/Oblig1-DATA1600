package Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class DataCollection {
    public ObservableList<PersonDataModel> objToTV = FXCollections.observableArrayList();

    public void kobligTilTable(TableView<PersonDataModel> table, TextField searchTxt){
        FilteredList<PersonDataModel> filteredList = new FilteredList<>(objToTV, b -> true);
        searchTxt.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(perObj->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                int age = 0;
                if(Pattern.matches("[0-9]*", newValue)) {
                    try {
                        age = Integer.parseInt(newValue);
                    } catch (NumberFormatException e) {
                        System.err.println(e.getMessage());
                    }
                }
                String lowerCaseF = newValue.toLowerCase();
                if(perObj.getName().toLowerCase().contains(lowerCaseF)){
                    return true;
                }else if(perObj.getEPost().contains(lowerCaseF)){
                    return true;
                }else if (perObj.getAge() == age){
                     return true;
                }else if(perObj.getBirthDate().contains(newValue)){
                    return true;
                }else return perObj.getTlfNr().contains(newValue);
            });
        });
        SortedList<PersonDataModel> sortertData = new SortedList<>(filteredList);
        sortertData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortertData);
    }
    public void leggTilEllement(PersonDataModel obj){
        objToTV.add(obj);
    }
}
