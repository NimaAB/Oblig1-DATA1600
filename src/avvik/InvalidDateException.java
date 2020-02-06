package avvik;

import java.time.DateTimeException;

public class InvalidDateException extends DateTimeException {
    public InvalidDateException(String msg){
        super(msg);
    }
}
