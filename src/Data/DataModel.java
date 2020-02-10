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
    private SimpleDateFormat birthDate;


    public DataModel(String name,String ePost,String tlfNr, String birthDate) {


        this.name = new SimpleStringProperty(name);
        this.ePost = new SimpleStringProperty(ePost);
        this.tlfNr=new SimpleStringProperty(tlfNr);
        this.birthDate = new SimpleDateFormat(birthDate);
    }
    private void setName(String name) throws InvalidNameException{
        /*if(avikkHåntering.isValidateName(name)){
            throw new InvalidNameException("Feil navn format");
        }*/
        this.name.set(name);
    }
    public SimpleStringProperty getName(){
        return this.name;
    }
    private void setePost(String ePost) throws InvalidEpostException{
        /*if(avikkHåntering.isValidEpost(ePost)){
            throw new InvalidEpostException("Feil epost format");
        }*/
        this.ePost.set(ePost);
    }
    public SimpleStringProperty getePost(){
        return this.ePost;
    }
    private void setTlfNr(String tlfNr) throws InvalidTlfnrException{
        /*if(avikkHåntering.isValidTlfnr(tlfNr)){
            throw new InvalidTlfnrException("Feil Telefon nummer format");
        }*/
        this.tlfNr.set(tlfNr);
    }
    public SimpleStringProperty getTlfNr(){
        return this.tlfNr;
    }
    // Do the same for the date too.
    // the date string must be splited to an array og string which each element
    // will be converted to integer again.

    private void setBirthDate(String date){
        try{
            Date birthDate = new SimpleDateFormat("YYYY-MM-DD").parse(date);
            this.birthDate.format(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public SimpleDateFormat getBirthDate() {
        return this.birthDate;
    }
}
