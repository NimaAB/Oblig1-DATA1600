package Håntering;

import avvik.InvalidDatoException;
import avvik.InvalidEpostException;
import avvik.InvalidNameException;
import avvik.InvalidTlfnrException;



public class AvikksHåntering {

    public static String melding;

    public static boolean isValidateName(String name){
        boolean sjekk = true;
        try{
            Valideringer.nameInputValidering(name);
        } catch (InvalidNameException e){
            melding=e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean isValidNumFormatAndDato(String year,String month,String day){
        boolean sjekk = true;
        int outYear = 0; int outMonth =0; int outDay = 0;
        try{
            outYear = Integer.parseInt(year);
            outMonth=Integer.parseInt(month);
            outDay=Integer.parseInt(day);
        } catch (NumberFormatException e){
            melding = e.getMessage();
            sjekk = false;
        }
        try {
            Valideringer.dateInputValidering(outYear,outMonth,outDay);
        } catch (InvalidDatoException e) {
            melding = e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean isValidEpost(String epost){
        boolean sjekk = true;
        try{
            Valideringer.ePostInputValidering(epost);

        } catch (InvalidEpostException e){
            melding=e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean isValidTlfnr(String tlfnr){
        boolean sjekk = true;
        try{
            Valideringer.tlfnrInputValidering(tlfnr);
        } catch (InvalidTlfnrException e){
            melding=e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
}
