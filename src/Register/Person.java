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
    public String toString() {
        return name + " | " + fødselsdato.toString() + "|"
                + epost + "|" + tlfnr + "\n";
    }
}
