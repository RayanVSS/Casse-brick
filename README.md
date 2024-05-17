
# 🧱Casse brique « physique » 

Nous sommes le groupe AD1-C qui a eux le [sujet](https://moodle.u-paris.fr/pluginfile.php/2299294/mod_resource/content/4/Projet_breakout.pdf) "Casse brique" dans lequel on doit recréer le jeu éponyme en développant un moteur physique adapter
## sommaire

- [Installation](#Installation)
- [Lancement du jeux](#Lancement-du-jeux)
- [Contributing](#Contributing)

## Installation

Le jeu requière d'avoir :
  - au moin la version 8 de [Java](https://www.java.com/en/download/manual.jsp)
  - [gradle](https://gradle.org/install/)

Pour obtenir les fichiers du jeu :
```bash
clone https://gaufre.informatique.univ-paris-diderot.fr/guano/2023-ad1-c.git
cd 2023-ad1-c
```
    
## Lancement du jeux

Pour notre projet, on utilise **gardle** afin de pouvoir bien séparer chaque partie du projet voici les differentes commandes : 

le jeu:
```bash
.\gradlew run
```

le moteur physique:
```bash
.\gradlew physic
```

test du moteur physique:
```bash
.\gradlew demoEnv
```

logiciel de maintenance pour les sauvegardes 
```bash
.\gradlew Repair
```

## Contributing

Tous les participants sont notés dans le fichier `contact.csv` .




![Logo](https://blogs.aphp.fr/wp-content/blogs.dir/214/files/2022/05/UniversiteParisCite_logo_horizontal_couleur_CMJN.jpg)

