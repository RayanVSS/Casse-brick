##Semaine 1:
    ###Benmalek Majda
    <span style="color:green">**Fait:**<\span>
        Un debut primaire de la balle, avec juste les collisions avec les 3 (nord, est, ouest) limites de la fenetre
    <span style="color:purple">**A Faire:**<\span>
        Les menu (acceuil(pour s2), option, game over, fin de niveau, une bonne gestion de l'animation)
        
    ###Mushtaq Amenah 
    <span style="color:green">**Fait:**<\span>
        une 1ère version d'un affichage des briques
    **A Faire:**
        faire la mise à jour de l'affichage des briques au cours de la partie (càd si des briques se cassent etc...)
        
    ###Olivier
    <span style="color:green">**Fait:**<\span>
        Initialisation de la disposition classique des briques, briques de différents types, préparation pour implémenter les différentes dispositions possibles
    <span style="color:purple">**A Faire:**<\span>
        Update de la partie, différentes config de map...
    
    ###Bencheikh Ilias
    <span style="color:green">**Fait:**<\span>
         Implementation du gradle et d'une base pour le jeu + commencer a implementer le moteur physique
    <span style="color:purple">**A Faire:**<\span>
        finir le moteur du jeu et commencer a approfondir la partie physique

    ###Belhassen rayan
    <span style="color:green">**Fait:**<\span>
        implémentation du code de la racket 
    <span style="color:purple">**A Faire:**<\span>
        collisions de la racket
        affichage de la racket

##Semaine 2:
    ###Belhassen rayan
    <span style="color:green">**Fait:**<\span>
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
    <span style="color:purple">**A Faire:**<\span>
        rien
