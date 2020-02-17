package main;

import Håntering.AvikksHåntering;
import Register.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

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

    private static ArrayList<Person> personRegister = new ArrayList<>();

    @FXML
    void register(ActionEvent event) {
        String innYear = yearTxt.getText();
        String innMonth = monthTxt.getText();
        String innDay = dayTxt.getText();

        boolean numformat = AvikksHåntering.isValidNumFormat(innYear, innMonth, innDay);
        int year = AvikksHåntering.outYear;
        int month = AvikksHåntering.outMonth;
        int day = AvikksHåntering.outDay;

        boolean nameValidate = AvikksHåntering.isValidateName(nameTxt.getText());
        boolean dateValidation = AvikksHåntering.isValidDate(year, month, day);
        boolean epostValidate = AvikksHåntering.isValidEpost(ePostTxt.getText());
        boolean tlfnrValidate = AvikksHåntering.isValidTlfnr(tlfNrTxt.getText());
        boolean allowAddObj = nameValidate && epostValidate && tlfnrValidate && numformat && dateValidation;
        ErrorLbl.setText(AvikksHåntering.melding);

        if (allowAddObj) {
            LocalDate date1 = LocalDate.of(year, month, day);
            Person enPerson = new Person(nameTxt.getText(), ePostTxt.getText(), tlfNrTxt.getText(), date1);
            personRegister.add(enPerson);
            ErrorLbl.setText("");
        }

    }
    @FXML
    void showList(ActionEvent event) {
        StringBuilder ut = new StringBuilder();
        for (Person p : personRegister) { ut.append(p.toString()); }
    }
}
