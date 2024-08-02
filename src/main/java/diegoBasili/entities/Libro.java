package diegoBasili.entities;


public class Libro extends Biblioteca {
    private String autore;
    private String genere;

    public Libro(String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }
}
