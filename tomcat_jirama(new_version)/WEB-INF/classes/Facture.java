package table;
import java.sql.Date;
public class Facture{
	String idFacture;
	String idClient;
	Date DatyFact;
	public Facture(){}
	public Facture(String i,String ic,Date df){
		setIdFacture(i);
		setIdClient(ic);
		setDatyFact(df);
	}
	public String getIdFacture(){return this.idFacture;}
	public String getIdClient(){return this.idClient;}
	public Date getDatyFact(){return this.DatyFact;}

	public void setIdFacture(String f){this.idFacture=f;}
	public void setIdClient(String c){this.idClient=c;}
	public void setDatyFact(Date d){this.DatyFact=d;}
}