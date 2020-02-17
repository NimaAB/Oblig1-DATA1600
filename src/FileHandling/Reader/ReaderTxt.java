package FileHandling.Reader;

import Data.PersonDataModel;
import FileHandling.ReaderIntefase;
import InfoFormats.ParsePerson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//readerTxt klasse implemennterer metodene fra readerItefase-n
public class ReaderTxt implements ReaderIntefase {

    //read metoden tar imot Filens sti som vi skal lese som en param.
    //der etter returnerer read() en arrayList av type PersonDataModel.
    public ArrayList<PersonDataModel> read(File path) throws IOException{
        ArrayList<PersonDataModel> personList = new ArrayList<>();

        //Under oppretes en objekt file av Type File reader og FileReader konstruktøren
        //tar imot en File type som i hvor tilfelle heter path og kommer fra read(@param).
        FileReader file = new FileReader(path);
        try (BufferedReader readerFile = new BufferedReader(file)){
            String line;
            while((line = readerFile.readLine())!=null){ //her sjekkes om linjen i text filen er ikke tom
                personList.add(ParsePerson.parsePerson(line));

                //så lenge er det en linje i filen da skal den leses
                //og skal lages en PersonDataModel baster på de dataene og bli lagt til Listen.
                //(ctrl+ click) på (parsePerson(line)) metoden for ålese om den.
            }
        }
        return personList;
    }
}