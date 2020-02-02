package Register;

import java.time.LocalDate;
public class Person {
    private String name, epost, tlfnr;
    private LocalDate fødselsdato;

    public Person(String name, String epost, String tlfnr, LocalDate fødselsdato) {
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
    public LocalDate getFødselsdato(){
        return fødselsdato;
    }
    public int beregnAlder(){
        java.time.LocalDate nå = java.time.LocalDate.now();
        return nå.getYear() - fødselsdato.getYear() - 1;
    }
}
