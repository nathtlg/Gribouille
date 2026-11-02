# Gribouille

Application de dessin libre en **JavaFX** : on dessine à la souris au crayon ou avec des étoiles, on choisit la couleur et l'épaisseur, et on sauvegarde ou recharge ses dessins dans un fichier texte.
 
Projet réalisé à l'**IUT Grand Ouest Normandie** (BUT Informatique), **en solo**.

> **Le projet complet et à jour se trouve sur la branche [`gribouille_stable`](https://github.com/nathtlg/Gribouille/tree/gribouille_stable).** Les autres branches contiennent des états intermédiaires.

## 1. Fonctionnalités
 
| Domaine | Ce que fait l'application |
|---|---|
| **Outils** | **Crayon** (tracé libre) et **Étoile** (rayons partant d'un centre) |
| **Couleurs** | Sélecteur de couleur (`ColorPicker`) + 8 pastilles de couleurs rapides |
| **Épaisseur** | De 1 à 9, via le menu ou le clavier |
| **Fichiers** | Sauvegarde et chargement d'un dessin au format texte |
| **Sécurité** | Le titre de la fenêtre affiche `*` si le dessin est modifié, et une boîte de dialogue propose de sauvegarder avant de quitter |
| **Barre de statut** | Position de la souris (X, Y), épaisseur, outil et couleur courants |
| **Fenêtre** | Le canevas s'adapte à la taille de la fenêtre et le dessin est redessiné |

### Menus
 
- **Dessin** : Charger, Sauvegarder, Quitter, Effacer
- **Outils** : Épaisseur (1 à 9), Crayon, Étoile

| Touche | Action |
|---|---|
| `c` / `e` | Outil Crayon / Étoile |
| `1` à `9` | Épaisseur |
| `r` `n` `b` `w` | Rouge, noir, bleu, blanc |
| `p` `a` `v` `y` | Rose, bleu clair, vert, jaune |

## 2. Prérequis et lancement
 
- **Java 21**
- **Maven**
- JavaFX 21.0.2 (récupéré automatiquement par Maven)
```bash
git clone https://github.com/nathtlg/Gribouille.git
cd Gribouille
git checkout gribouille_stable
cd Gribouille
mvn clean javafx:run
```
 
> **Attention :** dans le `pom.xml`, la classe principale est déclarée `iut.gon.test3.App`, un ancien nom. Si `mvn javafx:run` échoue, remplacez-la par `iut.gon.gribouille.App`. Sinon, lancez `App.java` directement depuis votre IDE (Eclipse, IntelliJ).
 
---

## 3. Architecture
 
Le projet suit le modèle **MVC** : le modèle, les contrôleurs JavaFX et les vues FXML sont séparés.
 
```
Gribouille/
├── pom.xml
└── src/main/
    ├── java/
    │   ├── module-info.java
    │   └── iut/gon/gribouille/
    │       ├── App.java                  Point d'entrée, fenêtre et raccourcis clavier
    │       ├── Dialogues.java            Boîtes de dialogue (quitter / sauvegarder)
    │       ├── Outil.java                Classe abstraite d'un outil de dessin
    │       ├── OutilCrayon.java          Tracé libre
    │       ├── OutilEtoile.java          Étoile (rayons depuis un centre)
    │       ├── controleurs/
    │       │   ├── Controleur.java       Contrôleur principal (état, outils, fichiers)
    │       │   ├── MenusController.java
    │       │   ├── DessinController.java Canevas et événements souris
    │       │   ├── CouleursController.java
    │       │   └── StatutController.java
    │       └── modele/
    │           ├── Dessin.java           Liste de figures, sauvegarde/chargement
    │           ├── Figure.java           Classe abstraite d'une figure
    │           ├── Trace.java            Figure « tracé libre »
    │           ├── Etoile.java           Figure « étoile »
    │           └── Point.java            Point (x, y)
    └── resources/iut/gon/gribouille/
        ├── CadreGribouille.fxml          Fenêtre principale (assemble les autres)
        ├── menus.fxml
        ├── dessin.fxml
        ├── couleurs.fxml
        └── statut.fxml
```

### Choix de conception
 
- **Une vue FXML par zone**, assemblées avec `fx:include`, chacune avec son contrôleur. Le `Controleur` principal les coordonne.
- **Un patron Strategy pour les outils** : `Outil` définit `onMousePress` et `onMouseDrag`, et chaque outil (`OutilCrayon`, `OutilEtoile`) les implémente. Ajouter un outil revient à ajouter une sous-classe.
- **Des propriétés JavaFX observables** (`couleur`, `epaisseur`, `estModifie`, nom du fichier) et du binding pour la barre de statut et le titre de la fenêtre.
- **Un modèle indépendant de l'interface** : `Dessin` stocke des `Figure`, qui stockent des `Point`.
---

 
Le dessin est enregistré en texte brut, **une figure par ligne** :
 
```
T <épaisseur> <couleur> <nbPoints> <x1> <y1> <x2> <y2> ...
E <épaisseur> <couleur> <nbPoints> <x1> <y1> ... <xCentre> <yCentre>
```
---
## 5. Contributions et code fourni
 
Projet réalisé **seul**. Certaines classes du package `modele` ont été **fournies en partie par l'enseignant de la ressource**, puis **retouchées et complétées** :
 
| Classe | Origine |
|---|---|
| `Point`, `Figure`, `Trace`, `Etoile`, `Dessin` (package `modele`) | Base fournie par l'enseignant |
| Tout le reste (`App`, `Dialogues`, `Outil*`, contrôleurs, FXML) | Réalisé en solo |
 
---
 
