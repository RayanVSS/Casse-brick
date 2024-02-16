## Semaine 1: 
### Benmalek Majda
<span style="color:green">**Fait:**</span>
Un debut primaire de la balle, avec juste les collisions avec les 3 (nord, est, ouest) limites de la fenetre

<span style="color:purple">**A Faire:**</span>
Les menu (acceuil(pour s2), option, game over, fin de niveau, une bonne gestion de l'animation)

### Mushtaq Amenah 
<span style="color:green">**Fait:**</span>
une 1ère version d'un affichage des briques

<span style="color:purple">**A Faire:**</span>
faire la mise à jour de l'affichage des briques au cours de la partie (càd si des briques se cassent etc...)

### Guan Olivier
<span style="color:green">**Fait:**</span>
Initialisation de la disposition classique des briques, briques de différents types, préparation pour implémenter les différentes dispositions possibles
Gitlab CI config, checktyle pipeline

<span style="color:purple">**A Faire:**</span>
Update de la partie, différentes config de map...
    
### Bencheikh Ilias
<span style="color:green">**Fait:**</span>
Implementation du gradle et d'une base pour le jeu + commencer a implementer le moteur physique

<span style="color:purple">**A Faire:**</span>
finir le moteur du jeu et commencer a approfondir la partie physique

### Belhassen rayan
<span style="color:green">**Fait:**</span>
implémentation du code de la racket 

<span style="color:purple">**A Faire:**</span>
collisions de la racket
affichage de la racket

## Semaine 2:
### Belhassen rayan
<span style="color:green">**Fait:**</span>

-collisions: collision de la raquette avec la balle + prend en compte que la raquette bouge durant la collision

-GravityBall: nouvelle balle qui subit la gravité (sans perte de vitesse)

-HyperBall: nouvelle balle qui à chaque rebond gagne de la vitesse

-racket: modification du code pour le rendre abstracte et l'adapter à "Pane" et non un "Stackpane" 

-ball: résolution d'un problème de collision avec le mur de droit

-menu: bouton retour quand on est dans les options

-fenêtre: la taille de la fenêtre ne change plus

-lisibilité: restructure du code et ajout de commentaire explicatif et de javaDoc

-particul: ajout d'une trainer a la balle

-touche: creation de la classe qui gere la lisibilié des touches

-simplification: rajout des varibles dans "GameConstants" afin de faciliter les modifications

-fps: creation de la class qui calcul les fps

<span style="color:purple">**A Faire:**</span>

-boost: boost pour la racket 

-sauvegarde: pouvoir enregistrer son avancée

### Guan Olivier
<span style="color:green">**Fait:**</span>

- update de Game.java : 
    collision balle-brique, intersection entre une balle et une brique
    gestion destruction briques (à continuer pour une destruction plus spécifique, en fct de la gravité, vitesse, couleur, pv, etc...)
        
- Ball : correction sur le rayon/diamètre

- Brick : x et y de la brique part maintenant du coin supérieur gauche de la brique et adapté directement à l'echelle et les tailles de GameConstants

- Map : nouvelle classe pour alleger Game.java + factorisation

<span style="color:purple">**A Faire:**</span>

- Optimiser les collisions, régler les bugs en diagonale

- Implementation des niveaux, chargement des niveaux, selecteur du niveau, ...

- UI du jeu en plusieurs panneaux (la partie ne doit plus être la fenêtre de l'application)


## Majda Benmalek

<span style="color:green">**Fait:**</span>

-RacketGraphics : affiche la racket

-BallGraphics : affiche la balle 


-Travail sur la colissions de la balle et de la racket (la balle ne peut plus traverser la raquette)

-FPSGraphics : affiche les FPS

-StartMenu : avec bouton options tutoriel(provisoire) Jouer et Quitter

-GameOver : retour au menu , rejouer et quitter 

-Factorisation de GameView: sortir AnimationTimer du constructeur pour pouvoir l'arreter plus simplement 

-Options : Bouton option dans le StartMenu (avec rien dedans pour l'instant juste un bouton retour)

-MVC pour les menus 

-Affichage des vies et leur maj

<span style="color:purple">**A Faire:**</span>

-Affichage et maj du score 

-Factorisation du code

-Commentaire/explication et javaDoc dans mon code

## Ilias Bencheikh

<span style="color:green">**Fait:**</span>

-Implementation d'un système de FPS

-Implementation d'un système de simulation pour le moteur physique

-Setup du gradle physique 

-Début de la piste pour une physique poussée 

<span style="color:purple">**A Faire:**</span>

-Amélioration de la physique 

-Implementation des options pour le jeu 

## Amenah MUSHTAQ

<span style="color:green">**Fait:**</span>

- mise à jour de l'affichage des briques (les briques cassées s'enlèvent)

<span style="color:purple">**A Faire:**</span>

-factorisation du code

-Commentaire/explication et javaDoc dans mon code

- bouton pause



