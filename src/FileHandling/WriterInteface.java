package FileHandling;

import java.io.File;
import java.io.IOException;


public interface WriterInteface {
     void save(String objString, File filePath,int numberOfLines) throws IOException;
}