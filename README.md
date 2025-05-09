# PersonnelApp
Améliorations dans EmployeeService :

1.Validation avant mise à jour : Tu pourrais vérifier si l'employé existe avant de le mettre à jour, sinon save() va en créer un nouveau s’il ne trouve pas l’ID.
2.Gestion d’erreurs ou exception personnalisée (facultatif mais utile) : Pour améliorer la lisibilité côté contrôleur ou REST API.

Test rapide avec Postman ou frontend :

Méthode	URL	Corps (JSON)	Action
GET	/api/departements	-	Liste tous les départements
GET	/api/departements/1	-	Détails du département id=1
POST	/api/departements	{ "name": "Informatique" }	Ajoute un département
PUT	/api/departements/1	{ "name": "RH" }	Modifie le département 1
DELETE	/api/departements/1	-	Supprime le département 1


dans le controlleur :
Vérifie les exceptions non gérées
L'erreur 500 pourrait être causée par une exception non gérée dans ton service ou contrôleur. Par exemple, cela pourrait se produire si tu essaies de manipuler une entité qui n'existe pas, ou si une opération échoue de manière inattendue.

Exemple de gestion des exceptions dans un contrôleur :

@ExceptionHandler(Exception.class)
public ResponseEntity<String> handleException(Exception e) {
// Log l'exception ici (optionnel)
e.printStackTrace();
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur interne s'est produite : " + e.getMessage());
}
Cela va capturer toutes les exceptions qui ne sont pas déjà gérées par Spring, et tu pourras retourner un message plus spécifique au client.