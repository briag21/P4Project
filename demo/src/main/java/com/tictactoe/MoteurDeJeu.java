package com.tictactoe;

public class MoteurDeJeu {
    private Plateau plateau;
    private char joueurActuel;

    public MoteurDeJeu(){
        this.plateau = new Plateau();
        this.joueurActuel = 'X'; //X always start a game
    }

    public char getJoueurActuel(){
        return this.joueurActuel;
    }

    public Plateau getPlateau(){
        return this.plateau;
    }

    public void changerJoueur(){
        if(this.joueurActuel == 'X'){
            this.joueurActuel = 'O'; 
        }else if(this.joueurActuel == 'O'){
            this.joueurActuel = 'X';
        }
    }

    public boolean verifieVictoire(char joueur){

        //check ligne
        for(int i = 0; i<Plateau.TAILLE; i++){
            if(plateau.getCase(i, 0)==joueur && 
            plateau.getCase(i, 1)==joueur && 
            plateau.getCase(i, 2)==joueur){
                return true;
            }
        }

        //check colonne
        for(int j = 0; j<Plateau.TAILLE; j++){
            if(plateau.getCase(0, j)==joueur && 
            plateau.getCase(1, j)==joueur && 
            plateau.getCase(2, j)==joueur){
                return true;
            }
        }

        //check diag1
        if(plateau.getCase(0, 0)==joueur && plateau.getCase(1, 1)==joueur && plateau.getCase(2, 2) == joueur){
            return true;
        }

        //check diag2
        if(plateau.getCase(2, 0)==joueur && plateau.getCase(1, 1)==joueur && plateau.getCase(0, 2) == joueur){
            return true;
        }

        return false;

    }

    
}
