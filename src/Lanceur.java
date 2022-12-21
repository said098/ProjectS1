import java.util.Scanner;

public class Lanceur {
    public static void main(String[] args) {
        char dameNoir = 9651;
        char dameBlanc = 9650;
        char pionBlanc = 9679;
        char pionNoir = 9675;
        char[][] tab = new char[8][8];
        String joueur1, joueur2;
        int nbTour = 0;
        int ligne, colonne;
        Scanner scanner = new Scanner(System.in);
        LesMethodes.mettreUnPionPourEssaie(tab, 5, 4,pionBlanc );
        LesMethodes.mettreUnPionPourEssaie(tab, 7, 6, ' ');
        LesMethodes.mettreUnPionPourEssaie(tab, 2, 3,pionNoir );
        LesMethodes.mettreUnPionPourEssaie(tab, 0, 1, ' ');
        System.out.println("Saisir le nom de joueurUn");
        joueur1 = scanner.nextLine();
        System.out.println("Saisir le nom de joueurDeux");
        joueur2 = scanner.nextLine();
        LesMethodes.remplirTableaux(tab);
        LesMethodes.affichePlateau(tab);
        boolean reponceFinalJ1 ;
        boolean reponceFinalJ2 ;

        while (nbTour < 20) {
            System.out.println("Bonne chance " + joueur1 + " Vous avec les pions noirs");
            do {
                do {
                    System.out.print("choisi la ligne de pion pour déplacer le pion Noir: ");
                    ligne = scanner.nextInt();
                    System.out.print("choisi la colone de pion pour déplacer le pion Noir: ");
                    colonne = scanner.nextInt();
                    if (colonne < 0 || colonne > 7 || ligne < 0 || ligne > 7 || tab[ligne][colonne] == pionBlanc || tab[ligne][colonne] == dameBlanc)
                    {
                        System.out.println("la position des pions est entre 0 et 7 enclu  chois la poisition de pion noir");
                    }
                } while (colonne < 0 || colonne > 7 || ligne < 0 || ligne > 7 || tab[ligne][colonne] == pionBlanc || tab[ligne][colonne] == dameBlanc);
                reponceFinalJ1 = LesMethodes.deplacementPionNoire(tab, ligne, colonne);
                if (!reponceFinalJ1) {
                    System.out.println("choisi un autre pion tu ne peut déplacer cette pion");
                }
            } while (!reponceFinalJ1);
            LesMethodes.affichePlateau(tab);

            System.out.println("Bonne chance " + joueur2 + " Vous avec les pions blanc");
            do {
                do {
                    System.out.print("choisi la ligne de pion pour déplacer le pion Blanc: ");
                    ligne = scanner.nextInt();
                    System.out.print("choisi la colone de pion pour déplacer le pion Blanc: ");
                    colonne = scanner.nextInt();
                    if (colonne < 0 || colonne > 7 || ligne < 0 || ligne > 7 || tab[ligne][colonne] == pionNoir || tab[ligne][colonne] == dameNoir) {
                        System.out.println("la position des pions est entre 0 et 7 enclu ou chois la poisition de pion blanc");
                    }
                } while (colonne < 0 || colonne > 7 || ligne < 0 || ligne > 7 || tab[ligne][colonne] == pionNoir || tab[ligne][colonne] == dameNoir);
                reponceFinalJ2 = LesMethodes.deplacementPionBlanc(tab, ligne, colonne);
                if (!reponceFinalJ2) {
                    System.out.println("choisi un autre pion tu ne peut déplacer cette pion");
                }
            } while (!reponceFinalJ2);
            LesMethodes.affichePlateau(tab);
            System.out.println("  Tour " + nbTour);
            nbTour++;
        }


    }
}

