**Attention : Cette branch est la branche active sur laquelle les modifications sont apportées.
Pour consulter la branche qui contient le projet tel qu'il était lors de sa présantation, rendez vous [ici](https://github.com/Darkbriks/L2_LOO_Projet_2/tree/v1).**

# L2_LOO_Projet_2 : AQUA ESCAPE

Aqua Escape est un jeu vidéo de type "Scramble" (ou "Scramble Like") dans lequel le joueur incarne une sirène qui doit s'échapper d'un labyrinthe sous-marin tout en évitant les obstacles et les ennemis.

## Présentation

Le jeu est actuellement composé de 3 niveaux, de difficulté croissante, dans lesquels le joueur doit atteindre la sortie du labyrinthe en évitant les obstacles et les ennemis.
La sortie est symbolisée par une statue de sirène à la fin du niveau.
Le joueur peut se déplacer dans toutes les directions, et dispose d'un tir principal et d'un tir secondaire pour se défendre.
Si le joueur entre en collision avec un mur, il est tué et doit recommencer le niveau, ou revenir à un checkpoint s'il en a atteint un.
Si le joueur entre en collision avec un projectile ou un ennemi, il perd une quantité donnée de points de vie, dépendant de l'ennemi ou du projectile.
Quand le joueur n'a plus de points de vie, il est tué et doit recommencer le niveau, ou revenir à un checkpoint s'il en a atteint un.
Les ennemis peuvent être tués en leur tirant dessus, et le joueur gagne des points en les tuant.
Le joueur peut également ramasser des power-ups pour améliorer la puissance de ses tirs, pour augmenter sa vitesse de déplacement, ou pour augmenter sa santé.
Au début de chaque niveau, le joueur peut également collecter une fraise dorée. Si il subit n'importe quelle quantité de dégâts, il perd la fraise et doit recommencer le niveau pour la récupérer.
Si il atteint la sortie du niveau en possédant la fraise, il obtient un bonus permanent de points de vie (proportionnel à la difficulté du niveau).

## Installation

*En cours de rédaction*

## Commandes

Le jeu peut être contrôlé à l'aide du clavier ou d'une manette de jeu (seulement testé avec une DualShock 4 et une DualSense).
La manette de jeu est recommandée pour une meilleure expérience de jeu.

### Clavier

- **Z** : Monter
- **Q** : Gauche
- **S** : Descendre
- **D** : Droite
- **Espace** : Tir principal
- **Shift** : Tir secondaire
- **Echap** : Pause
- **Flèche Haut** : Monter
- **Flèche Gauche** : Gauche
- **Flèche Bas** : Descendre
- **Flèche Droite** : Droite

### Manette de jeu

- **Stick Gauche** : Déplacement
- **Carre** : Tir principal
- **R1** : Tir secondaire
- **Options** : Pause

Sur linux et windows, la manette de jeu produit des vibrations lors de certains évènements.
Sur MacOS, les vibrations ne sont pas supportées.

## Guide du jeu

### Les niveaux

Le jeu est composé de 3 niveaux, de difficulté croissante.

#### Niveau 1 :

Le premier niveau, qui est le plus simple, consiste en un labyrinthe simple, sans ennemis, pour permettre au joueur de se familiariser avec les commandes de déplacement.

##### Objets :

Le niveau 1 contient 3 checkpoints et 1 fraise dorée.
Cette fraise dorée étant la plus facile à obtenir, elle donne un bonus permanent de 25 points de vie.

#### Niveau 2 :

Le deuxième niveau est plus complexe, avec des ennemis et des obstacles plus difficiles à éviter.

##### Objets :

Le niveau 2 contient 2 checkpoints, 1 power-up de vitesse, et 1 fraise dorée.
Cette fraise dorée étant plus difficile à obtenir, elle donne un bonus permanent de 50 points de vie.

#### Niveau 3 :

Le troisième niveau est le plus complexe et le plus long, avec un boss à la fin. Ce boss dispose de 3 phases de difficulté croissante.

##### Objets :

Le niveau 3 contient 4 checkpoints, 1 power-up de dégâts, 1 power-up de vitesse, 1 power-up de santé, et 1 fraise dorée.
Cette fraise dorée étant la plus difficile à obtenir, elle donne un bonus permanent de 125 points de vie.

### Les ennemis

#### Tortue :

La tortue est un ennemi basique qui se déplace de manière linéaire, en suivant un chemin prédéfini.
Elle ne tire pas, dispose de 10 points de vie, inflige 10 points de dégâts lorsqu'elle entre en collision avec le joueur, et rapporte 100 points lorsqu'elle est tuée.

#### Gros Poisson scie :

Le gros poisson scie est un ennemi légèrement plus complexe qui se déplace en utilisant une interpolation exponentielle sur une chemin prédéfini.
Il ne tire pas, dispose de 15 points de vie, inflige 10 points de dégâts lorsqu'il entre en collision avec le joueur, et rapporte 200 points lorsqu'il est tué.

#### Méduse :

La méduse est un ennemi qui se déclanche lorsqu'elle détecte le joueur à proximité.
Quand elle est déclanchée, elle monte vers la surface de manière très rapide.
Elle ne tire pas, dispose de 5 points de vie, inflige 15 points de dégâts lorsqu'elle entre en collision avec le joueur, et rapporte 25 points lorsqu'elle est tuée.

#### Anguille electrique :

L'anguille electrique est un ennemi qui se déplace de manière linéaire, en suivant un chemin prédéfini.
Quand le joueur s'approche, elle déclanche une attaque électrique dans une vastre zone autour d'elle, qui inflige 50 points de dégâts.
Elle dispose de 50 points de vie, inflige 10 points de dégâts lorsqu'elle entre en collision avec le joueur, et rapporte 500 points lorsqu'elle est tuée.

#### Boss :

Le boss qui garde la sortie du labyrinthe est un ennemi très puissant qui dispose de 3 phases de difficulté croissante.
Il s'agit d'un sous-marin qui avance a la même vitesse que le joueur.
Chaques phases dispose de patterns de tirs prédéfinis qui sont déclanchés à intervalles réguliers.
Le pattern déclanché par le boss est aléatoire.

##### Phase 1 :

Durant la première phase, le boss dispose de 100 points de vie, et de 2 patterns de tirs possibles.
Le premier pattern consiste en un tir de 5 roquettes en ligne droite, et le deuxième pattern consiste en un tir de 5 roquettes en éventail.

##### Phase 2 :

Durant la deuxième phase, le boss dispose de 200 points de vie, et de 4 patterns de tirs possibles.
Les 2 patterns de la première phase sont toujours possibles, et 2 nouveaux patterns sont ajoutés.
Le troisième pattern consiste en un tir de 15 roquettes qui se dirigent vers le haut de l'écran.
Le quatrième pattern consiste en un tir de 20 éclairs en cercle autour du boss. La durée de balayage est de 1 seconde.

##### Phase 3 :

Durant la troisième phase, le boss dispose de 300 points de vie, et de 5 patterns de tirs possibles.
Seul le quatrième pattern de la deuxième phase est conservé, et 4 nouveaux patterns sont ajoutés.
Le premier ajout consiste en un tir pseudo-aléatoire de 40 éclairs en cercle autour du boss en 2 secondes.
Le deuxième ajout consiste en un tir de 50 éclairs en cercle autour du boss en 0.25 secondes.
Le troisième ajout consiste en 10 balayages d'éclairs en cercle autour du boss en 7.5 secondes. Chaque balayage génère 75 éclairs, ce qui fait un total de 750 éclairs.
Le dernier ajout consiste en un double balayage d'éclairs en cercle autour du boss en 6 secondes. Les 2 balayages sont simultanés et ont une rotation opposée. Chaques balayage génère 60 éclairs, ce qui fait un total de 120 éclairs.

##### Dégâts des patterns :

Les roquettes des phases 1 et 2 infligent 10 points de dégâts.
Les éclairs des phases 2 et 3 infligent 20 points de dégâts.

### Tir principal et tir secondaire du joueur

#### Tir principal :

Le tir principal du joueur est un tir simple qui se déplace en ligne droite, et qui inflige 10 points de dégâts à l'ennemi touché.
Le joueur peut tirer 2 fois par seconde.

#### Tir secondaire :

Le tir secondaire du joueur est un tir plus puissant qui suit la trajectoire d'une bombe.
Ce tir inflige 30 points de dégâts à l'ennemi touché, et se recharge en 1.5 secondes.

### Power-ups

#### Power-up de vitesse :

Le power-up de vitesse augmente la vitesse de déplacement du joueur de 20% pendant toute la durée du niveau.

#### Power-up de dégâts :

Le power-up de dégâts augmente la puissance des tirs du joueur de 20% pendant toute la durée du niveau.

#### Power-up de santé :

Le power-up de santé augmente la santé maximale du joueur de 50% pendant toute la durée du niveau.

## Modes de cheat

Pour faciliter les tests et le développement, le jeu dispose de plusieurs modes de cheat, qui sont toujours utilisables.

### Mode invincible (God mode)

Le mode invincible permet au joueur de ne subir aucun dégât, que ce soit des collisions avec les ennemis, avec les murs, ou avec les projectiles.
Il est activé en appuyant sur la touche **F1**.

### Mode debug

Le mode debug permet d'afficher les collisions.
**Attention : Ce mode réduit considérablement les performances du jeu.**
Il est activé en appuyant sur la touche **F2**.

### Accélération de la vitesse de la caméra

Si vous utilisez une manette de jeu, vous pouvez accélérer la vitesse de la caméra en appuyant sur **R2**.
Cela va multiplier la vitesse de la caméra par 5 pendant toute la durée de l'appui.

## Architecture du code

Le coeur du jeu est basé sur une architecture similaire à celle du moteur de jeu Unity, avec des objets de jeu qui héritent de la classe `GameObject`, et qui peuvent être composés de plusieurs `Component` qui gèrent les différents aspects de l'objet.
Le coeur est situé dans le package `core/src/com/scramble_like/game/essential`.

### ScrambleLikeApplication

La classe `ScrambleLikeApplication` est la classe principale du jeu. C'est un singleton pour qu'elle puisse être accédée de n'importe où dans le code.
C'est elle qui contient le `ShapeRenderer`, le `SpriteBatch`, la `BitmapFont` et la `GameCamera`, qui sont utilisés pour le rendu du jeu.
Elle contient également la liste de tous les objets qui implémentent l'interface `TickableObject`.

### Scene

La classe `Scene` est une classe abstraite qui représente une scène du jeu. Elle contient une liste d'objets de jeu, et gère leur mise à jour et leur rendu.
C'est elle qui est responsable de la boucle de jeux, et qui appelle les differantes méthode des `GameObject` (comme `Begin`, `Update`, `Render`, `Destroy`).
Elle dispose également d'une méthode `LateUpdate` qui est appelée après la mise à jour de tous les objets, et qui est utilisée pour supprimer et ajouter des objet dans la scéne.

### GameObject

La classe `GameObject` est une classe abstraite qui représente un objet de jeu. Elle contient une liste de `Component` qui gèrent les différents aspects de l'objet.

### Component

La classe `Component` est une classe abstraite qui représente un composant d'un objet de jeu. Elle contient une référence vers l'objet auquel elle est attachée, et peut surcharger plusieurs méthodes pour gérer les différents aspects de l'objet.

### Packages

#### Essential

##### Chaos

Chaos est le package qui contient les éléments relatifs aux collisions.
La classe abstaite `Collider` contient toutes les méthodes pour vérifier si deux objets sont en collision. Elle hérite de `Component`.
La classe `CircleCollider` représante un collider en forme de cercle, et hérite de `Collider`
La classe `AABBCollider` (Axis Aligned Bounding Box) représante un collider en forme de rectangle sans rotation, et hérite de `Collider`
La classe `TileCollider` représante un collider qui as une position donnée, qui n'est pas relatives a celle du `GameObject` parent.
La classe `CollisionTask`

*En cours de rédaction*

## Crédits

### Musiques

- [Vigil - Mass Effect - Jack Wall](https://www.youtube.com/watch?v=Ea1eWarxdxQ) (Menu)
- [Horizon Beach - Monster Sanctuary - Steven Melin](https://www.youtube.com/watch?v=Kuwjxgo7fQU) (Niveau 1)
- [The Vents - Ecco the Dolphin - Spencer Nilsen](https://youtu.be/DaoshZkavwY?si=TAF3B0NCwG1W4rvc) (Niveau 2)
- [Confronting Myself - Celeste - Lena Raine](https://www.youtube.com/watch?v=b_oEDGONSc4) (Niveau 3)

*En cours de rédaction*