package com.connect4;

public class Plateau4 {
    private char[][] cases;
    public static final int TAILLE_L = 6;
    public static final int TAILLE_C = 7;
    public static final char VIDE=' ';
    
    public Plateau4(){
        this.cases = new char[Plateau4.TAILLE_L][Plateau4.TAILLE_C];
        initialiser();
    }

    public void initialiser(){
        for (int i = 0; i<TAILLE_L; i++){
            for(int j = 0; j<TAILLE_C;j++){
                this.cases[i][j] = VIDE;
            }
        }
    }

    public int jouerUnCoup(int col, char joueur){
        if(col<0 || col >= TAILLE_C){
            return -1;
        }
        for(int i = TAILLE_L-1; i>=0; i--){
            if (cases[i][col] == VIDE){
                cases[i][col] = joueur;
                return i;
            }
        }
        return -1;
    }

    public void afficher(){
        System.out.println("-----------------------------");
        for(int i = 0; i<TAILLE_L; i++){
            System.out.print("|");
            for(int j = 0; j<TAILLE_C; j++){
                System.out.print(cases[i][j] + "  |");
            }
            System.out.println();
            System.out.println("-----------------------------");
        }
    }

    public boolean estPlein(){
        for(int i = 0; i<TAILLE_L; i++){
            for(int j = 0; j<TAILLE_C; j++){
                if(cases[i][j]==VIDE){
                    return false;
                }
            }
        }
        return true;
    }

    public char getCase(int ligne, int col){
        return this.cases[ligne][col];
    }

    public boolean estColonneJouable(int col) {
        if (col < 0 || col >= TAILLE_C) {
            return false;
        }
        // Il suffit de vérifier la case du haut (ligne 0)
        return this.cases[0][col] == VIDE;
    }
}
