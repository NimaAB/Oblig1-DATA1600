package Håntering;

import avvik.InvalidEpostException;
import avvik.InvalidNameException;
import avvik.InvalidStiException;
import avvik.InvalidTlfnrException;
import java.time.DateTimeException;

public class AvikksHåntering {
    public static int outYear,outMonth,outDay;
    public static String melding = "";

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
    public static boolean isValidNumFormat(String ... date){
        boolean sjekk = true;
        try{
            outYear = Integer.parseInt(date[0]); //year
            outMonth=Integer.parseInt(date[1]);//month
            outDay=Integer.parseInt(date[2]);//day
        } catch (NumberFormatException e){
            melding = e.getMessage();
            sjekk = false;
        }
        return sjekk;
    }
    public static boolean isValidDate(int year,int month,int day) {
        boolean sjekk = true;
        try {
            Valideringer.dateInputValidering(year,month,day);
        } catch (DateTimeException e) {
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
    public static String pathInputhandling(String path){
        try{
            Valideringer.pathInputValidering(path);
        }catch (InvalidStiException e){
            melding = e.getMessage();
        }
        return path;
    }
}
