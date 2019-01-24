package table;
import java.sql.*;
import function.*;
import java.sql.Date;
import exception.*;
import java.text.*;
import java.sql.Timestamp;
public class Prelevement{
	String idPrelevement;
	String idClient;
	Date daty;
	Number numCompteur;
	String categorie;
	Number consommation;
	Number etat;

	public Prelevement(){}
	public Prelevement(String id,String idClient,String d,String n,String cat,String c,String e)throws Exception,AvaliderException,NumberFormatException{
		this.setIdPrelevement(id);
		this.setIdClient(idClient);
		this.setDaty(d);
		this.setNumCompteur(n);
		this.setCategorie(cat);
		this.setConsommation(c);
		this.setEtat(e);
	}
	public Prelevement(String cat){
		this.setCategorie(cat);
	}
	public String getIdClient(){
		return this.idClient;
	}
public String getIdPrelevement(){
		return this.idPrelevement;
	}
	public Date getDaty(){
		return this.daty;
	}
	public Number getEtat(){
		return this.etat;
	}

	public Number getNumCompteur(){
		return this.numCompteur;
	}
	public String getCategorie(){
		return this.categorie;
	}
	public Number getConsommation(){
		return this.consommation;
	}


	public void setIdPrelevement(String i){this.idPrelevement=i;}
	public void setIdClient(String ic){this.idClient=ic;}
	public void setDaty(Date d){this.daty=d;}
	public void setNumCompteur(Number n){this.numCompteur=n;}
	public void setCategorie(String c){this.categorie=c;}
	public void setConsommation(Number cons){this.consommation=cons;}
	public void setEtat(Number e){this.etat=e;}
	

	public void setDaty(String d)throws AvaliderException{
			try{
			System.out.println(d);
		java.util.Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(d);  
		System.out.println("date1"+date1);
	java.sql.Date dateSql=new java.sql.Date(date1.getTime());
	System.out.println("dateSql"+dateSql);
		this.daty=dateSql;
		}catch (ParseException e) {
			throw new AvaliderException("format datePrelevement invalide");
		}
		catch(Exception e){
			throw e;
		}

	}
	public void setNumCompteur(String n)throws Exception,NumberFormatException{
		try{
        Integer i=new Integer(n);
        this.numCompteur=i;
		}catch (NumberFormatException e) {
			throw new Exception("Compteur invalid");
		}
		catch (Exception e) {
			throw e;
		}
	}
	public void setConsommation(String n)throws Exception,NumberFormatException{
		try{
			System.out.println("Stringcnso"+n);
        Double i=new Double(n);
        System.out.println("Consommationdouble"+i);
        this.consommation=i;
        System.out.println(this.consommation);
		}catch (NumberFormatException e) {
			throw new Exception("Compteur invalid");
		}
		catch (Exception e) {
			throw e;
		}
	}
    public void setEtat(String n)throws Exception,AvaliderException,NumberFormatException{
		try{
        Integer i=new Integer(n);
        if (i.intValue() > 2) {
        	throw new AvaliderException("Etat inconnu");
        }
        this.consommation=i;
		}catch (NumberFormatException e) {
			throw new Exception("etat invalid");
		}
		catch (Exception e) {
			throw e;
		}
	}


	public Prelevement(String id,String idClient,Date d,Number n,String c,Number con,Number e)throws Exception,NumberFormatException{
		this.setIdPrelevement(id);
		this.setIdClient(idClient);
		this.setDaty(d);
		this.setNumCompteur(n);
		this.setCategorie(c);
		this.setConsommation(con);
		this.setEtat(e);
	}
	// 	public Prelevement(String id,String idClient,Date d,String n,String c,String con,String e)throws Exception,NumberFormatException{
	// 	this.setIdPrelevement(id);
	// 	this.setIdClient(idClient);
	// 	this.setDaty(d);
	// 	this.setNumCompteur(n);
	// 	this.setCategorie(c);
	// 	this.setConsommation(con);
	// 	this.setEtat(e);
	// }
	public Prelevement(String id,String daty,String valeur)throws AvaliderException{
		this.setIdPrelevement(id);
		this.setDaty(daty);

	}
}