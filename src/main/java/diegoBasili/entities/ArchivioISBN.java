package diegoBasili.entities;

import java.util.HashSet;
import java.util.Set;

public class ArchivioISBN {
    private static Set<Integer> codiceUnivocoISBN = new HashSet<>();

    /* Metodo per controllare e archiviare ISBN */
    public static boolean controlloEdArchivioISBN(int codiceISBN) {
        if (codiceUnivocoISBN.contains(codiceISBN)) {
            return false;
        } else {
            codiceUnivocoISBN.add(codiceISBN);
            return true;
        }
    }
}
