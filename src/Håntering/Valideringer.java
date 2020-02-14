package Håntering;

import Data.PersonDataModel;
import avvik.*;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.regex.Pattern;


public class Valideringer {

    public static LocalDate birthdate;


    public static void nameInputValidering(String navn)  throws InvalidNameException {
        if(!Pattern.matches("[A-ZÅÆØ][a-zåæø]* [A-ZÅÆØ][a-zåæø]*",navn)){
            throw new InvalidNameException("Feil navn format");
        }
    }
    public static void dateInputValidering(int year, int month, int day) throws InvalidDatoException {
        LocalDate now = LocalDate.now();

        Boolean birthdateTest= true;

        try {
            birthdate = LocalDate.of(year, month, day);

        }
        catch(Exception e){
            throw new InvalidDatoException("Ugyeldig dato");
        }


        YearMonth yearMonthObj = YearMonth.of(year,month);

        int daysInMonth = yearMonthObj.lengthOfMonth();

        boolean dateNotExcepted = birthdate.isAfter(now) || year < 1900;
        boolean dayNotExcepted = day > daysInMonth || day < 1;
        boolean monthNotExcepted = month > 12 || month < 1;

        if (dateNotExcepted || dayNotExcepted || monthNotExcepted ) {
            throw new InvalidDatoException("Ugyeldig dato");
        }
        PersonDataModel.beregnAlder(birthdate);
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
