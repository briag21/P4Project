package com.tictactoe;


public class Plateau {
    private char[][] cases;
    public static final int TAILLE = 3;
    public static final char VIDE = ' ';

    
    public Plateau(){
        this.cases = new char[TAILLE][TAILLE];
        initialiser();
    }

    public void initialiser() {
        for (int i = 0; i<TAILLE; i++){
            for(int j = 0; j<TAILLE; j++){
                cases[i][j] = VIDE;
            }
        }
    }

    public void afficher(){
        System.out.println("----------");
        for(int i = 0; i<TAILLE; i++){
            System.out.print("|");
            for(int j = 0; j<TAILLE; j++){
                System.out.print(cases[i][j] + " | ");
            }
            System.out.println();
            System.out.println("----------");
        }
    }

    public boolean jouerUnCoup(int ligne, int col, char joueur){
        if(estCaseJouable(ligne, col)){
            cases[ligne][col] = joueur;
            return true;
        }
        return false;
    }

    public boolean estCaseJouable(int ligne, int col){
        return ligne >= 0 && ligne < TAILLE && col >=0 && col < TAILLE && cases[ligne][col] == VIDE;
    }

    public boolean estPlein(){
        for(int i = 0; i<TAILLE; i++){
            for(int j = 0; j<TAILLE; j++){
                if(cases[i][j]==VIDE){
                    return false;
                }
            }
        }
        return true;
    }

    public char getCase(int ligne, int col){
        return cases[ligne][col];
    }

    public char[][] getCases(){
        return this.cases;
    }

}
