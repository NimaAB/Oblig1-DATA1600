package FileHandling.Reader;

import FileHandling.ReaderIntefase;
import InfoFormats.ParsePerson;
import Register.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReaderTxt implements ReaderIntefase {
    public ArrayList<Person> read(File path) throws IOException{
        ArrayList<Person> personList = new ArrayList<>();

        FileReader file = new FileReader(path);

        try (BufferedReader readerFile = new BufferedReader(file)){
            String line;

            while((line = readerFile.readLine())!=null){
                personList.add(ParsePerson.parsePerson(line));
            }
        }
        return personList;
    }
}