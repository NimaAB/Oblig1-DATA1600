package Data;

import Håntering.Valideringer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;


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
        this.age = new SimpleIntegerProperty(beregnAlder(Valideringer.birthdate));
    }

    public void setName(String name){
        this.name.set(name);
    }
    public String getName(){
        return this.name.getValue();
    }
    public void setEPost(String ePost){
        this.ePost.set(ePost);
    }
    public String getEPost(){
        return this.ePost.getValue();
    }
    public void setTlfNr(String tlfNr){
        this.tlfNr.set(tlfNr);
    }
    public String getTlfNr(){
        return this.tlfNr.getValue();
    }
    public void setBirthDate(String date){
            this.birthDate.set(date);
    }
    public String getBirthDate() {
        return this.birthDate.getValue();
    }
    public void setAge(int beregnAlder){
        this.age.set(beregnAlder);
    }
    public int getAge(){
        return this.age.getValue();
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







    public static int beregnAlder(LocalDate birthdate){
       LocalDate now = LocalDate.now();
       LocalDate birthDate1 = birthdate;
       int age =  now.getYear()- birthDate1.getYear();
       if(now.getMonth().getValue() < birthDate1.getMonth().getValue() ||
                now.getMonth().getValue() == birthDate1.getMonth().getValue()){
            age -=1;
        }
        return age;
    }
}
