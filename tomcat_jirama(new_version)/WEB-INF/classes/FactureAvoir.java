package table;
import java.sql.*;
public class FactureAvoir{
	String idFactureAvoir;
	String idFacture;
	String idClient;
	Date DatyFact;

	public FactureAvoir(String i,String j,String ic,Date df){
		setIdFactureAvoir(i);
		setIdFacture(j);
		setIdClient(ic);
		setDatyFact(df);

	}
		public String getIdFactureAvoir(){return this.idFactureAvoir;}
		public String getIdFacture(){return this.idFacture;}
public String getIdClient(){return this.idClient;}
	public Date getDatyFact(){return this.DatyFact;}

		public void setIdFactureAvoir(String f){this.idFactureAvoir=f;}
		public void setIdFacture(String f){this.idFacture=f;}
	public void setIdClient(String c){this.idClient=c;}
	public void setDatyFact(Date d){this.DatyFact=d;}

}