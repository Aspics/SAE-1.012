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
                while (scanner.hasNextLine() && !ligne.isEmpty()) {
                    ligne = scanner.nextLine();
                    if (!ligne.isEmpty()) {
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



    /**
     *  Calulate for each categorie of categories the score of each depeche of depeches
     *  Writes the classification results to a file.
     *
     * @param depeches     The list of depeche to calculate the score of.
     * @param categories   The list of categories used.
     * @param nomFichier   The name of the file to write the results to.
     */
    public static void classementDepeches(ArrayList<Depeche> depeches, ArrayList<Categorie> categories, String nomFichier) {
        ArrayList<PaireChaineEntier> justes = new ArrayList<>();
        // init the dictionary of correct classifications per category
        for (Categorie c : categories) {
            justes.add(new PaireChaineEntier(c.getNom(), 0));
        }
        try {
            
            // init the file to write to
            FileWriter file = new FileWriter(nomFichier);
            // for each depeche
            for (Depeche d : depeches) {
                // calculate the score for each categorie
                ArrayList<PaireChaineEntier> scores = new ArrayList<>();

                for (Categorie c : categories) {
                    scores.add(new PaireChaineEntier(c.getNom(), c.score(d)));
                }
                // write the results to the file
                file.write(d.getId() + ":" + UtilitairePaireChaineEntier.chaineMax(scores) + "\n");

                if (UtilitairePaireChaineEntier.chaineMax(scores).equals(d.getCategorie())) {
                    int indexcat = UtilitairePaireChaineEntier.indicePourChaine(justes, d.getCategorie());
                    justes.get(indexcat).setEntier(justes.get(indexcat).getEntier()+1);
                }
            }
            System.out.println("----------------------STATS----------------------");
            //write the percentages of correct classifications
            int moy = 0;
            for (PaireChaineEntier paire : justes) {
                moy += paire.getEntier();
                file.write(paire.getChaine() + ":\t\t\t\t\t\t\t\t" + paire.getEntier() + "%\n");
            }
            //write the average
            file.write("MOYENNE:\t\t\t\t\t\t\t\t" + moy/5 + "%");
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
    public static HashMap<String, Integer> initDico(ArrayList<Depeche> depeches, String categorie) {
        

        HashMap<String, Integer> dico = new HashMap<>();

        for (int i = 0; i < depeches.size(); i++) {

            // check if depeche is in the right category
            if (depeches.get(i).getCategorie().equals(categorie)) {

                for (int j = 0; j < depeches.get(i).getMots().size(); j++) {

                    // check if mot is already in the list
                    String mot = depeches.get(i).getMots().get(j);
                    // if not, add it
                    if (!dico.containsKey(mot)) {
                        dico.put(mot, 0);
                    }
                }
            }
        }

        return dico;

    }



    /**
     * Calculates the scores of words in the given list of depeches based on the specified category and dictionary.
     * The scores are incremented for words in depeches belonging to the specified category, and decremented for words in depeches not belonging to the category.
     *
     * @param depeches     the list of depeches to calculate scores for
     * @param categorie    the category to consider for score calculation
     * @param dictionnaire the dictionary containing words and their scores
     */
    public static void calculScores(ArrayList<Depeche> depeches, String categorie, HashMap<String, Integer> dictionnaire) {
        // for each depeche
        for (Depeche dep : depeches){
            // check if depeche is in the right category
            if (dep.getCategorie().equals(categorie)){
                // increment the score of each word in the depeche
                for (String mot : dep.getMots()){
                    if (dictionnaire.containsKey(mot)){
                        dictionnaire.put(mot, dictionnaire.get(mot) + 1);
                    }
                    
                }
            }
            else {
                // decrement the score of each word in the depeche
                for (String mot : dep.getMots()) {
                    if (dictionnaire.containsKey(mot)){
                        dictionnaire.put(mot, dictionnaire.get(mot) - 1);
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
        if (score < 0){return 0;}
        else if (score < 5){return 1;}
        else if (score < 10) {return 2;}
        else {return 3;}
    }
    

    /**
     * Generates a lexicon for the given category based on the given list of Depeche objects.
     * Writes the result to a file.
     * 
     * @param depeches the list of Depeche objects
     * @param categorie the category to generate the lexicon for
     * @param nomFichier the name of the file to write the result to
     */
    public static void generationLexique(ArrayList<Depeche> depeches, String categorie, String nomFichier) {
        //initialize the lexicon
        HashMap<String, Integer> lexique = initDico(depeches, categorie);
        //calculate the score of the words based on their frequency
        calculScores(depeches, categorie, lexique);

        //assign a weight based on the score
        lexique.replaceAll((k, v) -> v=poidsPourScore(v));

        try {
            //write the result to the file
            FileWriter file = new FileWriter(nomFichier);
            
            //delete lines with weight 0
            lexique.values().removeAll(Collections.singleton(0));

            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //Chargement des dépêches en mémoire
        ArrayList<Depeche> depeches = lectureDepeches("./depeches.txt");
        //creating categories
        ArrayList<Categorie> categories = new ArrayList<>();
        categories.add(new Categorie("ENVIRONNEMENT-SCIENCES"));
        categories.add(new Categorie("CULTURE"));
        categories.add(new Categorie("ECONOMIE"));
        categories.add(new Categorie("POLITIQUE"));
        categories.add(new Categorie("SPORTS"));

        for (Categorie c : categories) {
            //initialize the lexicon files and the categories
            generationLexique(depeches, c.getNom(), c.getNom() + ".txt");
            c.initLexique(c.getNom() + ".txt");
        }
        //resolve the problem
        classementDepeches(depeches, categories, "result.txt");
        System.out.println("classification written in result.txt");
    }


}

