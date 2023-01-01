import java.util.Scanner;

public class Methodes {
    public static Scanner sc = new Scanner(System.in);
    final static char DAME_NOIR = 9651;
    final static char DAME_BLANC = 9650;
    final static char VIDE = 9636;
    final static char BLANC = 9679;
    final static char NOIR = 9675;

    static int[] tabComptPourNbFoisPionNoirJouéSansPrise = new int[1];
    static int[] tabComptePourNbFoisPionBlancJouéSansPrise = new int[1];


    public static void affichePlateau(char[][] tab) {
        int nblignes = 0;
        int nbColonne = 0;
        for (int j = 0; j < tab.length; j++) {
            System.out.print("+-" + j + "-");
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
                    System.out.print("+-" + nbColonne + "-");
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
        System.out.println();
    }


    public static boolean verificationSiPion(char[][] tab, int ligne, int colonne) {
        return (tab[ligne][colonne] != ' ');
    }

    public static void enlevéLesPionsJoué(char[][] tab, int ligne, int colonne) {
        tab[ligne][colonne] = ' ';
    }


    public static boolean verificationPionBlancheCôtéGaucheNoir(char[][] tab, int ligne, int colonne) {
        // verification le pion blanc côté gauche de pion noir
        if (ligne > 0 && colonne > 0) {
            ligne = ligne - 1;
            colonne = colonne - 1;
            if (tab[ligne][colonne] == BLANC || tab[ligne][colonne] == DAME_BLANC) {
                return true;
            }
        }
        return false;
    }

    public static boolean verificationPionBlancheCôtéDroitNoir(char[][] tab, int ligne, int colonne) {
        //  // verification le pion blanc côté droit de pion noir
        if (ligne > 0 && colonne < 7) {
            ligne = ligne - 1;
            colonne = colonne + 1;
            if (tab[ligne][colonne] == BLANC || tab[ligne][colonne] == DAME_BLANC) {
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


    public static void devenirDameNoire(char tab[][], int ligne, int colonne) {
        if (ligne == 2 && (verificationPionBlancheCôtéDroitNoir(tab, ligne, colonne) && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne))) {
            ligne = ligne - 2;
            colonne = colonne + 2;
            tab[ligne][colonne] = DAME_NOIR;
            enlevéLesPionsJoué(tab, ligne + 1, colonne - 1); // retirer le pion enimies
            enlevéLesPionsJoué(tab, ligne + 2, colonne - 2);//retirer le pion joué
        }
        if (ligne == 2 && (verificationPionBlancheCôtéGaucheNoir(tab, ligne, colonne) && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne))) {
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

    public static boolean deplacementDame(char tab[][], int ligne, int colonne) {
        int choix;

        if (tab[ligne][colonne] == DAME_NOIR) {
            boolean basGaucheDN = verificationLesPionsBasGauche(tab, ligne, colonne)
                    || (!verificationSiPionNoirBasGauche(tab, ligne, colonne)
                    && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne));
            boolean basDroiteDN = verificationLesPionsBasDroite(tab, ligne, colonne)
                    || (!verificationSiPionNoirBasDroite(tab, ligne, colonne)
                    && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne));
            boolean hautGaucheDN = verificationLesPionsHautGauche(tab, ligne, colonne)
                    || (verificationPionBlancheCôtéGaucheNoir(tab, ligne, colonne)
                    && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne));
            boolean hautDroiteDN = verificationLesPionsHautDroite(tab, ligne, colonne)
                    || (verificationPionBlancheCôtéDroitNoir(tab, ligne, colonne)
                    && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne));

            if (basGaucheDN || basDroiteDN || hautDroiteDN || hautGaucheDN) {
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
                        || (verificationPionBlancheCôtéGaucheNoir(tab, ligne, colonne)
                        && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne))) {
                    System.out.println("3: c'est pour déplacer en haut gauche");
                }
                if (verificationLesPionsHautDroite(tab, ligne, colonne)
                        || (verificationPionBlancheCôtéDroitNoir(tab, ligne, colonne)
                        && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne))) {
                    System.out.println("4: c'est pour déplacer en haut droit");
                }
                choix = sc.nextInt();

            } else {
                // System.out.println("tu ne peux pas déplaceer cette dame choisi un autre pion"); // dans le si tu ne peux pas déplacer le dame
                return false;
            }


        } else {

            boolean basGaucheDB = (verificationLesPionsBasGauche(tab, ligne, colonne)
                    || (verificationSiPionNoirBasGauche(tab, ligne, colonne)
                    && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)));
            boolean basDroiteDB = (verificationLesPionsBasDroite(tab, ligne, colonne)
                    || (verificationSiPionNoirBasDroite(tab, ligne, colonne)
                    && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne)));
            boolean hautGaucheDB = (verificationLesPionsHautGauche(tab, ligne, colonne)
                    || (!verificationPionBlancheCôtéGaucheNoir(tab, ligne, colonne)
                    && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne)));
            boolean hautDroiteDB = (verificationLesPionsHautDroite(tab, ligne, colonne)
                    || (!verificationPionBlancheCôtéDroitNoir(tab, ligne, colonne)
                    && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne)));

            if (basGaucheDB || basDroiteDB || hautDroiteDB || hautGaucheDB) {
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
                        || (!verificationPionBlancheCôtéGaucheNoir(tab, ligne, colonne)
                        && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne))) {
                    System.out.println("3: c'est pour déplacer en haut gauche");
                }
                if (verificationLesPionsHautDroite(tab, ligne, colonne)
                        || (!verificationPionBlancheCôtéDroitNoir(tab, ligne, colonne)
                        && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne))) {
                    System.out.println("4: c'est pour déplacer en haut droit");
                }
                choix = sc.nextInt();
            } else {
                //  System.out.println("tu ne peux pas déplacer cette dame choisi un autre pion"); // dans le si tu ne peux pas déplacer le dame
                return false;
            }

        }
        char choixDame = tab[ligne][colonne];

        switch (choix) {
            case 1: {
                if (!verificationLesPionsBasGauche(tab, ligne, colonne)) {
                    if (verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)) {
                        ligne = ligne + 2;
                        colonne = colonne - 2;
                        tab[ligne][colonne] = choixDame;
                        enlevéLesPionsJoué(tab, ligne - 1, colonne + 1); // retirer le pion advarsaire
                        enlevéLesPionsJoué(tab, ligne - 2, colonne + 2); // retirer le pion joué
                        if (choixDame == DAME_NOIR) {
                            nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, -1);
                            System.out.println("si dameNoire a pris le pion advarsair " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                        }
                        if (choixDame == DAME_BLANC) {
                            nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, -1);
                            System.out.println("si dame blanc a pris le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                        }

                    }
                } else {
                    if (ligne < 7 && !verificationSiPion(tab, ligne + 1, colonne - 1)) {
                        tab[ligne + 1][colonne - 1] = choixDame;
                        enlevéLesPionsJoué(tab, ligne, colonne);
                        if (choixDame == DAME_NOIR) {
                            nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                            System.out.println("si dameNoire ne pas prise le pion advarsair " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                        }
                        if (choixDame == DAME_BLANC) {
                            nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                            System.out.println("si dame blanc ne pas prise le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                        }
                    }
                }
                break;
            }
            case 2: {
                if (!verificationLesPionsBasDroite(tab, ligne, colonne)) {
                    ligne = ligne + 2;
                    colonne = colonne + 2;
                    tab[ligne][colonne] = choixDame;
                    enlevéLesPionsJoué(tab, ligne - 1, colonne - 1); // retirer le pion advarsaire
                    enlevéLesPionsJoué(tab, ligne - 2, colonne - 2); // retirer le pion joué
                    if (choixDame == DAME_NOIR) {
                        nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, -1);              // si la dame noire prendre un pion advarsair le compteur vas métre à zéro
                        System.out.println("si dameNoire a pris le pion advarsair " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                    }
                    if (choixDame == DAME_BLANC) {
                        nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, -1);            // si la dame blanc prendre un pion advarsair le compteur vas métre à zéro
                        System.out.println("si dame blanc a pris le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                    }
                } else {
                    tab[ligne + 1][colonne + 1] = choixDame;
                    enlevéLesPionsJoué(tab, ligne, colonne); // retirer le pion joué
                    if (choixDame == DAME_NOIR) {
                        nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, tabComptPourNbFoisPionNoirJouéSansPrise[0]); // si la dame noire ne pas pris le pion advarsair il augumente à chaque fois
                        System.out.println("si dameNoire ne pas prise le pion advarsair " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                    }
                    if (choixDame == DAME_BLANC) {
                        nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, tabComptePourNbFoisPionBlancJouéSansPrise[0]); // si la dame blanche ne pas pris le pion advarsair il augumente à chaque fois
                        System.out.println("si dame blanc ne pas prise le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                    }
                }
                break;
            }
            case 3: {
                if (!verificationLesPionsHautGauche(tab, ligne, colonne)) {
                    ligne = ligne - 2;
                    colonne = colonne - 2;
                    tab[ligne][colonne] = choixDame;
                    enlevéLesPionsJoué(tab, ligne + 1, colonne + 1); // retirer le pion advarsaire
                    enlevéLesPionsJoué(tab, ligne + 2, colonne + 2); // retirer le pion joué
                    if (choixDame == DAME_NOIR) {
                        nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, -1);
                        System.out.println("si dameNoire a pris le pion advarsair " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                    }
                    if (choixDame == DAME_BLANC) {
                        nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, -1);
                        System.out.println("si dame blanc a pris le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                    }
                } else {
                    if (verificationLesPionsHautGauche(tab, ligne, colonne)) {
                        tab[ligne - 1][colonne - 1] = choixDame;
                        enlevéLesPionsJoué(tab, ligne, colonne); // retirer le pion joué
                        if (choixDame == DAME_NOIR) {
                            nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                            System.out.println("si dameNoire ne pas prise  le pion advarsair " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                        }
                        if (choixDame == DAME_BLANC) {
                            nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                            System.out.println("si dame blanc ne pas prise le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                        }
                    }
                }
                break;
            }
            case 4: {
                if (!verificationLesPionsHautDroite(tab, ligne, colonne)) {
                    ligne = ligne - 2;
                    colonne = colonne + 2;
                    tab[ligne][colonne] = choixDame;
                    enlevéLesPionsJoué(tab, ligne + 1, colonne - 1);// retirer le pion advarsaire
                    enlevéLesPionsJoué(tab, ligne + 2, colonne - 2);
                    if (choixDame == DAME_NOIR) {
                        nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, -1);
                        System.out.println("si dameNoire a pris le pion advarsair " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                    }
                    if (choixDame == DAME_BLANC) {
                        nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, -1);
                        System.out.println("si dame blanc a pris le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                    }
                } else {
                    if (verificationLesPionsHautDroite(tab, ligne, colonne)) {
                        tab[ligne - 1][colonne + 1] = choixDame;
                        enlevéLesPionsJoué(tab, ligne, colonne); // retirer le pion joué
                        if (choixDame == DAME_NOIR) {
                            nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                            System.out.println("si dameNoire ne pas prise le pion advarsair " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                        }
                        if (choixDame == DAME_BLANC) {
                            nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                            System.out.println("si dame blanc ne pas  prise le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                        }
                    }
                }
                break;
            }
        }
        return true;
    }


    public static boolean deplacementPionNoire(char[][] tab, int ligne, int colonne) {
        boolean laPaceDePionChoisi;
        int nbFoisPionJoué = 0;
        if (tab[ligne][colonne] == DAME_NOIR) {
            if (deplacementDame(tab, ligne, colonne)) {
                laPaceDePionChoisi = true;
            } else {
                laPaceDePionChoisi = false;
            }
        } else {
            int laPlaceChoisi;
            boolean côtéHautGauche = (verificationLesPionsHautGauche(tab, ligne, colonne) // si en haut gauche prémier case est vide
                    || (verificationPionBlancheCôtéGaucheNoir(tab, ligne, colonne)   //si c'est un pion blanc ou dame blanc
                    && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne))); // en deuxè lignes le case diagonale est vide

            boolean côtéHautDroite = (verificationLesPionsHautDroite(tab, ligne, colonne) // si en haut droite prémier case est vide
                    || (verificationPionBlancheCôtéDroitNoir(tab, ligne, colonne)    //si c'est un pion blanc ou dame blanc
                    && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne))); // en deuxième lignes le case diagonale vers droite est vide
            if (verificationSiPion(tab, ligne, colonne) && (côtéHautGauche || côtéHautDroite)) {  // si la pion choisi est vrai et une place en lignes haut est vides
                laPaceDePionChoisi = true;
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
                        if (verificationPionBlancheCôtéGaucheNoir(tab, ligne, colonne) && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne)) {
                            // dans le cas si la premiere case haut gauche diagonale est comple la deuxièmes case haut gauche diagonale  est vide
                            if (ligne == 2) {
                                devenirDameNoire(tab, ligne, colonne);
                                nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, -1);
                                System.out.println("Dans le cas si le pion noire prendre pion advarsaire " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                            } else {
                                ligne = ligne - 2;
                                colonne = colonne - 2;
                                tab[ligne][colonne] = NOIR;
                                enlevéLesPionsJoué(tab, ligne + 1, colonne + 1);//retirer le pion joué
                                enlevéLesPionsJoué(tab, ligne + 2, colonne + 2);
                                nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, -1);
                                System.out.println("Dans le cas si le pion noire prendre pion advarsaire " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                            }


                        } else {
                            // dans le cas si la premiere case haut gauche diagonale est vide
                            tab[ligne - 1][colonne - 1] = NOIR;
                            enlevéLesPionsJoué(tab, ligne, colonne);
                            nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                            System.out.println("Dans le cas si le pion noire ne prendre pas  pion advarsaire " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                        }
                        break;
                    case 2:
                        if (verificationPionBlancheCôtéDroitNoir(tab, ligne, colonne) && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne)) {
                            // dans le cas si la premiere case haut droite diagonale est comple la deuxièmes case haut droite diagonale  est vide
                            if (ligne == 2) {
                                devenirDameNoire(tab, ligne, colonne);
                                nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, -1);
                                System.out.println("Dans le cas si le pion noire prendre pion advarsaire " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                            } else {
                                ligne = ligne - 2;
                                colonne = colonne + 2;
                                tab[ligne][colonne] = NOIR;
                                enlevéLesPionsJoué(tab, ligne + 1, colonne - 1); // retirer le pion enimies
                                enlevéLesPionsJoué(tab, ligne + 2, colonne - 2);//retirer le pion joué
                                nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, -1); // dans le cas si un pion advarsair est pris  le valeur devient à zéro
                                System.out.println("Dans le cas si le pion noire prendre pion advarsaire " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                            }
                        } else {
                            // dans le cas si la premiere case haut droite  diagonale est vide
                            tab[ligne - 1][colonne + 1] = NOIR;
                            enlevéLesPionsJoué(tab, ligne, colonne);
                            nbFoisPionNoirJouéSansPrisePionAdvarsaire(tabComptPourNbFoisPionNoirJouéSansPrise, tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                            System.out.println("Dans le cas si le pion noire ne prendre pas  pion advarsaire " + tabComptPourNbFoisPionNoirJouéSansPrise[0]);
                        }
                        break;
                }

            } else { // si les deux place en linge ne sont pas vides
                laPaceDePionChoisi = false;
            }
        }
        return laPaceDePionChoisi;
    }

    // cet méthode c'est pour compter les numbres des fois pion noir joué  sans prise le pion advarsair
    public static int[] nbFoisPionNoirJouéSansPrisePionAdvarsaire(int[] tab, int nbFoisPion) {
        nbFoisPion++;
        tab[0] = nbFoisPion;
        return tab;
    }

    // verification de pions noire s'ils sont bloqués retouner vrai
    public static boolean siLesPionsNoirSontBloqué(char[][] tab) {
        for (int ligne = 0; ligne < tab.length; ligne++) {
            for (int colonne = 0; colonne < tab[ligne].length; colonne++) {
                boolean côtéHautGauche = (verificationLesPionsHautGauche(tab, ligne, colonne)        // si en haut gauche prémier case est vide
                        || (verificationPionBlancheCôtéGaucheNoir(tab, ligne, colonne)              //si c'est un pion blanc ou dame blanc
                        && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne)));      // en deuxè lignes le case diagonale est vide

                boolean côtéHautDroite = (verificationLesPionsHautDroite(tab, ligne, colonne)       // si en haut droite prémier case est vide
                        || (verificationPionBlancheCôtéDroitNoir(tab, ligne, colonne)               //si c'est un pion blanc ou dame blanc
                        && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne)));      // en deuxième lignes le case diagonale vers droite est vide

                boolean côteBasGauche = (verificationLesPionsBasGauche(tab, ligne, colonne)         // si en bas gauche case est vide
                        || (!verificationSiPionNoirBasGauche(tab, ligne, colonne)                   // ou si le premier case est complet
                        && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)));       // et en deuèxime ligne le case digonale est vide

                boolean côteBahDroite = (verificationLesPionsBasDroite(tab, ligne, colonne)
                        || (!verificationSiPionNoirBasDroite(tab, ligne, colonne)
                        && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne)));


                if (tab[ligne][colonne] == NOIR && (côtéHautGauche || côtéHautDroite)) {
                    return false;

                }
                if (tab[ligne][colonne] == DAME_NOIR && (côtéHautGauche || côtéHautDroite || côteBasGauche || côteBahDroite)) {
                    return false;
                }


            }

        }
        return true;
    }


    public static boolean deplacementPionBlanc(char[][] tab, int ligne, int colonne) {
        boolean laPaceDePionChoisi;
        if (tab[ligne][colonne] == DAME_BLANC) {                                                    // dans le cas si le pion choisi est une dame blanche
            if (deplacementDame(tab, ligne, colonne)) {
                laPaceDePionChoisi = true;
            } else {
                laPaceDePionChoisi = false;
            }

        } else {
            int laPlaceChoisi;
            boolean côtéBasGauche = (verificationLesPionsBasGauche(tab, ligne, colonne)             // si le premer case est vide
                    || (verificationSiPionNoirBasGauche(tab, ligne, colonne)                        // le premer case noire et
                    && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)));           // deuxièseme case vide

            boolean côtéBasDroite = (verificationLesPionsBasDroite(tab, ligne, colonne)             // si le premer case est vide
                    || (verificationSiPionNoirBasDroite(tab, ligne, colonne)                        // le premer case noire et
                    && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne)));           // deuxièseme case vide

            if (verificationSiPion(tab, ligne, colonne) && (côtéBasGauche || côtéBasDroite)) {      // si la pion choisi est vrai et une place en lignes haut est vides
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
                        if (verificationSiPionNoirBasGauche(tab, ligne, colonne)
                                && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)) {
                            // dans le cas si la premiere case bas gauche diagonale est comple la deuxièmes case bas gauche diagonale  est vide
                            if (ligne == 5) {
                                devenirDameBlanc(tab, ligne, colonne);
                                nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, -1);
                                System.out.println("dans le cas si le pion blanc prendre le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);

                            } else {
                                ligne = ligne + 2;
                                colonne = colonne - 2;
                                tab[ligne][colonne] = BLANC;
                                enlevéLesPionsJoué(tab, ligne - 1, colonne + 1);//retirer le pion joué
                                enlevéLesPionsJoué(tab, ligne - 2, colonne + 2);
                                nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, -1);
                                System.out.println("dans le cas si le pion blanc prendre le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                            }
                        } else {
                            // dans le cas si la premiere case haut gauche diagonale est vide
                            tab[ligne + 1][colonne - 1] = BLANC;
                            enlevéLesPionsJoué(tab, ligne, colonne);
                            nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                            System.out.println("dans le cas si le pion blanc ne prendre pas le pion advarsair" + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                        }
                        break;
                    case 2:
                        if (verificationSiPionNoirBasDroite(tab, ligne, colonne)
                                && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne)) {
                            // dans le cas si la premiere case bas droite diagonale est complet  la deuxièmes case haut droite diagonale  est vide
                            if (ligne == 5) {
                                devenirDameBlanc(tab, ligne, colonne);
                                nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, -1);
                                System.out.println("dans le cas si le pion blanc prendre le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                            } else {
                                ligne = ligne + 2;
                                colonne = colonne + 2;
                                tab[ligne][colonne] = BLANC;
                                enlevéLesPionsJoué(tab, ligne - 1, colonne - 1); // retirer le pion enimies
                                enlevéLesPionsJoué(tab, ligne - 2, colonne - 2);//retirer le pion joué
                                nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, -1);
                                System.out.println("dans le cas si le pion blanc prendre le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                            }
                        } else {
                            // dans le cas si la premiere case bas droite  diagonale est vide
                            tab[ligne + 1][colonne + 1] = BLANC;
                            enlevéLesPionsJoué(tab, ligne, colonne);
                            nbFoisPionBlancJouéSansPrisePionAdvarsaire(tabComptePourNbFoisPionBlancJouéSansPrise, tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                            System.out.println("dans le cas si le pion blanc  ne prendre pas  le pion advarsair " + tabComptePourNbFoisPionBlancJouéSansPrise[0]);
                        }
                        break;
                }

            } else { // si les deux place en linge ne sont pas vides
                laPaceDePionChoisi = false;
            }
        }
        return laPaceDePionChoisi;
    }

    // cet méthode c'est pour compter les numbres des fois pion blanc joué  sans prise le pion advarair
    public static int[] nbFoisPionBlancJouéSansPrisePionAdvarsaire(int[] tab, int nbFoisPion) {
        nbFoisPion++;
        tab[0] = nbFoisPion;
        return tab;
    }

    // verification de pions blanc s'ils sont bloqué retouner vraie
    public static boolean siLesPionsBlancheSontBlouqé(char[][] tab) {
        for (int ligne = 0; ligne < tab.length; ligne++) {
            for (int colonne = 0; colonne < tab[ligne].length; colonne++) {
                boolean côtéBasGauche = (verificationLesPionsBasGauche(tab, ligne, colonne)             // si le premer case est vide
                        || (verificationSiPionNoirBasGauche(tab, ligne, colonne)                        // le premer case noire et
                        && verificationLesPionsBasGaucheDeuxièmeLigne(tab, ligne, colonne)));           // deuxièseme case vide

                boolean côtéBasDroite = (verificationLesPionsBasDroite(tab, ligne, colonne)             // si le premer case est vide
                        || (verificationSiPionNoirBasDroite(tab, ligne, colonne)                        // le premer case noire et
                        && verificationLesPionsBasDroiteDeuxièmeLigne(tab, ligne, colonne)));           // deuxièseme case vide

                boolean côtéHautGauch = (verificationLesPionsHautGauche(tab, ligne, colonne)
                        || (!verificationPionBlancheCôtéGaucheNoir(tab, ligne, colonne)
                        && verificationLesPionsHautGaucheDeuxièmeLigne(tab, ligne, colonne)));

                boolean côtéHautDroit = (verificationLesPionsHautDroite(tab, ligne, colonne)
                        || (!verificationPionBlancheCôtéDroitNoir(tab, ligne, colonne)
                        && verificationLesPionsHautDroiteDeuxièmeLigne(tab, ligne, colonne)));
                if (tab[ligne][colonne] == BLANC && (côtéBasGauche || côtéBasDroite)) {
                    return false;
                }
                if (tab[ligne][colonne] == DAME_BLANC && (côtéBasGauche || côtéBasDroite || côtéHautDroit || côtéHautGauch)) {
                    return false;
                }
            }
        }
        return true;
    }


    // vérification le case vers haut gauche s'il est vide
    public static boolean verificationLesPionsHautGauche(char[][] tab, int ligne, int colonne) {
        if (ligne > 0 && colonne > 0) {
            ligne = ligne - 1;
            colonne = colonne - 1;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }

    // vérification la case de deuxième ligne s'il est vide
    public static boolean verificationLesPionsHautGaucheDeuxièmeLigne(char[][] tab, int ligne, int colonne) {
        if (ligne > 1 && colonne > 1) {
            ligne = ligne - 2;
            colonne = colonne - 2;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }


    // verification si le case  haut droit est vides
    public static boolean verificationLesPionsHautDroite(char[][] tab, int ligne, int colonne) {
        if (ligne > 0 && colonne < 7) {
            ligne = ligne - 1;
            colonne = colonne + 1;
            if (tab[ligne][colonne] == ' ') {
                return true;
            }
        }
        return false;
    }

    // verification si le case de deuxième ligne haut droit est vides
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


    // verification si le case en bas gauche est vide
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


    // verification si le case en deuxième ligne bas gauche est vide
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


    //verification si le case en bas droit est vide
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


    //verification si le case en deuxième lignes en bas droit est vide
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


    public static boolean pionBlancGange(char[][] tab) { // dans le cas s'il y a aucun pion et dame noir
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (i < 7) { // la première ligne ne doit pas parcourire pour les pions noirs, si les pions noirs sont dans la première lignes  ils ne pouvent être joué
                    if (tab[i + 1][j] == NOIR) {
                        return false;
                    }
                }
                if (tab[i][j] == DAME_NOIR) { // parcourire toute la tableau pour vérification de dame noire
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean pionNoirGange(char[][] tab) { // dans le cas s'il y a aucun pion et dame blanc
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (i < 7) { // parcourire tous le tableau sauf dernière ligne pour les pion blanche
                    if (tab[i][j] == BLANC) {
                        return false;
                    }
                }
                if (tab[i][j] == DAME_BLANC) {
                    return false;
                }
            }
        }
        return true;
    }


    public static char[][] mettreUnPionPourEssaie(char[][] tab, int ligne, int colonne, char pion) { //pour mettre les pion dans le tableau pour verifier les sous programme
        tab[ligne][colonne] = pion;
        return tab;
    }


}