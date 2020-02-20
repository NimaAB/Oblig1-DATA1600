package FileHandling.Reader;

import Data.PersonDataModel;
import FileHandling.ReaderIntefase;

import java.io.*;
import java.util.ArrayList;

public class ReaderJObj implements ReaderIntefase {
    public ArrayList<PersonDataModel> read(File path)throws IOException {
        ArrayList<PersonDataModel> personList = new ArrayList<>();
        try{
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream objIn = new ObjectInputStream(file);
            PersonDataModel person;
            while ((person = (PersonDataModel) objIn.readObject()) != null){
                personList.add(person);
            }
            objIn.close();
        }catch(IOException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        return personList;
    }
}
