package diegoBasili;

import com.github.javafaker.Faker;
import diegoBasili.entities.Biblioteca;
import diegoBasili.entities.Libro;
import diegoBasili.entities.Rivista;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Application {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<Biblioteca> biblioteca = new ArrayList<>();
        inizializzazioneDellaBiblioteca(biblioteca, 5);
        /*   System.out.println(biblioteca);*/
        biblioteca:
        while (true) {
            inizioGestione();
            int sceltaGestione = gestioneInputIntMenu();
            switch (sceltaGestione) {
                case 1: {
                    aggiuintaElemento(biblioteca);
                    break;
                }
                case 2: {
                    rimuoviPerISBN(biblioteca);
                    break;
                }
                case 3: {
                    ricercaPerISBN(biblioteca);
                    break;
                }
                case 4: {
                    ricercaPerAnno(biblioteca);
                    break;
                }
                case 5: {
                    ricercaPerAutore(biblioteca);
                    break;
                }
                case 6: {
                    File bibliotecaFromFile = new File("src/bibliotecaFromFile");
                    salvaSuDisco(biblioteca, bibliotecaFromFile);
                    break;
                }
                case 7: {
                    List<Biblioteca> ciclio
                    leggiProdottiDaDisco()
                    break;
                }
                case 8: {
                    scanner.close();
                    break biblioteca;
                }
                default:
                    System.out.println("Scelta non valida. Per favore, inserisci un numero da 1 a 8.");
                    break;
            }
        }
        /* System.out.println(biblioteca);*/
    }

    public static void inizializzazioneDellaBiblioteca(List<Biblioteca> biblioteca, int numeroElementi) {
        Faker f = new Faker(Locale.ITALIAN);
        Random random = new Random();
        for (int i = 0; i < numeroElementi; i++) {
            Libro libro = new Libro(f.book().title(), random.nextInt(2000, 2024), random.nextInt(1, 400), f.book().author(), f.book().genre());
            biblioteca.add(libro);

        }
    }

    private static int gestioneInputIntMenu() {
        while (true) {
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Pulisce il newline rimasto dopo nextInt()
                if (input < 1 || input > 8) {
                    System.out.println("Scelta non valida. Inserisci un numero tra 1 e 8.");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input non valido. Per favore, inserisci un numero intero.");
                scanner.nextLine(); // Pulisce l'input non valido
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

    public static void aggiuintaElemento(List<Biblioteca> biblioteca) {
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
                biblioteca.add(libro);
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
                biblioteca.add(rivista);
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
                int input = scanner.nextInt();
                scanner.nextLine();
                if (input >= 0) {
                    return input;
                } else {
                    System.out.println("Input non valido. Per favore, inserisci un numero positivo.");
                }
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

    public static void rimuoviPerISBN(List<Biblioteca> listaBiblioteca) {
        System.out.println("Inserisci il codice ISBN per rimuovere L'elemento");
        int codiceISBN = gestioneInputInt();
        scanner.nextLine();
        boolean match = false;
        for (int i = 0; i < listaBiblioteca.size(); i++) {
            if (listaBiblioteca.get(i).getCodiceISBN() == codiceISBN) {
                listaBiblioteca.remove(i);
                System.out.println("Oggetto rimosso");
                match = true;
                break;
            }
        }
        if (!match) {
            System.out.println("Nessun elemento presente con il codice ISBN");
        }
    }

    public static void ricercaPerISBN(List<Biblioteca> listaBiblioteca) {
        System.out.println("Inserisci il codice ISBN per rimuovere L'elemento");
        int codiceISBN = gestioneInputInt();
        scanner.nextLine();
        List<Biblioteca> risultato = listaBiblioteca.stream().filter(obj -> obj.getCodiceISBN() == codiceISBN).toList();
        if (risultato.isEmpty()) {
            System.out.println("Nessun oggetto trovato con il codice ISBN fornito.");
        } else {
            System.out.println("Oggetti trovati con codice ISBN " + codiceISBN + ":");
            risultato.forEach(System.out::println);
        }
    }

    public static void ricercaPerAnno(List<Biblioteca> listaBiblioteca) {
        System.out.println("Inserisci l'anno dei libri che vuoi visualizzare");
        int anno = gestioneInputInt();
        listaBiblioteca.stream().filter(book -> Objects.equals(book.getAnnoPubblicazione(), anno)).forEach(System.out::println);
    }

    public static void ricercaPerAutore(List<Biblioteca> listaBiblioteca) {
        if (listaBiblioteca.isEmpty()) {
            System.out.println("La lista di biblioteca è vuota.");
        }
        System.out.println("Inserisci l'autore dei libri che vuoi visualizzare");
        String autore = scanner.nextLine();
        if (autore.isEmpty()) {
            System.out.println("L'autore non può essere una stringa vuota.");
        }
        List<Libro> risultati = listaBiblioteca.stream()
                .filter(book -> book instanceof Libro) // Filtra solo gli oggetti di tipo Libro
                .map(book -> (Libro) book) // Cast a Libro
                .filter(libro -> Objects.equals(libro.getAutore(), autore)) // Filtra per autore
                .toList();
        if (risultati.isEmpty()) {
            System.out.println("Nessun libro trovato per l'autore specificato.");
        } else {
            System.out.println("Libri trovati per l'autore '" + autore + "':");
            risultati.forEach(System.out::println);
        }
    }

    public static void salvaSuDisco(List<Biblioteca> oggettiBiblioteca, File file) {
        StringBuilder stringa = new StringBuilder();
        try {
            for (Biblioteca biblioteca : oggettiBiblioteca) {
                if (biblioteca instanceof Libro) {
                    Libro libro = (Libro) biblioteca;
                    stringa.append("Libro@")
                            .append(libro.getCodiceISBN()).append("@")
                            .append(libro.getTitolo()).append("@")
                            .append(libro.getAnnoPubblicazione()).append("@")
                            .append(libro.getNumeroPagine()).append("@")
                            .append(libro.getAutore()).append(System.lineSeparator())
                            .append(libro.getGenere()).append(System.lineSeparator());
                } else if (biblioteca instanceof Rivista) {
                    Rivista rivista = (Rivista) biblioteca;
                    stringa.append("Rivista@")
                            .append(rivista.getCodiceISBN()).append("@")
                            .append(rivista.getTitolo()).append("@")
                            .append(rivista.getAnnoPubblicazione()).append("@")
                            .append(rivista.getNumeroPagine()).append("@")
                            .append(rivista.getPeriodicità()).append(System.lineSeparator());
                }
            }
            FileUtils.writeStringToFile(file, stringa.toString(), StandardCharsets.UTF_8);
            System.out.println("Oggetti salvati su " + file.getName());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void leggiProdottiDaDisco(File file, List<Biblioteca> bibliotecaFromFile) {
        String content = null;
        // Separa il contenuto in righe
        String[] contentAsArray = content.split(System.lineSeparator());
        for (String string : contentAsArray) {
            String[] objBibliotecaString = string.split("@");
            try {
                if (objBibliotecaString.length == 7) {
                    Libro libro = new Libro(
                            objBibliotecaString[2],
                            Integer.parseInt(objBibliotecaString[3]),
                            Integer.parseInt(objBibliotecaString[4]),
                            objBibliotecaString[5],
                            objBibliotecaString[6]
                    );
                    bibliotecaFromFile.add(libro);
                } else if (objBibliotecaString.length == 6) {
                    Rivista rivista = new Rivista(
                            objBibliotecaString[2],
                            Integer.parseInt(objBibliotecaString[3]),
                            Integer.parseInt(objBibliotecaString[4]),
                            objBibliotecaString[5]
                    );
                    bibliotecaFromFile.add(rivista);
                }
            } catch (NumberFormatException e) {
                System.err.println("Errore di formato numero nella riga: " + string);
                System.err.println("Messaggio: " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Errore di indice nella riga: " + string);
                System.err.println("Messaggio: " + e.getMessage());
            }
        }

    }
}
