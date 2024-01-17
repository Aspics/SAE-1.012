import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Categorie {

    private final String nom; // Le nom de la catégorie p. Ex : sport, politique,...
    private HashMap<String, Integer> lexique; //le lexique de la catégorie

    // constructeur
    public Categorie(String nom) {
        this.nom = nom;
    }


    public String getNom() {
        return nom;
    }


    public  HashMap<String, Integer> getLexique() {
        return lexique;
    }
   
    /**
     * Initializes the lexique by reading the contents of a file.
     * 
     * @param nomFichier the name of the file to read from
     */
    public void initLexique(String nomFichier) {

        HashMap<String, Integer> lexique = new HashMap<>();

        try {
            // lecture du fichier d'entrée
            FileInputStream file = new FileInputStream(nomFichier);
            Scanner scanner = new Scanner(file);
            String ligne;

            // skip the lines without sentences then read them if they are in the category
            while (scanner.hasNextLine()) {
                ligne = scanner.nextLine();
                if (!ligne.isEmpty()) {
                    String[] res = ligne.split(":");
                    // add the word and it's score to the lexique
                    lexique.put(res[0], Integer.parseInt(res[1]));
                }
                
            }
            scanner.close();

            this.lexique = lexique;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the score of a depeche for the category.
     * 
     * @param d The depeche to calculate the score for.
     * @return The score of the depeche for the category.
     */
    public int score(Depeche d) {
        int score = 0;
        for (String word : d.getMots()) {
            if (lexique.containsKey(word)) {
                score += lexique.get(word);
            }
        }
        return score;
    }
}
