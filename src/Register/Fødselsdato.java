package Register;
import java.time.LocalDate;

public class Fødselsdato {
    private LocalDate fødselsdato;
    private static LocalDate nå = LocalDate.now();

    public Fødselsdato(LocalDate fødselsdato){
        this.fødselsdato = fødselsdato;
    }
    private static int beregnAlder(LocalDate nå, LocalDate fødselsdato){
        return nå.getYear() - fødselsdato.getYear() - 1;
    }
    public String toString(){
        String ut = this.fødselsdato+ " | " + beregnAlder(nå,fødselsdato);
        return ut;
    }
}

