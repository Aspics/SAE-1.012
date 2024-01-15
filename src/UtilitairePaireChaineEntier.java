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
            if (listePaires.get(i).getChaine().equals(chaine)) {
                return i;
            }
        }
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
        for (int i = 0; i < listePaires.size(); i++) {
            if (listePaires.get(i).getChaine().equals(chaine)) {
                return listePaires.get(i).getEntier();
            }
        }
        return 0;
    
    }

    public static String chaineMax(ArrayList<PaireChaineEntier> listePaires) {
        return "SPORTS";
    }


    public static float moyenne(ArrayList<PaireChaineEntier> listePaires) {
        return 0;
    }

}
