package Håntering;

import avvik.*;

import java.time.LocalDate;
import java.util.regex.Pattern;


public class Valideringer {

    public static void nameInputValidering(String navn)  throws InvalidNameException {
        if(!Pattern.matches("[A-ZÅÆØ][a-zåæø]* [A-ZÅÆØ][a-zåæø]*",navn)){
            throw new InvalidNameException("Feil navn format");
        }
    }
    public static LocalDate dateInputValidering(int year, int month, int day)
            throws InvalidDatoException {
        LocalDate now = LocalDate.now();
        LocalDate date;
        try {
            date = LocalDate.of(year, month, day);
        }
        catch(Exception e){
            throw new InvalidDatoException("Ugyldig dato: måned eller dag");
        }
       if(date.isAfter(now) || year < 1900){
            throw new InvalidDatoException("Ugyldig dato: År");
        }
       return date;
    }
    public static void ePostInputValidering(String ePost) throws InvalidEpostException {
        boolean ligner = Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]" +
                "+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9]" +
                "(?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$",ePost);
        if(!ligner){
            throw new InvalidEpostException("Ugyldig E-post format!");
        }
    }
    public static void tlfnrInputValidering(String tlfnr) throws InvalidTlfnrException {
        boolean patternPlus = Pattern.matches("^\\+(?:[0-9-] ?){6,15}[0-9]$",tlfnr);
        boolean patternZeros = Pattern.matches("^0(?:[0-9-] ?){6,15}[0-9]$",tlfnr);
        boolean utenLandsKode = Pattern.matches("[0-9]{3,13}",tlfnr);
        if(!patternPlus && !patternZeros && !utenLandsKode){
            throw new InvalidTlfnrException("Ugyldig telefonnummer format!");
        }
    }
}
