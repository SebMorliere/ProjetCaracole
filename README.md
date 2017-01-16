# Projet caracole : jeu de carte multijoueur en ligne

----
## Objectifs du projet :
Crée une plateforme de jeu en ligne pour jouer à deux ou plusieurs amis au Caracole.

----
## Règle du jeu
Le jeu de caracole se joue de 2 à 5 joueurs.
Chaque joueur démarre avec 7 cartes en main, puis échange chacun leur tour une ou plusieurs de ses cartes (selon les combinaisons possibles) avec celle au dessus de la défausse ou de la pioche. La manche se termine lorsqu'un joueur atteint 10 points ou moins en main. Les autres joueurs ajoutent alors leur points en main à leur score. La partie se termine lorsqu'il ne reste plus qu'un joueur avec un score de moins de 100 points, il gagne alors la partie.

----
### Combinaisons de carte possible :
simple, paire, brelan, carré et suite de même couleur de plus de 3 cartes

### Valeur des cartes :
* chaque carte vaut la valeur inscrite dessus (de 1 à 10)
* les têtes valent chacune 10 points

### Pioche :
Les cartes non distribuées au début de manche sont posées face cachée.

### Défausse :
Il s'agit des cartes dont les joueurs se sont débarrassées pendant leur tour. Elles sont empilées face visible.
Au début de la manche, la première carte de la pioche est posé face visible sur la défausse.

----
## Détails technique sur le projet
* framework : Play 1.4 et Hibernate
* base de donnée : MySQL 5.7
* Interface web : HTML5, CSS3 et JavaScript

----
*Il est nécessaire d'ajouter au projet le fichier de configuration pour hibernate (app/hibernate.cfg.xml):*


    <?xml version='1.0' encoding='UTF-8' ?>
	<!DOCTYPE hibernate-configuration PUBLIC
	        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
	        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">

	<hibernate-configuration>
	    <session-factory>
	        <!-- Paramètre de connexion à la base de données -->
	        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
	        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
	        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/SchemaName</property>
	        <property name="hibernate.connection.username">UserName</property>
	        <property name="hibernate.connection.password">Password</property>
	        <!-- Comportemnt pour la conservation des tables -->
	        <property name="hbm2ddl.auto">update</property>

	        <!-- Comportement du batch -->
	        <property name="hibernate.jdbc.batch_size">30</property>

	        <!-- Fichiers à mapper -->
	        <mapping class="models.Compte" />
	        <!--<mapping class="models.Invite" />-->
	        <!--<mapping class="models.Utilisateur" />-->
	    </session-factory>
	</hibernate-configuration>

