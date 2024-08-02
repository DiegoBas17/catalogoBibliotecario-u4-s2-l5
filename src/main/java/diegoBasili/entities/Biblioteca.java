package diegoBasili.entities;

import java.util.Random;

public abstract class Biblioteca {
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

    public int getCodiceISBN() {
        return codiceISBN;
    }

    public void setCodiceISBN(int codiceISBN) {
        this.codiceISBN = codiceISBN;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
