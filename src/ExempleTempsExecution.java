import java.util.Scanner;

public class ExempleTempsExecution {
    public static void main(String[] args) {

        Scanner lecteur = new Scanner((System.in));
        System.out.println("entrez votre nom :");
        long startTime = System.currentTimeMillis();
        lecteur.nextLine();
        lecteur.close();
        long endTime = System.currentTimeMillis();
        System.out.println("votre saisie a été réalisée en : " + (endTime-startTime) + "ms");
    }
}
