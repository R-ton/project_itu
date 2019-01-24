package affichage;
import table.*;
import function.*;
import dbconnect.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class aff{
	public static void main(String[] args) throws Exception {
		DBconnect d=new DBconnect("jirama","jirama");
		Function fu=new Function();
		/*Compteur[] a=fu.getCompteur();*/
		// TarifEau[] t=fu.getTarifEau();
		Compteur c=new Compteur();
		c.setNumCompteur(1011);
		c.setCategorie("Electricite");
		// TrancheType[] t=fu.getTrancheId("1",null);
		// Client client=new Client();
		// client.setNom("Ralay");
		// client.setPrenom("Will");
		// System.out.print(c.getCategorie());
		// float[] ft=fu.testTrancheId("2");
		// fu.affichertest(ft);
		// TrancheType[] t=fu.getTrancheId("2");
		// Prelevement[]p=fu.getPrelevement();
		// Prelevement[] pl=fu.getPrelevement(c);
		// Date date=new Date();
		// SimpleDateFormat ft=new SimpleDateFormat("dd/MM/yyyy"); 
		// System.out.println("androany formatté\t"+ft.format(date));
		// Compteur[] p=fu.getCompteur(1011);
		// Compteur[]p=fu.getCompteur(client);
		// System.out.print("prelevement\n"+pl[0].getNumCompteur()); //mandeh
	// for(int b=0;b<t.length;b++){
	// 	System.out.print("lengthtranche\t"+t.length);
		// System.out.print("maxunite0\t"+t[0].getMaxUnite()+"\n");
	// 	System.out.print("categorie\t"+t[b].getLibellet()+"\n");
	// 	System.out.print("prixunitaire\t"+t[b].getPrixUnitaire()+"\n");
	// System.out.print("maxunitewater"+t[b].getPet1());
	// 	System.out.print("compteur\n"+p[b].getNumCompteur());
	// 	System.out.print(p[b].get());
	// 	System.out.print("id="+t[b].getId()+"\n");
	// }
	// System.out.print(t[1].getMaxUnite());
	// System.out.print(t[3].getMaxUnite());
	// System.out.print(t[6].getMaxUnite());
	// 	double light=fu.getConsoElectriciteperso(c,"0",null);
	// 	System.out.print("resultatcalculfacturation:\t"+light);
	// // 	Calendar cl=Calendar.getInstance();
	// 	Date dt=new Date(2018,13,37);
	// 	cl.setTime(dt);
	// try {
 //    	cl.getTime();
	// }
	// catch (Exception e) {
 //  		System.out.println("Invalid date");
	// }
		// System.out.print(d);
		// String[] spldt=fu.splitDate(d);
		// fu.displaySplit(spldt);
		// System.out.print(fu.setFact(c,d,"1",12,2017));
	// System.out.print(a);
		Funcdlc f=new Funcdlc();
		String s=f.getPrelevement("2","2018-12-02");
		System.out.println(s);
}

}