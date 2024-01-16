import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UtilitairePaireChaineEntier {


    // Warning: this method is useless with HashMaps
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
     * Retrieves the integer value associated with a given string from a HashMap.
     * 
     * @param listePaires The HashMap containing the string-integer pairs.
     * @param chaine The string for which the associated integer value is to be retrieved.
     * @return The integer value associated with the given string, or 0 if the string is not found in the HashMap.
     */
    public static int entierPourChaine(HashMap<String, Integer> listePaires, String chaine) {
        // function should be shorter if not for the need of returning 0 if the chaine is not found instead of null

        // check if the chaine is in the list
        if (listePaires.containsKey(chaine)) {
            // return it's entier associated with the chaine if it is
            return listePaires.get(chaine);
        }
        // return 0 if it isn't
        return 0;
    
    }

    
    /**
     * Returns the string with the highest integer value from the given HashMap.
     * Returns an empty string if the HashMap is empty.
     * 
     * @param listePaires the HashMap containing string-integer pairs
     * @return the string with the highest integer value
     */
    public static String chaineMax(HashMap<String, Integer> listePaires) {
        String chaine = "";

        // find the chaine with the highest entier
        chaine = Collections.max(listePaires.entrySet(), Map.Entry.comparingByValue()).getKey();

        // return the chaine with the highest entier
        return chaine;
    }

    
    /**
     * Calculates the average of the integers in a list of PaireChaineEntier objects.
     * 
     * @param listePaires The list of PaireChaineEntier objects.
     * @return The average of the integers in the list.
     */
    public static float moyenne(HashMap<String, Integer> listePaires) {
        Integer nb = 0; // total of all the integers
        // for each PaireChaineEntier in the list
        for (Integer paire : listePaires.values()) {
            // add the entier to the total
            nb += paire;
        }
        // return the average
        return (float) nb / listePaires.size();
    }

}
