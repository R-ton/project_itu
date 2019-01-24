package table;
import java.sql.Date;
public class DetailFactureAndFacture{
	String idDetailFacture;
	Number NumCompteur;
	String idClient;
	String idTranche;
	Number PrixUnitaire;
	Number Consommation;
		String idFacture;
	Date DatyFact;

    public DetailFactureAndFacture(){}
	public DetailFactureAndFacture(String i,Number n,String ic,String it,Number pu,Number c,String f,Date d){
		this.setIdDetailFacture(i);
		this.setNumCompteur(n);
		this.setIdClient(ic);
		this.setIdTranche(it);
		this.setPrixUnitaire(pu);
		this.setConsommation(c);
		this.setIdFacture(f);
		this.setDatyFact(d);
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

		public void setIdFacture(String f){this.idFacture=f;}
	public void setDatyFact(Date d){this.DatyFact=d;}

public String getIdFacture(){return this.idFacture;}

public Date getDatyFact(){return this.DatyFact;}

}