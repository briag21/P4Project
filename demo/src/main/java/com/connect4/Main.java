package com.connect4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoteurDeJeu4 jeu = new MoteurDeJeu4();
        Plateau4 plateau = jeu.getPlateau();

        System.out.println("-----Début de la partie-----");
        System.out.println("Vous êtes le joueur 'J'. L'IA est le joueur 'R'");

        while (true) {
            plateau.afficher();
            char joueurActuel = jeu.getJoueurActuel();
            System.out.println("Tour du joueur : " + joueurActuel);

            int col;

            while (true) {
                try {
                    System.out.println("Dans quelle colonne souhaitez-vous jouer ?");
                    int colHumaine = scanner.nextInt();

                    col = colHumaine - 1;

                    if (col >= 0 && col < Plateau4.TAILLE_C && plateau.estColonneJouable(col)) {
                        break;
                    } else {
                        System.out.println("Coup invalide.");
                    }

                } catch (InputMismatchException e) {
                    System.out.printf("Entrée valide. Veuillez entrer un chiffre.");
                    scanner.next();
                }

            }

            int lignejouee = plateau.jouerUnCoup(col, joueurActuel);

            if(lignejouee == -1){
                System.out.println("Erreur : la colonne est pleine");
                continue;
            }

            if(jeu.verifieVictoire(lignejouee, col, joueurActuel)){
                plateau.afficher();
                System.out.println("Félicitations au joueur " + joueurActuel + " ! Vous avez gagné.");
                break;
            }else{
                System.out.println("Pas gagné");
                System.out.println(lignejouee);
            }

            if(plateau.estPlein()){
                plateau.afficher();
                System.out.println("Match nul ! Plateau plein.");
                break;
            }

            jeu.changerJoueur();
        }

        scanner.close();
        System.out.println("-----Fin de partie-----");

    }
}
