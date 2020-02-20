package FileHandling.Writer;

import Data.PersonDataModel;
import FileHandling.WriterInterface;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriterJObj implements WriterInterface {
    public void save(ArrayList<PersonDataModel> objList, File filePath){
        try{
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream objOut = new ObjectOutputStream(file);
            for (PersonDataModel enObj:objList) {
                objOut.writeObject(enObj);
            }
            objOut.close();
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
}
