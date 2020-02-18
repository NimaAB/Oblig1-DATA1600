package main;

import Data.DataCollection;
import Data.PersonDataModel;
import FileHandling.Reader.ReaderTxt;
import FileHandling.Writer.WriterTxt;
import Håntering.AvikksHåntering;

import InfoFormats.PersonFormat;
import avvik.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
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
    private TableView<PersonDataModel> Table;
    @FXML
    private TableColumn<PersonDataModel, String> col_name;
    @FXML
    private TableColumn<PersonDataModel, String> col_email;
    @FXML
    private TableColumn<PersonDataModel, Integer> col_age;
    @FXML
    private TableColumn<PersonDataModel, String> col_number;
    @FXML
    private TableColumn<PersonDataModel, String> col_date;
    @FXML
    private TextField SearchTxt;
    @FXML
    private MenuItem openFile;
    @FXML
    private MenuItem saveFile;

    //metoden under evalurer inputene og håndterer dem etter at alt går fint så lager den en objekt av person
    // og returnerer den.
    private PersonDataModel creatPersonObjToDataModel() {
        String year = yearTxt.getText();
        String month = monthTxt.getText();
        String day = dayTxt.getText();

        int[] datoArr = AvikksHåntering.numArr(year, month, day);
        int yearInt = datoArr[0];
        int monthInt = datoArr[1];
        int dayInt = datoArr[2];

        boolean isValifNumFormat = AvikksHåntering.isValidDato(yearInt, monthInt, dayInt);
        String birthDate = year + "-" + month + "-" + day;
        String name = nameTxt.getText();
        boolean isValidName = AvikksHåntering.isValidateName(name);
        String ePost = ePostTxt.getText();
        boolean isValidEPost = AvikksHåntering.isValidEpost(ePost);
        String tlfNr = tlfNrTxt.getText();
        boolean isValidTlfnr = AvikksHåntering.isValidTlfnr(tlfNr);
        boolean allowAddObj = isValifNumFormat && isValidEPost && isValidName && isValidTlfnr;
        PersonDataModel personObj = null;
        if (!allowAddObj) {
            error.setTitle("Error: Wrong Input");
            error.setHeaderText(AvikksHåntering.melding);
            error.showAndWait();
        } else {
            personObj = new PersonDataModel(name, ePost, tlfNr, birthDate);
            personData.add(personObj);
        }
        return personObj;
    }

    //rydder opp tekst feltene på GUI-en.
    private void resetTxtFields() {
        nameTxt.setText("");
        ePostTxt.setText("");
        tlfNrTxt.setText("");
        yearTxt.setText("");
        monthTxt.setText("");
        dayTxt.setText("");
    }

    @FXML
        //knappen register på GUI legger objekten i tabelview.
    void addObjToTable(ActionEvent event) {

        PersonDataModel perObj = creatPersonObjToDataModel();
        if (perObj != null) {
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
                new FileChooser.ExtensionFilter("Tekst filer", "*.txt")
        );
        Stage stage = (Stage) openFile.getParentPopup().getScene().getWindow();
        File selectedFile = files.showOpenDialog(stage);
        if (selectedFile == null) {
            warning.setTitle("Warning");
            warning.setHeaderText("Du må velge en tekst fil");
            warning.showAndWait();
        } else {
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
    void saveFile(ActionEvent event) {
        FileChooser files = new FileChooser();
        files.setTitle("Lagrings vindu");
        files.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Tekst filer", "*.txt")
        );
        File selectedFile = files.showSaveDialog(saveFile.getParentPopup().getScene().getWindow());
        if (selectedFile != null) {
            WriterTxt SavingtestObj = new WriterTxt();
            String objString = PersonFormat.folkFormat(personData);
            SavingtestObj.save(objString, selectedFile, 1);
        }
    }

    //kobler tabellen med en ObservebelListe.
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        collection.kobligTilTable(Table);//kobler tabellen med en ObservebelListe.

        // gjør det mulig for tabellen og tabellfeltene til å bli redigert.
        Table.setEditable(true);
        col_name.setCellFactory(TextFieldTableCell.forTableColumn());
        col_date.setCellFactory(TextFieldTableCell.forTableColumn());
        col_email.setCellFactory(TextFieldTableCell.forTableColumn());
        col_number.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    //lukker stagen.
    public void CloseApp() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void nameOnEditCell(TableColumn.CellEditEvent<PersonDataModel, String> event) {
        try {
            event.getRowValue().setName(event.getNewValue());
        } catch (InvalidNameException e) {
            warning.setHeaderText(e.getMessage());
            warning.showAndWait();
            Table.refresh();
        }
    }

    @FXML
    private void emailOnEditCell(TableColumn.CellEditEvent<PersonDataModel, String> event) {
        try {
            event.getRowValue().setEmail(event.getNewValue());
        } catch (InvalidEpostException e) {
            warning.setHeaderText(e.getMessage());
            warning.showAndWait();
            Table.refresh();
        }
    }

    @FXML
    private void tlfOnEditCell(TableColumn.CellEditEvent<PersonDataModel, String> event) {
        try {
            event.getRowValue().setTlfNr(event.getNewValue());
        } catch (InvalidTlfnrException e) {
            warning.setHeaderText(e.getMessage());
            warning.showAndWait();
            Table.refresh();
        }
    }

    @FXML
    private void dateOnEditCell(TableColumn.CellEditEvent<PersonDataModel, String> event) {
        try {
            // Når datoen blir endret, oppdaterer alderen automatisk
            event.getRowValue().setBirthDate(event.getNewValue());
            int age = PersonDataModel.beregnAlder(event.getNewValue());
            event.getRowValue().setAge(age);
            Table.refresh();
        } catch (InvalidDatoException e) {
            warning.setHeaderText(e.getMessage());
            warning.showAndWait();
            Table.refresh();
        }
    }
}
