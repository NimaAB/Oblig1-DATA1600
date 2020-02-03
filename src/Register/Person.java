package Register;

import java.time.LocalDate;
import java.time.Month;

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

    public int beregnAlder(){ //Forberdret litt: fortsatt litt mer plass til forberdring av metoden.
        LocalDate nå = LocalDate.now();
        Month nåMonth = nå.getMonth();
        int nowMonthValue = nåMonth.getValue();
        int fødselsdatoMonthValue = fødselsdato.getMonth().getValue();
        int age = nå.getYear() - fødselsdato.getYear();
        if(nowMonthValue < fødselsdatoMonthValue || nowMonthValue == fødselsdatoMonthValue){
            age -=1;
        }
        return age;
    }
    /**En middlertidig metode. Vi kommer til å slette det etter hvert.*/
    public String toString(){
        return getName() + getFødselsdato() + beregnAlder() + getTlfnr() + getEpost() + "\n";
    }
}
//TIPS// nowMåned < måned || nowMåned == måned
