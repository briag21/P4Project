package com.connect4;

public class MoteurDeJeu4 {
    private Plateau4 plateau;
    private char joueurActuel;

    public MoteurDeJeu4(){
        this.plateau = new Plateau4();
        this.joueurActuel = 'J';
    }

    public char getJoueurActuel(){
        return this.joueurActuel;
    }

    public Plateau4 getPlateau(){
        return this.plateau;
    }

    public void changerJoueur(){
        if(this.getJoueurActuel() == 'J'){
            this.joueurActuel = 'R';
        }else{
            this.joueurActuel = 'J';
        }
    }

    public boolean verifieVictoire(int derniereLigneJouee, int derniereColonneJouee, char joueur){
        //int derniereLigneJouee = derniereLigneJouee1 -1;
        int count = 1;

        //Verification horizontale
        for(int c = derniereColonneJouee -1; c >= 0 && plateau.getCase(derniereLigneJouee, c) == joueur; c--){
            count++;
        }

        for (int c = derniereColonneJouee + 1; c < Plateau4.TAILLE_C && plateau.getCase(derniereLigneJouee, c) == joueur; c++){
            count++;
        }

        if(count>=4){return true;}

        //Verification verticale
        count = 1;
        for(int r = derniereLigneJouee + 1; r < Plateau4.TAILLE_L && plateau.getCase(r, derniereColonneJouee) == joueur; r++ ){
            count++;
        }

        if(count>=4){return true;}

        //Verification diagonale 1
        count = 1;
        for(int r = derniereLigneJouee - 1, c = derniereColonneJouee + 1; r>=0 && c < Plateau4.TAILLE_C && plateau.getCase(r, c) == joueur; r--, c++){
            count ++;
        }

        for(int r = derniereLigneJouee + 1, c = derniereColonneJouee - 1; r < Plateau4.TAILLE_L && c>=0 && plateau.getCase(r, c) == joueur; r++, c--){
            count ++;
        }

        if(count>=4)return true;

        //Verification diagonale 2
        count = 1;
        for(int r = derniereLigneJouee - 1, c = derniereColonneJouee -1; r>=0 && c>=0 && plateau.getCase(r, c) == joueur; r--, c--){
            count++;
        }
        for(int r = derniereLigneJouee + 1, c = derniereColonneJouee + 1; r < Plateau4.TAILLE_L && c < Plateau4.TAILLE_L && plateau.getCase(r, c) == joueur; r++, c++){
            count++;
        }

        if(count>=4)return true;
        

        return false;
    }
}

