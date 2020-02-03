package Validations;

import Validations.Valideringer;
import avvikshåntering.InvalidEpostException;
import avvikshåntering.InvalidNameException;
import avvikshåntering.InvalidTlfnrException;

import java.time.DateTimeException;

public class Sjekk {
    public static int outYear, outMonth, outDay;
    public static boolean sjekk = false;
    public static String validateName(String name){
        String melding = "";
        try{
            Valideringer.isNotValidName(name);
            sjekk = true;
        } catch (InvalidNameException e){
            melding=e.getMessage();
        }
        return melding;
    }
    public static String feilNummerFormat(String innYear, String innMonth, String innDay){
        String melding = "";
        try{
           outYear =Integer.parseInt(innYear);
           outMonth =Integer.parseInt(innMonth);
           outDay =Integer.parseInt(innDay);
           sjekk = true;
        } catch (NumberFormatException e){
            melding = e.getMessage();
        }
        return melding;
    }
    public static String validationMsgDate(int year,int month, int day) {
        String melding = "";
        try {
            Valideringer.dateIsAccepted(year, month, day);
            sjekk = true;
        } catch (DateTimeException e) {
            melding = e.getMessage();
        }
        return melding;
    }

    public static String validateEpost(String epost){
        String melding = "";
        try{
            Valideringer.epostValidate(epost);
            sjekk = true;
        } catch (InvalidEpostException e){
            melding=e.getMessage();
        }
        return melding;
    }
    public static String validateTlfnr(String tlfnr){
        String melding = "";
        try{
            Valideringer.tlfnrValidate(tlfnr);
            sjekk = true;
        } catch (InvalidTlfnrException e){
            melding=e.getMessage();
        }
        return melding;
    }
}
