--CREATE TABLE Prelevement 
--CREATE TABLE Client
--CREATE TABLE Tarif
--CREATE TABLE Facture

--prix unitaire eau tranche 1 max:60000


DROP TABLE Client;
CREATE TABLE Client(
	idClient VARCHAR(10) PRIMARY KEY,
	Nom VARCHAR(60),
	Prenom VARCHAR(60),
	Lieu VARCHAR(60) 
	);

DROP TABLE Compteur IF EXISTS;
CREATE TABLE Compteur(
	NumCompteur INTEGER PRIMARY KEY,
	Categorie VARCHAR(15),
	Unite DECIMAL,
	idClient VARCHAR(10),
	FOREIGN KEY(idClient) REFERENCES Client(idClient)
);


DROP TABLE Prelevement;
CREATE TABLE Prelevement(
	id VARCHAR(10) PRIMARY KEY,
	idClient VARCHAR(10),
	Daty DATE,
	NumCompteur INTEGER,
	Categorie VARCHAR(15),
	Consommation NUMBER(8,2)
);

CREATE TABLE TrancheType(
	id VARCHAR(10),
	libellet VARCHAR(30),
	MaxUnite INTEGER,
	PrixUnitaire NUMBER(8,2),
	NumCompteur INTEGER,
	FOREIGN KEY(NumCompteur) REFERENCES Compteur(NumCompteur)
);

DROP TABLE TarifEau;
CREATE TABLE TarifEau(
	MaxuniteEau1 INTEGER,
	PrixEauTranche1 NUMBER(6,2),
	MaxuniteEau2 INTEGER,
	PrixEauTranche2	NUMBER(8,2)
	);

CREATE TABLE TarifElectricite(
	MaxuniteElectricite1 INTEGER,
	MaxuniteElectricite2 INTEGER,
	MaxuniteElectricite3 INTEGER,
	PrixElectriciteTranche1 NUMBER(6,2),
	PrixElectriciteTranche2 NUMBER(8,2),
	PrixElectriciteTranche3 NUMBER(8,2)
	);

DROP TABLE Facture;
CREATE TABLE Facture(
	idPrelevement VARCHAR(10),
	idClient VARCHAR(10) NOT NULL,
	NumCompteur NUMBER,
	DatyFact DATE,
	Mois INTEGER,
	Annee INTEGER,
	TotalConsommation NUMBER(10,3),
	TotalPayer DECIMAL,
	constraint Facture_pk primary key(idPrelevement,NumCompteur),
	FOREIGN KEY(NumCompteur) REFERENCES Compteur(NumCompteur)
	);

	CREATE SEQUENCE id_compteur start with 1010;
CREATE SEQUENCE id start with 31;
CREATE SEQUENCE idPrelevement INCREMENT BY 1;
CREATE SEQUENCE idTranche INCREMENT BY 1;
CREATE SEQUENCE idFacture INCREMENT BY 1;

--connaitre liste compteur d un client
select compteur.numcompteur,compteur.categorie,compteur.unite from compteur join client on compteur.idclient=client.idclient where client.nom='Rasoazanany';                                                                                                                                                     
select compteur.numcompteur,compteur.categorie,compteur.unite from compteur join client on compteur.idclient=client.idclient where client.idclient='31';                       

--connaitre daty ancien et nouvueau prelevement d un client
create view diffdate as select max(daty) as nouveau,min(daty) as ancien,consommation,idclient  from prelevement order by daty;

--recuperer daty et numcompteur
select DISTINCT(NumCompteur),Daty FROM Prelevement GROUP BY NumCompteur,Daty ORDER BY NumCompteur ASC,MAX(Daty) desc; 
SELECT * FROM Prelevement where NumCompteur='1010' ORDER BY Daty desc;
		INSERT INTO Client VALUES(id.nextval,'Ralay','Will','Analamahitsy');
		INSERT INTO Client VALUES(id.nextval,'Ratsima','Henri','Analakely');
		INSERT INTO Client VALUES(id.nextval,'Rabenatoandro','Phillipe','Itaosy');
		INSERT INTO Client VALUES(id.nextval,'Rakotoson','Domoina','Tanjombato');
		INSERT INTO Client VALUES(id.nextval,'Rabarinjaka','Lita','Ambodifilao');
		INSERT INTO Client VALUES(id.nextval,'Botovao','Elise','Analamahitsy');
		INSERT INTO Client VALUES(id.nextval,'Rasoazanany','Njaka','Tanjombato');
		INSERT INTO Client VALUES(id.nextval,'Smith','Will','Anosy');
		INSERT INTO Client VALUES(id.nextval,'Ravelojaona','Arthur','67 ha');
		INSERT INTO Client VALUES(id.nextval,'Ratsimihety','Jean','Anosizato');

	INSERT INTO TarifEau VALUES(1500,200.12,3500,300);
	INSERT INTO TarifElectricite VALUES(1000,1500,null,100,120,150);

	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Eau',160000.98,31);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Electricite',10000,31);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Electricite',12000.12,31);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Eau',231000,32);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Eau',13000.21,32);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Electricite',303000,33);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Electricite',120000.12,34);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Eau',12800,35);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Eau',11080,36);
	INSERT INTO Compteur(NumCompteur,Categorie,Unite,idClient) VALUES(id_compteur.nextval,'Electricite',42000,37);

	INSERT  INTO Prelevement(idClient,NumCompteur)
	(SELECT idClient from Client UNION ALL SELECT
	NumCompteur from Compteur); 

	update Prelevement set (NumCompteur)=(select NumCompteur from Compteur where Prelevement.NumCompteur=Compteur.NumCompteur or Prelevement.NumCompteur=null);

	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,31,'12-01-2018',1010,'Eau',16000.98);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,31,'01-10-2018',1011,'Electricite',10000);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,31,'01-11-2018',1012,'Electricite',12000.12);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,32,'03-12-2018',1013,'Eau',23100);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,32,'02-01-2018',1014,'Eau',13000.21);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,33,'01-05-2018',1015,'Electricite',30300);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,34,'09-09-2018',1016,'Electricite',12000.12);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,35,'02-12-2018',1017,'Eau',12800);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,36,'02-01-2018',1018,'Eau',11080);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,37,'01-11-2018',1019,'Electricite',42000);	



	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,31,'24-02-2018',1010,'Eau',32000);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,31,'05-12-2018',1011,'Electricite',25000);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,31,'01-12-2018',1012,'Electricite',20000);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,32,'09-01-2019',1013,'Eau',32000);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,32,'02-12-2018',1014,'Eau',26000.34);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,33,'01-06-2018',1015,'Electricite',40000.45);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,34,'15-10-2018',1016,'Electricite',21000.87);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,35,'01-01-2019',1017,'Eau',15800.64);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,36,'06-02-2018',1018,'Eau',17000.9);	
	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,37,'01-12-2018',1019,'Electricite',48000.98);	
  
	UPDATE Compteur set idClient=(SELECT DISTINCT(idClient) from Prelevement where NumCompteur='1010') where NumCompteur='1010';  

	INSERT INTO Prelevement(id,idClient,Daty,NumCompteur,Categorie,Consommation) VALUES(idPrelevement.nextval,31,'05-03-2018',1010,'Eau',35000);	

	INSERT INTO TrancheType(id,libellet,MaxUnite,PrixUnitaire) VALUES(idTranche.nextval,'ElectriciteTranche1',1000,100);
	INSERT INTO TrancheType(id,libellet,MaxUnite,PrixUnitaire) VALUES(idTranche.currval,'ElectriciteTranche2',1500,120);
	INSERT INTO TrancheType(id,libellet,MaxUnite,PrixUnitaire) VALUES(idTranche.currval,'ElectriciteTranche3',null,150);
	INSERT INTO TrancheType(id,libellet,MaxUnite,PrixUnitaire) VALUES(idTranche.nextval,'ElectriciteTranche1',2000,200);
	INSERT INTO TrancheType(id,libellet,MaxUnite,PrixUnitaire) VALUES(idTranche.currval,'ElectriciteTranche2',2500,240);
	INSERT INTO TrancheType(id,libellet,MaxUnite,PrixUnitaire) VALUES(idTranche.currval,'ElectriciteTranche3',null,250);
	INSERT INTO TrancheType(id,libellet,MaxUnite,PrixUnitaire) VALUES(idTranche.nextval,'ElectriciteTranche1',3000,300);
	INSERT INTO TrancheType(id,libellet,MaxUnite,PrixUnitaire) VALUES(idTranche.currval,'ElectriciteTranche2',4500,400);
	INSERT INTO TrancheType(id,libellet,MaxUnite,PrixUnitaire) VALUES(idTranche.currval,'ElectriciteTranche3',null,450);
