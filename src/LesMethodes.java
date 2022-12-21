import java.util.Scanner;

public class LesMethodes {
    public static Scanner sc = new Scanner(System.in);
    final static char DAME_NOIR = 9651;
    final static char DAME_BLANC = 9650;
    final static char VIDE = 9636;
    final static char BLANC = 9679;
    final static char NOIR = 9675;

    public static void affichePlateau(char[][] tab) {
        int nblignes = 0;
        int nbColonne = 0;
        for (int nbColonneHaut = 0; nbColonneHaut < tab.length; nbColonneHaut++) { // afficher les nombres en haut de tableaux
            System.out.print("+-"+ nbColonneHaut +"-");
        }
        System.out.println();
        for (int i = 0; i < tab.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print(tab[i][j]);
                System.out.print(" | ");
                if (j == tab.length - 1) {
                    System.out.print(nblignes);
                    nblignes++;
                }

            }
            System.out.println();
            for (int j = 0; j < tab.length; j++) {
                if (i == tab.length - 1) {
                    System.out.print("+-" + nbColonne + "-"); // afficher les nombres de colonnes en bas de tableau
                    nbColonne++;
                } else {
                    System.out.print("+--+");
                }
            }
            System.out.println();
        }
    }

    public static void remplirTableaux(char[][] tab) {
        for (int ligne = 0; ligne < tab.length; ligne++) {
            for (int colonne = 0; colonne < tab[ligne].length; colonne++) {
                if (ligne >= 0 && ligne <= 2) {
                    if (ligne % 2 == 0) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = VIDE;
                        else
                            tab[ligne][colonne] = BLANC;
                    } else if ((ligne % 2 != 0)) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = BLANC;
                        else
                            tab[ligne][colonne] = VIDE;
                    }

                }
                if (ligne == 3 || ligne == 4) {
                    if (ligne % 2 == 0) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = VIDE;
                        else
                            tab[ligne][colonne] = ' ';
                    } else if ((ligne % 2 != 0)) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = ' ';
                        else
                            tab[ligne][colonne] = VIDE;
                    }

                }
                if (ligne >= 5 && ligne <= tab.length) {
                    if (ligne % 2 == 0) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = VIDE;
                        else
                            tab[ligne][colonne] = NOIR;
                    } else if ((ligne % 2 != 0)) {
                        if (colonne % 2 == 0)
                            tab[ligne][colonne] = NOIR;
                        else
                            tab[ligne][colonne] = VIDE;
                    }
                }
            }


        }
    }
    public static boolean verificationSiPion(char[][] tab, int ligne, int colonne) {
        return tab[ligne][colonne] != ' ';
    }

    public static boolean verificationSiPionBlancHautGauche(char[][] tab, int ligne, int colonne) {
        // verification si pion est d'advarsaire
        if (ligne > 0 && colonne > 0) {
            ligne = ligne - 1;
            colonne = colonne - 1;
            if (tab[ligne][colonne] == BLANC) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationSiPionBlancHautDroite(char[][] tab, int ligne, int colonne) {
        // verification si pion est d'advarsaire
        if (ligne > 0 && colonne < 7) {
            ligne = ligne - 1;
            colonne = colonne + 1;
            if (tab[ligne][colonne] == BLANC) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationSiPionNoirBasGauche(char[][] tab, int ligne, int colonne) {
        // verification si pion est d'advarsaire
        if (ligne < 7 && colonne > 0) {
            ligne = ligne + 1;
            colonne = colonne - 1;
            if (tab[ligne][colonne] == NOIR || tab[ligne][colonne] == DAME_NOIR) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationSiPionNoirBasDroite(char[][] tab, int ligne, int colonne) {
        // verification si pion est d'advarsaire
        if (ligne < 7 && colonne < 7) {
            ligne = ligne + 1;
            colonne = colonne + 1;
            if (tab[ligne][colonne] == NOIR || tab[ligne][colonne] == DAME_NOIR) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationDameBlancCôtéHautGaucheDePionNoir(char[][] tab, int ligne, int colonne) {
        if (ligne > 0 && ligne < 7 && colonne > 0 && colonne < 7) {
            ligne = ligne - 1;
            colonne = colonne - 1;
            if (tab[ligne][colonne] == DAME_BLANC) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationDameBlancCôtéHautDroiteDePionNoir(char[][] tab, int ligne, int colonne) {
        if (ligne > 0 && ligne < 7 && colonne > 0 && colonne < 7) {
            ligne = ligne - 1;
            colonne = colonne + 1;
            if (tab[ligne][colonne] == DAME_BLANC) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationDameNoirCôtéBasGaucheDePionBlanc(char[][] tab, int ligne, int colonne) {
        if (ligne > 0 && ligne < 7 && colonne > 0 && colonne < 7) {
            ligne = ligne + 1;
            colonne = colonne - 1;
            if (tab[ligne][colonne] == DAME_NOIR) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationDameNoirCôtéBasDroiteDePionBlanc(char[][] tab, int ligne, int colonne) {
        if (ligne > 7 && colonne < 7) {
            ligne = ligne + 1;
            colonne = colonne + 1;
            if (tab[ligne][colonne] == DAME_NOIR) {
                return true;
            }
        }
        return false;
    }

    public static boolean deplacementPionNoire(char[][] tab, int ligne, int colonne) {
        boolean laPaceDePionChoisi;
        if (tab[ligne][colonne] == DAME_NOIR) {
            deplacementDame(tab, ligne, colonne);
            laPaceDePionChoisi = true;
        } else {
            int laPlaceChoisi;
            boolean côtéHautGauche = (verificationLesPionsHautGauche(tab, ligne, colonne) // si en haut gauche prémier case est vide
                    || ((verificationSiPionBlancHautGauche(tab, ligne, colonne) || verificationDameBlancCôtéHautGaucheDePionNoir(tab, ligne, colonne))   //si c'est un pion blanc ou dame blanc
                    && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne))); // en deuxè lignes le case diagonale est vide

            boolean côtéHautDroite = (verificationLesPionsHautDroite(tab, ligne, colonne) // si en haut droite prémier case est vide
                    || ((verificationSiPionBlancHautDroite(tab, ligne, colonne) || verificationDameBlancCôtéHautDroiteDePionNoir(tab, ligne, colonne))   //si c'est un pion blanc ou dame blanc
                    && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne))); // en deuxième lignes le case diagonale vers droite est vide
            if (verificationSiPion(tab, ligne, colonne) && (côtéHautGauche || côtéHautDroite)) {  // si la pion choisi est vrai et une place en lignes haut est vides
                laPaceDePionChoisi = true;
                // if (colonne > 1 && colonne < 7 && !verificationSiPion(tab, ligne - 1, colonne - 1) && !verificationSiPion(tab, ligne - 1, colonne + 1)) {
                if (côtéHautGauche && côtéHautDroite) {
                    do {
                        System.out.println("saisir 1 pour deplacer le pion vers la gauche et 2 pour vers le droit");
                        laPlaceChoisi = sc.nextInt();
                    } while (laPlaceChoisi < 1 || laPlaceChoisi > 2);
                } else {
                    if (côtéHautDroite) {
                        do {
                            System.out.println("saisir  2 pour deplacer vers le  droite ");
                            laPlaceChoisi = sc.nextInt();
                        } while (laPlaceChoisi != 2);

                    } else {
                        do {
                            System.out.println("saisir 1 pour deplacer à la gauche");
                            laPlaceChoisi = sc.nextInt();
                        } while (laPlaceChoisi != 1);

                    }
                }

                switch (laPlaceChoisi) {
                    case 1:
                        if ((verificationSiPionBlancHautGauche(tab, ligne, colonne) || verificationDameBlancCôtéHautGaucheDePionNoir(tab, ligne, colonne)) && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne)) {
                            // dans le cas si la premiere case haut gauche diagonale est comple la deuxièmes case haut gauche diagonale  est vide
                            if (ligne == 2) {
                                devenirDameNoire(tab, ligne, colonne);
                            } else {
                                ligne = ligne - 2;
                                colonne = colonne - 2;
                                tab[ligne][colonne] = NOIR;
                                enlevéLesPionsJoué(tab, ligne + 1, colonne + 1);//retirer le pion joué
                                enlevéLesPionsJoué(tab, ligne + 2, colonne + 2);
                            }


                        } else {
                            // dans le cas si la premiere case haut gauche diagonale est vide
                            tab[ligne - 1][colonne - 1] = NOIR;
                            enlevéLesPionsJoué(tab, ligne, colonne);

                        }
                        break;
                    case 2:
                        if ((verificationSiPionBlancHautDroite(tab, ligne, colonne) || verificationDameBlancCôtéHautDroiteDePionNoir(tab, ligne, colonne)) && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne)) {
                            // dans le cas si la premiere case haut droite diagonale est comple la deuxièmes case haut droite diagonale  est vide
                            if (ligne == 2) {
                                devenirDameNoire(tab, ligne, colonne);
                            } else {
                                ligne = ligne - 2;
                                colonne = colonne + 2;
                                tab[ligne][colonne] = NOIR;
                                enlevéLesPionsJoué(tab, ligne + 1, colonne - 1); // retirer le pion enimies
                                enlevéLesPionsJoué(tab, ligne + 2, colonne - 2);//retirer le pion joué
                            }
                        } else {
                            // dans le cas si la premiere case haut droite  diagonale est vide
                            tab[ligne - 1][colonne + 1] = NOIR;
                            enlevéLesPionsJoué(tab, ligne, colonne);
                        }
                        break;
                }

            } else { // si les deux place en linge ne sont pas vides
                laPaceDePionChoisi = false;
            }
        }
        return laPaceDePionChoisi;
    }

    public static boolean deplacementPionBlanc(char[][] tab, int ligne, int colonne) {
        boolean laPaceDePionChoisi;
        if (tab[ligne][colonne] == DAME_BLANC) { // dans le cas si le pion choisi est une dameblanc
            deplacementDame(tab, ligne, colonne);
            laPaceDePionChoisi = true;
        } else {
            int laPlaceChoisi;
            boolean côtéBasGauche = (verificationLesPionsBasGauche(tab, ligne, colonne)
                    || ((verificationSiPionNoirBasGauche(tab, ligne, colonne) || verificationDameNoirCôtéBasGaucheDePionBlanc(tab, ligne, colonne))
                    && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)));
            boolean côtéBasDroite = (verificationLesPionsBasDroite(tab, ligne, colonne)
                    || ((verificationSiPionNoirBasDroite(tab, ligne, colonne) || verificationDameNoirCôtéBasDroiteDePionBlanc(tab, ligne, colonne))
                    && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne)));

            if (verificationSiPion(tab, ligne, colonne) && (côtéBasGauche || côtéBasDroite)) {  // si la pion choisi est vrai et une place en lignes haut est vides
                laPaceDePionChoisi = true;
                if (côtéBasGauche && côtéBasDroite) {
                    do {
                        System.out.println("saisir 1 pour deplacer le pion vers la gauche et 2 pour vers le droit");
                        laPlaceChoisi = sc.nextInt();
                    } while (laPlaceChoisi < 1 || laPlaceChoisi > 2);
                } else {
                    if (côtéBasDroite) {
                        do {
                            System.out.println("saisir  2 pour deplacer vers le  droite ");
                            laPlaceChoisi = sc.nextInt();
                        } while (laPlaceChoisi != 2);

                    } else {
                        do {
                            System.out.println("saisir 1 pour deplacer à la gauche");
                            laPlaceChoisi = sc.nextInt();
                        } while (laPlaceChoisi != 1);

                    }
                }
                switch (laPlaceChoisi) {
                    case 1:
                        if ((verificationSiPionNoirBasGauche(tab, ligne, colonne) || verificationDameNoirCôtéBasGaucheDePionBlanc(tab, ligne, colonne))
                                && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)) {
                            // dans le cas si la premiere case bas gauche diagonale est comple la deuxièmes case bas gauche diagonale  est vide
                            if (ligne == 5) {
                                devenirDameBlanc(tab, ligne, colonne);
                            } else {
                                ligne = ligne + 2;
                                colonne = colonne - 2;
                                tab[ligne][colonne] = BLANC;
                                enlevéLesPionsJoué(tab, ligne - 1, colonne + 1);//retirer le pion joué
                                enlevéLesPionsJoué(tab, ligne - 2, colonne + 2);
                            }
                        } else {
                            // dans le cas si la premiere case haut gauche diagonale est vide
                            tab[ligne + 1][colonne - 1] = BLANC;
                            enlevéLesPionsJoué(tab, ligne, colonne);

                        }
                        break;
                    case 2:
                        if (((verificationSiPionNoirBasDroite(tab, ligne, colonne) || verificationDameNoirCôtéBasDroiteDePionBlanc(tab, ligne, colonne))
                                && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne))) {
                            // dans le cas si la premiere case bas droite diagonale est complet  la deuxièmes case haut droite diagonale  est vide
                            if (ligne == 5) {
                                devenirDameBlanc(tab, ligne, colonne);
                            } else {
                                ligne = ligne + 2;
                                colonne = colonne + 2;
                                tab[ligne][colonne] = BLANC;
                                enlevéLesPionsJoué(tab, ligne - 1, colonne - 1); // retirer le pion enimies
                                enlevéLesPionsJoué(tab, ligne - 2, colonne - 2);//retirer le pion joué
                            }
                        } else {
                            // dans le cas si la premiere case bas droite  diagonale est vide
                            tab[ligne + 1][colonne + 1] = BLANC;
                            enlevéLesPionsJoué(tab, ligne, colonne);
                        }
                        break;
                }

            } else { // si les deux place en linge ne sont pas vides
                laPaceDePionChoisi = false;
            }
        }

        return laPaceDePionChoisi;
    }

    public static void enlevéLesPionsJoué(char[][] tab, int ligne, int colonne) {
        tab[ligne][colonne] = ' ';
    }

    public static boolean verificationLesPionsHautGauche(char[][] tab, int ligne, int colonne) { //  vérification la case vers la gauche si elle vide
        if (ligne > 0 && colonne > 0) {
            ligne = ligne - 1;
            colonne = colonne - 1;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationLesPionsHautGaucheDeuxièmeLigne(char[][] tab, int ligne, int colonne) {  // vérification la case de deuxième ligne si elle vide
        if (ligne > 1 && colonne > 1) {
            ligne = ligne - 2;
            colonne = colonne - 2;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }


    public static boolean verificationLesPionsHautDroite(char[][] tab, int ligne, int colonne) { // si la case haut droit est vide
        if (ligne > 0 && colonne < 7) {
            ligne = ligne - 1;
            colonne = colonne + 1;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationLesPionsHautDroiteDeuxièmeLigne(char[][] tab, int ligne, int colonne) {
        if (ligne > 1 && colonne < 6) {
            ligne = ligne - 2;
            colonne = colonne + 2;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }


    public static boolean verificationLesPionsBasGauche(char[][] tab, int ligne, int colonne) {
        if (ligne < 7 && colonne > 0) {
            ligne = ligne + 1;
            colonne = colonne - 1;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationLesPionsBasGaucheDeuxièmeLigne(char[][] tab, int ligne, int colonne) {
        if (ligne < 6 && colonne > 1) {
            ligne = ligne + 2;
            colonne = colonne - 2;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }


    public static boolean verificationLesPionsBasDroite(char[][] tab, int ligne, int colonne) {
        if (ligne < 7 && colonne < 7) {
            ligne = ligne + 1;
            colonne = colonne + 1;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationLesPionsBasDroiteDeuxièmeLigne(char[][] tab, int ligne, int colonne) {
        if (ligne < 6 && colonne < 6) {
            ligne = ligne + 2;
            colonne = colonne + 2;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }

        }
        return false;
    }


    public static char[][] mettreUnPionPourEssaie(char[][] tab, int ligne, int colonne, char pion) { //pour mettre les pion dans le tableau pour verifier les sous programme
        tab[ligne][colonne] = pion;
        return tab;
    }


    public static void devenirDameNoire(char tab[][], int ligne, int colonne) {
        if (ligne == 2 && (verificationSiPionBlancHautDroite(tab, ligne, colonne) && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne))) { //
            ligne = ligne - 2;
            colonne = colonne + 2;
            tab[ligne][colonne] = DAME_NOIR;
            enlevéLesPionsJoué(tab, ligne + 1, colonne - 1); // retirer le pion enemies
            enlevéLesPionsJoué(tab, ligne + 2, colonne - 2);//retirer le pion joué
        }
        if (ligne == 2 && (verificationSiPionBlancHautGauche(tab, ligne, colonne) && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne))) {
            ligne = ligne - 2;
            colonne = colonne - 2;
            tab[ligne][colonne] = DAME_NOIR;
            enlevéLesPionsJoué(tab, ligne + 1, colonne + 1); // retirer le pion enimies
            enlevéLesPionsJoué(tab, ligne + 2, colonne + 2);//retirer le pion joué
        }

    }

    public static void devenirDameBlanc(char[][] tab, int ligne, int colonne) {
        if (ligne == 5 && verificationSiPionNoirBasGauche(tab, ligne, colonne) && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)) {
            ligne = ligne + 2;
            colonne = colonne - 2;
            tab[ligne][colonne] = DAME_BLANC;
            enlevéLesPionsJoué(tab, ligne - 1, colonne + 1); // retirer le pion enimies
            enlevéLesPionsJoué(tab, ligne - 2, colonne + 2);//retirer le pion joué
        }
        if (ligne == 5 && verificationSiPionNoirBasDroite(tab, ligne, colonne) && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne)) {
            ligne = ligne + 2;
            colonne = colonne + 2;
            tab[ligne][colonne] = DAME_BLANC;
            enlevéLesPionsJoué(tab, ligne - 1, colonne - 1); // retirer le pion enimies
            enlevéLesPionsJoué(tab, ligne - 2, colonne - 2);//retirer le pion joué
        }

    }

    /*public static boolean verificationSiDame(char tab[][], int ligne, int colonne) {
        if (tab[ligne][colonne] == DAME_NOIR || tab[ligne][colonne] == DAME_BLANC) {
            return true;
        }
        return false;
    }*/

    public static void deplacementDame(char tab[][], int ligne, int colonne) {
        int choix;
        if (tab[ligne][colonne] == DAME_NOIR) {
            boolean basGaucheN = verificationLesPionsBasGauche(tab, ligne, colonne)
                    || (!verificationSiPionNoirBasGauche(tab, ligne, colonne)
                    && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne));
            boolean basDroiteN = verificationLesPionsBasDroite(tab, ligne, colonne)
                    || (!verificationSiPionNoirBasDroite(tab, ligne, colonne)
                    && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne));
            boolean hautGaucheN = verificationLesPionsHautGauche(tab, ligne, colonne)
                    || (verificationSiPionBlancHautGauche(tab, ligne, colonne)
                    && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne));
            boolean hautDroiteN = verificationLesPionsHautDroite(tab, ligne, colonne)
                    || (verificationSiPionBlancHautDroite(tab, ligne, colonne)
                    && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne));

            if (basGaucheN || basDroiteN || hautDroiteN || hautGaucheN ){

            }
          /*  if(basGauche && basDroite && hautDroite&& hautGauche){
                System.out.println("1: c'est pour déplacer en bas gauche");
                System.out.println("2: c'est pour déplacer en bas droit");
                System.out.println("3: c'est pour déplacer en haut gauche");
                System.out.println("4: c'est pour déplacer en haut droit");
            }*/

            if (verificationLesPionsBasGauche(tab, ligne, colonne)
                    || (!verificationSiPionNoirBasGauche(tab, ligne, colonne)
                    && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne))) {
                System.out.println("1: c'est pour déplacer en bas gauche");
            }
            if (verificationLesPionsBasDroite(tab, ligne, colonne)
                    || (!verificationSiPionNoirBasDroite(tab, ligne, colonne)
                    && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne))) {
                System.out.println("2: c'est pour déplacer en bas droit");
            }
            if (verificationLesPionsHautGauche(tab, ligne, colonne)
                    || (verificationSiPionBlancHautGauche(tab, ligne, colonne)
                    && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne))) {
                System.out.println("3: c'est pour déplacer en haut gauche");
            }
            if (verificationLesPionsHautDroite(tab, ligne, colonne)
                    || (verificationSiPionBlancHautDroite(tab, ligne, colonne)
                    && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne))) {
                System.out.println("4: c'est pour déplacer en haut droit");
            }
        } else {
            if (verificationLesPionsBasGauche(tab, ligne, colonne)
                    || (verificationSiPionNoirBasGauche(tab, ligne, colonne)
                    && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne))) {
                System.out.println("1: c'est pour déplacer en bas gauche");
            }
            if (verificationLesPionsBasDroite(tab, ligne, colonne)
                    || (verificationSiPionNoirBasDroite(tab, ligne, colonne)
                    && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne))) {
                System.out.println("2: c'est pour déplacer en bas droit");
            }
            if (verificationLesPionsHautGauche(tab, ligne, colonne)
                    || (!verificationSiPionBlancHautGauche(tab, ligne, colonne)
                    && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne))) {
                System.out.println("3: c'est pour déplacer en haut gauche");
            }
            if (verificationLesPionsHautDroite(tab, ligne, colonne)
                    || (!verificationSiPionBlancHautDroite(tab, ligne, colonne)
                    && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne))) {
                System.out.println("4: c'est pour déplacer en haut droit");
            }
        }

        char choitDame = tab[ligne][colonne];
        choix = sc.nextInt();  // un bague ici dans le cas si une dame ne peut pas bouger
        switch (choix) {
            case 1: {
                if (!verificationLesPionsBasGauche(tab, ligne, colonne)) {
                    if (verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)) {
                        ligne = ligne + 2;
                        colonne = colonne - 2;
                        tab[ligne][colonne] = choitDame;
                        enlevéLesPionsJoué(tab, ligne - 1, colonne + 1); // retirer le pion advarsaire
                        enlevéLesPionsJoué(tab, ligne - 2, colonne + 2); // retirer le pion joué
                    }
                } else {
                    if (ligne < 7 && !verificationSiPion(tab, ligne + 1, colonne - 1)) {
                        tab[ligne + 1][colonne - 1] = choitDame;
                        enlevéLesPionsJoué(tab, ligne, colonne);
                    }
                }
                break;
            }

            case 2: {
                if (!verificationLesPionsBasDroite(tab, ligne, colonne)) {
                    ligne = ligne + 2;
                    colonne = colonne + 2;
                    tab[ligne][colonne] = choitDame;
                    enlevéLesPionsJoué(tab, ligne - 1, colonne - 1); // retirer le pion advarsaire
                    enlevéLesPionsJoué(tab, ligne - 2, colonne - 2); // retirer le pion joué
                } else {

                    tab[ligne + 1][colonne + 1] = choitDame;
                    enlevéLesPionsJoué(tab, ligne, colonne); // retirer le pion joué
                }
                break;
            }
            case 3: {
                if (!verificationLesPionsHautGauche(tab, ligne, colonne)) {
                    ligne = ligne - 2;
                    colonne = colonne - 2;
                    tab[ligne][colonne] = choitDame;
                    enlevéLesPionsJoué(tab, ligne + 1, colonne + 1); // retirer le pion advarsaire
                    enlevéLesPionsJoué(tab, ligne + 2, colonne + 2); // retirer le pion joué
                } else {
                    if (verificationLesPionsHautGauche(tab, ligne, colonne)) {
                        tab[ligne - 1][colonne - 1] = choitDame;
                        enlevéLesPionsJoué(tab, ligne, colonne); // retirer le pion joué
                    }
                }
                break;
            }
            case 4: {
                if (!verificationLesPionsHautDroite(tab, ligne, colonne)) {
                    ligne = ligne - 2;
                    colonne = colonne + 2;
                    tab[ligne][colonne] = choitDame;
                    enlevéLesPionsJoué(tab, ligne + 1, colonne - 1);// retirer le pion advarsaire
                    enlevéLesPionsJoué(tab, ligne + 2, colonne - 2);
                } else {
                    if (verificationLesPionsHautDroite(tab, ligne, colonne)) {
                        tab[ligne - 1][colonne + 1] = choitDame;
                        enlevéLesPionsJoué(tab, ligne, colonne); // retirer le pion joué
                    }
                }
                break;
            }
        }

    }
    public static void main(String[] args) {
        char c = 10105;
        char[][] tab = new char[8][8];
        remplirTableaux(tab);
        mettreUnPionPourEssaie(tab, 5, 2, BLANC);
        mettreUnPionPourEssaie(tab, 0, 1, ' ');
        mettreUnPionPourEssaie(tab, 1, 4, ' ');
        mettreUnPionPourEssaie(tab, 3, 6, ' ');
        mettreUnPionPourEssaie(tab, 2, 1, ' ');
        mettreUnPionPourEssaie(tab, 3, 2, NOIR);
        mettreUnPionPourEssaie(tab, 4, 3, DAME_BLANC);
        affichePlateau(tab);
        devenirDameNoire(tab, 2, 3);
        affichePlateau(tab);
    }
}