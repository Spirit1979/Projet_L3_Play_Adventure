=====================================================================================
|                                                                                   |
|                           Jeu en mode texte                                       |
|                                                                                   |
=====================================================================================

1/Informations :
 			Dev : 					Fabien Grosieux,Guillaume Clochard,Anthony Blanchard
 			Date de création : 		14/11/2017
			Une javadoc est disponible dans le dossier "doc" de l'archive.


2/Prérequis : 

Vérifier que le poste à le dernier environnement d'exécution Java JRE .

3/Commande pour éxécuter le jeu :

Ouvrir un terminal et saisir  la commande suivante : java -jar jeu.jar 

4/Voici les commandes nécessaire du jeu :


go direction : la commande go est suivie du nom du lieu voisin où le joueur souhaite se rendre.

help : indique l'ensemble des commandes disponibles.

look [object] : affiche un descriptif du lieu courant si aucun argument n'est ajouté. Si l'argument object est précisé, la commande affiche un descriptif de ce dernier.

take object : ajoute l'objet à l’inventaire du joueur.

quit : quitte la partie.

attaque : Permet au héro d’attaquer un monstre.

Buy : permet d’acheter un objet au marchant.

infos : Affiche les informations relatif au personnage.

use object1 [object2] : utilisation de l'objet désigné par le premier argument. Si un second argument est renseigné, le premier objet est utilisé avec le second. Par exemple, on peut imaginer que l'instruction use gun bullet permette de charger le gun, ce qui pourra être utile pour sa une future utilisation.

Talk : permet de dialoguer avec un personnage du jeu.

-----------------------------------------------------------------------------
