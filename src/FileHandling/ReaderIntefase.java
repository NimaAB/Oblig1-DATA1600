package FileHandling;

import Data.PersonDataModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface ReaderIntefase {
    ArrayList<PersonDataModel> read(File path) throws IOException;
}
