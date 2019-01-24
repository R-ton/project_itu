package table;
public class TrancheType{
	String idTranche;
	String libellet;
	Number MaxUnite;
	Number PrixUnitaire;

public TrancheType(){}
public TrancheType(String i,String l,Number m,Number pu){
	this.setIdTranche(i);
	this.setLibellet(l);
	this.setMaxUnite(m);
	this.setPrixUnitaire(pu);
}
	public String getIdTranche(){
		return this.idTranche;
	}
	public String getLibellet(){
		return this.libellet;
	}
	public Number getMaxUnite(){
		return this.MaxUnite;
	}
	public Number getPrixUnitaire(){
		return this.PrixUnitaire;
	}
	public void setIdTranche(String s){
		this.idTranche=s;
	}
	public void setLibellet(String l){
		this.libellet=l;
	}
	public void setMaxUnite(Number m){
		this.MaxUnite=m;
	}
	public void setPrixUnitaire(Number p){
		this.PrixUnitaire=p;
	}
}