package InfoFormats;

import Data.PersonDataModel;

import avvik.InvalidPersonFormatException;


public class ParsePerson {
    /**En metode som konverterer hver linje i en text fil til en array av strenger.
     * Metoden tar imot en string og splitter opp stringen i fem deler ved hjelp av
     * String sin split metode. Og splitt metoden letter etter Delimter tegn(;).
     * metoden er implementert i WriterTxt klassen.
     */
    public static PersonDataModel parsePerson(String str) throws InvalidPersonFormatException{
        //En String array av linje, det indeksene plasseres etter DELIMITER tegnet (;)
        String [] linjeArray = str.split(PersonFormat.DELIMITER);
        if(linjeArray.length != 5){
            throw new InvalidPersonFormatException("Filen åpnes ikke: Feil bruk av skiletegn -;-.");
        }
        String name = linjeArray[0];
        String dato = linjeArray[1];
        /**Her hopper vi over index 2,fordi det er alder. Men vi beregner alder selv*/
        String ePost = linjeArray[3];
        String tlfNum = linjeArray[4];

        return new PersonDataModel(name,ePost,tlfNum,dato);
    }
}
