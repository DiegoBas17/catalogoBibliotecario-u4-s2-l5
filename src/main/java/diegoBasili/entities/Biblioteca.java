package diegoBasili.entities;

import java.util.Random;

public class Biblioteca {
    private int codiceISBN;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

    /*Costruttore*/

    public Biblioteca(String titolo, int annoPubblicazione, int numeroPagine) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
        this.codiceISBN = generaCodiceISBNUnivoco();
    }


    private int generaCodiceISBNUnivoco() {
        Random random = new Random();
        int nuovoCodiceISBN;
        do {
            nuovoCodiceISBN = random.nextInt(1000000);
        } while (!ArchivioISBN.controlloEdArchivioISBN(nuovoCodiceISBN));
        return nuovoCodiceISBN;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "codiceISBN=" + codiceISBN +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                '}';
    }
}
