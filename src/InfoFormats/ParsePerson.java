package InfoFormats;

import Register.Person;
import avvik.InvalidPersonFormatException;

import java.time.LocalDate;

public class ParsePerson {
    // En metode som konverterer hver linje i en text fil til en array av strenger.
    public static Person parsePerson(String str) throws InvalidPersonFormatException{
        String [] linjeArray = str.split(PersonFormat.DELIMITER);
        if(linjeArray.length != 5){
            throw new InvalidPersonFormatException("Feil bruk av skiletegn.");
        }
        String name = linjeArray[0];
        String dato = linjeArray[1];
        String [] datoArr = dato.split("-");
        int year = parseNumber(datoArr[0],"Feil input: dato nummer format!");
        int month = parseNumber(datoArr[1],"Feil input: dato nummer format!");
        int day = parseNumber(datoArr[2],"Feil input: dato nummer format!");
        LocalDate fødselsdato = LocalDate.of(year,month,day);
        //her hopper jeg over index 2 i arrayet fordi, det er alder og alder skal beregens i Person Klassen.
        String ePost = linjeArray[3];
        String tlfNum = linjeArray[4];
        //Objekt av type Person.
        return new Person(name,ePost,tlfNum,fødselsdato);
    }
    private static int parseNumber(String strInput,String errorMessage) throws InvalidPersonFormatException{
        int number;
        try {
            number = Integer.parseInt(strInput);
        }catch (NumberFormatException e){
            throw new InvalidPersonFormatException(errorMessage);
        }
        return number;
    }
}
