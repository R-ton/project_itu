--facture d avoir integrale
CREATE TABLE FactureAnnuler(
	idFactureAnnuler  VARCHAR(20) PRIMARY KEY,
	idFacture VARCHAR(20),
	idClient VARCHAR(10),
	DatyFact DATE,
	FOREIGN KEY (idClient) REFERENCES Client(idClient),
	FOREIGN KEY (idFacture) REFERENCES Facture(idFacture)
);
CREATE SEQUENCE FactureAnnulerSequence start with 1;
--prelevement facture annuler
CREATE TABLE PrelevFactAnnuler(
	idPrelevFactAnnuler VARCHAR(10),
	idPrelevement VARCHAR(10),
	idFacture VARCHAR(10),
	FOREIGN KEY(idPrelevement) REFERENCES Prelevement(idPrelevement),
	FOREIGN KEY(idFacture) REFERENCES Facture(idFacture)
);
CREATE SEQUENCE PrelevFactAnnulerSequence start with 1;
--facture d avoir partiel
CREATE TABLE FactureAvoir(
	idFactureAvoir VARCHAR(20) PRIMARY KEY,
	idFacture VARCHAR(20),
	idClient VARCHAR(10),
	DatyFact DATE,
	FOREIGN KEY (idClient) REFERENCES Client(idClient),
	FOREIGN KEY(idFacture) REFERENCES Facture(idFacture)
);
CREATE SEQUENCE FactureAvoirSequence start with 1;
CREATE TABLE DetailFactureAnnuler(
	idDetailFactureAnnuler VARCHAR(10) PRIMARY KEY,
	idDetailFacture VARCHAR(10),
	NumCompteur NUMBER,
	idClient VARCHAR(10),
	idTranche VARCHAR(10),
	PrixUnitaire NUMBER(8,2),
	Consommation NUMBER(17,2),
	FOREIGN KEY(NumCompteur) REFERENCES Compteur(NumCompteur),
	FOREIGN KEY(idClient) REFERENCES Client(idClient)
);
CREATE SEQUENCE DetailFactureAnnulerSequence start with 1;


CREATE VIEW DetailFactureAndFacture as select idDetailFacture,NumCompteur,
	facture.idClient,
	idTranche,
	PrixUnitaire,
	Consommation,
	idFacture,
	DatyFact
 from DetailFacture join facture on DetailFacture.idClient=facture.idClient order by facture.datyfact desc;