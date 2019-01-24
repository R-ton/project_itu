CREATE USER jirama identified by jirama;
CREATE TABLE Client(
	idClient VARCHAR(10) PRIMARY KEY,
	Nom VARCHAR(60),
	Prenom VARCHAR(60),
	Lieu VARCHAR(60) 
	);
CREATE TABLE Compteur(
	NumCompteur NUMBER PRIMARY KEY,
	Categorie VARCHAR(15),
	Unite NUMBER,
	idClient VARCHAR(10),
	idTranche VARCHAR(10)
);
CREATE TABLE TrancheType(
	idTranche VARCHAR(10),
	libellet VARCHAR(30),
	MaxUnite NUMBER,
	PrixUnitaire NUMBER(8,2)
);

CREATE TABLE Prelevement(
	idPrelevement VARCHAR(10),
	idClient VARCHAR(10),
	Daty DATE,
	NumCompteur NUMBER,
	Categorie VARCHAR(15),
	Consommation NUMBER(8,2),
	Etat NUMBER,
	CONSTRAINT Prelevement_pk PRIMARY KEY(idPrelevement)
);
CREATE TABLE Facture(
	idFacture VARCHAR(10),
	idClient VARCHAR(10),
	DatyFact DATE,
	CONSTRAINT Facture_pk PRIMARY KEY(idFacture),
	FOREIGN KEY (idClient) REFERENCES Client(idClient)
);

CREATE TABLE PrelevementFacture(
	idPrelevementFacture VARCHAR(10),
	idPrelevement VARCHAR(10),
	idFacture VARCHAR(10),
	FOREIGN KEY(idPrelevement) REFERENCES Prelevement(idPrelevement),
	FOREIGN KEY(idFacture) REFERENCES Facture(idFacture)
);

CREATE TABLE DetailFacture(
	idDetailFacture VARCHAR(10),
	NumCompteur NUMBER,
	idClient VARCHAR(10),
	idTranche VARCHAR(10),
	PrixUnitaire NUMBER(8,2),
	Consommation NUMBER(17,2),
	FOREIGN KEY(NumCompteur) REFERENCES Compteur(NumCompteur),
	FOREIGN KEY(idClient) REFERENCES Client(idClient)
);
DROP TABLE TarifEau;
CREATE TABLE TarifEau(
	MaxuniteEau1 NUMBER,
	PrixEauTranche1 NUMBER(6,2),
	MaxuniteEau2 NUMBER,
	PrixEauTranche2	NUMBER(8,2)
	);

CREATE TABLE TarifElectricite(
	MaxuniteElectricite1 NUMBER,
	MaxuniteElectricite2 NUMBER,
	MaxuniteElectricite3 NUMBER,
	PrixElectriciteTranche1 NUMBER(6,2),
	PrixElectriciteTranche2 NUMBER(8,2),
	PrixElectriciteTranche3 NUMBER(8,2)
	);

	INSERT INTO TarifEau VALUES(1500,200.12,3500,300);
	INSERT INTO TarifElectricite VALUES(1000,1500,null,100,120,150);


	CREATE SEQUENCE CompteurSequence start with 1010;
CREATE SEQUENCE ClientSequence start with 1;
CREATE SEQUENCE TrancheTypeSequence start with 1;
CREATE SEQUENCE PrelevementSequence start with 1;
CREATE SEQUENCE FactureSequence start with 1;
CREATE SEQUENCE DetailFactureSequence INCREMENT BY 1;
CREATE SEQUENCE PrelevementFactureSequence INCREMENT BY 1;

DROP VIEW getTrancheCompteur;
CREATE VIEW getCompteurClient as select NumCompteur,client.idClient,nom,prenom from compteur join client on compteur.idClient=client.idClient;
CREATE VIEW getTrancheId as select idTranche,libellet,MaxUnite,PrixUnitaire from TrancheType;
CREATE VIEW getTranche as select distinct(idTranche),libellet,MaxUnite,PrixUnitaire from TrancheType;
CREATE VIEW getTrancheCompteur as select prixunitaire,NumCompteur from compteur join TrancheType on compteur.idTranche=TrancheType.idTranche;
CREATE VIEW getUniteparCategorie as select compteur.numcompteur,compteur.categorie,compteur.unite from compteur join client on compteur.idclient=client.idclient;
		INSERT INTO Client VALUES(ClientSequence.nextval,'Ralay','Will','Analamahitsy');
		INSERT INTO Client VALUES(ClientSequence.nextval,'Ratsima','Henri','Analakely');
		INSERT INTO Client VALUES(ClientSequence.nextval,'Rabenatoandro','Phillipe','Itaosy');
		INSERT INTO Client VALUES(ClientSequence.nextval,'Rakotoson','Domoina','Tanjombato');
		INSERT INTO Client VALUES(ClientSequence.nextval,'Rabarinjaka','Lita','Ambodifilao');
		INSERT INTO Client VALUES(ClientSequence.nextval,'Botovao','Elise','Analamahitsy');
		INSERT INTO Client VALUES(ClientSequence.nextval,'Rasoazanany','Njaka','Tanjombato');
		INSERT INTO Client VALUES(ClientSequence.nextval,'Smith','Will','Anosy');
		INSERT INTO Client VALUES(ClientSequence.nextval,'Ravelojaona','Arthur','67 ha');
		INSERT INTO Client VALUES(ClientSequence.nextval,'Ratsimihety','Jean','Anosizato');

	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Eau',160000.98,1,1);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Electricite',10000,);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Electricite',12000.12,1);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Eau',231000,2,1);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Eau',13000.21,2,1);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Electricite',303000,3,1);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Electricite',120000.12,4,1);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Eau',12800,5,1);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Eau',11080,6,1);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient,idTranche) VALUES(CompteurSequence.nextval,'Electricite',42000,7,1);

	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,1,'12-01-2018',1010,'Eau',16000.98,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,1,'01-10-2018',1011,'Electricite',10000,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,1,'01-11-2018',1012,'Electricite',12000.12,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,2,'03-12-2018',1013,'Eau',23100,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,2,'02-01-2018',1014,'Eau',13000.21,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,3,'01-05-2018',1015,'Electricite',30300,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,4,'09-09-2018',1016,'Electricite',12000.12,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,5,'02-12-2018',1017,'Eau',12800,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,6,'02-01-2018',1018,'Eau',11080,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,7,'01-11-2018',1019,'Electricite',42000,0);	



	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,1,'24-02-2018',1010,'Eau',32000,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,1,'05-12-2018',1011,'Electricite',25000,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,1,'01-12-2018',1012,'Electricite',20000,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,2,'09-01-2019',1013,'Eau',32000,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,2,'02-12-2018',1014,'Eau',26000.34,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,3,'01-06-2018',1015,'Electricite',40000.45,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,4,'15-10-2018',1016,'Electricite',21000.87,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,5,'01-01-2019',1017,'Eau',15800.64,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,6,'06-02-2018',1018,'Eau',17000.9,0);	
	INSERT INTO Prelevement(idPrelevement,idClient,Daty,NumCompteur,Categorie,Consommation,Etat) VALUES(PrelevementSequence.nextval,7,'01-12-2018',1019,'Electricite',48000.98,0);	
  
  	INSERT INTO TrancheType(idTranche,libellet,MaxUnite,PrixUnitaire) VALUES(TrancheTypeSequence.nextval,'ElectriciteTranche1',1000,100);
	INSERT INTO TrancheType(idTranche,libellet,MaxUnite,PrixUnitaire) VALUES(TrancheTypeSequence.currval,'ElectriciteTranche2',1500,120);
	INSERT INTO TrancheType(idTranche,libellet,MaxUnite,PrixUnitaire) VALUES(TrancheTypeSequence.currval,'ElectriciteTranche3',null,150);
	INSERT INTO TrancheType(idTranche,libellet,MaxUnite,PrixUnitaire) VALUES(TrancheTypeSequence.nextval,'ElectriciteTranche1',2000,200);
	INSERT INTO TrancheType(idTranche,libellet,MaxUnite,PrixUnitaire) VALUES(TrancheTypeSequence.currval,'ElectriciteTranche2',2500,240);
	INSERT INTO TrancheType(idTranche,libellet,MaxUnite,PrixUnitaire) VALUES(TrancheTypeSequence.currval,'ElectriciteTranche3',null,250);
	INSERT INTO TrancheType(idTranche,libellet,MaxUnite,PrixUnitaire) VALUES(TrancheTypeSequence.nextval,'ElectriciteTranche1',3000,300);
	INSERT INTO TrancheType(idTranche,libellet,MaxUnite,PrixUnitaire) VALUES(TrancheTypeSequence.currval,'ElectriciteTranche2',4500,400);
	INSERT INTO TrancheType(idTranche,libellet,MaxUnite,PrixUnitaire) VALUES(TrancheTypeSequence.currval,'ElectriciteTranche3',null,450);

drop table Prelevement cascade CONSTRAINT;
drop table Facture cascade CONSTRAINT;
drop sequence PrelevementSequence;
drop sequence FactureSequence;
Pour récupérer une valeur du champ MYDATE de type date : 
TO_CHAR(MYDATE,'DD-MM-YYYY HH24:MI:SS') 
Pour insérer une date dans MYDATE : 
TO_DATE('01-01-2004 13:38:11','DD-MM-YYYY HH24:MI:SS');

ALTER SESSION set nls_date_format='DD/MM/YYYY/';
ALTER SESSION set nls_date_format='YYYY-MM-DD';
