package main;

import Data.DataCollection;
import Data.PersonDataModel;
import FileHandling.FileExtentionFilter;
import FileHandling.Reader.ReaderJObj;
import FileHandling.Reader.ReaderTxt;
import FileHandling.Writer.WriterJObj;
import FileHandling.Writer.WriterTxt;
import Håntering.AvviksHåntering;

import Håntering.TVInputHåntering;
import InfoFormats.PersonFormat;
import avvik.InvalidPersonFormatException;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Controller implements Initializable {
    Alert warning = new Alert(Alert.AlertType.WARNING);
    Alert error = new Alert(Alert.AlertType.ERROR);
    private DataCollection collection = new DataCollection();

    private ArrayList<PersonDataModel> personData = new ArrayList<>();
    private PersonDataModel perObj;

    @FXML
    private TextField nameTxt, ePostTxt, tlfNrTxt;
    @FXML
    private TextField yearTxt, monthTxt, dayTxt;
    @FXML
    private TableColumn <PersonDataModel,String> nameCol,birthCol,epostCol,tlfNrCol;
    @FXML
    private TableView<PersonDataModel> Table;
    @FXML
    private TextField SearchTxt;
    @FXML
    private MenuItem openFile;
    @FXML
    private MenuItem saveFile;

    /**
     * initialize sørger for at personData listen skal være koblet til Tableview
     * og at filtrering av listen skjer straks.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        collection.kobligTilTable(Table);//kobler tabellen med en ObservebelListe.
        //ctrl + click

        //Gjør mulig å skrive i feltene til TabelView:
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        birthCol.setCellFactory(TextFieldTableCell.forTableColumn());
        epostCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tlfNrCol.setCellFactory(TextFieldTableCell.forTableColumn());
        Table.setEditable(true);

        //filtrering:
        FilteredList<PersonDataModel> filteredList = new FilteredList<>(collection.objToTV,b -> true);
        SearchTxt.textProperty().addListener((observable,oldValue,newValue)->{
            filteredList.setPredicate(perObj->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String searchtxtfield = newValue;
                int age = 0;
                if(Pattern.matches("[0-9]*",searchtxtfield)) {

                    try {
                        age = Integer.parseInt(searchtxtfield);
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
                }else if(perObj.getBirthDate().contains(searchtxtfield)){
                    return true;
                }else if(perObj.getTlfNr().contains(searchtxtfield)){
                    return true;
                }
                else return false;
            });
        });
        SortedList<PersonDataModel> sortertData = new SortedList<>(filteredList);
        sortertData.comparatorProperty().bind(Table.comparatorProperty());
        Table.setItems(sortertData);
    }
    /**metoden under evalurer inputene og håndterer dem etter at alt går fint så lager den en objekt av person
     *  og returnerer den.
     *  */
    private PersonDataModel creatPersonObjToDataModel(){
        String year = yearTxt.getText();
        String month = monthTxt.getText();
        String day = dayTxt.getText();
        String birthDate = year+"-"+month+"-"+day;

        boolean isValifNumFormat = AvviksHåntering.isValidDate(birthDate);
        String name = nameTxt.getText();
        boolean isValidName = AvviksHåntering.isValidateName(name);
        String ePost = ePostTxt.getText();
        boolean isValidEPost = AvviksHåntering.isValidEpost(ePost);
        String tlfNr = tlfNrTxt.getText();
        boolean isValidTlfnr = AvviksHåntering.isValidTlfnr(tlfNr);
        boolean allowAddObj= isValifNumFormat && isValidEPost && isValidName && isValidTlfnr;
        if(!(year.isEmpty()||month.isEmpty()||day.isEmpty()||name.isEmpty()||ePost.isEmpty()||tlfNr.isEmpty())){
            PersonDataModel personObj = null;
            if(!allowAddObj){
                error.setTitle("Error: Wrong Input");
                error.setHeaderText(AvviksHåntering.melding);
                error.showAndWait();
            }else{
                personObj = new PersonDataModel(name,ePost,tlfNr,birthDate);
                personData.add(personObj);
            }
            return personObj;
        }else {
            error.setTitle("Error");
            error.setHeaderText("Du har ikke fylt ut alle text feltene");
            error.showAndWait();
        }
        return null;
    }
    /**rydder opp tekst feltene på GUI-en*/
    private void resetTxtFields(){
        nameTxt.setText("");
        ePostTxt.setText("");
        tlfNrTxt.setText("");
        yearTxt.setText("");
        monthTxt.setText("");
        dayTxt.setText("");
    }
    @FXML
    /**knappen register på GUI legger objekten i tabelview.*/
    void addObjToTable(ActionEvent event){
        perObj = creatPersonObjToDataModel();
        if(perObj != null){
            collection.leggTilEllement(perObj);
            resetTxtFields();
        }
    }
    @FXML
    void openFile(ActionEvent event) throws IOException{
        FileChooser files = new FileChooser();
        files.setTitle("Leser vindu");
        files.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tekst filer","*.txt","*.jobj")
        );
        File selectedFile = files.showOpenDialog(openFile.getParentPopup().getScene().getWindow());
        //Frem til hit er kode for å åpne en vindu for å den den filen som vi skal lese i tabellen.
        if(selectedFile == null){
            warning.setTitle("Warning");
            warning.setHeaderText("Ingen filer er valgt");
            warning.showAndWait();
        }else {
            FileExtentionFilter extention = new FileExtentionFilter();
            String extentionFilter = extention.getExtention(selectedFile);
            switch (extentionFilter){
                case "txt":
                    try {
                        ReaderTxt readerObjTxt = new ReaderTxt();
                        personData = readerObjTxt.read(selectedFile);
                    }
                    catch (InvalidPersonFormatException e){
                        warning.setHeaderText("Warnig");
                        warning.setTitle(e.getMessage());
                        warning.showAndWait();
                    }
                    break;
                case "jobj":
                    try{
                        ReaderJObj readerObjJObj = new ReaderJObj();
                        personData = readerObjJObj.read(selectedFile);
                    }catch (InvalidPersonFormatException e){
                        warning.setHeaderText("Warning");
                        warning.setTitle(e.getMessage());
                        warning.showAndWait();
                    }
                    break;
                default:
                        warning.setHeaderText("Warning");
                        warning.setTitle("ingen riktige filer har blitt valgt.");
                        warning.showAndWait();
                    break;
            }
            for (PersonDataModel p : personData){
                collection.leggTilEllement(p);
                System.out.println(p.toString());
            }
        }
    }
    /**en metode for å skrive til fil. WriterTxt klassen.*/
    @FXML
    void saveFile(ActionEvent event) throws IOException {
        FileChooser files = new FileChooser();
        files.setTitle("Lagrings vindu");
        files.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tekst filer","*.txt","*.jobj")
        );
        File selectedFile = files.showSaveDialog(saveFile.getParentPopup().getScene().getWindow());

        if(selectedFile != null) {

            FileExtentionFilter extention = new FileExtentionFilter();
            String extentionFilter = extention.getExtention(selectedFile);
            switch (extentionFilter) {
                case "txt":
                    WriterTxt savingObjToTxt = new WriterTxt();
                    savingObjToTxt.save(personData, selectedFile);
                    break;
                case "jobj":
                    WriterJObj savingObjToJObj = new WriterJObj();
                    savingObjToJObj.save(personData,selectedFile);
                    break;
                default:
                    warning.setHeaderText("Warning");
                    warning.setTitle("ingen filer er valgt");
                    warning.showAndWait();
                    break;
            }
        }
    }
    //lukker stagen.
    public void CloseApp (){
        Platform.exit();
        System.exit(0);
    }
    /**
     * Disse metodene evaluerer nye input data via TableView
     * og oppdaterer dem
     * */
    private TVInputHåntering enObj = new TVInputHåntering();
    @FXML
    private void nameCEdited(TableColumn.CellEditEvent<PersonDataModel, String> event){
        if(!enObj.nameCExceptable(event.getNewValue())){
            warning.setTitle("Warning");
            warning.setHeaderText("Feil navn format (Ole Olsen)");
            warning.showAndWait();
            Table.refresh();
        } else {
            event.getRowValue().setName(event.getNewValue());
        }
    }
    @FXML
    private void birthCEdited(TableColumn.CellEditEvent<PersonDataModel, String> event){
        if(!enObj.birthdateCExceptable(event.getNewValue())){
            warning.setTitle("Warning");
            warning.setHeaderText("Feil dato format YYYY-MM-DD");
            warning.showAndWait();
            Table.refresh();
        } else {
            event.getRowValue().setBirthDate(event.getNewValue());
            event.getRowValue().setAge();
            Table.refresh();
        }
    }
    @FXML
    private void ePostEdited(TableColumn.CellEditEvent<PersonDataModel, String> event){
        if(!enObj.epostCExceptable(event.getNewValue())){
            warning.setTitle("Warning");
            warning.setHeaderText("Feil Epost format");
            warning.showAndWait();
            Table.refresh();
        } else {
            event.getRowValue().setEPost(event.getNewValue());
        }
    }
    @FXML
    private void tlfNrEdited(TableColumn.CellEditEvent<PersonDataModel, String> event){
        if(!enObj.tlfNrCExeptable(event.getNewValue())){
            warning.setTitle("Warning");
            warning.setHeaderText("Feil telefonnummer format");
            warning.showAndWait();
            Table.refresh();
        } else {
            event.getRowValue().setTlfNr(event.getNewValue());
        }
    }
}
