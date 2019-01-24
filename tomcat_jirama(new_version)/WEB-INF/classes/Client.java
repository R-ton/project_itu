package table;
public class Client{
	String idClient;
	String Nom;
	String Prenom;
	String Lieu;
	public Client(){}
	public void setIdClient(String i) throws Exception{
		this.idClient=i;
		if (i == null) {
			throw new Exception("Le client n existe pas");
		}
	}
	public void setNom(String n){
		if (n instanceof String) {
		this.Nom=n;
		}
		// throw new Exception("Please enter an")
	}
	public void setPrenom(String p){
		this.Prenom=p;
	}
	public void setLieu(String l){
		this.Lieu=l;
	}
	public String getIdClient(){
		return this.idClient;
	}
	public String getPrenom(){
		return this.Prenom;
	}
	public String getNom(){
		return this.Nom;
	}
	public String getLieu(){
		return this.Lieu;
	} 
}