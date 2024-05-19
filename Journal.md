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


### Majda Benmalek

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

### Ilias Bencheikh

<span style="color:green">**Fait:**</span>

-Implementation d'un système de FPS

-Implementation d'un système de simulation pour le moteur physique

-Setup du gradle physique 

-Début de la piste pour une physique poussée 

<span style="color:purple">**A Faire:**</span>

-Amélioration de la physique 

-Implementation des options pour le jeu 

### Amenah MUSHTAQ

<span style="color:green">**Fait:**</span>

- mise à jour de l'affichage des briques (les briques cassées s'enlèvent)

<span style="color:purple">**A Faire:**</span>

-factorisation du code

-Commentaire/explication et javaDoc dans mon code

- bouton pause

## Semaine 3:
### Belhassen rayan
<span style="color:green">**Fait:**</span>
    
-#14 boost: boost pour la racket 

-#15 sauvegarde: pouvoir enregistrer son avancée (a finir quand les options et les niveaux sont plus avancés)

-gestion de sauvegarde: mini logiciel hors du code qui permet de pouvoir gerer les sauvegarde en cas de probleme 

<span style="color:purple">**A Faire:**</span>

-#30 travailler sur un mode pour daltonien

-#31 mode deux joueur 

### Benmalek Majda 
<span style="color:green">**Fait:**</span>
- Modif du game over (transparence)
  
- Le score et les vies s'update bien
  
- Factorisation des classes menu + javadoc
  
- Modif des options (MVC + foctorisation)
  
- Ajout de fichier css pour mieux s'adapter au changement de themes (pas encore commencé les themes)

<span style="color:purple">**A Faire:**</span>
-Ajouter du son

-Rajout de theme changeable 

-Colission de la balle et la racket (j’attends que la physics soit merge)

-Reglé le probleme de la fluidité entre les scenes

### Amenah MUSHTAQ

<span style="color:green">**Fait:**</span>
-factorisation du code Commentaire/explication et javaDoc dans mon code
-#27 bouton pause

<span style="color:purple">**A Faire:**</span>
- pb de timer à régler pour le bouton pause
-.....

### Guan Olivier 
<span style="color:green">**Fait:**</span>

- Mis en place des preconfig (niveaux prédéfinis) de game avec de nouvelles classes.

- Class GameRules : contient les règles/options (temps limité, mélanger la position des briques, briques qui devient invisibles/transparentes/incassables etc...) que va vérifier la game

- Class StageLevel : contient la difficulté, le niveau de débloquement, et la game prédifinie à charger.

- Class StageController : contient un tableau de StageLevel (l'ensemble des niveaux préconfig).

- Class PlayerData : contient toutes les données du joueur, càd : nom, niveau, monnaie (peut-être), un stageController (correspond aux données sur la progression des niveaux). Le fait de charger un objet PlayerData (par la sauvegarde) permettra de reconstiuer l'état d'un jeu complet.


<span style="color:purple">**A Faire:**</span>

- Finir le travail ci-dessus, l'implementation des rules à appliquer/vérifier.

- Brick : Physique de la brique, introduire la durabilité, absorption des chocs en fonction de la vitesse, taille de la balle etc... 


### Ilias Bencheikh
<span style="color:green">**Fait:**</span>

- Preview de la balle #29
- Implémentation des options #19
- Amélioration de la physique #20 : 
    - Début d'un système de masse 
    - Début d'un système de perte de vitesse du a la gravité
    - Début d'un système de vent
    - Amélioration de la simulation (choix de la masse , gravité ...)

<span style="color:purple">**A Faire:**</span>

- Amélioration de la physique #20
- Implémentation de celle-ci dans le jeu
- Implémentation du niveau infini #23

## Semaine 4 (27/2)

### Majda Benmalek 
<span style="color:green">**Fait:**</span>
- Rajout de 2 themes( utilisation du css )
- Factorisation et changement de comment on gere le changement de scene 
- Rajout d'une transition (pas ouf) entre chaque changement de scene 

<span style="color:purple">**A Faire:**</span>
- Ajout du son 
- Probleme d'angle collision de la balle 
- Rajout d'autre theme avec la couleur de la balle et la raquette qui suivent 
- Changer la disposition dans l'affichage du jeu


### Guan Olivier 
<span style="color:green">**Fait:**</span>

- Toutes les règles additionnelles au jeu classique : [Temps limité](https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c/issues/37), [Rebonds limité](https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c/issues/38), [Briques TP aléatoire](https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c/issues/39), [Correspondance de couleur](https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c/issues/41), [Brique transparente/Ghost](https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c/issues/42), [Brique incassable](https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c/issues/45)

- Factorisation de la logique des règles

- Possibilité de "stacker" les règles


<span style="color:purple">**A Faire:**</span>

- Inclure ces nouvelles règles dans les preconfig de niveaux

- Création d'un menu de selection de niveaux et de création d'une game custom



### Amenah MUSHTAQ

<span style="color:green">**Fait:**</span>
-#27 bouton pause pb timer reglé
-#44 : 
    -affichage pour le mode couleur
    -optimisation de l'affichage des briques
    -affichage pour la permutation des briques pas finie

<span style="color:purple">**A Faire:**</span>
-#44 affichage pour la permutation des briques pas finie
- mode infini

### Belhassen rayan
<span style="color:green">**Fait:**</span>

- magnetRacket: raquette magnétique 
- magnetBall: balle magnétique 
- sauvegarde:
    - finalisation du menu pour les sauvegardes
    - possibilité de pouvoir enregistrer les options 
    - le jeu charge sur la dernière sauvegarde 

<span style="color:purple">**A Faire:**</span>

- travailler sur un mode pour daltonien

- mode deux joueur 

### Bencheikh Ilias
<span style="color:green">**Fait:**</span>

- Implémentation des effets de la balle (début de rotation) #32

- Implémentation de prendre de la balle et de l'envoyer afin de la tester #40

- Recherche d'un meilleur mouvement de la balle (loi de bernouilli et effet de magnus)

<span style="color:purple">**A Faire:**</span>

- Améliorer les effets de la balle ( problème de collision avec le mur après plusieur effets) #34

- Régler le problème de FPS (vitesse de la balle et de la racket varie en fonction des performances) #43

## Semaine 5 (5/3):

### Belhassen rayan
<span style="color:green">**Fait:**</span>
    
- boost: apparition des boost
- sauvegarde: ajout du theme dans la sauvegarde
- icône: ajout d'une icône pour le jeux 
- daltonisme: thème pour les personnes atteintes de "protanopie","deuteranopie","tritanopie" et "achromatopsie"

<span style="color:purple">**A Faire:**</span>

- mode deux joueur 


### Guan Olivier 
<span style="color:green">**Fait:**</span>

- Niveaux (parties)
- Création d'un menu de selection de mode de jeu (Niveaux/Game Custom)
- Création d'un menu de selection de niveaux
- Création d'un menu de création d'une game custom (pas fini)
- Intégration des données joueur dans la sauvegarde (progression dans les niveaux (Game est "transcient" tout de même), exp, pseudo, ...)
- GameView, lancement et chargement en fonction d'un StageLevel et non d'un simple Game pour traiter toutes les informations nécessaires
- Le jeu s'arrête lors de la victoire et mets à jour la progression : levelUp lors du premier clear, save le maxScore, reset la partie pour préparer la nouvelle tentative, ... (pas encore d'écran de victoire)

<span style="color:purple">**A Faire:**</span>

- Finir le menu pour créer un Game totalement custom : choix true/false sur chacunes des règles (+ réglages temps/quantité), type de balle, type de raquette, vitesse de la balle, taille de la balle, taille map (hauteur/largeur), vie, ... 
- Régler les problèmes des collisions avec les règles + collisions sur les briques en général

### Bencheikh Ilias
<span style="color:green">**Fait:**</span>

- Amélioration des effets de la balle : effet plus visible + rotation de la balle (invisible car la balle est rond noir)
- Amélioration de la racket (collision en dessous et sur les côtés)
- Début d'implémentation de la friction de l'air 
    
<span style="color:purple">**A Faire:**</span>

- Amélioration de la trajectoire + régler le problème de la trajectoire de la balle (problème d'effets avec la trajectoire)
- Régler le problème de FPS
- Implémenter la friction de l'air et de la balle 

### Mushtaq Amenah 
<span style="color:green">**Fait:**</span>

-#44:
- Brique incassable 
- Brique transparentes
- Brique change de position 

<span style="color:purple">**A Faire:**</span>
- Mode infini 

### Benmalek Majda 
<span style="color:green">**Fait:**</span>
- #26 : separation de l'ecran de jeu en plusieurs Pane 
- Differente implementation du score, gere le cas ou on casse plusieurs briques a la fois
- Changement dans le mouvement de la balle => angle different a chaque collision 

<span style="color:purple">**A Faire:**</span>
- Ajout du son #21
- Intergrer des images dans les themes pour la balle et la racket 
- Enlever les transitions entre chaque changement de scene et essayer de fluidifié ca
- Ajouter un ecran de victoire


## Semaine 6 (12/3):

### Guan Olivier 
<span style="color:green">**Fait:**</span>

- Création d'une partie totalement custom (+ Revue de plusieurs classes s'articulant autour)
- Classes de création de composants graphiques
- Qq améliorations dans les collisions briques/balle (minimes)

<span style="color:purple">**A Faire:**</span>

- Régler les problèmes des collisions avec la physique pris en compte également (avec Ilias)
- Affichage des infos de parties plus complètes (si pas encore fait d'ici là)
  
### Benmalek Majda 
<span style="color:green">**Fait:**</span>

- Changement dans l'affichage des vies 
- Travail sur l'integration d'image et de changer le visuel de la balle/brique/racket/vies par rapport au theme choisi

<span style="color:purple">**A Faire:**</span>
- Avoir des image pour chaque cas 
- Ameliorer le visuel 
- Créer un ecran de victoire 

### Mushtaq Amenah 
<span style="color:green">**Fait:**</span>
- Debut du mode infini 

<span style="color:purple">**A Faire:**</span>
- Suite du mode infini

### Bencheikh Ilias
<span style="color:green">**Fait:**</span>

- Amélioration des effets de la balle 
- Implémentation de la physique dans le jeu #47
- Début de javadoc (pdf expliquant comment bouge la balle) #48
    
<span style="color:purple">**A Faire:**</span>

- Réparer le simulateur #53
- Régler le problème de FPS #43
- Régler le problème de trajectoire #46
- Implémenter la friction de l'air et de la balle #20
- Améliorer la collision de la balle et des briques (avec Olivier) #52
- Améliorer la structure de la partie physique #49

### Belhassen Rayan  
<span style="color:green">**Fait:**</span>

- boost: nouveau boost zhonya 

<span style="color:purple">**A Faire:**</span>

- terminer repair_software 

### À faire

- lister quelques phénomènes physiques amusants à ajouter, écrire les issues, rechercher la physique, commencer à implémenter

## Semaine 7 (19/3):

### Belhassen Rayan  
<span style="color:green">**Fait:**</span>

- graphique: la raquette peut avoir des forme differente 
- collision: les collisions fonctionne sur des raquettes non rectangle 
- raquette: nouvelle raquette "CircleRacket","DegradeRacket" et "DiamondRacket"
- boost: nouveau boost intensityBall
- 
<span style="color:purple">**A Faire:**</span>

- terminer repair_software 

### Guan Olivier 
<span style="color:green">**Fait:**</span>

- Debut modèle de briques physiques

<span style="color:purple">**A Faire:**</span>

- Continuer les briques physiques

### Bencheikh Ilias
<span style="color:green">**Fait:**</span>

- Amélioration des effets de la balle #32
- Moteur physique réparé #53
- Implémentation de plusieurs balles dans la simulation #54
- Implémentation d'un menu in-simulation #59
- Problème de trajectoire régler #46
    
<span style="color:purple">**A Faire:**</span>

- Régler le problème de FPS #43
- Implémenter la friction de l'air et de la balle #20
- Améliorer la collision de la balle et des briques (avec Olivier) #52
- Améliorer la structure de la partie physique #49
- Début implémentation des collisions de balles entre eux #55

### Benmalek Majda
<span style="color:green">**Fait:**</span>
- Ecran de victoire
- Ecran de fin du jeu
- La balle est une image maintenant (reste a adapter l'image au theme)
- Modif de l'affiche des vies (aussi fais avec des images maintenant)
- Ajout du niveau en cours dans la barre d'info
- Ajout d'un separateur entre les info du jeu et le jeu (provisoire a ameliorer)

<span style="color:purple">**A Faire:**</span>
- Continuer de modifier l'affiche des balles
- Essayer de faire une balle carrée 

### Mushtaq Amanh

<span style="color:green">**Fait:**</span>
- Suite du mode infini 

<span style="color:purple">**A Faire:**</span>
-  mode infini
-  

### À faire

- déboguer (nombreux chantiers en cours à finaliser, notamment rebonds en biais)
- relire code et refactoriser

## Semaine 8 (26/3):

### Belhassen Rayan  
<span style="color:green">**Fait:**</span>

- collision: amélioration des collisions de la raquette triangle (pas encore fini)

<span style="color:purple">**A Faire:**</span>

- terminer repair_software 
- retravailler les collisions des raquettes non rectangle 

### Bencheikh Ilias
<span style="color:green">**Fait:**</span>

- Début implémentation des collisions de balles entre eux (dans simulation béta) #55
- Amélioration du menu in-simulation #59
- Début de la rotation graphique de la balle #61
    
<span style="color:purple">**A Faire:**</span>

- Régler le problème de FPS #43
- Implémenter la friction de l'air et de la balle #20
- Améliorer la collision de la balle et des briques (avec Olivier) #52
- Améliorer la structure de la partie physique #49
- Régler les differents problèmes de collisions de balles entre eux #55
- Ajout de fonctionalité dans la simulation (changement de racket et autre)

### Guan Olivier 

<span style="color:green">**Fait:**</span>

- Création d'une interface de développement des briques et balles avec réactions physiques entre-elles (pas fini)

<span style="color:purple">**A Faire:**</span>

- Finir l'outil et les collisions


### Benmalek Majda
<span style="color:green">**Fait:**</span>
- affichage du temps restant
- affichage des rebonds restants
- affichage du niveau
- affichage des vies amélioré
- ameliorations de l'affichage des images

### Amenah MUSHTAQ 
<span style="color:green">**Fait:**</span>
-résolutions de bugs

## Semaine 8 (02/4):

### Guan Olivier 

<span style="color:green">**Fait:**</span>

- Presets de l'environnement avec arrêt/pause (manque affichage Brique à régler + fusionner les implémentation avec collisions entre balles)

<span style="color:purple">**A Faire:**</span>

- Finaliser l'outil

### Belhassen Rayan :
<span style="color:red">**Absent**</span>
-> Justifié

### Bencheikh Ilias :
<span style="color:green">**Fait:**</span>

- test des différents cas de collisions entre deux balles #55
- rotation graphique de la balle (dans la simulation et le jeu) #61
- améliorations des problèmes d'image de balle et des formes de la recket (surchargé lié au problème de FPS #43) 
- Problème de décalage de la balle et de son image réglé (lié au problème de coeff de décalage de la collision avec la racket )
- Ajout de fonctionalité dans la simulation (changement de racket et autre)

<span style="color:purple">**A Faire:**</span>

- Régler le problème de FPS #43
- Implémenter la friction de l'air et de la balle #20
- Améliorer la collision de la balle et des briques (avec Olivier) #52
- Améliorer la structure de la partie physique #49
- Régler les differents problèmes de collisions de balles entre eux #55
- Implémenter le fonctionnement et la gestion de plusieurs balles dans le jeu (seulement présent dans la simulation) #54

### Benmalek Majda
<span style="color:green">**Fait:**</span>
- Création de toute les images pour les balles par rapport au themes
- Modifications de l'affichage des options 

<span style="color:purple">**A Faire:**</span>
- Ajout du son
- Affichage de la racket avec une image et l'adapter en fonctions du theme 

### Mushtaq Amenah
<span style="color:green">**Fait:**</span>
- Mode infini

<span style="color:purple">**A Faire:**</span>
- Bug sur le mode infini a reglé 
- Modification de l'affichage des briques 


## Semaine 9 (23/4):

### Mushtaq Amenah
<span style="color:green">**Fait:**</span>
-absente 
(Remarque : était malade pendant les vacances)

<span style="color:purple">**A Faire:**</span>
- Bug sur le mode infini a reglé 
- Modification de l'affichage des briques 

### Benmalek Majda
<span style="color:green">**Fait:**</span>
- Changement de l'affiche des options
- Couleur de la raquette adapté au thème
- Affichage des règles du jeu : temps et rebonds #57
- Indication des boost/malus sur la raquette
- Ajout du son #21 :
    - Ajout du son de : bouton , la balle (collision raquette et brique), gameOver
    - Ajout d'une musique
    - Contrôle du niveau sonore adéquat
    - Ajout de bouton mute pour le son et la musique dans l'écran de pause

<span style="color:purple">**A Faire:**</span>
- Trouver les sons pour le reste 

### Bencheikh Ilias :
<span style="color:green">**Fait:**</span>
- test des différents cas de collisions entre deux balles #55
- collision entre les balles fini #55
- Problème de décalage de la balle et de son image réglé
- amélioration de la simulation
- amélioration de la structure du code (redeveloppement de la maniere des collision entre entité) #64

<span style="color:purple">**A Faire:**</span>

- Régler le problème de FPS #43
- Implémenter la friction de l'air et de la balle #20
- Améliorer la collision de la balle et des briques (avec Olivier) #52
- Améliorer la structure de la partie physique #49
- Implémenter le fonctionnement et la gestion de plusieurs balles dans le jeu (seulement présent dans la simulation) #54


### GUAN Olivier :
<span style="color:green">**Fait:**</span>

- Résolution de bugs
- Sauvegarde auto & création de profil temporaire
- Refactoring & Clean
- Restructuration des classes des entites, classe interface pour regrouper les entités à affichage graphique 

<span style="color:purple">**A Faire:**</span>

- Finir l'app de démo (en attente du fix sur un prob d'affichage des briques)
- Recherche de bugs


### Belhassen rayan
<span style="color:green">**Fait:**</span>
- améliorer les colisions des raquettes  
- application des textures sur les raquettes  

<span style="color:purple">**A Faire:**</span>
- setup le gradle pour les sauvegardes

### À faire

Ajouts :

- polir les collisions (la balle slenfonce un peu dans le mur de droite)
- réfléchir à soutenance (explication modélisation physique et conception objet)
- préparer belle démo du produit quasi-fini
- ajouter "tests" pour la soutenance : la balle est directement dans la configuration mettant en évidence le phénomène à démontrer

## Semaine 10 (30/4):

### Belhassen rayan
<span style="color:green">**Fait:**</span>
- ajout de niveaux supplémentaires 
- ajout de nouveaux chapitres 
- repair_softwair:
    - adaptation du code pour pouvoir être lancé avec gradle 
    - ajout du gradle (./gradlew repair --console=plain)
    - détection de sauvegarde non fonctionnelle 
    - ouverture de l'emplacement des sauvegardes 
    - enlever la sauvegarde par défaut

<span style="color:purple">**A Faire:**</span>
- travailler sur le rapport 


### GUAN Olivier :
<span style="color:green">**Fait:**</span>

- Résolution de bugs (Collision Balle-Mur invisible, màj du score, reset correct des rules lors du replay, Sauvegarde auto à la fermeture et le bouton quitter)
- Refactoring de certaines classes
- Système gain + affichage de monnaie ([Détails : #69](https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c/issues/69))

<span style="color:purple">**A Faire:**</span>

- Finir l'app de démo (en attente du fix sur un prob d'affichage des briques)
- Page Profil
- Terminal intégré / Afficheur d'erreurs (afficher comme dans un chat) ([#78](https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c/issues/78) [#66](https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c/issues/66))
- Derniers bugs
- Ajouter les nouveaux contenus (balle, raquette, ...) dans les anciens

## Benmalek Majda 
<span style="color:green">**Fait:**</span>
- Debut de la boutique 
- Modif de l'affichage de la balle


<span style="color:purple">**A Faire:**</span>
- Fin de la boutique 
- Recherche des derniers sons
- Rapport
- Factorisation du code

### Bencheikh Ilias :
<span style="color:green">**Fait:**</span>
- test des différents cas de collisions entre deux balles #55
- amélioration des collisions des balles #55
- amélioration de la simulation
- amélioration de la structure du code (redeveloppement de la maniere des collision entre entité) #64
- Implémenter le fonctionnement et la gestion de plusieurs balles dans le jeu #54

<span style="color:purple">**A Faire:**</span>

- Régler les problèmes de lagues dans le jeu du à la gestion de plusieurs balles dans le jeu #74
- Régler le problème de FPS #43
- Implémenter la friction de l'air et de la balle #20
- Améliorer la collision de la balle et des briques (avec Olivier) #52
- Améliorer la structure de la partie physique #49


### Mushtaq Amenah :

<span style="color:green">**Fait:**</span>
- Modifications de l'affichage des briques pour corriger des bugs 

<span style="color:purple">**A Faire:**</span>
- finir l'affichage des briques
- Finir le mode infini (ajouter un bonus pour stopper les briques)
- Factoriser/ commenter les codes
- Rapport


## Du 30/4 a 16/5:
### Belhassen rayan
- création du README
- création du tutoriel :
    - lance un docs qui sert à expliquer le jeu
    - ouvrir le depot git
    - lancer Google 
- repair_softwair: 
    - résolution d'un bug lier a la détection de sauvegarde
    - réparation automatique des sauvegarde 
    - finalisation du docs pour la réparation manuelle
- résolution du bug des balles sans texture
- résolution du bug qui empêchait de lancer le chapitre 3
- résolution du bug avec le bonus "LargeurP" qui ne marcher pas  
- chapitre 2 :
    - rajout de limite de vitesse pour la balle 
    - résolution de mauvaise collision de la balle avec les briques
    - la raquette peut attirer plusieurs balles en même temps 
    - résolution d'une mauvaise rotation de la balle
    - la balle peut s'accrocher sur toute la raquette
- ajout de commentaire dans le code 
- amélioration de "GravityBall" 
- création du chapitre 4
- résolution du bug dans laquel la balle rentre dans la raquette 
- ajout de l'option de faire un saut avec une raquette 
- la raquette ne peut plus sortir du jeu
- résolution du bug qui faisait perdre la balle durant une partie

## Du 30/4 a 16/5:
### Benmalek Majda
- Boutique fini 
- Nouvelle police du jeu 
- Diagramme des classes 
- Affichage visuel de l'état d'un niveau (verrouiller ou pas)


### Amenah MUSHTAQ

- Mode infini terminé
- Résolution de bug 
- Rapport