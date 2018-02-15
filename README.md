# TP Génie Logiciel

- Premier TP de Génie logiciel

## Description

- Écrire un logiciel qui gère le fonctionnement d'une machine à café 
- Pour une association d'étudiants
- Interface homme-machine basique en lignes de commandes

## Spécifications client

- La machine peut faire les actions suivantes
```
- Acheter une boisson
- Ajouter une boisson
- Modifier une boisson
- Supprimer une boisson
- Ajouter un ingrédient
- Verifier le stock d'ingrédients
- Augmenter la quantité d'un ingrédient du stock
- Augmenter la quantité de tous les ingrédients du stock
- Quitter
```

## Pré-requis

[Java 8](https://java.com/fr/download/)

La machine à café a été étudiée pour fonctionner sur le système d'exploitation Windows 10.
Les versions Linux et MacOS ne sont pas disponibles pour l'instant.

## Téléchargement et utilisation

[Dépôt github](https://github.com/Ncalley/TPGenieLogiciel)
- Télécharger le dossier au format .Zip
- Décompresser le fichier à l'aide de votre programme préféré : [7zip](http://www.7-zip.org/download.html) ou [Winrar](https://www.win-rar.com/download.html?&L=0) (attention, Winrar n'est pas censé être gratuit).
- Aller dans le dossier *dist*.
- Exécuter le programme **Coffee.jar** .
- Profiter des nombreuses fonctions de notre fabuleuse machine.

## Documentation

La documentation du programme se trouve dans le dossier *dist/javadoc*.
Une fois dans ce dossier, il est possible d'afficher la documentation en ouvrant le fichier **index.html**

## Politique et fonctionnement de l'application

### Une prise en main intuitive

Tout au long de votre expérience auprès de notre machine à café, votre chemin sera aiguillé et les choix disponibles et les éventuelles erreurs effectuées clairement indiqués afin de réduire au maximum l'effet :
*"Pourquoi ça marche pas ?"*
De plus notre interface graphique simplifiée vous permettra de parcourir en toute simplicité les nombreux menus et options à votre disposition.

### Une grande liberté de gestion

Nous avons préféré laisser le choix de composition des boissons vendues par la machine à l'utilisateur. Ainsi comme spécifié dans les **Spécifications client** l'utilisateur a toujours la liberté de créer les boissons de son choix avec pour seul contrainte d'y ajouter au moins un élément liquide. La gestion du prix est également laissée à la libre appréciation de l'utilisateur, ainsi vous pouvez décider de vendre de l'eau sucrée à un prix exorbitant, après tout ce sont vos clients ~~(Peut-être que cette eau sucrée se vendra mieux si on l'appelle **IEau**)~~.

### Une application Open-source

Nous avons pris le parti de laisser les sources de l'application à disposition dans le dossier *src*. La raison principale étant l'évaluation du code de l'application par un éminent professeur.

### Des données constamment à jour et sécurisées

Chaque modification des stocks de la machine est enregistrée dans le fichier *Data/data.csv* de manière cryptée **(Créé automatiquement au premier démarrage de la machine)**. Ainsi un programme externe ne peut pas accéder aux données de la machine pour les modifier. Il est recommandé de ne pas retirer le fichier ni le supprimer sous peine de provoquer une erreur de la machine. Si vous décidez de réinitialiser la machine, il suffit de supprimer ce fichier et de relancer la machine. Elle sera alors dotée d'une mémoire toute neuve.

## Contacts

[Nicolas CALLEY](nicolas.calley@gmail.com) : Chef de projet - Développeur
[Thomas GALL](thomas.gall1@etu.univ-lorraine.fr) : Concepteur - Analyste

Nous seront heureux de répondre à vos questions concernant l'application, de recevoir votre feedback et vos suggestions d'améliorations.
