import numpy as np

LIGNE = 6
COL = 7
VIDE = 0
JOUEUR_1 = 1
JOUEUR_2 = 2
class Plateau:
    def __init__(self):
        self.plateau = np.zeros((LIGNE, COL), dtype=int)

    def afficher(self):
        print(self.board)

    def colonne_est_jouable(self, col):
        return self.board[0][col] == VIDE
    
    def jouer_un_coup(self, col, joueur):
        for r in range(LIGNE - 1, -1, -1):
            if self.board[r][col] == VIDE:
                self.board[r][col]=joueur
                return r
        return -1
    
    def get_coups_valides(self):
        coups_valides = []

        for c in range(COL):
            if self.colonne_est_jouable(self, c):
                coups_valides.append(c)
        return coups_valides
    
    def est_plein(self):
        return len(self.get_coups_valides())==0
