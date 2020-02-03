package InfoFormats;

import Register.Person;
import avvikshåntering.InvalidPersonFormatException;

public class ParsePerson {
    public static Person parsePerson(String str) throws InvalidPersonFormatException{
        String [] linjeArray= str.split(PersonFormat.DELIMITER);
        if(linjeArray.length != 5){
            throw new InvalidPersonFormatException("Feil bruk av skiletegn.");
        }
        return null;
    }
}
