package Register;


public class Person {
    private String name, epost, tlfnr;
    private Fødselsdato fødselsdato;

    public Person(String name, String epost, String tlfnr, Fødselsdato fødselsdato) {
            this.name = name;
            this.epost = epost;
            this.tlfnr = tlfnr;
            this.fødselsdato = fødselsdato;
    }
    public String getName(){
        return name;
    }
    public String getEpost(){
        return epost;
    }
    public String getTlfnr(){
        return tlfnr;
    }
    public Fødselsdato getFødselsdato(){
        return fødselsdato;
    }
}
