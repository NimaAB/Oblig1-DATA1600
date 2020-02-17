package FileHandling.Reader;

import FileHandling.ReaderIntefase;
import InfoFormats.ParsePerson;
import Register.Person;
import avvik.InvalidPersonFormatException;

import java.io.*;
import java.util.ArrayList;


public class ReaderTxt implements ReaderIntefase {
    public ArrayList<Person> read(File path) {
        ArrayList<Person> personList = new ArrayList<>();

        try {
            FileReader file = new FileReader(path);
            BufferedReader readerFile = new BufferedReader(file);
            String line;
            while((line = readerFile.readLine())!=null){
                personList.add(ParsePerson.parsePerson(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }
}