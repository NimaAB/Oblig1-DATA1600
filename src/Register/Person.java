package Register;

public class Person{
    private String navn;
    private Fødselsdato fødselsdato;

    public Person(String navn, Fødselsdato fødselsdato) {
        this.navn = navn;
        this.fødselsdato = fødselsdato;
    }
    public String toString(){
        return navn + " | " +fødselsdato.toString() + "\n";
    }
}
