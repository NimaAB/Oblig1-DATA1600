package Data;

import Håntering.AvviksHåntering;
import Håntering.Valideringer;
import avvik.InvalidPersonFormatException;
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
        this.age = new SimpleIntegerProperty(beregnAlder(birthDate));
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

    public static int beregnAlder(String date){
       LocalDate now = LocalDate.now();
        //konverterer String arrayet til Intigers:
        int[] dateInt = new int[3];
        try{
            dateInt=AvviksHåntering.numArr(date);
        } catch (InvalidPersonFormatException e){
            AvviksHåntering.melding = e.getMessage();
        }
        LocalDate birthDateObjFrom = Valideringer.dateInputValidering(dateInt[0],
                dateInt[1],dateInt[2]);
        int age =  now.getYear() - birthDateObjFrom.getYear();
        if(now.getMonth().getValue() < birthDateObjFrom.getMonth().getValue() ||
                now.getMonth().getValue() == birthDateObjFrom.getMonth().getValue()){
            age -=1;
        }
        return age;
    }
}
