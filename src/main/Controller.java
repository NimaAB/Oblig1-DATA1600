package main;

import Data.DataCollection;
import Data.PersonDataModel;
import FileHandling.Writer.WriterTxt;
import Håntering.AvikksHåntering;

import InfoFormats.PersonFormat;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private DataCollection collection = new DataCollection();
    private ArrayList<PersonDataModel> personData = new ArrayList<>();
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
    @FXML
    private MenuItem openFile;
    @FXML
    private MenuItem saveFile;

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
        PersonDataModel personObj = null;
        if(!allowAddObj){
            ErrorLbl.setText(AvikksHåntering.melding);
        }else{
            personObj = new PersonDataModel(name,ePost,tlfNr,birthDate);
            personData.add(personObj);
            ErrorLbl.setText("");
        }
        return personObj;
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
        PersonDataModel perObj = creatPersonObjToDataModel();
        if(perObj != null) {
            collection.leggTilEllement(perObj);
            resetTxtFields();
        }
    }
    @FXML
    void openFile(ActionEvent event){
    }
    @FXML
    void saveFile(ActionEvent event){
        FileChooser files = new FileChooser();
        files.setTitle("Lagrings vindu");
        files.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tekst filer","*.txt")
        );
        File selectedFile = files.showSaveDialog(saveFile.getParentPopup().getScene().getWindow());
        if(selectedFile != null){
            WriterTxt SavingtestObj = new WriterTxt();
            String objString = PersonFormat.folkFormat(personData);
            SavingtestObj.save(objString,selectedFile,1);
        }
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
