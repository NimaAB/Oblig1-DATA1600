package main;
import FileHandling.Reader.ReaderTxt;
import FileHandling.Writer.WriterTxt;
import InfoFormats.PersonFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import Register.Person;
import Håntering.AvikksHåntering;

public class Controller {
    private static ArrayList<Person> personRegister = new ArrayList<>();
    @FXML
    private TextField navnTxt,yearTxt,monthTxt,dayTxt,ePosttxt,tlfnrTxt,pathTxt;
    @FXML
    private Label InvalidMsgLbl, regLbl;
    @FXML
    void registrer(ActionEvent event){
        boolean nameValidate = AvikksHåntering.isValidateName(navnTxt.getText());
        String innYear = yearTxt.getText();
        String innMonth = monthTxt.getText();
        String innDay = dayTxt.getText();
        boolean numformat = AvikksHåntering.isValidNumFormat(innYear, innMonth, innDay);
        int year = AvikksHåntering.outYear;
        int month = AvikksHåntering.outMonth;
        int day = AvikksHåntering.outDay;
        boolean dateValidation = AvikksHåntering.isValidDate(year,month,day);
        boolean epostValidate = AvikksHåntering.isValidEpost(ePosttxt.getText());
        boolean tlfnrValidate = AvikksHåntering.isValidTlfnr(tlfnrTxt.getText());
        InvalidMsgLbl.setText(AvikksHåntering.melding);
        boolean allowAddObj = nameValidate && epostValidate && tlfnrValidate && numformat && dateValidation;
        if(allowAddObj){
            InvalidMsgLbl.setText("");
            LocalDate date1=LocalDate.of(year,month,day);
            Person enPerson = new Person(navnTxt.getText(), ePosttxt.getText(), tlfnrTxt.getText(), date1);
            personRegister.add(enPerson);
        }
    }
    @FXML
    void visListe(ActionEvent event){
        StringBuilder ut = new StringBuilder();
        for(Person p : personRegister){
            ut.append(p.toString());
        }
        regLbl.setText(ut.toString());
    }
    @FXML
    void saving(ActionEvent event) throws IOException {

        String path = AvikksHåntering.pathInputhandling(pathTxt.getText()); //endre verdien med en pathNameTxt.getText()
        InvalidMsgLbl.setText(AvikksHåntering.melding);
        assert path != null;
        File filepath = new File(path);
        WriterTxt SavingtestObj = new WriterTxt();
        String objString = PersonFormat.folkFormat(personRegister);
        SavingtestObj.save(objString,filepath,1);
        InvalidMsgLbl.setText(AvikksHåntering.melding);
    }
    @FXML
    void reader(ActionEvent event) throws IOException {
        String path = AvikksHåntering.pathInputhandling(pathTxt.getText());
        InvalidMsgLbl.setText(AvikksHåntering.melding);
        assert path != null;
        File filePath = new File(path);
        ReaderTxt readerObj = new ReaderTxt();
        personRegister = readerObj.read(filePath);
        StringBuilder ut = new StringBuilder();
        for(Person p : personRegister){
            ut.append(p.toString());
        }
        regLbl.setText(ut.toString());
    }
    @FXML
    void removePersons(ActionEvent event){
        regLbl.setText("");
        personRegister.clear();
    }
}



