package main;

import Håntering.AvikksHåntering;
import Register.Person;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static ArrayList<Person> personRegister = new ArrayList<>();
    @FXML
    private TextField nameTxt, ePostTxt, tlfNrTxt;
    @FXML
    private TextField yearTxt, monthTxt, dayTxt;
    @FXML
    private Label ErrorLbl;
    @FXML
    private TableColumn<?, ?> nameC;
    @FXML
    private TableColumn<?, ?> BirthC;
    @FXML
    private TableColumn<?, ?> AgeC;
    @FXML
    private TableColumn<?, ?> EpostC;
    @FXML
    private TableColumn<?, ?> tlfNrC;
    @FXML
    private TextField SearchTxt;

    @FXML
    void addToList(ActionEvent event) {

        String innYear = yearTxt.getText();
        String innMonth = monthTxt.getText();
        String innDay = dayTxt.getText();
        int year = AvikksHåntering.outYear;
        int month = AvikksHåntering.outMonth;
        int day = AvikksHåntering.outDay;

        boolean nameValidate = AvikksHåntering.isValidateName(nameTxt.getText());
        boolean numformat = AvikksHåntering.isValidNumFormat(innYear, innMonth, innDay);
        boolean dateValidation = AvikksHåntering.isValidDate(year, month, day);
        boolean epostValidate = AvikksHåntering.isValidEpost(ePostTxt.getText());
        boolean tlfnrValidate = AvikksHåntering.isValidTlfnr(tlfNrTxt.getText());
        ErrorLbl.setText(AvikksHåntering.melding);
        boolean allowAddObj = nameValidate && epostValidate && tlfnrValidate && numformat && dateValidation;
        if (allowAddObj) {
            ErrorLbl.setText("");
            LocalDate date1 = LocalDate.of(year, month, day);
            Person enPerson = new Person(nameTxt.getText(), ePostTxt.getText(), tlfNrTxt.getText(), date1);
            personRegister.add(enPerson);
        }
    }
    @FXML
    void PrintList(ActionEvent event) {
        StringBuilder ut = new StringBuilder();
        for (Person p : personRegister) {
            ut.append(p.toString());
        }
        nameC.setText(ut.toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
