package function;
import java.sql.*;
// import java.util.*;
import dbconnect.*;
import table.*;
import control.*;
import exception.*;
import java.util.Vector;
import java.util.Calendar;
import java.lang.reflect.*;
public class Funcdlc {
public Funcdlc(){}
	public Abonnement[] getListeAbonnement(DBconnect d)throws Exception{
		Abonnement[] a=null;
		Vector v=new Vector();
		try{
			if (d == null) {
				d=new DBconnect("jirama","jirama");
			}
			String sql="select * from Abonnement";
			Statement st=d.getConnect().createStatement();
			ResultSet rs=st.executeQuery(sql);
			int i=0,j=0;
			while(rs.next()){
				Abonnement ab=new Abonnement(rs.getString(1),rs.getString(2),(Number)rs.getObject(3),(Number)rs.getObject(4));
				v.addElement(ab);
				i++;
			}
			rs.close();
			st.close();
			a=new Abonnement[i];
			for (j=0;j<i ;j++ ) {
				a[j]=(Abonnement)v.elementAt(j);
			}
		}catch (Exception e) {
			throw e;
		}
		return a;
	}

	public String getTabAbonnement(DBconnect d)throws Exception{
		
		String tab="";
		try{

		Abonnement[] a=getListeAbonnement(d);
		Function f=new Function();
		Compteur[] c=f.getCompteur(d);
		int i=0;
		tab+="<h2>Liste dispo abonnement</h2>";
		tab+="<table border='1'>";
			tab+="<th>Numero</th>";
			tab+="<th>Categorie</th>";
			tab+="<th>Unite</th>";
			tab+="<th>Prix</th>";
		for (i=0;i<a.length ;i++ ) {
				tab+="<tr>";
				tab+="<td>"+a[i].getIdAbonnement()+"</td>";
				tab+="<td>"+a[i].getCategorie()+"</td>";
				tab+="<td>"+a[i].getUnite()+"</td>";
				tab+="<td>"+a[i].getPrix()+"</td>";
				tab+="</tr>";
		}
		tab+="</table>";
		tab+="<form action='InsertComptePrepayer.jsp' method='post'>";
				tab+="<label>Entrer le numero</label>";
				tab+="<select name='idabonner'>";
				for (int j=0;j<a.length ;j++ ) {
				tab+="<option value='"+a[j].getIdAbonnement()+"'>"+a[j].getIdAbonnement();
				tab+="</option>";
					
				}

				tab+="</select>";
				tab+="<label>Entrer le numero du Compteur</label>";
				tab+="<select name='num'>";
				for (i=0;i<c.length ;i++ ) {
				tab+="<option value='"+c[i].getNumCompteur()+"'>"+c[i].getNumCompteur();
				tab+="</option>";					
				}
				tab+="</select>";
		tab+="<input type='submit' value=\"S'abonner\">";
		tab+="</form>";
		d.getConnect().close();
	}catch (Exception e) {
		throw e;
	}
		return tab;
	}
	public double getConsoPrepayer(Compteur[] c,String id,DBconnect d) throws Exception{
		double result=0;
		try{
			Function func=new Function();
			double consosimple=0;
			int j=0;
			// for (j=0;j<c.length ;j++ ) {
				
			// consosimple+=func.getConsoElectriciteperso(c[j],"0",d);
			// System.out.println("consosimple"+consosimple);
			// }
			double unite=0;
			String[] col=new String[1];
			col[0]="idAbonnement";			
			String[] val=new String[1];
			val[0]=id;
			FindBase f=new FindBase();
			Object[] o=f.multiFind("Abonnement",col,val);
			Abonnement[] a=new Abonnement[o.length];
			int i=0,l=c.length;
			a[0]=(Abonnement)o[0];
			// col[0]="NumCompteur";
			col=new String[l];
			val=new String[l]; 
			for (i=0;i<l ;i++ ) {
			col[i]="NumCompteur";
			val[i]=c[i].getNumCompteur().toString();
			System.out.println("val"+val[i]);
			}
			Object[] o2=f.multiFind("Prelevement",col,val);
			int l2=o2.length;
			System.out.println(l2);
			Prelevement[] p=new Prelevement[l2];
			for (i=0;i<l2 ;i++ ){
				p[i]=(Prelevement)o2[i];
				System.out.println(p[i].getNumCompteur());
			}
			double maxunite=a[0].getUnite().doubleValue();
			double maxprix=a[0].getPrix().doubleValue();
			System.out.println("maxprix"+maxprix);
			double delta=0,consoreste=0;
//			purcentage alaina amle mihoatra			
			double pourcentage=0.15;
			System.out.println("pourcentage"+pourcentage);
			for (i=0;i<l2;i++ ) {				
				unite=p[i].getConsommation().doubleValue();
				System.out.println("unite"+unite);
				if (unite<=maxunite) {
					result+=maxprix;
				}else if (unite>maxunite) {
					delta=unite-maxunite;
					System.out.println("delta"+delta);
					consoreste=func.getTrancheparReste(delta,d);
					System.out.println("consoreste"+consoreste);
					result+=maxprix+(delta*consoreste*pourcentage);
					System.out.println((delta*consoreste*pourcentage));
				}
			}
		}catch (Exception e) {
			throw e;
		}
		d.getConnect().close();
		return result;
	}
	public java.sql.Date StringToDate(String[]d){
		String s="";
		for (int i=0;i<(d.length-1) ;i++ ) {
			s+=d[i]+"-";
		}
		java.sql.Date dt=java.sql.Date.valueOf(s);
		return dt;
	}
	public int insertComptePrepayer(/*Compteur[] c,*/String[] id,String dureeJour,String ida,DBconnect d)throws Exception,AvaliderException{
		try{
			Function func=new Function();
			Compteur[] cpt=func.getStringIdToCompteur(id);
			for (int bc=0;bc<id.length ;bc++ ) {
				
					System.out.println(cpt[bc].getNumCompteur());
					int n=cpt[bc].getNumCompteur().intValue();
					String[] col=new String[1];
					col[0]="NumCompteur";
					String[] val=new String[1];
					int i=0;
						val[0]=cpt[bc].getNumCompteur().toString();
					FindBase f=new FindBase();
					Object[] o=f.multiFind("Prelevement",col,val);
					System.out.println("numero client\t"+cpt[bc].getIdClient());
					if (cpt[bc].getIdClient() != cpt[bc+1].getIdClient()) {
						throw new AvaliderException("le numero du client est different");
					}
					Prelevement[] p=new Prelevement[o.length];
					while (i<o.length) {
						p[i]=new Prelevement();
						p[i]=(Prelevement)o[i];
						System.out.println("etatPrelevement\t"+p[i].getEtat());
						if (p[i].getEtat().intValue() == 1) {
							throw new AvaliderException("Facturation deja fait");
						}
						i++;
					}
					
					String client=p[bc].getIdClient();
					double conso=p[bc].getConsommation().doubleValue();
			// double conso=getConsoPrepayer(c,id,d);
			java.sql.Date dat=new java.sql.Date(System.currentTimeMillis());
			String dd=dat.toString();
			System.out.println("datedebutString\t"+dd);
				Calendar cal = Calendar.getInstance();
					int maxdayofmonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);		
		int month = cal.get(Calendar.MONTH);
		int dt = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);
			if (dureeJour != null) {
			Integer a=new Integer(dureeJour);
				dt=a.intValue();
				//manampy jour de validite anle offre
				if (dt > maxdayofmonth || dt < 0) {
					throw new AvaliderException("jour invalide");
				}
				cal.set(Calendar.DAY_OF_MONTH,dt);
				month = cal.get(Calendar.MONTH);
				dt = cal.get(Calendar.DAY_OF_MONTH);
				year = cal.get(Calendar.YEAR);
			}
			System.out.println("year"+year);
			System.out.println("month"+month);
			System.out.println("day"+dt);
			System.out.println("maxdayofmonth"+maxdayofmonth);
			String df=year+"-"+month+"-"+dt;
			System.out.println("datyFIn\t"+df);
				col[0]="idAbonnement";			
			val[0]=id[0];
			Object[] o2=f.multiFind("Abonnement",col,val);
			Abonnement[] a=new Abonnement[o2.length];
			// int i=0,l=c.length;
			a[0]=(Abonnement)o[0];
			// col[0]="NumCompteur";
			// col=new String[l];
			// val=new String[l]; 
			// for (i=0;i<l ;i++ ) {
			// col[i]="NumCompteur";
			// val[i]=c[i].getNumCompteur().toString();
			// System.out.println("val"+val[i]);
			// }


			ComptePrepayer cp=new ComptePrepayer("compteprepayer",id[bc],dd,df,ida);
				Facture fact=new Facture("Facture",client,dat);
						DetailFacture detail=new DetailFacture("detailfacture",n,client,null,a[0].getUnite(),getConsoPrepayer(cpt,id[bc],d));
						Insert insert=new Insert();
						int ins=insert.insert("ComptePrepayer",cp,d);
						insert.insert("Facture",fact,null); 
						insert.insert("DetailFacture",detail,null);
						String idfact=func.getSequence(null,"Facture");
						if (idfact.compareTo("1") == 0) {
							idfact="1";
						}
						PrelevementFacture pf=new PrelevementFacture("PrelevementFacture",p[bc].getIdPrelevement(),idfact);
						insert.insert("PrelevementFacture",pf,null);
						//update etat prelevement lasa 1
						p[0].setEtat(1);
						Update update=new Update();
						Object objupdate=(Object)p[0];
						update.update(objupdate,p[0].getIdPrelevement());

			}
			d.getConnect().close();
		}catch (Exception e) {
			throw e;
		}
		return 0;
	}
	
	public String getIndex(String idc,String daty,DBconnect d)throws Exception{
		String str="";
		try{
			if (d == null) {
				d=new DBconnect("jirama","jirama");
			}
			str+="<h3>Affichage index</h3>";
			str+="<form action=\"prelevement.jsp\" method='post'>";
			str+="<center>";
			str+="<label>IdClient</label>";
			str+="<input type=\"text\" name='idClient' value='"+idc+"'/>";
			str+="<br/>";
			str+="<label>Daty</label>";
			str+="<input type=\"text\" name='daty' value='"+daty+"'/>";
			str+="<br/>";
			str+="<input type='submit' value='OK'/>";
			str+="</center";
			str+="</form>";
		}catch (Exception e) {
			throw e;
		}
		return str;
	}
	public String getPrelevement(String idClient,String daty)throws Exception{
		System.out.println(idClient);
		System.out.println(daty);
		String str="";
		String etatToString="";
		try{
			String[] col=new String[2];
			col[0]="idClient";
			col[1]="daty";
			String[] val=new String[2];
			val[0]=idClient;
			val[1]=daty;
			FindBase find=new FindBase();
			Object[] o=find.multiFindAdvanced("Prelevement",col,val);
			int l=o.length,j=0;
			Prelevement[] p=new Prelevement[l];
			for(int i=0;i<o.length;i++){
				// p[i]=new Prelevement();
				p[i]=(Prelevement)o[i];
				System.out.println(p[i].getNumCompteur());
			
			}
			// System.out.println(p.length);
			// if (o.length == 0) {
			// 	throw new Exception("Aucun prelevement trouvé");
			// }
			str+="<html>";
			str+="<head>";
			str+="<title>Prelevement jsp</title>";
			str+="<meta charset='UTF-8'>";
			str+="</head>";
			str+="<body>";
			str+="<table>";
				str+="<th>Prelevement</th>";
				str+="<th>Etat</th>";
					str+="<tr>";
						str+="<td>NumPrelevment</td>";
						str+="<td>Daty</td>";
						str+="<td>NumCompteur</td>";
						str+="<td>Categorie</td>";
						str+="<td>Consommation</td>";

					str+="</tr>";
				while(j<l){
					str+="<tr>";
						str+="<input type='hidden' name='num' value='"+p[j].getNumCompteur()+"'/>";
						str+="<td>"+p[j].getIdPrelevement()+"</td>";
						str+="<td>"+p[j].getDaty()+"</td>";
						str+="<td>"+p[j].getNumCompteur().intValue()+"</td>";
						str+="<td>"+p[j].getCategorie()+"</td>";
						str+="<td>"+p[j].getConsommation()+"</td>";
					str+="</tr>";
					if (p[j].getEtat().intValue() == 0) {
						etatToString="Non Facturer";
					}else if (p[j].getEtat().intValue() == 1) {
						etatToString="Facturé non payer";
					}else{
						etatToString="Facturé payer";
					}
						str+="<td>"+etatToString+"</td>";
						str+="<input type='checkbox' value='1' name='checkbox'/>";
						j++;
				}
			str+="</table>";
			str+="</body>";
			str+="</html>";
			// d.getConnect().close();
		}catch (Exception e) {
			throw e;
		}
		return str;
	}
	public int setPrelevement(String idClient,String daty,String numCompteur,String categorie,String conso,String etat,DBconnect d)throws Exception{
		System.out.println(conso);
		Prelevement p=new Prelevement("Prelevement",idClient,daty,numCompteur,categorie,conso,etat);
		System.out.println("consoprelevement\t"+p.getConsommation());
		Insert i=new Insert();
		d=new DBconnect("jirama","jirama");
		i.insert("Prelevement",p,d);
		return 0;
	}
	public String rechercheFacture() throws Exception{
		String[] mois=new String[12];
		mois[0]="Jan";
		mois[1]="Fev";
		mois[2]="Mar";
		mois[3]="Avr";
		mois[4]="Mai";
		mois[5]="Jun";
		mois[6]="Juil";
		mois[7]="Aout";
		mois[8]="Sep";
		mois[9]="Oct";
		mois[10]="Nov";
		mois[11]="Dec";
		String html="<html>";
			html+="<head>";
				html+="<title>Recherche facture</title>";
			html+="</head>";
			html+="<body>";
				html+="<h3>Recherche Facture</h3>";
				html+="<form action='recherche.jsp' method='get'>";
				html+="<label>idClient</label>";
				Function f=new Function();
				Client[] c=f.getIdClient(null);
				html+="<select name='idClient'>";
				for(int i=0;i<c.length;i++){

					html+="<option value="+c[i].getIdClient()+">"+c[i].getIdClient();
					html+="</option>";
				}
				html+="</select>";
				html+="<br/>";
				html+="<label>Mois</label>";
				html+="<select name='Mois'>";
				int j=0,/*k=2015*/k=2019;
                System.out.println(k);
				while (j<(mois.length-1)) {
					html+="<option value="+(j+1)+">"+mois[j];
					html+="</option>";
					j++;
				}
				html+="</select>";
				html+="<br/>";
				html+="<label>Annee</label>";
				html+="<select name='Annee'>";
				java.sql.Date dat=new java.sql.Date(System.currentTimeMillis());
				Calendar cal = Calendar.getInstance();
					cal.setTime(dat);
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DAY_OF_MONTH);
				int year = cal.get(Calendar.YEAR);
                System.out.println("year"+year);
				// 	while (k<year) {
				// 	html+="<option value="+k+">"+k;
				// 	html+="</option>";
				// 	k++;
				// }
					while (k>year) {
					html+="<option value="+year+">"+year;
					html+="</option>";
					year++;
				}
				html+="</select>";
				html+="<br/>";
				html+="<input type='submit' value='GO' name='Recherche'>";
				html+="</form>";
				html+="</body>";
		html+="</html>";
		return html;
	}

	public String getSearch(String idclient,String Annee,String mois)throws Exception{
		String html="<html>";
		try{

			html+="<head>";
				html+="<title>Affichage Recherche facture effectue</title>";
			html+="</head>";
			html+="<body>";
			html+="<h3>Facture tsotra</h3>";
			FindBase f=new FindBase();
		String begin="01";
		String last="31";
		String year=Annee;
		String month=mois;
		if (mois.equals("2")) {
			last="29";
		}
		String concat1=year+"-"+month+"-"+begin;
		String concat2=year+"-"+month+"-"+last;
		String[] col=new String[2];
		String[] val=new String[3];
		col[0]="datyfact";
		col[1]="idClient";
		val[0]=concat1;
		val[1]=concat2;
		val[2]=idclient;
		Object[] o=f.multiFindExtended("Facture",col,val,"between");
		System.out.println("length FindBase"+o.length);
		if (o.length == 0) {
			throw new AvaliderException("Aucun resultat");
		}
		Facture[] p=new Facture[o.length];
		Facture fact=new Facture();
		Class c=fact.getClass();
		Field[] field=c.getDeclaredFields();
		html+="<table>";
		for(int j=0;j<field.length;j++){
		html+="<th>"+field[j].getName()+"</th>";
		}
		for (int i=0;i<p.length ;i++ ) {
			p[i]=(Facture)o[i];
			html+="<tr>";
			html+="<td>"+p[i].getIdFacture()+"</td>";
			html+="<td>"+p[i].getIdClient()+"</td>";
			html+="<td>"+p[i].getDatyFact()+"</td>";
			html+="</tr>";
		}
		html+="</table>";
        col=new String[1];
        val=new String[1];
        col[0]="idClient";
        val[0]=idclient;
        Object[] o2=f.multiFind("DetailFacture",col,val);
        DetailFacture[] df=new DetailFacture[o2.length];
        DetailFacture detail=new DetailFacture();
        c=detail.getClass();
        field=c.getDeclaredFields();
        html+="<h3>DetailFacture</h3>";
        html+="<table>";
        for(int j=0;j<field.length;j++){
		html+="<th>"+field[j].getName()+"</th>";
		}
        for (int k=0;k<o2.length;k++) {
        	df[k]=(DetailFacture)o2[k];
        	html+="<tr>";
        	    html+="<td>"+df[k].getIdDetailFacture()+"</td>";
        	    html+="<td>"+df[k].getNumCompteur()+"</td>";
        	    html+="<td>"+df[k].getIdClient()+"</td>";
        	    html+="<td>"+df[k].getIdTranche()+"</td>";
        	    html+="<td>"+df[k].getPrixUnitaire()+"</td>";
        	    html+="<td>"+df[k].getConsommation()+"</td>";
        	html+="</tr>";
        }
        html+="</table>";
			html+="</body>";

			html+="</html>";
		}catch (Exception e) {
			throw e;
		}
		return html;
	}
	
}