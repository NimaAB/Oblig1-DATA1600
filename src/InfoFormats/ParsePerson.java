package InfoFormats;

import Data.PersonDataModel;

import avvik.InvalidPersonFormatException;


public class ParsePerson {
    // En metode som konverterer hver linje i en text fil til en array av strenger.
    public static PersonDataModel parsePerson(String str) throws InvalidPersonFormatException{
        String [] linjeArray = str.split(PersonFormat.DELIMITER);
        if(linjeArray.length != 5){
            throw new InvalidPersonFormatException("Feil bruk av skiletegn.");
        }
        String name = linjeArray[0];
        String dato = linjeArray[1];
        String ePost = linjeArray[3];
        String tlfNum = linjeArray[4];

        return new PersonDataModel(name,ePost,tlfNum,dato);
    }
}
