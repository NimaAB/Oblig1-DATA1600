package FileHandling;

import Data.PersonDataModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public interface WriterInterface {
     void save(ArrayList<PersonDataModel> objList, File filePath) throws IOException;
}