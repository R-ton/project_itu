package affichage;
import table.*;
import java.sql.*;
import function.*;
import dbconnect.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class test{
	public static void main(String[] args)throws Exception {
		DBconnect d=new DBconnect("jirama","jirama");
	// FindBase f=new FindBase();
	// 	String[] col=new String[1];
	// 	col[0]="NumCompteur";
	// 	String[] val=new String[1];
	// 	val[0]="1010";
	// 	Object[] o=f.multiFind("Prelevement",col,val);
		Function f=new Function();
		Prelevement[] pr=f.getOrder(d,"19");
		// FindBase f=new FindBase();
		// 	String[] col=new String[1];
		// 	String[] val=new String[1];
		// 	col[0]="idPrelevement";
		// 	val[0]="15";
		// 	Object[] o=f.multiFind("Prelevement",col,val);
		// 	// Prelevement p=(Prelevement)o[0];
		// 	System.out.println(o.length);
		// 	Prelevement[] pr=new Prelevement[o.length];
		for (int i=0;i<pr.length ;i++ ) {
			// pr[i]=(Prelevement)o[i];
			System.out.print(pr[i].getConsommation());
		}

		// String html=f.getPrelevementTab(d);
		// java.sql.Date dt=f.getDate("1017");
		// System.out.println(dt);
		// System.out.println(html);
		// TrancheType[] b=f.getTrancheId("2",null);
		// for (int i=0;i<b.length ;i++ ) {
		// 	System.out.print(b[i].getMaxUnite());
		// }
		// 		Compteur[] c=new Compteur[2];
		// c[0]=new Compteur();
		// c[0].setNumCompteur(1011);
		// c[0].setCategorie("Electricite");
		// 	c[1]=new Compteur();
		// c[1].setNumCompteur(1012);
		// c[1].setCategorie("Electricite");
	
		// double result=f.getConsoPrepayer(c,"2",d);
		// System.out.println("result"+result);

//  <!-- a tester -->
		// FindBase f=new FindBase();
		// String[] col=new String[2];
		// 	col[0]="NumCompteur";
		// 	// col[1]="NumCompteur";
		// String[] val=new String[2]; 
		// 	val[0]="1011";
		// 	val[1]="1012";
		// 	Object[] o2=f.findRowOneColumn("Prelevement",col,val);
		// 	int l2=o2.length;
		// 	System.out.println(l2);
		// 	Prelevement[] p=new Prelevement[l2];
		// 	int i=0;
		// 	for (i=0;i<l2 ;i++ ){
		// 		p[i]=(Prelevement)o2[i];
		// 		System.out.println("NumCompteur\t"+p[i].getNumCompteur());
		// 	}
// 	</.!--  -->



//test find colone > 1
			// String[] col=new String[2];
			// col[0]="idClient";
			// col[1]="daty";
			// String[] val=new String[2];
			// val[0]="1";
			// val[1]="2018-01-12";
			// FindBase find=new FindBase();
			// Object[] o=find.multiFindAdvanced("Prelevement",col,val);
			// int l=o.length,i=0,j=0;
			// Prelevement[] p=new Prelevement[l];
			// while(i<l){
			// 	p[i]=new Prelevement();
			// 	p[i]=(Prelevement)o[i];
			// 	System.out.println("NumCompteur\t"+p[i].getNumCompteur());
			// 	System.out.println("idClient\t"+p[i].getIdClient());
			// 	i++;
			// }
//
    /* test conso eau
            			Compteur[] c=new Compteur[1];
		c[0]=new Compteur();
		c[0].setNumCompteur(1013);
		c[0].setCategorie("Eau");
        Function f=new Function();
        double ce=f.getConsoEau(c[0],d);
        System.out.println(ce);
	*/
               			Compteur[] c=new Compteur[1];
		c[0]=new Compteur();
		c[0].setNumCompteur(1015);
		// c[0].setCategorie("Eau");
        // Function f=new Function();
        	// DetailFacture[] df=f.getFact(c[0],d);
        	// System.out.println(df[0].getNumCompteur());
        // int i=f.setFact(c,"","","",d);
   //      DetailFacture[] df=f.getFact(c[0],d);
   //      System.out.println(df.length);
   //      int i=0;
   //       while(i<df.length){
   //       	System.out.println(df[i].getPrixUnitaire());
   //       	System.out.println(df[i].getConsommation());
			// // Funcdlc f=new Funcdlc();
			// // String html=f.getSearch("1","1970","01");
			// // System.out.println(html);
   //       	i++;
   //       }
	}
}