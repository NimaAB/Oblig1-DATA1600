package InfoFormats;

import Register.Person;

import java.util.List;

public class PersonFormat {
    public static String DELIMITER =";"; //En skiletegn mellom Strengene i samme linje i filen.

    private static String personFormat(Person enPerson){
        return enPerson.getName() + DELIMITER+enPerson.getFødselsdato() + DELIMITER
                +enPerson.beregnAlder() + DELIMITER + enPerson.getEpost()
                + DELIMITER + enPerson.getTlfnr();
    }
    public static String folkFormat(List<Person> personer){
        StringBuilder pFormat = new StringBuilder();
        for(Person element : personer){
            pFormat.append(personFormat(element));
            pFormat.append("\n");
        }
        return pFormat.toString();
    }
}
