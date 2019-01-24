package table;
import java.sql.*;
// import java.util.*;
import dbconnect.*;
import java.util.Vector;
public class Abonnement{
	String idAbonnement;
	String categorie;
	Number unite;
	Number prix;
public Abonnement(){}
public Abonnement(String i,String cat,Number u,Number prix){
	this.setIdAbonnement(i);
	this.setCategorie(cat);
	this.setUnite(u);
	this.setPrix(prix);
}
	public String getIdAbonnement(){
		return this.idAbonnement;
	} 
	public String getCategorie(){
		return this.categorie;
	}
	public Number getUnite(){
		return this.unite;
	}
	
	public Number getPrix(){
		return this.prix;
	}
	public void setCategorie(String ctg){
		this.categorie=ctg;
	}
		public void setIdAbonnement(String i){
		this.idAbonnement=i;
	}
	public void setUnite(Number num){
		// if (num.toString() != null) {
		// 	throw new Exception("NumCompteur is not an integer");
		// }
		this.unite=num;
	}
	public void setPrix(Number num){
		// if (num.toString() != null) {
		// 	throw new Exception("NumCompteur is not an integer");
		// }
		this.prix=num;
	}
}