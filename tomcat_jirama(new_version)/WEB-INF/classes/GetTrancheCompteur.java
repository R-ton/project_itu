package table;
public class GetTrancheCompteur{
		Number numCompteur;
        Number PrixUnitaire;
	public Number getNumCompteur(){
		return this.numCompteur;
	}
		public Number getPrixUnitaire(){
		return this.PrixUnitaire;
	}
	public void setNumCompteur(Number num){
		// if (num.toString() != null) {
		// 	throw new Exception("NumCompteur is not an integer");
		// }
		this.numCompteur=num;
	}
    public void setPrixUnitaire(Number p){
		this.PrixUnitaire=p;
	}
}