package FileHandling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface WriterInteface {
     void save(String objString, File filePath,int numberOfLines) throws IOException;
}