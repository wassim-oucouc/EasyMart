🛒 EASYMART – Application Web de Gestion Commerciale
📌 Présentation

EASYMART est une application web de gestion commerciale développée pour MicroTech Maroc.
Elle permet de gérer efficacement un portefeuille d’environ 650 clients actifs, avec :

🎯 Un système de fidélité à remises progressives

💳 Des paiements fractionnés

📦 La gestion des produits

🧾 La gestion des commandes

👥 La gestion des clients

🔐 Un système d’authentification utilisateur

L’application est conçue selon une architecture RESTful en utilisant Spring Boot.

🏗️ Architecture Technique

Backend : Spring Boot (Java)

API : REST

Validation : Jakarta Validation (@Valid)

Session : HttpSession

Pagination : Spring Data

DTO Pattern : Request / Response DTOs

Sécurité : Authentification via session + annotations AOP (@Secured)

🔐 Authentification – /api/auth
➕ Inscription
POST /api/auth/register


Body (JSON) :

{
  "username": "user1",
  "password": "password123"
}


Réponse :

{
  "id": 1,
  "username": "user1",
  "role": "ADMIN"
}

🔑 Connexion
POST /api/auth/login?username={username}&password={password}


Crée une session utilisateur (CURRENT_USER)

🚪 Déconnexion
POST /api/auth/logout

👥 Gestion des Clients
➕ Créer un client
POST /client/create


Body (JSON) :

{
  "nom": "Client A",
  "email": "client@email.com",
  "telephone": "0600000000"
}

✏️ Modifier un client
PUT /client/update/{id}

❌ Supprimer un client
DELETE /client/delete/{id}

📋 Lister tous les clients
GET /client/all

📦 Gestion des Produits
➕ Créer un produit
POST /product/create

✏️ Modifier un produit
PUT /product/update/{id}

❌ Supprimer un produit
DELETE /product/delete/{id}

📄 Liste paginée des produits
GET /product/all/{size}/{page}


Exemple :

GET /product/all/10/0

🧾 Gestion des Commandes
➕ Créer une commande
POST /create/commande

✅ Confirmer une commande
PUT /confirm/commande?orderId={id}

❌ Rejeter une commande
PUT /reject/commande?orderId={id}

💳 Gestion des Paiements
💰 Effectuer un paiement (paiement fractionné possible)
POST /make/paiement


Body (JSON) :

{
  "commandeId": 1,
  "montant": 500
}

⭐ Fonctionnalités Clés

✔️ Gestion de 650+ clients

✔️ Remises progressives basées sur la fidélité

✔️ Paiements fractionnés

✔️ Suivi des commandes (création, confirmation, rejet)

✔️ Pagination des produits

✔️ Séparation claire DTO / Service / Controller

✔️ Sécurité par session

🚀 Lancement du Projet
Prérequis

Java 17+

Maven

Base de données (MySQL / PostgreSQL)

Démarrage
mvn clean install
mvn spring-boot:run


L’API sera accessible sur :

http://localhost:8080

🧑‍💻 Auteur

Projet développé pour MicroTech Maroc
Nom du projet : EASYMART
