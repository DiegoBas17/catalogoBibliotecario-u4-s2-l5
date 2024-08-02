package diegoBasili;

import diegoBasili.entities.Libro;
import diegoBasili.entities.Rivista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inizioGestione();
        int sceltaGestione = scanner.nextInt();
        switch (sceltaGestione) {
            case 1: {
                aggiuintaElemento();
            }
        }
    }

    public static void inizioGestione() {
        System.out.println("Premi un pulsante per scegliere un operazione:");
        System.out.println("1-aggiungi un elemento");
        System.out.println("2-rimozione di un elemento dato un codice ISBN");
        System.out.println("3-ricerca per ISBN");
        System.out.println("4-ricerca per anno pubblicazione");
        System.out.println("5-ricerca per autore");
        System.out.println("6-salvataggio su disco dell'archivio");
        System.out.println("7-caricamento dal disco dell'archivio di una nuova lista");
        System.out.println("8-esci");
    }

    public static void aggiuintaElemento() {
        int scelta = 0;
        while (true) {
            try {
                System.out.println("Che elemento vuoi aggiungere?");
                System.out.println("1-Libro");
                System.out.println("2-Rivista");
                scelta = scanner.nextInt();
                scanner.nextLine();
                if (scelta == 1 || scelta == 2) {
                    break;
                } else {
                    System.out.println("Scelta non valida. Per favore, inserisci 1 per Libro o 2 per Rivista.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Per favore, inserisci un numero.");
                scanner.nextLine();
            }
        }
        if (scelta == 1) {
            String titolo;
            int annoPublicazione = 0;
            int numeroPagine = 0;
            String autore;
            String genere;
            try {
                System.out.println("Inserisci il titolo del libro");
                titolo = scanner.nextLine();
                System.out.println("Inserisci l'anno di pubblicazione");
                annoPublicazione = gestioneInputInt();
                System.out.println("Inserisci numero di pagine");
                numeroPagine = gestioneInputInt();
                scanner.nextLine();
                System.out.println("Inserisci l'autore");
                autore = scanner.nextLine();

                System.out.println("Inserisci il genere");
                genere = scanner.nextLine();

                Libro libro = new Libro(titolo, annoPublicazione, numeroPagine, autore, genere);
                System.out.println("Libro aggiunto: " + libro);
            } catch (InputMismatchException e) {
                System.out.println("Input non valido.");
            }
        } else if (scelta == 2) {
            String titolo;
            int annoPublicazione = 0;
            int numeroPagine = 0;
            String periodicita = "";
            try {
                System.out.println("Inserisci il titolo della rivista");
                titolo = scanner.nextLine();
                System.out.println("Inserisci l'anno di pubblicazione");
                annoPublicazione = gestioneInputInt();
                System.out.println("Inserisci numero di pagine");
                numeroPagine = gestioneInputInt();
                periodicita = gestionePeriodicita();
                Rivista rivista = new Rivista(titolo, annoPublicazione, numeroPagine, periodicita);
                System.out.println("Rivista aggiunta: " + rivista);
            } catch (InputMismatchException e) {
                System.out.println("Input non valido.");
                scanner.nextLine();

            }
        }


    }

    private static int gestioneInputInt() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Per favore, inserisci un numero.");
                scanner.nextLine();
            }
        }
    }

    private static String gestionePeriodicita() {
        int periodicitaScelta;
        while (true) {
            try {
                System.out.println("Con quale periodicità è pubblicata la rivista?");
                System.out.println("1-SETTIMANALE");
                System.out.println("2-MENSILE");
                System.out.println("3-SEMESTRALE");
                periodicitaScelta = scanner.nextInt();
                scanner.nextLine(); // Consuma la nuova linea

                switch (periodicitaScelta) {
                    case 1:
                        return "SETTIMANALE";
                    case 2:
                        return "MENSILE";
                    case 3:
                        return "SEMESTRALE";
                    default:
                        System.out.println("Non hai inserito un valore corretto. Riprova.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Riprova.");
                scanner.nextLine();
            }
        }
    }
}
