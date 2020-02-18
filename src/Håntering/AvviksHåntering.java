package Håntering;

import avvik.*;


public class AvviksHåntering {

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
    public static boolean isValidDate(String birthDate){
        boolean sjekk = true;
        int[]dateArr;
        try {
            dateArr = AvviksHåntering.numArr(birthDate);
            Valideringer.dateInputValidering(dateArr[0],dateArr[1],dateArr[2]);
        } catch (InvalidDatoException | NumberFormatException | InvalidPersonFormatException e) {
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
    public static int [] numArr(String birthDate) throws NumberFormatException,InvalidPersonFormatException{
        String [] dateStr = birthDate.split("-");
        if(dateStr.length!=3){
            throw new InvalidPersonFormatException("Feil Format i fødselsdato YYYY-MM-DD");
        }
        int [] numbers = new int [3];
        try{
            numbers[0] = Integer.parseInt(dateStr[0]);
            numbers[1] = Integer.parseInt(dateStr[1]);
            numbers[2] = Integer.parseInt(dateStr[2]);
        }catch (NumberFormatException e){
            melding = e.getMessage();
        }
        return numbers;
    }
}
