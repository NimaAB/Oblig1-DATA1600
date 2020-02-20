package Håntering;
/**
 * TableView Input Håntering
 * */
public class TVInputHåntering {
    public boolean nameCExceptable(String newValue){
        if(newValue.isEmpty()){ return false; }
        return AvviksHåntering.isValidateName(newValue);
    }
    public boolean birthdateCExceptable(String newValue){
        if(newValue.isEmpty()){ return false; }
        return AvviksHåntering.isValidDate(newValue);
    }
    public boolean epostCExceptable(String newValue){
        if(newValue.isEmpty()){ return false; }
        return AvviksHåntering.isValidEpost(newValue);
    }
    public boolean tlfNrCExeptable(String newValue){
        if(newValue.isEmpty()){ return false; }
        return AvviksHåntering.isValidTlfnr(newValue);
    }
}
