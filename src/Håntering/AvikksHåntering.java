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
    public static boolean isValidDato(int year, int month, int day){
        boolean sjekk = true;
        try {
            Valideringer.dateInputValidering(year, month, day);
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
    public static int [] numArr(String year,String month,String day){
        int [] numbers = new int [3];
        try{
            numbers[0] = Integer.parseInt(year);
            numbers[1] = Integer.parseInt(month);
            numbers[2] = Integer.parseInt(day);
        }catch (NumberFormatException e){
            melding = e.getMessage();
        }
        return numbers;
    }
}
