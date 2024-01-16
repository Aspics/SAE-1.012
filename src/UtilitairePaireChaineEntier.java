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
            //Check if the chaine is in the list
            if (listePaires.get(i).getChaine().equals(chaine)) {
                //Return it's entier associated with the chaine
                return listePaires.get(i).getEntier();
            }
        }
        return 0;
    
    }

    public static String chaineMax(ArrayList<PaireChaineEntier> listePaires) {
        int max = 0;
        String chaine = "";
        for (PaireChaineEntier paire : listePaires) {
            if (paire.getEntier() > max) {
                max = paire.getEntier();
                chaine = paire.getChaine();
            }
        }
        return chaine;
    }


    public static float moyenne(ArrayList<PaireChaineEntier> listePaires) {
        int nb = 0;
        int i = listePaires.size();
        for (PaireChaineEntier paire : listePaires) {
            nb += paire.getEntier();
        }
        return nb/i;
    }

}
