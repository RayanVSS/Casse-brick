# 🧱 Casse-Brique « Physique »

Nous sommes le groupe AD1-C qui a eu le [sujet](https://moodle.u-paris.fr/pluginfile.php/2299294/mod_resource/content/4/Projet_breakout.pdf) "Casse-Brique" dans lequel nous devons recréer le jeu éponyme en développant un moteur physique adapté.

## Sommaire

- [Installation](#installation)
- [Lancement du jeu](#lancement-du-jeu)
- [Contribution](#contribution)

## Installation

Le jeu requiert :
  - au moins la version 8 de [Java](https://www.java.com/en/download/manual.jsp)
  - [Gradle](https://gradle.org/install/)

Pour obtenir les fichiers du jeu :
```bash
git clone https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c.git
cd 2023-ad1-c
```

## Lancement du jeu

Pour notre projet, nous utilisons **Gradle** afin de pouvoir bien séparer chaque partie du projet. Voici les différentes commandes :

Pour lancer le jeu :
```bash
./gradlew run
```

Pour lancer le moteur physique :
```bash
./gradlew physic
```

Pour le logiciel de maintenance des sauvegardes :
```bash
./gradlew repair --console=plain
```

## Contribuer

Tous les membres du projet sont notés dans le fichier `contact.csv`.

![Logo](https://blogs.aphp.fr/wp-content/blogs.dir/214/files/2022/05/UniversiteParisCite_logo_horizontal_couleur_CMJN.jpg)
