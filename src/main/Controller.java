package main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.time.LocalDate;
/*Egne pakager-------------*/
import Register.Fødselsdato;
import Register.Person;
import Validations.*;
import avvikshåntering.*;
/*----------------------*/

public class Controller {
    private static  LocalDate date1;
    private static Person enPerson;
    private static ArrayList<Person> personRegister = new ArrayList<>();

    @FXML
    private TextField navnTxt;

    @FXML
    private TextField yearTxt;

    @FXML
    private TextField monthTxt;

    @FXML
    private TextField dayTxt;

    @FXML
    private Label eNamelbl, eAgeDatelbl, regLbl;

    @FXML
    void registrer(ActionEvent event) {

        int year = 0;
        int month = 0;
        int day = 0;
        try{
            year = Integer.parseInt(yearTxt.getText());
            month = Integer.parseInt(monthTxt.getText());
            day = Integer.parseInt(dayTxt.getText());
        } catch (NumberFormatException e){
            eAgeDatelbl.setText(e.getMessage());
        }
        try{
            Valideringer.monthofyearValidate(month);
        }catch (InvalidDateException eda){
            eAgeDatelbl.setText(eda.getMessage());
        }
        try{
            Valideringer.daysOfMonthValidate(day,month,year);
        }catch (InvalidDateException eda){
            eAgeDatelbl.setText(eda.getMessage());
        }
        try{
            Valideringer.dateIsAccepted(year,month,day);
            date1 = LocalDate.of(year,month,day);
        } catch (InvalidDateException eda){
            eAgeDatelbl.setText(eda.getMessage());
        }
        Fødselsdato date = new Fødselsdato(date1);
        try{
            Valideringer.isNotValidName(navnTxt.getText());
            enPerson = new Person(navnTxt.getText(), date);
        } catch (InvalidNameException e){
            eNamelbl.setText(e.getMessage());
        }
        personRegister.add(enPerson);
    }

    @FXML
    void visListe(ActionEvent event){
        /*String ut = personRegister.toString();
        regLbl.setText(ut);*/
        String ut = "";
        for(Person p : personRegister){
            ut += p;
        }
        regLbl.setText(ut);

        /*TabellReg nyttVindu = new TabellReg();
        nyttVindu.getClass();*/
    }
    @FXML
    void removePersons(ActionEvent event){
        regLbl.setText("");
        personRegister.clear();
    }
}



