package table;
public class PrelevementFacture{
	String idPrelevementFacture;
	String idPrelevement;
	String idFacture;
	public PrelevementFacture(){}
	public PrelevementFacture(String i,String ip,String ifact){
			setIdPrelevementFacture(i);
			setIdPrelevement(ip);
			setIdFacture(ifact);
	}
	public String getIdPrelevementFacture(){return this.idPrelevementFacture;}
	public String getIdPrelevement(){return this.idPrelevement;}
	public String getIdFacture(){return this.idFacture;}

	public void setIdPrelevementFacture(String d){this.idPrelevementFacture=d;}
	public void setIdPrelevement(String d){this.idPrelevement=d;}
	public void setIdFacture(String d){this.idFacture=d;}
}