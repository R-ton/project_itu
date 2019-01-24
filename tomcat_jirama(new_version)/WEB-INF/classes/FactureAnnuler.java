package table;
import java.sql.Date;
public class FactureAnnuler{
	String idFactureAnnuler;
	String idFacture;
	String idClient;
	Date DatyFact;
	public FactureAnnuler(){}
	public FactureAnnuler(String f,String i,String ic,Date df){
		setIdFactureAnnuler(f);
		setIdFacture(i);
		setIdClient(ic);
		setDatyFact(df);
	}
	public String getIdFacture(){return this.idFacture;}
	public String getIdFactureAnnuler(){return this.idFactureAnnuler;}
	
	public String getIdClient(){return this.idClient;}
	public Date getDatyFact(){return this.DatyFact;}

	public void setIdFactureAnnuler(String f){this.idFacture=f;}
	public void setIdFacture(String f){this.idFacture=f;}
	public void setIdClient(String c){this.idClient=c;}
	public void setDatyFact(Date d){this.DatyFact=d;}
}