package FileHandling.Writer;

import FileHandling.WriterInteface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterTxt implements WriterInteface {
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
                    assert fileWriter != null;
                    //Java føreslo assert, fordi det skal sjekkes om filen
                    //ikke er null for den lukes.
                    fileWriter.close();
                }catch (IOException e){
                    System.err.println(e.getMessage());
                }
    }
}
