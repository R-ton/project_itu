package table;
public class DetailFacture{
	String idDetailFacture;
	Number NumCompteur;
	String idClient;
	String idTranche;
	Number PrixUnitaire;
	Number Consommation;

    public DetailFacture(){}
	public DetailFacture(String i,Number n,String ic,String it,Number pu,Number c){
		this.setIdDetailFacture(i);
		this.setNumCompteur(n);
		this.setIdClient(ic);
		this.setIdTranche(it);
		this.setPrixUnitaire(pu);
		this.setConsommation(c);
	}
	public String getIdDetailFacture(){return this.idDetailFacture;}
	public Number getNumCompteur(){return this.NumCompteur;}
	public String getIdClient(){return this.idClient;}
	public String getIdTranche(){return this.idTranche;}
	public Number getPrixUnitaire(){return this.PrixUnitaire;}
	public Number getConsommation(){return this.Consommation;}

	public void setIdDetailFacture(String i){this.idDetailFacture=i;}
	public void setNumCompteur(Number n){this.NumCompteur=n;}
	public void setIdClient(String i){this.idClient=i;}
	public void setIdTranche(String i){this.idTranche=i;}
	public void setPrixUnitaire(Number d){this.PrixUnitaire=d;}
	public void setConsommation(Number d){this.Consommation=d;}
}