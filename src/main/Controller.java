package main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
/*Egne pakager-------------*/
import Register.Person;
import Håntering.*;
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
        boolean nameValidate = avikkHåntering.validateName(navnTxt.getText());
        //eNamelbl.setText(nameValidate);
        boolean epostValidate = avikkHåntering.validateEpost(ePosttxt.getText());
        //epostLbl.setText(epostValidate);
        boolean tlfnrValidate = avikkHåntering.validateTlfnr(tlfnrTxt.getText());
        //tlfnrLbl.setText(tlfnrValidate);
        String innYear = yearTxt.getText();
        String innMonth = monthTxt.getText();
        String innDay = dayTxt.getText();
        boolean numformat = avikkHåntering.feilNummerFormat(innYear,innMonth,innDay);
        boolean dateValidation = avikkHåntering.validationMsgDate(avikkHåntering.outYear, avikkHåntering.outMonth, avikkHåntering.outDay);
        eAgeDatelbl.setText(avikkHåntering.melding);

        boolean allowAddObj = nameValidate && epostValidate && tlfnrValidate && numformat && dateValidation;

        //eAgeDatelbl.setText(dateValidation);
        if(allowAddObj){
            eAgeDatelbl.setText("");
            LocalDate date1=LocalDate.of(avikkHåntering.outYear, avikkHåntering.outMonth, avikkHåntering.outDay);
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



