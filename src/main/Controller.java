package main;

import Data.DataCollection;
import Data.PersonDataModel;
import FileHandling.Reader.ReaderTxt;
import FileHandling.Writer.WriterTxt;
import Håntering.AvikksHåntering;

import InfoFormats.PersonFormat;
import avvik.InvalidPersonFormatException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Alert warning = new Alert(Alert.AlertType.WARNING);
    Alert error = new Alert(Alert.AlertType.ERROR);
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
    //metoden under evalurer inputene og håndterer dem etter at alt går fint så lager den en objekt av person
    // og returnerer den.
    private PersonDataModel creatPersonObjToDataModel(){
        String year = yearTxt.getText();
        String month = monthTxt.getText();
        String day = dayTxt.getText();

        int [] datoArr = AvikksHåntering.numArr(year,month,day);
        int yearInt = datoArr[0];
        int monthInt= datoArr[1];
        int dayInt= datoArr[2];

        boolean isValifNumFormat = AvikksHåntering.isValidDato(yearInt,monthInt,dayInt);
        String birthDate = year+"-"+month+"-"+day;
        String name = nameTxt.getText();
        boolean isValidName = AvikksHåntering.isValidateName(name);
        String ePost = ePostTxt.getText();
        boolean isValidEPost = AvikksHåntering.isValidEpost(ePost);
        String tlfNr = tlfNrTxt.getText();
        boolean isValidTlfnr = AvikksHåntering.isValidTlfnr(tlfNr);
        boolean allowAddObj= isValifNumFormat && isValidEPost && isValidName && isValidTlfnr;
        PersonDataModel personObj = null;
        if(!allowAddObj){
            error.setTitle("Error: Wrong Input");
            error.setHeaderText(AvikksHåntering.melding);
            error.showAndWait();
        }else{
            personObj = new PersonDataModel(name,ePost,tlfNr,birthDate);
            personData.add(personObj);
        }
        return personObj;
    }
    //rydder opp tekst feltene på GUI-en.
    private void resetTxtFields(){
        nameTxt.setText("");
        ePostTxt.setText("");
        tlfNrTxt.setText("");
        yearTxt.setText("");
        monthTxt.setText("");
        dayTxt.setText("");
    }

    @FXML
    //knappen register på GUI legger objekten i tabelview.
    void addObjToTable(ActionEvent event){

        PersonDataModel perObj = creatPersonObjToDataModel();
        if(perObj != null){
            collection.leggTilEllement(perObj);
            resetTxtFields();
        }
    }
    @FXML
    //Metode for å implementere readeTxt classen sim metode.
    void openFile(ActionEvent event) throws IOException {

        FileChooser files = new FileChooser();
        files.setTitle("Leser vindu");
        files.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tekst filer","*.txt")
        );
        Stage stage =(Stage) openFile.getParentPopup().getScene().getWindow();
        File selectedFile = files.showOpenDialog(stage);
        if(selectedFile == null){
            warning.setTitle("Warning");
            warning.setHeaderText("Du må velge en tekst fil");
            warning.showAndWait();
        }else {
            //Frem til hit er kode for å åpne en vindu for å den den filen som vi skal lese i tabellen.
            try {
                ReaderTxt readerObj = new ReaderTxt();
                personData = readerObj.read(selectedFile);
                for (PersonDataModel p : personData) {
                    collection.leggTilEllement(p);//leger inn liste elementene i en ObservebelList
                    //Se på metoden selv: ctrl + click
                }
            } catch (InvalidPersonFormatException e) {
                error.setTitle("Error: Wrong Input");
                error.setHeaderText(e.getMessage());
                error.showAndWait();
            }
        }
    }
    //en metode for å skrive til fil. WriterTxt klassen.
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
    //kobler tabellen med en ObservebelListe.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            collection.kobligTilTable(Table);//kobler tabellen med en ObservebelListe.
        //ctrl + click
    }
    //lukker stagen.
    public void CloseApp (){
        Platform.exit();
        System.exit(0);
    }
}
