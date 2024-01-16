import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Classification {


    public static ArrayList<Depeche> lectureDepeches(String nomFichier) {

        //creation d'un tableau de dépêches
        ArrayList<Depeche> depeches = new ArrayList<>();
        try {
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String id = ligne.substring(3);
                ligne = scanner.nextLine();
                String date = ligne.substring(3);
                ligne = scanner.nextLine();
                String categorie = ligne.substring(3);
                ligne = scanner.nextLine();
                String lignes = ligne.substring(3);
                while (scanner.hasNextLine() && !ligne.equals("")) {
                    ligne = scanner.nextLine();
                    if (!ligne.equals("")) {
                        lignes = lignes + '\n' + ligne;
                    }
                }
                Depeche uneDepeche = new Depeche(id, date, categorie, lignes);
                depeches.add(uneDepeche);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return depeches;
    }


    public static void classementDepeches(ArrayList<Depeche> depeches, ArrayList<Categorie> categories, String nomFichier) {
        Hashtable<String, Integer> justes = new Hashtable<>();
        for (Categorie c : categories) {
            justes.put(c.getNom(), 0);
        }
        try {

            FileWriter file = new FileWriter(nomFichier);
            for (Depeche d : depeches) {
                ArrayList<PaireChaineEntier> scores = new ArrayList<>();

                for (Categorie c : categories) {
                    scores.add(new PaireChaineEntier(c.getNom(), c.score(d)));
                }
                file.write(d.getId() + ":" + UtilitairePaireChaineEntier.chaineMax(scores) + "\n");
                if (UtilitairePaireChaineEntier.chaineMax(scores).equals(d.getCategorie())) {
                    justes.put(d.getCategorie(), justes.get(d.getCategorie()) + 1);
                }
            }
            for (String c : justes.keySet()) {
                file.write(c + ":\t\t\t\t" + justes.get(c) + "%\n");
            }
            int moy = 0;
            for (int i : justes.values()) {
                moy += i;
            }
            file.write("MOYENNE:\t\t\t\t" + moy/5 + "%");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Initializes a dictionary of word-frequency pairs based on the given list of depeches and category.
     * 
     * @param depeches The list of depeches.
     * @param categorie The category of depeches to consider.
     * @return The initialized dictionary of word-frequency pairs.
     */
    public static ArrayList<PaireChaineEntier> initDico(ArrayList<Depeche> depeches, String categorie) {
        

        ArrayList<PaireChaineEntier> resultat = new ArrayList<>();

        for (int i = 0; i < depeches.size(); i++) {

            //Check if depeche is in the right category
            if (depeches.get(i).getCategorie().equals(categorie)) {

                for (int j = 0; j < depeches.get(i).getMots().size(); j++) {

                    //Check if mot is already in the list
                    String mot = depeches.get(i).getMots().get(j);
                    boolean containsMot = false;
                    for (PaireChaineEntier paire : resultat) {
                        if (paire.getChaine().equals(mot)) {
                            containsMot = true;
                            break;
                        }
                    }
                    //If not, add it
                    if (!containsMot) {
                        resultat.add(new PaireChaineEntier(mot, 0));
                    }
                }
            }
        }

        return resultat;

    }


    /**
     * Calculates the scores for each word in the given list of Depeche objects based on the specified category.
     * Updates the integer value of each PaireChaineEntier object in the given dictionary accordingly.
     *
     * @param depeches    the list of Depeche objects
     * @param categorie   the category to calculate scores for
     * @param dictionnaire the dictionary containing PaireChaineEntier objects
     */
    public static void calculScores(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
        // for each depeche
        for (Depeche dep : depeches){
            // check if depeche is in the right category
            if (dep.getCategorie().equals(categorie)){
                // increment the score of each word in the depeche
                for (String mot : dep.getMots()){
                    for (PaireChaineEntier paire : dictionnaire){
                        if (paire.getChaine().equals(mot)){
                            paire.setEntier(paire.getEntier() + 1);
                        }
                    }
                }
            }
            else {
                // decrement the score of each word in the depeche
                for (String mot : dep.getMots()){
                    for (PaireChaineEntier paire : dictionnaire){
                        if (paire.getChaine().equals(mot)){
                            paire.setEntier(paire.getEntier() - 1);
                        }
                    }
                }
            }
                
        }

    }


    /**
     * Calculates the weight based on the given score.
     * 
     * @param score the score to calculate the weight for
     * @return the weight corresponding to the score
     */
    public static int poidsPourScore(int score) {
        if (score < 100){return 1;}
        else if (score > 100 && score < 400){return 2;}
        else {return 3;}
    }

    public static void generationLexique(ArrayList<Depeche> depeches, String categorie, String nomFichier) {

    }

    public static void main(String[] args) {

        //Chargement des dépêches en mémoire
        System.out.println("chargement des dépêches");
        ArrayList<Depeche> depeches = lectureDepeches("./depeches.txt");

        for (int i = 0; i < depeches.size(); i++) {
            depeches.get(i).afficher();
        }

    }


}

