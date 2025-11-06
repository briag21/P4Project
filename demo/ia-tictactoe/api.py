from flask import Flask, request, jsonify

app = Flask(__name__)

VIDE = ' '

def verifier_victoire(plateau, joueur):
    taille=len(plateau)

    #Check Lignes
    for i in range (taille):
        if all(plateau[i][j] == joueur for j in range(taille)):
            return True
        
    #Check Colonnes
    for j in range (taille):
        if all(plateau[i][j] == joueur for i in range (taille)):
            return True

    #Check diag1  
    if all(plateau[i][i] == joueur for i in range (taille)):
        return True
    
    #Check diag2
    if all(plateau[i][taille - i - 1] == joueur for i in range (taille)):
        return True
    return False

def verifier_nul(plateau):
    for ligne in plateau:
        if VIDE in ligne:
            return False #Il reste une case vide
    return True

def get_coups_valides(plateau):
    coups=[]
    for i in range(len(plateau)):
        for j in range(len(plateau)):
            if plateau[i][j] == VIDE:
                coups.append((i, j))
    return coups

JOUEUR_IA = ''
JOUEUR_HUMAIN = ''
def minimax(plateau, profondeur, is_maximizing):

    if verifier_victoire(plateau, JOUEUR_IA):
        return 10 - profondeur
    if verifier_victoire(plateau, JOUEUR_HUMAIN):
        return -10 + profondeur
    if verifier_nul(plateau):
        return 0
    
    if is_maximizing: #L'algorithme cherche à maximiser son score, c'est son tour !
        meilleur_score = -float('inf') #Pire cas
        for (ligne, col) in get_coups_valides(plateau):
            plateau[ligne][col] = JOUEUR_IA
            score = minimax(plateau, profondeur + 1, False) #Simulation des prochaines situations, prochain coup sera humain donc False
            plateau[ligne][col] = VIDE
            meilleur_score = max(meilleur_score, score)
        return meilleur_score
    else: #L'algorithme cherche à minimiser le score, c'est le tour de l'humain
        meilleur_score = float('inf') #Pire cas
        for (ligne, col) in get_coups_valides(plateau):
            plateau[ligne][col] = JOUEUR_HUMAIN
            score = minimax(plateau, profondeur +  1, True) #Simulation des prochaines situations, prochain coup sera IA donc True
            plateau[ligne][col] = VIDE
            meilleur_score = min(meilleur_score, score) #on cherche le score le moins élevé vu que c'est l'humain
        return meilleur_score
    

def trouver_meilleur_coup (plateau, joueur_ia):
    global JOUEUR_IA, JOUEUR_HUMAIN #global permet d'utiliser les variables de app.py et pas des variables pour la fonction
    JOUEUR_IA = joueur_ia
    JOUEUR_HUMAIN = 'O' if joueur_ia == 'X' else 'X'

    meilleur_score = -float('inf')
    coup_final = None

    for (ligne, col) in get_coups_valides(plateau):
        plateau[ligne][col] = JOUEUR_IA

        score = minimax(plateau, 0, False)

        plateau[ligne][col] = VIDE

        if score > meilleur_score:
            meilleur_score = score
            coup_final = (ligne, col)

    if coup_final is None: #Cas où il n'y a pas de coup final choisi (fin de partie...)
        coups = get_coups_valides(plateau)
        if coups:
            coup_final = coups[0]
    
    return coup_final

@app.route('/get_move', methods=['POST'])
def get_move():
    try:
        data = request.json
        plateau = data['board']
        joueur_actuel_ia = data['player']

        print(f"Reçu demande pour le joueur : {joueur_actuel_ia}")
        print("Plateau actuel  : ")
        for ligne in plateau:
            print(ligne)
        
        meilleur_coup = trouver_meilleur_coup(plateau, joueur_actuel_ia)

        if meilleur_coup:
            print(f"Coup calculé : {meilleur_coup}")
            return jsonify({'move' : [meilleur_coup[0], meilleur_coup[1]]})
        else:
            print("Erreur : Aucun coup trouvé.")
            return jsonify({'error': 'Aucun coup valide trouvé'}), 400
    except Exception as e:
        print(f"Erreur serveur : {e}")
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    print("---Démarrage du serveur IA Tic Tac Toe (Flask)---")
    print("Accessible sur http://127.0.0.1:5000")
    app.run(debug=True, port=5000)



