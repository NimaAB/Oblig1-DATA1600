package InfoFormats;

import Data.PersonDataModel;

import avvik.InvalidPersonFormatException;


public class ParsePerson {
    // En metode som konverterer hver linje i en text fil til en array av strenger.
    // Metoden tar imot en string og splitter opp stringen i fem deler ved hjelp av
    // string split metode. Og splitt metoden letter etter Delimter tegn(;).
    public static PersonDataModel parsePerson(String str) throws InvalidPersonFormatException{
        String [] linjeArray = str.split(PersonFormat.DELIMITER); // en arra
        if(linjeArray.length != 5){
            throw new InvalidPersonFormatException("Filen åpnes ikke: Feil bruk av skiletegn -;-.");
        }
        String name = linjeArray[0];
        String dato = linjeArray[1];
        String ePost = linjeArray[3];
        String tlfNum = linjeArray[4];

        return new PersonDataModel(name,ePost,tlfNum,dato);
    }
}
