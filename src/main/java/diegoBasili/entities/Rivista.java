package diegoBasili.entities;


public class Rivista extends Biblioteca {
    private String periodicità;

    public Rivista(String titolo, int annoPubblicazione, int numeroPagine, String periodicità) {
        super(titolo, annoPubblicazione, numeroPagine);
        this.periodicità = periodicità;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicità='" + periodicità + '\'' +
                '}';
    }

    public String getPeriodicità() {
        return periodicità;
    }

    public void setPeriodicità(String periodicità) {
        this.periodicità = periodicità;
    }
}
