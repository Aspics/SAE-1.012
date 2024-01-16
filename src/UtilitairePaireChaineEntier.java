import java.util.ArrayList;

public class UtilitairePaireChaineEntier {


    /**
     * Returns the index of the first occurrence of the specified string in the given list of PaireChaineEntier objects (listePaires).
     * If no matching string is found, returns -1.
     * 
     * @param listePaires The list of PaireChaineEntier objects to search in.
     * @param chaine The chaine to search for.
     * @return The index of the first occurrence of the specified string, or -1 if the string is not found.
     */
    public static int indicePourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        for (int i = 0; i < listePaires.size(); i++) {
            //Check if the chaine is in the list
            if (listePaires.get(i).getChaine().equals(chaine)) {
                // return it's index if it is
                return i;
            }
        }
        // return -1 if it isn't
        return -1;

    }

    /**
     * Returns the integer value associated with the given string in the list of PaireChaineEntier objects (listePaires).
     * If no matching string is found, returns 0.
     *
     * @param listePaires the list of PaireChaineEntier objects to search in.
     * @param chaine the chaine to search for.
     * @return the integer value associated with the string, or 0 if the string is not found.
     */
    public static int entierPourChaine(ArrayList<PaireChaineEntier> listePaires, String chaine) {
        for (PaireChaineEntier paire : listePaires) {
            // check if the chaine is in the list
            if (paire.getChaine().equals(chaine)) {
                // return it's entier associated with the chaine if it is
//                System.out.println(chaine + " " + paire.getChaine());
                return paire.getEntier();
            }
        }
        // return 0 if it isn't
        return 0;
    
    }

    /**
     * Returns the string with the highest integer value in the list of PaireChaineEntier objects (listePaires).
     * If the list is empty, returns an empty string.
     *
     * @param listePaires the list of PaireChaineEntier objects to search in.
     * @return the string with the highest integer value, or an empty string if the list is empty.
     */
    public static String chaineMax(ArrayList<PaireChaineEntier> listePaires) {
        int max = 0;
        String chaine = "";
        // for each PaireChaineEntier in the list
        for (PaireChaineEntier paire : listePaires) {
            // check if the entier is higher than the current max
            if (paire.getEntier() > max) {
                max = paire.getEntier();
                chaine = paire.getChaine();
            }
        }
        // return the chaine with the highest entier
        return chaine;
    }

    
    /**
     * Calculates the average of the integers in a list of PaireChaineEntier objects.
     * 
     * @param listePaires The list of PaireChaineEntier objects.
     * @return The average of the integers in the list.
     */
    public static float moyenne(ArrayList<PaireChaineEntier> listePaires) {
        int nb = 0; // total of all the integers
        // for each PaireChaineEntier in the list
        for (PaireChaineEntier paire : listePaires) {
            // add the entier to the total
            nb += paire.getEntier();
        }
        // return the average
        return (float) nb /listePaires.size();
    }

}
