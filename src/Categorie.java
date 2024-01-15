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
            
            //create an ArrayList of all categories in depeche
            ArrayList<String> categories = new ArrayList<>();
            for (int i = 0; i < depeche.size(); i++) {
                //Check if the categorie is in the list
                if (!categories.contains(depeche.get(i).getCategorie()))  {
                    //Add the categorie to the list
                    categories.add(depeche.get(i).getCategorie());
                }
            }

            ArrayList<PaireChaineEntier> lexique = new ArrayList<>();
            for (String cat : categories) {
                lexique.addAll(Classification.initDico(depeche, cat));
            }

            // the lexique contains several time the sames words proveded they were in used different categories
            // all check for knowing which category the chains does belong to should not be stopped with the first occurence of the word
            
            this.lexique = lexique;
            
        }

    //calcul du score d'une dépêche pour la catégorie
    public int score(Depeche d) {
        return 0;
    }


}
