package Data;

import Håntering.Valideringer;
import Håntering.AvikksHåntering;
import avvik.InvalidEpostException;
import avvik.InvalidNameException;
import avvik.InvalidTlfnrException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class PersonDataModel {

    private SimpleStringProperty name;
    private SimpleStringProperty ePost;
    private SimpleStringProperty tlfNr;
    private SimpleStringProperty birthDate;
    private SimpleIntegerProperty  age;
    public PersonDataModel(String name, String ePost, String tlfNr, String birthDate){

        this.name = new SimpleStringProperty(name);
        this.ePost = new SimpleStringProperty(ePost);
        this.tlfNr=new SimpleStringProperty(tlfNr);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.age = new SimpleIntegerProperty(beregnAlder(birthDate));
    }

    private void setName(String name){
        this.name.set(name);
    }
    public String getName(){
        return this.name.getValue();
    }
    private void setePost(String ePost){
        this.ePost.set(ePost);
    }
    public String getePost(){
        return this.ePost.getValue();
    }

    private void setTlfNr(String tlfNr){
        this.tlfNr.set(tlfNr);
    }
    public String getTlfNr(){
        return this.tlfNr.getValue();
    }
    private void setBirthDate(String date){
            this.birthDate.set(date);
    }
    public String getBirthDate() {
        return this.birthDate.getValue();
    }
    // en metode som henter dato so en string og gjør dette til en array av strenger
    // arrayet er splittet med "-".
    private LocalDate date(String birthDate){
        String [] dateArray = birthDate.split("-");
        int year = 0; int month = 0; int day = 0;
        try{
            year = Integer.parseInt(dateArray[0]);
            month = Integer.parseInt(dateArray[1]);
            day = Integer.parseInt(dateArray[2]);
        }catch(NumberFormatException e){
            e.getMessage();
        }
        return LocalDate.of(year,month,day);
    }
    public int beregnAlder(String birthDate){
       LocalDate now = LocalDate.now();
       LocalDate birthDate1 = date(birthDate);
        int age =  now.getYear()- birthDate1.getYear();
        if(now.getMonth().getValue() < birthDate1.getMonth().getValue() ||
                now.getMonth().getValue() == birthDate1.getMonth().getValue()){
            age -=1;
        }
        return age;
    }
}
