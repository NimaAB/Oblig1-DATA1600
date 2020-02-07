package main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.ArrayList;
import Register.Person;
import Håntering.avikkHåntering;

public class Controller {
    private static ArrayList<Person> personRegister = new ArrayList<>();
    @FXML
    private TextField navnTxt,yearTxt,monthTxt,dayTxt,ePosttxt,tlfnrTxt;
    @FXML
    private Label InvalidMsgLbl, regLbl;
    @FXML
    void registrer(ActionEvent event){
        boolean nameValidate = avikkHåntering.isValidateName(navnTxt.getText());
        String innYear = yearTxt.getText();
        String innMonth = monthTxt.getText();
        String innDay = dayTxt.getText();
        boolean numformat = avikkHåntering.isValidNumFormat(innYear, innMonth, innDay);
        int year = avikkHåntering.outYear;
        int month = avikkHåntering.outMonth;
        int day = avikkHåntering.outDay;
        boolean dateValidation = avikkHåntering.isValidDate(year,month,day);
        boolean epostValidate = avikkHåntering.isValidEpost(ePosttxt.getText());
        boolean tlfnrValidate = avikkHåntering.isValidTlfnr(tlfnrTxt.getText());
        InvalidMsgLbl.setText(avikkHåntering.melding);
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
    void removePersons(ActionEvent event){
        regLbl.setText("");
        personRegister.clear();
    }
}



