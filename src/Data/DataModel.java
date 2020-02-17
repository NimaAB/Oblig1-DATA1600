package Data;

import javafx.beans.property.SimpleStringProperty;

public class DataModel {
    private SimpleStringProperty name;
    private SimpleStringProperty epost;
    private SimpleStringProperty tlfNr;
    private SimpleStringProperty date;


    public DataModel(String name, String epost, String tlfNr, String date) {
        this.name = new SimpleStringProperty(name);
        this.epost = new SimpleStringProperty(epost);
        this.tlfNr=new SimpleStringProperty(tlfNr);
        this.date = new SimpleStringProperty(date);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setEpost(String epost) {
        this.epost.set(epost);
    }

    public void setTlfNr(String tlfNr) {
        this.tlfNr.set(tlfNr);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getName() { return name.get(); }
    public String getEpost() { return epost.get(); }
    public String getTlfNr() { return tlfNr.get(); }
    public String getDate() { return date.get(); }
}
