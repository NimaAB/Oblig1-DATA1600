package main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
/*Egne pakager-------------*/
import Register.Person;
import Validations.*;
import avvikshåntering.*;
/*----------------------*/

public class Controller {
    private static LocalDate date1;
    private static Person enPerson;
    private static ArrayList<Person> personRegister = new ArrayList<>();

    @FXML
    private TextField navnTxt,yearTxt,monthTxt,dayTxt,ePosttxt,tlfnrTxt;
    @FXML
    private Label eNamelbl, eAgeDatelbl, regLbl, epostLbl,tlfnrLbl;
    @FXML

    void registrer(ActionEvent event){

        int year = 0; int month =0; int day=0;
        try{
            year = Integer.parseInt(yearTxt.getText());
            month = Integer.parseInt(monthTxt.getText());
            day = Integer.parseInt(dayTxt.getText());
        } catch (NumberFormatException e){
            eAgeDatelbl.setText(e.getMessage());
        }
        try{
            Valideringer.monthofyearValidate(month);
        }catch (InvalidDateException e){
            eAgeDatelbl.setText(e.getMessage());
        }
        try{
            Valideringer.daysOfMonthValidate(day,month,year);
            Valideringer.dateIsAccepted(year,month,day);
            date1 = LocalDate.of(year,month,day);
        }catch (InvalidDateException e){
            eAgeDatelbl.setText(e.getMessage());
        }
        String ePost ="";
        String tlfnr="";
        try{
            Valideringer.epostValidate(ePosttxt.getText());
            ePost = ePosttxt.getText();
        }catch (InvalidEpostException e) {
            epostLbl.setText(e.getMessage());
        }
        try{
            Valideringer.tlfnrValidate(tlfnrTxt.getText());
            tlfnr = tlfnrTxt.getText();
        }catch (InvalidTlfnrException e){
            tlfnrLbl.setText(e.getMessage());
        }
        try{
            Valideringer.isNotValidName(navnTxt.getText());
            enPerson = new Person(navnTxt.getText(), ePost,tlfnr, date1);
            personRegister.add(enPerson);
        } catch (InvalidNameException e){
            eNamelbl.setText(e.getMessage());
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



