CREATE TABLE ComptePrepayer(
	idComptePrepayer VARCHAR(20) PRIMARY KEY,
	NumCompteur NUMBER,
	DateDebut DATE,
	DateFin DATE,
	idAbonnement VARCHAR(20)
);

CREATE TABLE Abonnement(
	idAbonnement VARCHAR(20) PRIMARY KEY,
	Categorie VARCHAR(20),
	Unite NUMBER,
	Prix NUMBER
);

CREATE SEQUENCE ComptePrepayerSequence start with 1;
CREATE SEQUENCE AbonnementSequence start with 1;

INSERT INTO Abonnement VALUES(AbonnementSequence.nextval,'Eau',20,18000);
INSERT INTO Abonnement VALUES(AbonnementSequence.nextval,'Electricite',15,2000);

/*le jour tonga de apetraka fotsiny
	ex 
	idAbonnement 		Duree
	1 					31 jour
	2 					29 jour
	Ny tena mety de atao miankina @ jour fin du mois par defaut
	De afaka safidina le jour(mety hisy @ alea)


INSERT INTO ComptePrepayer VALUES(ComptePrepayerSequence.nextval,);


*/
