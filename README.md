# TennisApp

---

TennisApp est une application console Java qui simule une partie de tennis (règles officielles, score, égalité, avantage, victoire) avec une architecture hexagonale, des tests unitaires, et l'utilisation du pattern Strategy pour les règles métier.

La logique métier (calcul du score, transitions d'état, gestion des règles) est organisée dans le dossier `src/main/java/domain/core/` :
- `model/` : objets métier (joueur, score)
- `rule/` : toutes les règles de gestion du score et de transition d'état
- `state/` : les différents états possibles d'une partie (Playing, Deuce, Win...)

L'orchestration d'une partie se trouve dans `usecase/`, et l'interface console (saisie et affichage) dans `adapter/in/cli/`.

---

## Lancement
- Lancer la classe `TennisApp` pour jouer en console
- Exécuter les tests avec `mvn test`

---
