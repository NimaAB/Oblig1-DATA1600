package FileHandling.Writer;

import FileHandling.WriterInteface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Writer klasse implementer metodene fra WriterIntefase.
//klassens Oppgave: Skrive objektene til en text fil på en spesiel format.
public class WriterTxt implements WriterInteface {

    //metoden save: tar imot en string som er objekten, File stien,
    //og antall linjer som skal skrives per objekt. numberOfLines settes lik 1 i Controller
    public void save(String objString, File filePath, int numberOfLines){
        FileWriter writerParamTo_fWriter;
        BufferedWriter fileWriter = null;
                try{
                    writerParamTo_fWriter = new FileWriter(filePath);
                    fileWriter= new BufferedWriter(writerParamTo_fWriter);
                    for(int i=numberOfLines; i> 0;i--){
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
