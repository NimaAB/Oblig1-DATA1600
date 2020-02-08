package FileHandling;

import Register.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface ReaderIntefase {
    ArrayList<Person> read(File path) throws IOException;
}
