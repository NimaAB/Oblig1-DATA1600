package FileHandling;

import java.io.File;
/**
 * En klasse som hjelper oss med finne fileformaten med sin FileExtentionFilter metoden
 * */
public class FileExtentionFilter {
    public String getExtention(File selectedFile){
        String name = selectedFile.getName();
        if(name.lastIndexOf(".")!=-1 && name.lastIndexOf(".")!=0){
            return name.substring(name.lastIndexOf(".")+1);
        }
        return "";
    }
}
