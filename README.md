# Simulation d'Epicerie

Langage : Java

## Description

Jeu faisant une légère représentation d'une épicerie cherchant à régler une dette en fructifiant son magasin.
Le projet est une évolution de la simulation de ferme visant à maîtriser des mécaniques plus avancées en Java telles que la notion d'héritage, d'interface ou de classe abstraite.

### `Aliments`

Interface représentant tous les aliments à vendre du jeu :
  * Ananas.java
  * Artichaut.java
  * Boeuf.java
  * Carotte.java
  * Chou.java
  * Fromage.java
  * Miel.java
  * Oeuf.java
  * Poire.java
  * Poisson.java
  * Pomme.java
  * Porc.java
  * Poulet.java
  * Tofu.java

### `Client`

Classe abstraite permettant la création de différents types de clients :
  * Classique.java
  * Riche.java
  * Habitue.java

### `Article`

Classe abstraite permettant la création de la classe Aliment.java et de fonction de stockage et destockage de l'épicerie

### `Epicerie`

Classe principale créant l'entièreté du jeu et son tutoriel

### `TestEpicerie`

Lancement du jeu
