package main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
/*Egne pakager-------------*/
import Register.Person;
import Validations.*;
/*----------------------*/

public class Controller {

    private static Person enPerson;
    private static ArrayList<Person> personRegister = new ArrayList<>();

    @FXML
    private TextField navnTxt,yearTxt,monthTxt,dayTxt,ePosttxt,tlfnrTxt;
    @FXML
    private Label eNamelbl, eAgeDatelbl, regLbl, epostLbl,tlfnrLbl;
    @FXML

    void registrer(ActionEvent event){
        String nameValidate = Sjekk.validateName(navnTxt.getText());
        eNamelbl.setText(nameValidate);
        String epostValidate = Sjekk.validateEpost(ePosttxt.getText());
        epostLbl.setText(epostValidate);
        String tlfnrValidate = Sjekk.validateTlfnr(tlfnrTxt.getText());
        tlfnrLbl.setText(tlfnrValidate);

        String innYear = yearTxt.getText();
        String innMonth = monthTxt.getText();
        String innDay = dayTxt.getText();
        String numformat = Sjekk.feilNummerFormat(innYear,innMonth,innDay);
        eAgeDatelbl.setText(numformat);
        String dateValidation = Sjekk.validationMsgDate(Sjekk.outYear,Sjekk.outMonth,Sjekk.outDay);
        eAgeDatelbl.setText(dateValidation);



        if(Sjekk.sjekk){
            LocalDate date1=LocalDate.of(Sjekk.outYear,Sjekk.outMonth,Sjekk.outDay);
            enPerson = new Person(navnTxt.getText(), ePosttxt.getText(),tlfnrTxt.getText(), date1);
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



