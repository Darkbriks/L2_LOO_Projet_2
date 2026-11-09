# Changelog
Tous les changements notables concernant le cœur du jeu et l'aspect graphique seront documentés dans ce fichier.

Le format est basé sur [Keep a Changelog](https://keepachangelog.com/fr/1.1.0/), et ce projet adhère à [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Corrigé
- Effondrement progressif du framerate dans les niveaux : `GameObject.componentsToRemove` n'était jamais vidée, et chaque frame re-parcourait tous les composants déjà retirés (tuiles des chunks déchargés). Coût en O(retirés × composants), jusqu'à ~100 ms/frame en fin de niveau 1.
- Les retraits de composants se font maintenant en une seule passe (`removeAll` + `HashSet`), ce qui supprime les saccades de 10 à 25 ms au déchargement d'un chunk.
- `Collider.Update` créait un `ForkJoinPool` (donc des threads) par collider et par frame, soit ~70 µs par appel et ~16 000 threads/s dans le niveau 2. La tâche de collision est désormais exécutée via `invoke()`.
- Trous dans les colliders des bordures : les cases situées après la fin d'une ligne de moins de 50 caractères (`'\0'`) n'étaient pas considérées comme vides, et les murs voisins ne recevaient pas de collider (275 tuiles concernées sur les 3 niveaux). Toute case sans tuile compte maintenant comme non-collidable.
- Rayon de simulation des chunks porté de 1250 à 1400 : le joueur peut s'éloigner de 750 unités du centre de la caméra, et les murs du chunk précédent n'avaient pas de collision quand il longeait le bord gauche de l'écran.
- La distance aux chunks ignorait la position verticale de la caméra : au niveau 3, tous les chunks restaient chargés et simulés en permanence.
- `SpatialGrid` : chaque collider était enregistré deux fois et laissait une entrée fantôme à chaque changement de cellule (la suppression utilisait la nouvelle position). Les colliders mémorisent désormais leur cellule.
- Niveau 3 : la hauteur de la caméra pendant le boss dépendait du moment où le joueur déclenchait le passage au scrolling horizontal (jusqu'à 600 unités d'écart). La caméra est maintenant interpolée vers la hauteur du déclencheur (`GameConstant.CAMERA_ALIGNMENT_DURATION`).
- La taille réelle de la fenêtre n'était pas prise en compte (`ScreenViewport`) : le monde affiché, le HUD et les limites du joueur dépendaient de la taille de la fenêtre. Le jeu affiche désormais toujours 1600×900 unités, avec des bandes noires si nécessaire (`FitViewport`).
- Splash screen positionné en pixels de fenêtre et texte mal centré.

### Modifié
- Texte : la police bitmap 17 px agrandie jusqu'à 10× est remplacée par Noto Sans (licence OFL, `assets/UI/`) générée via FreeType à 4× la taille avec mipmaps. Le `Skin` est créé une seule fois et partagé entre les scènes.

### Ajouté
- Mode autoplay / profiling activable par propriétés système (`-Dae.level`, `-Dae.profile`, `-Dae.duration`, `-Dae.god`, `-Dae.debug`, `-Dae.shoot`, `-Dae.shots`, `-Dae.window`, `-Dae.bot`), voir `essential/utils/Profiler.java` et `Autoplay.java`.

---