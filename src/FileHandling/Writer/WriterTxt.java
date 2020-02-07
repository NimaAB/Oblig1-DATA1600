package FileHandling.Writer;

import FileHandling.WriterInteface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterTxt implements WriterInteface {
    public void save(String objString, File filePath, int numberOfLines){
        FileWriter writerParamTo_fWriter;
        BufferedWriter fWriter = null;
                try{
                    writerParamTo_fWriter = new FileWriter(filePath);
                    fWriter= new BufferedWriter(writerParamTo_fWriter);
                    for(int i=numberOfLines; i> 0;i--){
                        fWriter.write(objString);
                    }
                }catch (IOException e){
                    System.err.println(e.getMessage());
                }
                try{
                    assert fWriter != null;//Java føreslo dette har ingen ide.
                    fWriter.close();
                }catch (IOException e){
                    System.err.println(e.getMessage());
                }
    }
}
