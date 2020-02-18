package Data;

import Håntering.AvikksHåntering;
import Håntering.Valideringer;
import avvik.InvalidEpostException;
import avvik.InvalidNameException;
import avvik.InvalidTlfnrException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class PersonDataModel {

    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty tlfNr;
    private SimpleStringProperty birthDate;
    private SimpleIntegerProperty  age;

    public PersonDataModel(String name, String email, String tlfNr, String birthDate){
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.tlfNr=new SimpleStringProperty(tlfNr);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.age = new SimpleIntegerProperty(beregnAlder(birthDate));
    }

    public String getName() { return name.get(); }
    public String getEmail() { return email.get(); }
    public String getTlfNr() { return tlfNr.get(); }
    public String getBirthDate() { return birthDate.get(); }
    public int getAge() { return age.get(); }

    public void setName(String name) throws InvalidNameException {
        Valideringer.nameInputValidering(name);
        this.name.set(name);
    }
    public void setEmail(String ePost) throws InvalidEpostException {
        Valideringer.ePostInputValidering(ePost);
        this.email.set(ePost);
    }

    public void setTlfNr(String tlfNr) throws InvalidTlfnrException {
        Valideringer.tlfnrInputValidering(tlfNr);
        this.tlfNr.set(tlfNr);
    }

    public void setBirthDate(String birthDate) {
        String[] vals = birthDate.split("-");
        int day = Integer.parseInt(vals[0]);
        int month = Integer.parseInt(vals[1]);
        int year = Integer.parseInt(vals[2]);
        Valideringer.dateInputValidering(day, month, year);
        this.birthDate.set(birthDate);
    }

    public void setAge(int age) {
        beregnAlder(birthDate.getValue());
        this.age.set(age);
    }

    public static int beregnAlder(String date){
       LocalDate now = LocalDate.now();
       String [] dateStr = date.split("-"); //spliter date String til array
        //konverterer String arrayet til Intigers:
        int[] dateInt = AvikksHåntering.numArr(dateStr[0],dateStr[1],dateStr[2]);
       LocalDate birthDateObjFrom = Valideringer.dateInputValidering(dateInt[0],dateInt[1],dateInt[2]);
       int age =  now.getYear() - birthDateObjFrom.getYear();
       if(now.getMonth().getValue() < birthDateObjFrom.getMonth().getValue() ||
                now.getMonth().getValue() == birthDateObjFrom.getMonth().getValue()){
            age -=1;
        }
        return age;
    }
}
