package FileHandling.Writer;


import Data.PersonDataModel;
import FileHandling.WriterInterface;
import InfoFormats.PersonFormat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**Writer klasse implementer metodene fra WriterIntefase.
 * klassens Oppgave: Skrive objektene til en text fil på en spesiel format.
 * */
public class WriterTxt implements WriterInterface {

    /**metoden save: tar imot en string som er objekten, File stien,
     * og antall linjer som skal skrives per objekt.
     * numberOfLines settes lik 1 i Controller
     * */
    public void save(ArrayList<PersonDataModel> objList, File filePath){
        FileWriter writerParamTo_fWriter;
        BufferedWriter fileWriter = null;
        int numberOfLines = 1;
        try{
            writerParamTo_fWriter = new FileWriter(filePath);
            fileWriter= new BufferedWriter(writerParamTo_fWriter);
            for(int i=numberOfLines; i> 0;i--){
                String objString = PersonFormat.folkFormat(objList);
                fileWriter.write(objString);
            }
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
        try{
            //assert skal sjekke om filen ikke er null for den lukes.
            assert fileWriter != null;
            //filen lukes at det er skrevet
            fileWriter.close();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
