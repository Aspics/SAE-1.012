import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Classification {


    private static ArrayList<Depeche> lectureDepeches(String nomFichier) {
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
                        resultat.add(new PaireChaineEntier(mot, 1));
                    } else {
                        //If yes, increment its score
                        for (PaireChaineEntier paire : resultat) {
                            if (paire.getChaine().equals(mot)) {
                                paire.setEntier(paire.getEntier() + 1);
                                break;
                            }
                        }
                    }
                }
            }
        }


        return resultat;

    }

    public static void calculScores(ArrayList<Depeche> depeches, String categorie, ArrayList<PaireChaineEntier> dictionnaire) {
    }

    public static int poidsPourScore(int score) {
        return 0;
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

