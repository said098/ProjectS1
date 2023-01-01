import java.util.Scanner;

public class Lanceur {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] tab = new char[8][8];

        String joueur1, joueur2;
        int ligne, colonne;

        boolean reponseFinal = true;
        boolean reponceFinalJ1;
        boolean reponceFinalJ2;

        System.out.println("Saisir le nom de joueurUn");
        joueur1 = scanner.nextLine();
        System.out.println("Saisir le nom de joueurDeux");
        joueur2 = scanner.nextLine();

        Methodes.remplirTableaux(tab);

        Methodes.mettreUnPionPourEssaie(tab,7,0,Methodes.DAME_NOIR);
        Methodes.affichePlateau(tab);

        while (reponseFinal) {
            System.out.println("Bonne chance " + joueur1 + " Vous avec les Pions Noirs");
            do {
                do {
                    System.out.print("choisi la ligne de pion pour déplacer le pion Noir : ");
                    ligne = scanner.nextInt();
                    System.out.print("choisi la colone de pion pour déplacer le pion Noir : ");
                    colonne = scanner.nextInt();
                    if (colonne < 0 || colonne > 7 || ligne < 0 || ligne > 7 || tab[ligne][colonne] == Methodes.BLANC || tab[ligne][colonne] == Methodes.DAME_BLANC) {
                        System.out.println("la position des pions est entre 0 et 7 enclu  chois la poisition de pion noir");
                    }
                } while (colonne < 0 || colonne > 7 || ligne < 0 || ligne > 7 || tab[ligne][colonne] == Methodes.BLANC || tab[ligne][colonne] == Methodes.DAME_BLANC);
                reponceFinalJ1 = Methodes.deplacementPionNoire(tab, ligne, colonne);

                if (!reponceFinalJ1) {
                    System.out.println("choisi un autre pion tu ne peut déplacer cette pion");
                }
            } while (!reponceFinalJ1);

            Methodes.affichePlateau(tab);

            if (Methodes.pionNoirGange(tab) || Methodes.siLesPionsBlancheSontBlouqé(tab)) { // la méthode si le joueur1 a gangé dans le cas s'il n'y pas des pions blans ou ils sont tous blouqé
                System.out.println(joueur1 + " a gangné ");
                if (Methodes.siLesPionsBlancheSontBlouqé(tab)) {
                    System.out.println(joueur2 + " vous avez perdu car tous votre  pions sont blouqé");
                }
                return;
            }
            System.out.println("Bonne chance " + joueur2 + " Vous avec les Pions Blanc");
            do {
                do {
                    System.out.print("choisi la ligne de pion pour déplacer le pion Blanc : ");
                    ligne = scanner.nextInt();
                    System.out.print("choisi la colone de pion pour déplacer le pion Blanc : ");
                    colonne = scanner.nextInt();
                    if ((colonne < 0) || (colonne > 7) || (ligne < 0) || (ligne > 7) || (tab[ligne][colonne] == Methodes.NOIR) || (tab[ligne][colonne] == Methodes.DAME_NOIR)) {
                        System.out.println("la position des pions est entre 0 et 7 enclu ou chois la poisition de pion blanc");
                    }
                } while (colonne < 0 || colonne > 7 || ligne < 0 || ligne > 7 || tab[ligne][colonne] == Methodes.NOIR || tab[ligne][colonne] == Methodes.DAME_NOIR );
                reponceFinalJ2 = Methodes.deplacementPionBlanc(tab, ligne, colonne);
                if (!reponceFinalJ2) {
                    System.out.println("choisi un autre pion tu ne peut déplacer cette pion");
                }
            } while (!reponceFinalJ2);
            Methodes.affichePlateau(tab);
            if (Methodes.pionBlancGange(tab) || Methodes.siLesPionsNoirSontBloqué(tab)) { // la méthode si le joueur2 a gangé dans le cas s'il n'y plus les pions noirs ou ils sont tous blouqé
                System.out.println(joueur2 + " a gangné ");
                if (Methodes.siLesPionsNoirSontBloqué(tab)) {
                    System.out.println(joueur1 + " Vous avez perdu car tous votre pions sont  blouqé");
                }
                return;
            }

            if (Methodes.tabComptePourNbFoisPionBlancJouéSansPrise[0] >= 20 || Methodes.tabComptPourNbFoisPionNoirJouéSansPrise[0] >= 20) {
                System.out.println("le jeu est égale ");
                return;
            }
        }

    }
}
