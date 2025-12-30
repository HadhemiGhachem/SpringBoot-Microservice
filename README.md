Ce projet est un mini-projet de gestion de salle de sport développé avec Spring Boot et Spring Cloud en architecture microservices. Il gère les coachs, membres, séances d'entraînement et authentification des utilisateurs via JWT, avec une API Gateway pour la sécurité et la découverte de services via Eureka.
​

Fonctionnalités Principales

Le système inclut quatre microservices métiers : Coach (CRUD coachs), Member (CRUD membres), Seance (gestion des séances et associations coach/membres) et User (authentification, login/signup avec JWT). Les communications synchrone entre services utilisent OpenFeign, tandis que Config Server centralise les configurations.
​

Architecture

Eureka sert de registre de découverte dynamique, l'API Gateway route les requêtes et valide les tokens JWT pour sécuriser les endpoints protégés. Chaque microservice est indépendant, scalable et utilise des DTOs pour exposer les données via des API REST.
​

Technologies Utilisées

Spring Boot pour les microservices

Spring Cloud (Eureka, Config Server, Gateway, OpenFeign)

Spring Security et JWT pour l'authentification

Bases de données avec entités JPA (Coach, Member, Seance, User)
