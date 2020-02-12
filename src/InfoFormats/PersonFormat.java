package InfoFormats;

import Data.PersonDataModel;

import java.util.ArrayList;


public class PersonFormat {
    public static String DELIMITER =";"; //En skiletegn mellom Strengene i samme linje i filen.

    private static String personFormat(PersonDataModel enPerson){
        return enPerson.getName() + DELIMITER  + enPerson.getBirthDate() + DELIMITER
                +enPerson.getAge() + DELIMITER + enPerson.getEPost()
                + DELIMITER + enPerson.getTlfNr();
    }
    public static String folkFormat(ArrayList<PersonDataModel> personDataList){
        StringBuilder pFormat = new StringBuilder();
        for(PersonDataModel element : personDataList){
            pFormat.append(personFormat(element));
            pFormat.append("\n");
        }
        return pFormat.toString();
    }
}
