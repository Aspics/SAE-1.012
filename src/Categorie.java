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
    public void initLexique(String nomFichier) {

        // Warning : Really not sure if it'll work
        // if not try the second initLexique function it's wayyyy better

        try {
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);
            ArrayList<PaireChaineEntier> lexique = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] tabchaine = ligne.split(" ");
                PaireChaineEntier paire = new PaireChaineEntier(tabchaine[0], Integer.parseInt(tabchaine[1]));
                lexique.add(paire);
            }
            scanner.close();
            this.lexique = lexique;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


        
        /**
         * Initializes the lexique of the category based on the content of a text file.
         * 
         * @param nomFichier The name of the input file.
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
    public int score(Depeche d) {
        return 0;
    }


}
