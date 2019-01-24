package table;
import java.sql.*;
// import java.util.*;
import dbconnect.*;
import table.*;
import java.util.Vector;
import java.text.SimpleDateFormat;
import exception.*;
import java.text.ParseException;
public class ComptePrepayer{
	String idComptePrepayer;
	Number NumCompteur;
	Date DateDebut;
	Date DateFin;
	String idAbonnement;
	public ComptePrepayer(){}
	public ComptePrepayer(String i,String n,String dd,String df,String ida)throws AvaliderException,Exception{
		this.setIdComptePrepayer(i);
		this.setNumCompteur(n);
		this.setDateDebut(dd);
		this.setDateFin(df);
		this.setIdAbonnement(ida);
	}
		public Number getNumCompteur(){
		return this.NumCompteur;
	}
	public String getIdComptePrepayer(){
		return this.idComptePrepayer;
	} 
	public String getIdAbonnement(){
		return this.idAbonnement;
	} 
	public Date getDateDebut(){
		return this.DateDebut;
	}

	public Date getDateFin(){
		return this.DateFin;
	}

	public void setIdComptePrepayer(String i){
		this.idComptePrepayer=i;
	}

	public void setIdAbonnement(String i){
		this.idAbonnement=i;
	}
	public void setNumCompteur(Number num){
		// if (num.toString() != null) {
		// 	throw new Exception("NumCompteur is not an integer");
		// }
		this.NumCompteur=num;
	}
	public void setNumCompteur(String num){
		// if (num.toString() != null) {
		// 	throw new Exception("NumCompteur is not an integer");
		// }
		Double d=new Double(num);
		Number n=d;
		this.NumCompteur=n;
	}
		public void setDateDebut(Date d){this.DateDebut=d;}
		public void setDateDebut(java.util.Date d)throws Exception{

		java.sql.Date dateSql=new java.sql.Date(d.getTime());
		this.DateDebut=dateSql;

		}

		public void setDateFin(Date d){this.DateFin=d;}
		public void setDateFin(java.util.Date d)throws Exception{

		java.sql.Date dateSql=new java.sql.Date(d.getTime());
		this.DateFin=dateSql;

		}
		public void setDateDebut(String d)throws AvaliderException{
			try{
			
		java.util.Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(d);  
	java.sql.Date dateSql=new java.sql.Date(date1.getTime());
		this.DateDebut=dateSql;
		}catch (ParseException e) {
			
			throw new AvaliderException("format dateDebut invalide");
		}
		catch(Exception e){
			throw e;
		}

	}
	public void setDateFin(String d)throws AvaliderException{
			try{
			
		java.util.Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(d);  
	java.sql.Date dateSql=new java.sql.Date(date1.getTime());
		this.DateFin=dateSql;
		}catch(ParseException e){
			throw new AvaliderException("format dateFin invalide");
		}catch (Exception e) {
			throw e;
		}

	}
}