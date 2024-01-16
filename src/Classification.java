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
            //initialize the categories
            c.initLexique(c.getNom() + ".txt");
        }
        //resolve the problem
        classementDepeches(depeches, categories, "result.txt");
        System.out.println("classification written in result.txt");
    }


}

