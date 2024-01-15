import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    
    private String nom; // le nom de la catégorie p.ex : sport, politique,...
    private ArrayList<PaireChaineEntier> lexique; //le lexique de la catégorie


    public void initLexique(String nomFichier) {

        // Warning : Really not sure if it'll work
        // if not try the second initLexique function it's wayyyy better

        ArrayList<PaireChaineEntier> lexique = new ArrayList<>();

        try {
                // lecture du fichier d'entrée
                FileInputStream file = new FileInputStream(nomFichier);
                Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                ligne = scanner.nextLine();
                ligne = scanner.nextLine();
                ligne = scanner.nextLine();
                String lignes = ligne.substring(3);
                while (scanner.hasNextLine() && !ligne.equals("")) {
                    ligne = scanner.nextLine();
                    if (!ligne.equals("")) {
                        lignes = lignes + ' ' + ligne;
                    }
                }

                //if mot not in lexique add it
                if (lexique.contains(lignes.split(" "), 0) {
                    
                }
                lexique.add(new PaireChaineEntier(lignes, 0));
                

            }
            scanner.close();
            this.lexique = lexique;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



        // dumbass version for all

    
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
            
            // this part is needed in order to use the initDico function since it takes a category as a parameter
            // create an ArrayList of all categories in depeche
            ArrayList<String> categories = new ArrayList<>();
            for (int i = 0; i < depeche.size(); i++) {
                // check if the categorie is in the list
                if (!categories.contains(depeche.get(i).getCategorie()))  {
                    // add the categorie to the list
                    categories.add(depeche.get(i).getCategorie());
                }
            }
            
            // create an ArrayList of all words in depeche
            ArrayList<PaireChaineEntier> lexique = new ArrayList<>();
            // for each category
            for (String cat : categories) {
                // add all words from the category to the lexique
                lexique.addAll(Classification.initDico(depeche, cat));
            }

            // the lexique contains several time the sames words provided they were in used different categories
            // all check for knowing which category the chains does belong to should not be stopped with the first occurence of the word
            
            this.lexique = lexique;
            
        }


}
