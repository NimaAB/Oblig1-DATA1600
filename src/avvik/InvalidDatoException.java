package avvik;

import java.time.DateTimeException;

public class InvalidDatoException extends DateTimeException {
    public InvalidDatoException(String msg){
        super(msg);
    }
}
