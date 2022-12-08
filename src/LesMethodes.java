import java.util.Scanner;

public class LesMethodes {
    public static Scanner sc = new Scanner(System.in);
    public static void affichePlateau(char[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            System.out.print("|");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j]);
                System.out.print("|");
            }
            System.out.println();

        }
    }

    public static void remplirTableaux(char[][] tab) {
        for (int ligne = 0; ligne < tab.length; ligne++) {
            for (int colonne = 0; colonne < tab[ligne].length; colonne++) {
                if (ligne >= 0 && ligne <= 3) {
                    if (ligne % 2 == 0) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = ' ';
                        else
                            tab[ligne][colonne] = 'B';
                    } else if ((ligne % 2 != 0)) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = 'B';
                        else
                            tab[ligne][colonne] = ' ';
                    }

                }
                if (ligne >= 6 && ligne <= tab.length) {
                    if (ligne % 2 == 0) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = ' ';
                        else
                            tab[ligne][colonne] = 'B';
                    } else if ((ligne % 2 != 0)) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = 'B';
                        else
                            tab[ligne][colonne] = ' ';
                    }


                }

            }


        }
        System.out.println();
    }

    public static boolean verificationSiPion(char[][] tab,int x,int y ) {
        return (tab[x][y] != ' ');
    }

    public static int positionOuLePionDeSeDeplace(char[][] tab,int x,int y){
        if((verificationSiPion(tab,x+1,y+1)==true) || (verificationSiPion(tab,x-1,y+1)==true) ) {

        }
        return 9;
    }
    public static void deplacementPion(char [][] tab){
        int choix;
        System.out.println("Quelle pion souhaitez vous déplacer ?");
        choix = sc.nextInt();
        //if (verificationSiPion(tab,))



    }
    public static void main(String[] args) {
        char[][] tab = new char[10][10];
        remplirTableaux(tab);
        affichePlateau(tab);
        // afficherDamier(10,10);

        System.out.println(verificationSiPion(tab,7,7));


    }
}
