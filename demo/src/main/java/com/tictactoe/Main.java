package com.tictactoe;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final char JOUEUR_HUMAIN = 'X';
    private static final char JOUEUR_IA = 'O';
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoteurDeJeu jeu = new MoteurDeJeu();
        Plateau plateau = jeu.getPlateau();

        ApiClient apiClient = new ApiClient();


        System.out.println("-----Début de la partie-----");
        System.out.println("Vous êtes le joueur 'X'. L'IA est le joueur 'O'");

        while (true) {   
            plateau.afficher();
            System.out.println("Tour du joueur : " + jeu.getJoueurActuel());
            
            int ligne, col;

            if(jeu.getJoueurActuel() == JOUEUR_HUMAIN){
                while(true){
                    try {
                        System.out.println("Dans quel ligne souhaitez-vous jouer ? (1, 2, 3)");
                        ligne = scanner.nextInt() -1;
                        System.out.println("Dans quel colonne souhaitez-vous jouer ? (1, 2, 3)");
                        col = scanner.nextInt() -1;

                        if(plateau.estCaseJouable(ligne, col)){
                            break;
                        } else {
                            System.out.println("Coup invalide.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Entrée invalide, veuillez entrez des chiffres");
                        scanner.next();
                    }
                }
            } else {
                int[] coupIA = apiClient.getBestMove(plateau, JOUEUR_IA);

                if(coupIA==null){
                    System.out.println("L'IA a rencontré une erreur");
                    break;
                }

                ligne = coupIA[0];
                col = coupIA[1];
                System.out.printf("L'IA a joué en ligne %d et colonne %d\n", ligne+1,col+1);
            }

            plateau.jouerUnCoup(ligne, col, jeu.getJoueurActuel());

            if(jeu.verifieVictoire(jeu.getJoueurActuel())){
                plateau.afficher();
                System.out.println("Félicitations au joueur " + jeu.getJoueurActuel() + " pour sa victoire.");
                break;
            }

            if(plateau.estPlein()){
                plateau.afficher();
                System.out.println("Match nul !");
                break;
            }

            jeu.changerJoueur();

            
        }
        scanner.close();
        System.out.println("Fin de partie.");
    }
}