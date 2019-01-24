package table;
public class Compteur{
	Number numCompteur;
	String categorie;
	Number unite;
	String idClient;
	String idTranche;
	public Compteur(){}

	public Compteur(Number n,String ue,Number uel,String s,String i)throws Exception{
		this.setNumCompteur(n);
		this.setCategorie(ue);
		this.setUnite(uel);
		this.setIdClient(s);
		this.setIdTranche(i);
		}
	public Compteur(Number n){
		this.setNumCompteur(n);
	}
	public Number getNumCompteur(){
		return this.numCompteur;
	}
	public String getIdClient(){
		return this.idClient;
	} 
	public String getCategorie(){
		return this.categorie;
	}
		public String getIdTranche(){
		return this.idTranche;
	}
	public Number getUnite(){
		return this.unite;
	}
	public void setIdTranche(String i){
		this.idTranche=i;
	}
	public void setNumCompteur(Number num){
		// if (num.toString() != null) {
		// 	throw new Exception("NumCompteur is not an integer");
		// }
		this.numCompteur=num;
	}
	public void setCategorie(String ctg){
		this.categorie=ctg;
	}
	public void setUnite(Number u)throws Exception{
		try{
			this.unite=u;
		}catch(Exception e){
			if (u.doubleValue() > 50000) {
				throw new Exception("vous avez atteint la limite de consommation");
			}
		}
	}
	public void setIdClient(String s){
		this.idClient=s;
	}
}