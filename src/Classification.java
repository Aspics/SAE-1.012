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
     *  Calculate for each categorie of categories the score of each depeche of depeches
     *  Writes the classification results to a file.
     *
     * @param depeches     The list of depeche to calculate the score of.
     * @param categories   The list of categories used.
     * @param nomFichier   The name of the file to write the results to.
     */
    public static void classementDepeches(ArrayList<Depeche> depeches, ArrayList<Categorie> categories, String nomFichier) {
        long startTime = System.currentTimeMillis();
        HashMap<String, Integer> justes = new HashMap<>();
        // init the dictionary of correct classifications per category
        for (Categorie c : categories) {
            justes.put(c.getNom(), 0);
        }
        try {
            
            // init the file to write to
            FileWriter file = new FileWriter(nomFichier);
            // for each depeche
            for (Depeche d : depeches) {
                // calculate the score for each categorie
                HashMap<String, Integer> scores = new HashMap<>();

                for (Categorie c : categories) {
                    scores.put(c.getNom(), c.score(d));
                }
                // write the results to the file
                String result = Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getKey();
                file.write(d.getId() + ":" + result + "\n");

                //update the counter of right results
                if (result.equals(d.getCategorie())) {
                    justes.put(d.getCategorie(), justes.get(d.getCategorie())+1);
                }
            }
            file.write("----------------------STATS----------------------\n");
            //write the percentages of correct classifications
            int moy = 0;
            for (String c : justes.keySet()) {
                moy += justes.get(c);
                file.write(c + ":\t\t\t\t\t\t\t\t" + justes.get(c) + "%\n");
            }
            //write the average
            file.write("MOYENNE:\t\t\t\t\t\t\t\t" + moy/5 + "%");
            file.close();
            long endTime = System.currentTimeMillis();
            System.out.println("function classementDepeches: " + (endTime - startTime) + "ms");
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
        long startTime = System.currentTimeMillis();
        HashMap<String, Integer> dico = new HashMap<>();

        for (Depeche d : depeches) {
            // check if depeche is in the right category
            if (d.getCategorie().equals(categorie)) {
                for (String mot : d.getMots()) {
                    // check if mot is already in the list
                    if (dico.containsKey(mot)) {
                        //if yes, increment its score
                        dico.put(mot, dico.get(mot) + 1);
                    } else {
                        // if not, add it
                        dico.put(mot, 1);
                    }
                }
            } else {
                //if not, decrement the score of each word in the depeche
                for (String mot : d.getMots()) {
                    if (dico.containsKey(mot)) {
                        dico.put(mot, dico.get(mot) - 1);
                    } else {
                        dico.put(mot, 0);
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("function initDico: " + (endTime - startTime) + "ms");
        return dico;
    }


    /**
     * Calculates the weight based on the given score.
     * 
     * @param score the score to calculate the weight for
     * @return the weight corresponding to the score
     */
    public static int poidsPourScore(int score) {
        if (score <= 0){return 0;}
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
        long startTime = System.currentTimeMillis();
        //assign a weight based on the score
        lexique.replaceAll((k, v) -> poidsPourScore(v));

        try {
            //write the result to the file
            FileWriter file = new FileWriter(nomFichier);

            for (String s : lexique.keySet()) {
                //write only the lines with weight more than 0
                if (lexique.get(s) != 0) {
                    file.write(s + ":" + lexique.get(s) + "\n");
                }
            }

            file.close();
            long endTime = System.currentTimeMillis();
            System.out.println("function generationLexique: " + (endTime - startTime) + "ms");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        //Chargement des dépêches en mémoire
        ArrayList<Depeche> depeches = lectureDepeches("./depeches.txt");
        long startTime = System.currentTimeMillis();

        //creating categories
        ArrayList<Categorie> categories = new ArrayList<>();
        categories.add(new Categorie("ENVIRONNEMENT-SCIENCES"));
        categories.add(new Categorie("CULTURE"));
        categories.add(new Categorie("ECONOMIE"));
        categories.add(new Categorie("POLITIQUE"));
        categories.add(new Categorie("SPORTS"));

        for (Categorie c : categories) {
            //initialize the lexicon files and the categories
            System.out.println("-------------------Cartegoy " + c.getNom() + "-------------------");
            generationLexique(depeches, c.getNom(), c.getNom() + ".txt");
            c.initLexique(c.getNom() + ".txt");
        }
        //resolve the problem
        System.out.println("--------------------------------------");
        classementDepeches(depeches, categories, "result.txt");
        System.out.println("classification written in result.txt");
        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + "ms");
    }


}

