# Changelog
Tous les changements notables concernant le cœur du jeu et l'aspect graphique seront documentés dans ce fichier.

Le format est basé sur [Keep a Changelog](https://keepachangelog.com/fr/1.1.0/), et ce projet adhère à [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [Unreleased]

### Corrigé
- Effondrement progressif du framerate dans les niveaux : `GameObject.componentsToRemove` n'était jamais vidée, et chaque frame re-parcourait tous les composants déjà retirés (tuiles des chunks déchargés). Coût en O(retirés × composants), jusqu'à ~100 ms/frame en fin de niveau 1.
- Les retraits de composants se font maintenant en une seule passe (`removeAll` + `HashSet`), ce qui supprime les saccades de 10 à 25 ms au déchargement d'un chunk.
- `Collider.Update` créait un `ForkJoinPool` (donc des threads) par collider et par frame, soit ~70 µs par appel et ~16 000 threads/s dans le niveau 2. La tâche de collision est désormais exécutée via `invoke()`.

### Ajouté
- Mode autoplay / profiling activable par propriétés système (`-Dae.level`, `-Dae.profile`, `-Dae.duration`, `-Dae.god`), voir `essential/utils/Profiler.java`.

---