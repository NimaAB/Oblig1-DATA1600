package Håntering;

import avvik.InvalidEpostException;
import avvik.InvalidNameException;
import avvik.InvalidTlfnrException;

import java.time.DateTimeException;

public class avikkHåntering {
    public static int [] dateNumFormat;
    public static String melding = "";

    public static boolean isValidateName(String name){
        boolean sjekk = true;
        try{
            Valideringer.isNotValidName(name);
        } catch (InvalidNameException e){
            melding=e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean isValidNumFormat(String[] date){
        boolean sjekk = true;
        try{
            dateNumFormat[0]=Integer.parseInt(date[0]); //year
            dateNumFormat[1]=Integer.parseInt(date[1]);//month
            dateNumFormat[2]=Integer.parseInt(date[2]);//day
        } catch (NumberFormatException e){
            melding = e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean isValidDate(int [] datenumFormat) {
        boolean sjekk = true;
        try {
            Valideringer.dateIsAccepted(datenumFormat[0],datenumFormat[1],datenumFormat[2]);
        } catch (DateTimeException e) {
            melding = e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean isValidEpost(String epost){
        boolean sjekk = true;
        try{
            Valideringer.epostValidate(epost);

        } catch (InvalidEpostException e){
            melding=e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean isValidTlfnr(String tlfnr){
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
