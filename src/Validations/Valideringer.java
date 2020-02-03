package Validations;

import avvikshåntering.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.regex.Pattern;


public class Valideringer {
    public static void isNotValidName(String navn)  throws InvalidNameException {
        if(!Pattern.matches("[A-ZÅÆØ][a-zåæø]* [A-ZÅÆØ][a-zåæø]*",navn)){
            throw new InvalidNameException("Feil navn format");
        }
    }
    public static void ageIsAccepted(int alder) throws InvalidAgeException{
        if(alder>120 || alder<0){
            throw new InvalidAgeException("Ugyeldig alder");
        }
    }
    public static void dateIsAccepted(int year,int month, int day) throws InvalidDateException {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(year, month, day);
        if(date.isAfter(now) || year<1900){
            throw new InvalidDateException("Ugyeldig dato eller år");
        }
    }
    public static void daysOfMonthValidate(int day,int month, int year)throws InvalidDateException{
        YearMonth yearMonthObj = YearMonth.of(year,month);
        int daysInMonth = yearMonthObj.lengthOfMonth();
        if(day > daysInMonth || day < 1){
            throw new InvalidDateException("Feil dag!");
        }
    }
    public static void monthofyearValidate(int month)throws InvalidDateException{
        if(month >13 || month<1){
            throw new InvalidDateException("Ugyeldig dato: måneden eksisterer ikke!");
        }
    }
    public static void epostValidate(String ePost) throws InvalidEpostException {
        boolean ligner = Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]" +
                "+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9]" +
                "(?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$",ePost);
        if(!ligner){
            throw new InvalidEpostException("Ugyldig E-post format!");
        }
    }
    public static void tlfnrValidate(String tlfnr) throws InvalidTlfnrException {
        boolean patternPlus = Pattern.matches("^\\+(?:[0-9-] ?){6,15}[0-9]$",tlfnr);
        boolean patternZeros = Pattern.matches("^0(?:[0-9-] ?){6,15}[0-9]$",tlfnr);
        boolean utenLandsKode = Pattern.matches("[0-9]{3,13}",tlfnr);
        if(!patternPlus && !patternZeros && !utenLandsKode){
            throw new InvalidTlfnrException("Ugyldig telefonnummer format!");
        }
    }
}
