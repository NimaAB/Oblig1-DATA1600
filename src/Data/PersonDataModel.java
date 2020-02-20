package Data;

import Håntering.AvviksHåntering;
import Håntering.Valideringer;
import avvik.InvalidPersonFormatException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;


public class PersonDataModel implements Serializable {

    private transient SimpleStringProperty name;
    private transient SimpleStringProperty ePost;
    private transient SimpleStringProperty tlfNr;
    private transient SimpleStringProperty birthDate;
    private transient SimpleIntegerProperty age;

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

    public void setAge(){
        this.age.set(beregnAlder(getBirthDate()));
    }
    public int getAge(){
        return this.age.getValue();
    }

    /** en metode beregner alder utifra en streng.*/
    public static int beregnAlder(String date){
        LocalDate now = LocalDate.now();
        //konverterer String til int array:
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
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getName());
        s.writeUTF(getBirthDate());
        s.writeInt(getAge());
        s.writeUTF(getEPost());
        s.writeUTF(getTlfNr());
    }
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        String name = s.readUTF();
        String birthDate = s.readUTF();
        int age = s.readInt();
        String epost = s.readUTF();
        String tlfNr = s.readUTF();
        this.name = new SimpleStringProperty(name);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.age = new SimpleIntegerProperty(age);
        this.ePost = new SimpleStringProperty(epost);
        this.tlfNr = new SimpleStringProperty(tlfNr);
    }
}
