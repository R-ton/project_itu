package table;
public class PrelevFactAnnuler{
	String idPrelevFactAnnuler;
	String idPrelevementFacture;
	String idPrelevement;
	String idFacture;
	public PrelevFactAnnuler(String h,String i,String ip,String ifact){
			setIdPrelFactAnnuler(h);
			setIdPrelevementFacture(i);
			setIdPrelevement(ip);
			setIdFacture(ifact);
	}
		public String getIdPrelFactAnnuler(){return this.idPrelevFactAnnuler;}
	public String getIdPrelevementFacture(){return this.idPrelevementFacture;}
	public String getIdPrelevement(){return this.idPrelevement;}
	public String getIdFacture(){return this.idFacture;}

	public void setIdPrelFactAnnuler(String d){this.idPrelevFactAnnuler=d;}
	public void setIdPrelevementFacture(String d){this.idPrelevementFacture=d;}
	public void setIdPrelevement(String d){this.idPrelevement=d;}
	public void setIdFacture(String d){this.idFacture=d;}
}