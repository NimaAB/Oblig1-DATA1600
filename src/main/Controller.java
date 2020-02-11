package main;

import Data.DataCollection;
import Data.PersonDataModel;
import Håntering.AvikksHåntering;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private DataCollection collection = new DataCollection();
    @FXML
    private TextField nameTxt, ePostTxt, tlfNrTxt;
    @FXML
    private TextField yearTxt, monthTxt, dayTxt;
    @FXML
    private Label ErrorLbl;
    @FXML
    private TableView Table;
    @FXML
    private TextField SearchTxt;

    private PersonDataModel creatPersonObjToDataModel(){
        String yearStr = yearTxt.getText();
        String monthStr = monthTxt.getText();
        String dayStr = dayTxt.getText();

        int year = AvikksHåntering.outYear;
        int month = AvikksHåntering.outMonth;
        int day = AvikksHåntering.outDay;

        boolean isValifNumFormat = AvikksHåntering.isValidNumFormat(yearStr,monthStr,dayStr);
        boolean isValidDate = AvikksHåntering.isValidDate(year,month,day);
        String birthDate = yearStr+"-"+monthStr+"-"+dayStr;
        String name = nameTxt.getText();
        boolean isValidName = AvikksHåntering.isValidateName(name);
        String ePost = ePostTxt.getText();
        boolean isValidEPost = AvikksHåntering.isValidEpost(ePost);
        String tlfNr = tlfNrTxt.getText();
        boolean isValidTlfnr = AvikksHåntering.isValidTlfnr(tlfNr);
        boolean allowAddObj= isValifNumFormat && isValidDate
                && isValidEPost && isValidName && isValidTlfnr;
        PersonDataModel obj = null;
        if(!allowAddObj){
            ErrorLbl.setText(AvikksHåntering.melding);
        }else{
            obj = new PersonDataModel(name,ePost,tlfNr,birthDate);
            ErrorLbl.setText("");
        }
        return obj;
    }
    private void resetTxtFields(){
        nameTxt.setText("");
        ePostTxt.setText("");
        tlfNrTxt.setText("");
        yearTxt.setText("");
        monthTxt.setText("");
        dayTxt.setText("");
    }
    @FXML
    void addObjToTable(ActionEvent event) {
        PersonDataModel obj = creatPersonObjToDataModel();
        if(obj !=null) {
            collection.leggTilEllement(obj);
            resetTxtFields();
        }
    }
    @FXML
    void PrintList(ActionEvent event) {
        DataCollection collection = new DataCollection();
        String dateS = yearTxt.getText()+"-"+monthTxt.getText()+"-"+dayTxt.getText();
        PersonDataModel data = new PersonDataModel(nameTxt.getText(),ePostTxt.getText(),tlfNrTxt.getText(),dateS);
        collection.leggTilEllement(data);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            collection.kobligTilTable(Table);
    }

    public void CloseApp (){
        Platform.exit();
        System.exit(0);
    }
}
