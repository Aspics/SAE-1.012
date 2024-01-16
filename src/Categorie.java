import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Categorie {

    private String nom; // le nom de la catégorie p.ex : sport, politique,...
    private ArrayList<PaireChaineEntier> lexique; //le lexique de la catégorie

    // constructeur
    public Categorie(String nom) {
        this.nom = nom;
    }


    public String getNom() {
        return nom;
    }


    public  ArrayList<PaireChaineEntier> getLexique() {
        return lexique;
    }


    // initialisation du lexique de la catégorie à partir du contenu d'un fichier texte
   
    /**
     * Initializes the lexique by reading the contents of a file.
     * 
     * @param nomFichier the name of the file to read from
     */
    public void initLexique(String nomFichier) {

        ArrayList<PaireChaineEntier> lexique = new ArrayList<>();

        try {
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);
            String ligne = "";

            // skip the lines without sentences then read them if they are in the category
            while (scanner.hasNextLine()) {
                
                while (scanner.hasNextLine() && !ligne.equals("")) {
                    ligne = scanner.nextLine();
                    if (!ligne.equals("")) {
                        String[] res = ligne.split(":");
                        lexique.add(new PaireChaineEntier(res[0], Integer.valueOf(res[1])));
                    }
                }
                
            }
            scanner.close();

            this.lexique = lexique;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        
        /**
         * Initializes the lexique by reading sentences from a file and adding unique words to the lexique.
         * The lexique is stored as an ArrayList of PaireChaineEntier objects.
         * 
         * @param nomFichier The name of the file to read sentences from.
         */
        public void initLexiqueModif(String nomFichier) {
            // Warning : Changed lectureDepeches  in Classification.java from private to public to be able to use it in Categorie.java

            // initialisation du lexique de la catégorie à partir du contenu d'un fichier texte


            // lecture du fichier d'entrée
            ArrayList<Depeche> depeche = Classification.lectureDepeches(nomFichier);
            
            // create an ArrayList of all words in depeche
            ArrayList<PaireChaineEntier> lexique = new ArrayList<>();
            
            // add all words from the category to the lexique
            lexique.addAll(Classification.initDico(depeche, nom));
            
            
            this.lexique = lexique;
            
        }

    //calcul du score d'une dépêche pour la catégorie
    /**
     * Calculates the score of a depeche for the category.
     * 
     * @param d The depeche to calculate the score for.
     * @return The score of the depeche for the category.
     */
    public int score(Depeche d) {
        int score = 0;
        for (String word : d.getMots()) {
            score += UtilitairePaireChaineEntier.entierPourChaine(lexique, word);
        }
        return score;
    }


}
