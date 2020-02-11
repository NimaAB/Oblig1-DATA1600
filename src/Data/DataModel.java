package Data;

import Håntering.Valideringer;
import Håntering.AvikksHåntering;
import avvik.InvalidEpostException;
import avvik.InvalidNameException;
import avvik.InvalidTlfnrException;
import javafx.beans.property.SimpleStringProperty;


import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


public class DataModel {

    private SimpleStringProperty name;
    private SimpleStringProperty ePost;
    private SimpleStringProperty tlfNr;
    private SimpleStringProperty birthDate;


    public DataModel(String name,String ePost,String tlfNr, String birthDate) {


        this.name = new SimpleStringProperty(name);
        this.ePost = new SimpleStringProperty(ePost);
        this.tlfNr=new SimpleStringProperty(tlfNr);
        this.birthDate = new SimpleStringProperty(birthDate);
    }
    private void setName(String name) throws InvalidNameException{
        /*if(avikkHåntering.isValidateName(name)){
            throw new InvalidNameException("Feil navn format");
        }*/
        this.name.set(name);
    }
    public String getName(){
        return this.name.getValue();
    }
    private void setePost(String ePost) throws InvalidEpostException{
        /*if(avikkHåntering.isValidEpost(ePost)){
            throw new InvalidEpostException("Feil epost format");
        }*/
        this.ePost.set(ePost);
    }
    public String getePost(){
        return this.ePost.getValue();
    }
    private void setTlfNr(String tlfNr) throws InvalidTlfnrException{
        /*if(avikkHåntering.isValidTlfnr(tlfNr)){
            throw new InvalidTlfnrException("Feil Telefon nummer format");
        }*/
        this.tlfNr.set(tlfNr);
    }
    public String getTlfNr(){
        return this.tlfNr.getValue();
    }
    // Do the same for the date too.
    // the date string must be splited to an array og string which each element
    // will be converted to integer again.

    private void setBirthDate(String date){

            //Date birthDate = new SimpleDateFormat("YYYY-MM-DD").parse(date);
            this.birthDate.set(date);

    }
    public String getBirthDate() {

        return this.birthDate.getValue();
    }
}
