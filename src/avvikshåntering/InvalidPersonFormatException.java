package avvikshåntering;

import java.io.IOException;

public class InvalidPersonFormatException extends IOException {
    public InvalidPersonFormatException(String msg){
        super(msg);
    }
}
