package InfoFormats;

import Data.PersonDataModel;

import java.util.ArrayList;


public class PersonFormat {
    public static String DELIMITER =";"; //En skiletegn mellom Strengene i samme linje i filen.

    //En metode som bestemmer om at hvordan dataene skal skrives i et text fil.
    //etter hvert attributt har vi lagt til en DELIMITER tegn. (;)
    private static String personFormat(PersonDataModel enPerson){

        return enPerson.getName() + DELIMITER  + enPerson.getBirthDate() + DELIMITER
                + enPerson.getAge() + DELIMITER + enPerson.getEmail()
                + DELIMITER + enPerson.getTlfNr();
    }
    //Metoden tar imot en ArrayList av type PersonDataModel. og den returnerer en String
    //av ArrayListen.
    public static String folkFormat(ArrayList<PersonDataModel> personDataList){
        StringBuilder pFormat = new StringBuilder(); //StringBuilder har metodon append().
        for(PersonDataModel element : personDataList){
            pFormat.append(personFormat(element));
            pFormat.append("\n");
        }
        return pFormat.toString();
    }
}
