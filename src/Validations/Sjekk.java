package Validations;

import avvikshåntering.InvalidEpostException;
import avvikshåntering.InvalidNameException;
import avvikshåntering.InvalidTlfnrException;

import java.time.DateTimeException;

public class Sjekk {
    public static int outYear, outMonth, outDay;
    public static String melding = "";


    public static boolean validateName(String name){
        boolean sjekk = true;
        try{
            Valideringer.isNotValidName(name);

        } catch (InvalidNameException e){
            melding=e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean feilNummerFormat(String innYear, String innMonth, String innDay){
        boolean sjekk = true;
        try{
           outYear =Integer.parseInt(innYear);
           outMonth =Integer.parseInt(innMonth);
           outDay =Integer.parseInt(innDay);
        } catch (NumberFormatException e){
            melding = e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean validationMsgDate(int year,int month, int day) {
        boolean sjekk = true;
        try {
            Valideringer.dateIsAccepted(year, month, day);

        } catch (DateTimeException e) {
            melding = e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean validateEpost(String epost){
        boolean sjekk = true;
        try{
            Valideringer.epostValidate(epost);

        } catch (InvalidEpostException e){
            melding=e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean validateTlfnr(String tlfnr){
        boolean sjekk = true;
        try{
            Valideringer.tlfnrValidate(tlfnr);
        } catch (InvalidTlfnrException e){
            melding=e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
}
