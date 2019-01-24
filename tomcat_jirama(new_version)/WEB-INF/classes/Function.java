package function;
import java.sql.*;
// import java.util.*;
import dbconnect.*;
import exception.*;
import table.*;
import java.util.Vector;
import java.lang.reflect.*;
public class Function{

	public int[] Etat(DBconnect c)throws Exception{
		int[] val=null;
	try{
		if (c == null) {
			
		c=new DBconnect("jirama","jirama");
		}
		String sql="select Etat from Prelevement";
		Statement st=c.getConnect().createStatement();
		ResultSet rs=st.executeQuery(sql);
		int n=0;
		while(rs.next()){n++;}
		val=new int[n];
		ResultSet rs2=st.executeQuery(sql);
		int u=0;
		while(rs.next()){
			val[u]=rs.getInt(1);
			u++;
		}
		rs2.close();
		rs.close();
		st.close();
		c.closeConnect();
	}catch (Exception e) {
		throw e;
	}	
		return val;
	}
	public String[] getEtat(DBconnect db,int[] e){
		String[] s=null;
		s=new String[e.length];
		for (int i=0;i< e.length;i++ ) {
			if (e[i] == 0) {
				s[i]=new String("Annuler");
			}
			else if(e[i] == 1){
				s[i]=new String("Prelever");
			}
			if (e[i] == 2) {
				s[i]=new String("Facturer");
			}else{
				s[i]=new String("Payer");
			}
		}
		return s;
	}
	public Client[] getIdClient(DBconnect d)throws Exception{
		Client[] c=null;
		Vector v=new Vector();
		if (d == null) {
			d=new DBconnect("jirama","jirama");
		}
		String sql="select idClient from client";
		Statement s=d.getConnect().createStatement();
		ResultSet rs=s.executeQuery(sql);
		int n=0;
		while(rs.next()){
			Client cl=new Client();
			cl.setIdClient(rs.getString("IDCLIENT"));
			v.add(cl);
			n++;
		}
		c=new Client[n];
		for(int i=0;i<v.size();i++){
			c[i]=new Client();
			c[i]=(Client)v.elementAt(i);
		}
		d.getConnect().close();
		return c;
	}

	public Prelevement[] getPrelevement(Client cl,DBconnect d)throws Exception{
		Prelevement[] p=null;
		try{	
			String sql="select * from Prelevement,Client where prelevement.idClient=Client.idClient Client.Nom like "+cl.getNom()+" and Client.Prenom like "+cl.getPrenom();
			Statement st=d.getConnect().createStatement();
			ResultSet rs=st.executeQuery(sql);
			int n=0;
			while(rs.next()){n++;}
			rs=st.executeQuery(sql);
			p=new Prelevement[n];
			int n2=0;
			while(rs.next()){
				p[n2]=new Prelevement(rs.getString(1),rs.getString(2),rs.getDate(3),(Number)rs.getObject(4),rs.getString(5),(Number)rs.getObject(6),(Number)rs.getObject(7));
				n2++;
			}
			rs.close();
			st.close();
			d.closeConnect();
		}catch (Exception e) {
			throw e;	
		}	
		return p;
	}
		public Prelevement[] getPrelevement(DBconnect d) throws Exception{
		Prelevement[] p=null;
		Vector v=new Vector();
		try{
			String sql="select * from Prelevement";
			Statement st=d.getStatement();
			ResultSet rs=st.executeQuery(sql);
			System.out.print(sql);
			int n=0;
			Prelevement pr=null;
			while(rs.next()){
				pr=new Prelevement();
				pr.setIdPrelevement(rs.getString(1));
				pr.setIdClient(rs.getString(2));
				pr.setDaty(rs.getDate(3));
				pr.setNumCompteur((Number)rs.getObject(4));
				pr.setCategorie(rs.getString(5));
				pr.setConsommation((Number)rs.getObject(6));
				pr.setEtat((Number)rs.getObject(7));
				v.addElement(pr);
				n++;
			}
			p=new Prelevement[n];
			int m=0;
			for (m=0;m<n ;m++ ) {
				p[m]=(Prelevement)v.elementAt(m);
			}
			rs.close();
			st.close();
			d.closeConnect();
		}catch (Exception e) {
			throw e;
		}	
		return p;
	}

	/****************************************/
		public TrancheType[] getTranche(DBconnect d) throws Exception{
		TrancheType[] t=null;
		try{
			if (d == null) {
				d=new DBconnect("jirama","jirama");
			}
			String sql="select * from tranchetype";
			Statement s=d.getConnect().createStatement();
			ResultSet rst=s.executeQuery(sql);
			int r=0;
				while(rst.next()){r++;}
				rst=s.executeQuery(sql);
				t=new TrancheType[r];
				int u=0;
				while(rst.next()){
					t[u]=new TrancheType(rst.getString(1),rst.getString(2),(Number)rst.getObject(3),(Number)rst.getObject(4));
					u++;
				}
			rst.close();
			s.close();
			// d.closeConnect();
		}catch (Exception e) {
			throw e;
		}
			return t;
	}
	
	/****************************************/
	public String getPrelevementTab(DBconnect d)throws Exception{
		String str="";
		str+="<html>";
			str+="<head>";
				str+="<title>Liste Prelevement</title>";
			str+="</head>";
			str+="<body>";
			str+="<form action='formulaire.jsp' method='post'>";
			str+="<p><input type='submit' value='Prelevement' name='insertPrelevement'>(inserer)</p>";
			str+="<p><input type='submit' value='recherche' name='rechercheFacture'></p>";
			str+="<p><input type='submit' value='Abonnement' name='Abonner'></p>";
			str+="</form>";

					str+="<form action='facturer.jsp' method='post'>";
				str+="<table style='float:left;'>";
					str+="<th>Numero</th>";
					str+="<th>idClient</th>";
					str+="<th>Daty</th>";
					str+="<th>NumCompteur</th>";
					str+="<th>Categorie</th>";
					str+="<th>Consommation</th>";
					str+="<th>Etat</th>";
					TrancheType[] t=getTranche(d);
					Prelevement[] p=getPrelevement(d);
					int i=0,j=0;
					String etat0="Non Facturer";
					String etat1="Facturer non payer";
					String etat2="Facturer payer";
					for (i=0;i<p.length ;i++ ) {	
						System.out.println(p[i].getEtat());
					str+="<tr>";

						// str+="<input type='hidden' name='box' value='"+p[i].getNumCompteur()+"'/>";
						str+="<td>"+p[i].getIdPrelevement()+"</td>";
						str+="<td>"+p[i].getIdClient()+"</td>";
						str+="<td>"+p[i].getDaty()+"</td>";
						str+="<td>"+p[i].getNumCompteur()+"</td>";
						str+="<td>"+p[i].getCategorie()+"</td>";
						str+="<td>"+p[i].getConsommation()+"</td>";
						if (p[i].getEtat() == null || p[i].getEtat().intValue() == 0) {
							
						str+="<td>"+etat0+"</td>";
						}else if (p[i].getEtat().intValue() == 1) {
							str+="<td>"+etat1+"</td>";
						}else if (p[i].getEtat().intValue() == 2) {
							str+="<td>"+etat2+"</td>";
						}
						str+="<td><input type='checkbox' name='box' value='"+p[i].getIdPrelevement()+"'/></td>";
						str+="<td><input type=\"hidden\" name=\"dt\" value='"+p[i].getDaty()+"'></td>";
						System.out.println("aaaaaaaa\t"+p[i].getDaty());
					str+="</tr>";
					}
					str+="<input type='submit' value='Facturer'>";
				str+="</table>";
				str+="</form>";
				str+="<table style='float:right'>";
					str+="<th>NumTRanche</th>";
					str+="<th>libellet</th>";
					str+="<th> MaxUnite</th>";
					str+="<th>PrixUnitaire</th>";

					for (j=0;j<t.length ;j++ ) {
						str+="<tr>";
						str+="<td>"+t[j].getIdTranche()+"</td>";
						str+="<td>"+t[j].getLibellet()+"</td>";
						str+="<td>"+t[j].getMaxUnite()+"</td>";
						str+="<td>"+t[j].getPrixUnitaire()+"</td>";
						str+="</tr>";
					}
				str+="</table>";
			str+="</body>";
		str+="</html>";
		return str;
	}
	/***************************************************/
		public TrancheType[] getTrancheId(String id,DBconnect d) throws Exception{
		TrancheType[] t=null;
		if (id == "") {
			throw new Exception("TRanche invalide");
		}
		if (d == null) {
			System.out.print("io aryw \t");
			d=new DBconnect("jirama","jirama");
		}
		String sql="select * from getTrancheId where idTranche = '"+id+"'";
		Statement s=d.getConnect().createStatement();
		ResultSet rst=s.executeQuery(sql);
		int r=0;
			while(rst.next()){r++;}
			t=new TrancheType[r];
			int u=0;
			rst=s.executeQuery(sql);
			while(rst.next()){
				t[u]=new TrancheType(rst.getString(1),rst.getString(2),(Number)rst.getObject(3),(Number)rst.getObject(4));
				u++;
			}
			s.close();
			rst.close();
			d.closeConnect();
		
			return t;
	}
	public TarifEau[] getTarifEau(DBconnect d) throws Exception{
		TarifEau[] t=null;
		try{
		if (d == null) {
			d=new DBconnect("jirama","jirama");
		}
		String sql="select * from TarifEau";
		Statement s=d.getConnect().createStatement();
		ResultSet rst=s.executeQuery(sql);
		int r=0;
			while(rst.next()){r++;}
			rst=s.executeQuery(sql);
			t=new TarifEau[r];
			int u=0;
			while(rst.next()){
				t[u]=new TarifEau((Number)rst.getObject(1),(Number)rst.getObject(2),(Number)rst.getObject(3),(Number)rst.getObject(4));
				u++;
			}
			rst.close();
			s.close();
			// d.getConnect().close();
		}catch (Exception e) {
			throw e;
		}
			return t;
	}

	public TarifElectricite[] getTarifElectricite(DBconnect d) throws Exception{
			TarifElectricite[] t=null;
		try{
			
				if (d == null) {
					d=new DBconnect("jirama","jirama");
				}
				String sql="select * from TarifElectricite";
				Statement s=d.getConnect().createStatement();
				ResultSet rst=s.executeQuery(sql);
				int r=0;
					while(rst.next()){r++;}
					rst=s.executeQuery(sql);
					t=new TarifElectricite[r];
					int u=0;
					while(rst.next()){
						t[u]=new TarifElectricite((Number)rst.getObject(1),(Number)rst.getObject(2),(Number)rst.getObject(3),(Number)rst.getObject(4),(Number)rst.getObject(5),(Number)rst.getObject(6));
						u++;
					}
					rst.close();
					s.close();
					// d.getConnect().close();
		}catch (Exception e) {
			throw e;
		}
					return t;
	}

	public Compteur[] getCompteur(DBconnect d) throws Exception{
		Compteur[] co=null;
		Vector v=new Vector();
		try{
			if (d == null) {
				d=new DBconnect("jirama","jirama");
			}
				String sql="select * from Compteur";
				Statement st=d.getConnect().createStatement();
				ResultSet rst=st.executeQuery(sql);
				int w=0;
				Compteur compt=null;
				// int rows=rs.getRow();
				// System.out.print(rows);
				int z=0;
				while(rst.next()){
				// for (int z=0;z<rows ;z++ ) {
					 compt=new Compteur((Number)rst.getObject(1),rst.getString(2),(Number)rst.getObject(3),rst.getString(4),rst.getString(5));
					System.out.println("---------------------------");
					v.addElement(compt);
					z++;
				// }
			}
				rst.close();
				st.close();
				d.closeConnect();
			co=new Compteur[z];
			int l=0;
			for (l=0;l<z;l++) {
				co[l]=(Compteur)v.elementAt(l);
			}
		}catch (Exception e) {
			throw e;
		}
		return co;
	}

	public Compteur[] getCompteur(Client cl,DBconnect d) throws Exception{
		Compteur[] compt=null;
			d=new DBconnect("jirama","jirama");
			String sql="select * from getUniteparCategorie where client.nom='"+cl.getNom()+"' and client.prenom='"+cl.getPrenom()+"'";
			Statement stat=d.getStatement();
			ResultSet rs=stat.executeQuery(sql);
			int w=0;
			while(rs.next()){w++;}
			rs=stat.executeQuery(sql);
			compt=new Compteur[w];
			int z=0;
			while(rs.next()){
				compt[z]=new Compteur((Number)rs.getObject(1),rs.getString(2),(Number)rs.getObject(3),rs.getString(4),rs.getString(5));
				z++;
			}
			// finally{
			stat.close();
		// }
			return compt;
	}

	// public float getConsoElectricite(Compteur c,String tranche) throws Exception{
	// 	float f1=0;
	// 	float f2=0;

	// 	int num=c.getNumCompteur();
	// 	// TarifElectricite[] b=this.getTarifElectricite();
	
		// 	Prelevement[] p=this.getPrelevement(c);

	// 				int numc=c.getNumCompteur();
	// 	Prelevement[] pr=getPrelevement(c);
// 		// TarifElectricite[] a=getTarifElectricite();
// 		// 				float elec=pr[0].getConsommation();
// 		// // 				System.out.print("eau"+eau);
// 		// 				float tranche1=a[0].getPelec1();
// 		// 				float tranche2=a[0].getPelec2();
// 		// 				float tranche3=a[0].getPelec3();
// 		// 				float max1=a[0].getMaxuniteElectricite1();
// 		// 				float max2=a[0].getMaxuniteElectricite2();
// 		// 				float max3=a[0].getMaxuniteElectricite3();
// 						if (elec<=max1) {
// 								f1=elec * tranche1;
// 								return f1;
								
// 							}else if(ecart>max1){
// 								if((max2>ecart) && (max1<ecart)){
// 								finaltranche1=max1*tranche1;
// 									delta=ecart;
// 									finaltranche2=delta*max2;	
// 									f1=finaltranche1+finaltranche2;
// 									return f1;
// 								}else if(ecart>max2){
// 								finaltranche1=max1*tranche1;
// 								System.out.print("mxt3"+max1+"\t"+tranche1+"\n");
// 								System.out.print("finaltranche1\t="+finaltranche1);
// 								delta=max2-max1;
// 								System.out.print("max2=\t"+max2+"\n"+"max1=\t"+max1+"\n");
// 								System.out.print("delta\t"+delta);
// 								finaltranche2=delta*tranche2;
// 								System.out.print("finaltranche2\t="+finaltranche2);
// 								finaltranche3=(elec-(max1+delta))*tranche3;
// 						f1=finaltranche2+finaltranche1+finaltranche3;
// 						return f1;
// 				}
// 		return f1;
// }
	public double[] getFloatPU()throws Exception{
		DBconnect d=new DBconnect("jirama","jirama");
		TrancheType[] trtp=this.getTranche(d);
		int l=trtp.length;
		double[] tab=new double[l];
		for (int i=0;i<trtp.length ;i++ ) {
			tab[i]=trtp[i].getPrixUnitaire().doubleValue();
		}
		d.closeConnect();
		return tab;
	}
	public double[] testTrancheId(String tranche,DBconnect d)throws Exception{
		// DBconnect d=new DBconnect("")
		TrancheType[] t=getTrancheId(tranche,d);
		int l=t.length;
		System.out.print("tranchetype length\t"+l+"\n");
		double[] result=new double[l*2];
		for(int h=0;h<l;h++){
			result[h]=t[h].getMaxUnite().doubleValue();
			System.out.print("maxunite\t"+result[h]+"\n");
		}
		// System.out.print("resultlength"+result.length);
		int u=l++;
		int v=0;
		int resultlength=result.length;
		int r=(resultlength/2);
		r=r++;
		while ((u<result.length) && (v<r) ){
			result[u]=t[v].getPrixUnitaire().doubleValue();
			System.out.println("RESULT\t"+result[u]+"\n");
			u++;
			v++;
		}
		d.closeConnect();
		return result;
	}
	public void affichertest(float[] f){
		for (int y=0;y<f.length ;y++ ) {
			System.out.println("afficher\t"+f[y]+"\n");
		}
	}
	public double getTrancheparReste(double reste,DBconnect d) throws Exception{
		double result=0;
		try{

			if(d == null){
				d=new DBconnect("jirama","jirama");
			}
				TarifElectricite[] a=getTarifElectricite(null);
					
		// // 				System.out.print("eau"+eau);
						double finaltranche1=0;
						double finaltranche2=0;
						double finaltranche3=0;
						double delta=0;
						double tranche1=a[0].getPelec1().doubleValue();
						double tranche2=a[0].getPelec2().doubleValue();
						double tranche3=a[0].getPelec3().doubleValue();
						double max1=a[0].getMaxuniteElectricite1().doubleValue();
						double max2=a[0].getMaxuniteElectricite2().doubleValue();
						double max3=a[0].getMaxuniteElectricite3().doubleValue();
				double ecart=reste-max1;
						if (reste<=max1) {
								result=reste * tranche1;
								
							}else if(ecart>max1){
								if((max2>ecart) && (max1<ecart)){
								finaltranche1=max1*tranche1;
									delta=ecart;
									finaltranche2=delta*max2;	
									result=finaltranche1+finaltranche2;
									
								}else if(ecart>max2){
								finaltranche1=max1*tranche1;
								System.out.println("mxt3"+max1+"\t"+tranche1);
								System.out.println("finaltranche1\t="+finaltranche1);
								delta=max2-max1;
								System.out.println("max2=\t"+max2+"\n"+"max1=\t"+max1+"\n");
								System.out.println("delta\t"+delta);
								finaltranche2=delta*tranche2;
								System.out.println("finaltranche2\t="+finaltranche2);
								finaltranche3=(reste-(max1+delta))*tranche3;
						result=finaltranche2+finaltranche1+finaltranche3;
								}
		
							}

		}catch (Exception e) {
			throw e;
		}	
		return result;
	}
		public Prelevement getDate(String id)throws Exception{
			FindBase f=new FindBase();
			String[] col=new String[1];
			String[] val=new String[1];
			col[0]="idPrelevement";
			val[0]=id;
			Object[] o=f.multiFind("Prelevement",col,val);
			Prelevement p=(Prelevement)o[0];
			return p;
		}
		public Prelevement[] getOrder(DBconnect d,String id)throws Exception{
		Prelevement[] val=null;
		try{
		Prelevement date=getDate(id);
		String sql="select * from Prelevement where NumCompteur="+date.getNumCompteur()+"  and daty < TO_DATE('"+date.getDaty()+"','YYYY-MM-DD') order by daty desc";
		System.out.println(sql);
		Statement stat=d.getConnect().createStatement();
		ResultSet rs=stat.executeQuery(sql);
		Vector v=new Vector();
		Prelevement compt=null;
			int i=0,j=0;
			while(rs.next()){
				compt=new Prelevement(rs.getString(1),rs.getString(2),rs.getDate(3),(Number)rs.getObject(4),rs.getString(5),(Number)rs.getObject(6),(Number)rs.getObject(7));
				v.add(compt);
				i++;
			}
			val=new Prelevement[i];
			for (j=0;j<i ;j++ ) {
				val[j]=(Prelevement)v.elementAt(j);
			}
			rs.close();
			stat.close();
			if (val.length == 0) {
				System.out.println("val.length=0");
			// 	throw new AvaliderException("sql2");
		sql="select * from Prelevement where NumCompteur="+id+" and daty = TO_DATE('"+date+"','YYYY-MM-DD') order by daty desc";
		System.out.println(sql);
		stat=d.getConnect().createStatement();
		rs=stat.executeQuery(sql);
		 v=new Vector();
		 compt=null;
			 i=0;j=0;
			while(rs.next()){
				compt=new Prelevement(rs.getString(1),rs.getString(2),rs.getDate(3),(Number)rs.getObject(4),rs.getString(5),(Number)rs.getObject(6),(Number)rs.getObject(7));
				v.add(compt);
				i++;
			}
			val=new Prelevement[i];
			for (j=0;j<i ;j++ ) {
				val[j]=(Prelevement)v.elementAt(j);
			}
			// if (val.length == 0) {
			// 	throw new AvaliderException("sql2");
			// }
			// finally{
	
			rs.close();
			stat.close();
			}
			// finally{
		}
			catch (Exception e) {
				System.out.println(e.getMessage());
			throw e;
		}
			return val;
	}
		public double getConsoEau(Compteur c/*,String dt*/,DBconnect d) throws Exception{
		double f=0;
		try{
				int num=c.getNumCompteur().intValue();
		System.out.println("ConsommationEau=\t"+c.getUnite()); 
		// String sql="select (prelevement.consommationeau-tarif.maxuniteeau) as PayerEau from prelevement,tarifselect (prelevement.consommationeau-tarif.maxuniteeau) as PayerEau from prelevement,tarif";
			Prelevement[] pr=getOrder(d,c.getNumCompteur().toString());
		TarifEau[] a=getTarifEau(d);
		// 				System.out.print("eau"+eau)
						double eau1=pr[0].getConsommation().doubleValue();
						double eau2=0;
						if (pr.length == 1 ) {
							
							eau2=0;
						}else{
						 eau2=pr[1].getConsommation().doubleValue();
						}
						double eau=eau1-eau2;
						System.out.println("eau1\t"+eau1);
						System.out.println("eau2\t"+eau2);
						System.out.println("eau\t"+eau);

                        double finaltranche1=0;
						double finaltranche2=0;
						double delta=0;

						double tranche1=a[0].getPet1().doubleValue();
						System.out.print("tranche1\t"+tranche1+"\n");

						double tranche2=a[0].getPet2().doubleValue();

						double max1=a[0].getMaxuniteEau1().doubleValue();
						double max2=a[0].getMaxuniteEau2().doubleValue();
						System.out.print("maxuniteeau\t"+max1+"\n");
						double ecart=eau-max1;
							if (eau <= max1) {
								f+=max1 * tranche1;
								System.out.print("valeur 1"+f);
							}
						else if(ecart>max1){
								if((max2>ecart) && (max1<ecart)){
								finaltranche1=max1*tranche1;
									delta=ecart;
									finaltranche2=delta*max2;	
									f+=finaltranche1+finaltranche2;
									
								}else if(ecart>max2){
								finaltranche1=max1*tranche1;
								System.out.println("mxt3"+max1+"\t"+tranche1);
								System.out.println("finaltranche1\t="+finaltranche1);
								delta=max2-max1;
								System.out.println("max2=\t"+max2+"\n"+"max1=\t"+max1+"\n");
								System.out.println("delta\t"+delta);
								finaltranche2=delta*tranche2;
								System.out.println("finaltranche2\t="+finaltranche2);
								
						f+=finaltranche2+finaltranche1;
								}
						// f+=finaltranche2+finaltranche1;
					}

		}catch (Exception e) {
			throw e;
		}
		return f;
	}

public double getConsoElectriciteperso(Compteur c,String tranche,DBconnect d) throws Exception{
		double f1=0;
		double f2=0;
	try{

		if(d == null){
			d=new DBconnect("jirama","jirama");
		}
		int num=c.getNumCompteur().intValue();
		if (tranche == null || tranche == "0" || tranche == "") {
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
/*		
	manao find generaliser maka anle numero anle compteur
	misy probleme anle [0] sy [1]
	lasa mitovy zareo
		// 		FindBase f=new FindBase();
		// String[] col=new String[1];
		// col[0]="NumCompteur";
		// String[] val=new String[1];
		// val[0]=c.getNumCompteur().toString();
		// Object[] o=f.multiFind("Prelevement",col,val);
		// Prelevement[] pr=new Prelevement[o.length];
		// for (int i=0;i<pr.length ;i++ ) {
		// 	pr[i]=(Prelevement)o[i];
		// 	System.out.print(pr[i].getNumCompteur());
		// }

*/
	System.out.println(c.getNumCompteur().toString());
		Prelevement[] pr=getOrder(d,c.getNumCompteur().toString());
		// TarifElectricite[] tarifelec=this.getTarifElectricite(null);
				TarifElectricite[] a=getTarifElectricite(null);
						double elec1=pr[0].getConsommation().doubleValue();
						double elec2=pr[1].getConsommation().doubleValue();
						double elec=elec1-elec2;
						System.out.println("elec1\t"+elec1);
						System.out.println("elec2\t"+elec2);
						System.out.println("elec\t"+elec);

		// // 				System.out.print("eau"+eau);
						double finaltranche1=0;
						double finaltranche2=0;
						double finaltranche3=0;
						double delta=0;
						double tranche1=a[0].getPelec1().doubleValue();
						double tranche2=a[0].getPelec2().doubleValue();
						double tranche3=a[0].getPelec3().doubleValue();
						double max1=a[0].getMaxuniteElectricite1().doubleValue();
						double max2=a[0].getMaxuniteElectricite2().doubleValue();
						double max3=a[0].getMaxuniteElectricite3().doubleValue();
				double ecart=elec-max1;
						if (elec<=max1) {
								f1+=elec * tranche1;
								
							}else if(ecart>max1){
								if((max2>ecart) && (max1<ecart)){
								finaltranche1=max1*tranche1;
									delta=ecart;
									finaltranche2=delta*max2;	
									f1+=finaltranche1+finaltranche2;
									
								}else if(ecart>max2){
								finaltranche1=max1*tranche1;
								System.out.println("mxt3"+max1+"\t"+tranche1);
								System.out.println("finaltranche1\t="+finaltranche1);
								delta=max2-max1;
								System.out.println("max2=\t"+max2+"\n"+"max1=\t"+max1+"\n");
								System.out.println("delta\t"+delta);
								finaltranche2=delta*tranche2;
								System.out.println("finaltranche2\t="+finaltranche2);
								finaltranche3=(elec-(max1+delta))*tranche3;
						f1+=finaltranche2+finaltranche1+finaltranche3;
								}
		
							}
		}else{
			System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		TrancheType[] b=getTrancheId(tranche,null);
		double[] tranchefloat=this.getFloatPU();
					int numc=c.getNumCompteur().intValue();
		// FindBase f=new FindBase();
		// String[] col=new String[1];
		// col[0]="NumCompteur";
		// String[] val=new String[1];
		// val[0]=c.getNumCompteur().toString();
		// Object[] o=f.multiFind("Prelevement",col,val);
		// Prelevement[] pr=new Prelevement[o.length];
		// for (int i=0;i<pr.length ;i++ ) {
		// 	pr[i]=(Prelevement)o[i];
		// 	System.out.print(pr[i].getNumCompteur());
		// }
		// TarifElectricite[] a=getTarifElectricite();
					Prelevement[] pr=getOrder(d,c.getNumCompteur().toString()/*,dt*/);
							double elec1=pr[0].getConsommation().doubleValue();
						double elec2=pr[1].getConsommation().doubleValue();
						double elec=elec1-elec2;
						System.out.println("elec1\t"+elec1);
						System.out.println("elec2\t"+elec2);
						System.out.println("elec\t"+elec);

		// 				System.out.print("eau"+eau);
						double tranche1=0;
						double tranche2=0;
						double tranche3=0;
						double max1=0;
						double max2=0;
						double max3=0;
						double finaltranche1=0;
						double finaltranche2=0;
						double finaltranche3=0;
						double delta=0;
						Number var=0;
						int j=0;
//						boucle manao 0 anze valeur null ny anatiny
// 						for (j=0;j<b.length ;j++ ) {
//		 				if (b[j].getMaxUnite() == null) {
// 							b[j].getMaxUnite()=var;
// 							}
// 						}
							max1=b[0].getMaxUnite().doubleValue();
							System.out.print("max1=\t"+max1+"\n");
							max2=b[1].getMaxUnite().doubleValue();
							// max3=b[2].getMaxUnite().doubleValue();
							max3=0;
								tranche1=b[0].getPrixUnitaire().doubleValue();
								System.out.print("tranche1=\t"+tranche1);
									tranche2=b[1].getPrixUnitaire().doubleValue();
								tranche3=b[2].getPrixUnitaire().doubleValue();
								double ecart=elec-max1;
								System.out.print("ecart=\t"+ecart+"\n");
							if (elec<=max1) {
								f1=elec * tranche1;
							
							}else if(ecart>max1){
								if((max2>ecart) && (max1<ecart)){
								finaltranche1=max1*tranche1;
									delta=ecart;
									finaltranche2=delta*max2;	
									f1=finaltranche1+finaltranche2;
								
								}else if(ecart>max2){
								finaltranche1=max1*tranche1;
								System.out.print("mxt3"+max1+"\t"+tranche1+"\n");
								System.out.print("finaltranche1\t="+finaltranche1);
								delta=max2-max1;
								System.out.print("max2=\t"+max2+"\n"+"max1=\t"+max1+"\n");
								System.out.print("delta\t"+delta);
								finaltranche2=delta*tranche2;
								System.out.print("finaltranche2\t="+finaltranche2);
								finaltranche3=(elec-(max1+delta))*tranche3;
						f1=finaltranche2+finaltranche1+finaltranche3;
							}
						}
		}
	}catch (Exception e) {
		throw e;
	}
		return f1;
}
Prelevement[] getCategorie(Compteur x,DBconnect d) throws Exception{
		Prelevement[] ctgr=null;
	String sql="select Categorie from Prelevement where Categorie='"+x.getCategorie()+"'";
	Statement stmt=d.getConnect().createStatement();
		ResultSet rest=stmt.executeQuery(sql);
		int cop=0;
		while(rest.next()){cop++;}
		ctgr=new Prelevement[cop];
		int u=0;
		ResultSet rest2=stmt.executeQuery(sql);
		while(rest2.next()){
			System.out.print(rest2.getString(1));
			ctgr[u]=new Prelevement(rest2.getString(1));
			// u++;
			break;
		}
		rest2.close();
		stmt.close();
	return ctgr;
}
	public String[] splitDate(Date d){
		String strd=d.toString(); 
		String[] s=null;
		s=strd.split("-");
		String p1=s[0];
		String p3=s[2];
		s[0]=p3;
		s[2]=p1;
		for (int y=0;y<(s.length-1) ;y++ ) {
			s[y]=s[y]+"-";
		}
		return s;
	}
		public String getSequence(DBconnect d,String table)throws Exception{
			String str;
			try{

			Integer n=0;
           if (d == null) {
             d=new DBconnect("jirama","jirama");
           }
           String sql="select "+table+"Sequence.currval from dual";
           System.out.println(sql);
           Statement s=d.getConnect().createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()){
                n += rs.getInt(1);
			}
			rs.close();
			s.close();
			d.closeConnect();
			str=n.toString();
		}catch (java.sql.SQLException e) {
			str="1";
		}
			return str;
		}
		public Compteur[] getStringIdToCompteur(String[] id)throws Exception{
			Compteur[] result=new Compteur[id.length];
			int i=0;
			Integer a=null;
			Number n=0;
			while(i<id.length){
				System.out.println(id[i]);
				a=new Integer(id[i]);
				n=a;
                result[i]=new Compteur(n);
				i++;
			}
			return result;
		}
	public int setFact(Compteur[] cpt,String tranche,String mois,String annee/*,String[] date*/,DBconnect d) throws Exception,AvaliderException{
    try{

			if(d == null){
				d=new DBconnect("jirama","jirama");
			}
			String[] col=null;
			String[] val=null;
			if (cpt.length == 1) {
					    System.out.println(cpt[0].getNumCompteur());
				int n=cpt[0].getNumCompteur().intValue();
				col=new String[1];
				col[0]="NumCompteur";
				val=new String[1];
				int i=0;
					val[0]=cpt[0].getNumCompteur().toString();
				FindBase f=new FindBase();
				Object[] o=f.multiFind("Prelevement",col,val);
				// if (cpt[bc].getIdClient() != cpt[bc+1].getIdClient()) {
				// 	throw new AvaliderException("le numero du client est different");
				// }
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
			
			Prelevement[] pcateg=getCategorie(cpt[0],d);
			String client=p[0].getIdClient();
			double conso=p[0].getConsommation().doubleValue();
			System.out.println("Categorie\t"+p[0].getCategorie());
			String cat=p[0].getCategorie();
			// java.util.Date utilDate=new java.util.Date();
			// java.sql.Date dt = new java.sql.Date(utilDate.getDate());
			java.sql.Date dt=new java.sql.Date(System.currentTimeMillis());
			String[] split=splitDate(dt);
			double PU=0; 
			if (cat.equalsIgnoreCase("Eau")) {
						System.out.println(cat);
									TarifEau[] te=getTarifEau(d);
			     	PU=te[0].getPet1().doubleValue();
			     	System.out.println("prix PrixUnitaire eau\t"+PU);
					double rano=this.getConsoEau(cpt[0]/*,date[0]*/,d);
					System.out.println("rano\t"+rano);
						Facture fact=new Facture("Facture",client,dt);
							DetailFacture detail=new DetailFacture("detailfacture",n,client,"0",PU,rano);
								Insert insert=new Insert();
								insert.insert("Facture",fact,null); 
					insert.insert("DetailFacture",detail,null);
					String idfact=getSequence(null,"Facture");
					if (idfact.compareTo("1") == 0) {
						idfact="1";
					}
					PrelevementFacture pf=new PrelevementFacture("PrelevementFacture",p[0].getIdPrelevement(),idfact);
					insert.insert("PrelevementFacture",pf,null);
					//update etat prelevement lasa 1
					p[0].setEtat(1);
					Update update=new Update();
					Object objupdate=(Object)p[0];
					update.update(objupdate,p[0].getIdPrelevement());
			//  sql="insert into Facture Values('idPrelevement.nextval'"+","+client+","+n+","+dt+rano/*+sommepayer*/+")";
			// stmt.executeQuery(sql);
			// stmt.execute("commit");	
			// stmt.close();
			}else{
				double jiro=getConsoElectriciteperso(cpt[0],tranche/*,date[0]*/,d);
				System.out.println(jiro);
				FindBase find=new FindBase();
				Object[] obj=find.multiFind("GetTrancheCompteur",col,val);
				// Prelevement[] p=(Prelevement[])o;
				if (obj.length == 0) {
					TarifElectricite[] te=getTarifElectricite(d);
					PU=te[0].getPelec1().doubleValue();
				}else{
					GetTrancheCompteur gtc=(GetTrancheCompteur)(obj[0]);
				Number nb=gtc.getPrixUnitaire();
				PU=nb.doubleValue();
				}
				String idTranche1="1";
				String idTranche="";
				idTranche=cpt[0].getIdTranche();
				if (idTranche == null) {
					idTranche=idTranche1;
				}
				System.out.println("prixunitaire facture\t"+PU);
				// TrancheType[] tr=getTranche(null);
				Facture fact=new Facture("Facture",client,dt);
				DetailFacture detail=new DetailFacture("detailfacture",n,client,idTranche,PU,jiro);
				Insert insert=new Insert();
				insert.insert("Facture",fact,null); 
				insert.insert("DetailFacture",detail,null);
				String idfact=getSequence(null,"Facture");
				if (idfact.compareTo("1") == 0) {
					idfact="1";
				}
				PrelevementFacture pf=new PrelevementFacture("PrelevementFacture",p[0].getIdPrelevement(),idfact);
				insert.insert("PrelevementFacture",pf,null);
				//update etat prelevement lasa 1
				p[0].setEtat(1);
				Update update=new Update();
				Object objupdate=(Object)p[0];
				update.update(objupdate,p[0].getIdPrelevement());
				
			}
		}else{

							for(int bc=0;bc<(cpt.length-1);bc++){
					// Statement stmt=d.getConnect().createStatement();
					System.out.println(cpt[bc].getNumCompteur());
					int n=cpt[bc].getNumCompteur().intValue();
					col=new String[1];
					col[0]="NumCompteur";
					val=new String[1];
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
					
					Prelevement[] pcateg=getCategorie(cpt[bc],d);
					String client=p[bc].getIdClient();
					double conso=p[bc].getConsommation().doubleValue();
					System.out.println("Categorie\t"+p[bc].getCategorie());
					String cat=p[bc].getCategorie();
					// java.util.Date utilDate=new java.util.Date();
					// java.sql.Date dt = new java.sql.Date(utilDate.getDate());
					java.sql.Date dt=new java.sql.Date(System.currentTimeMillis());
					String[] split=splitDate(dt);
					double PU=0; 
					if (cat.equalsIgnoreCase("Eau")) {
								System.out.println(cat);
											TarifEau[] te=getTarifEau(d);
					     	PU=te[0].getPet1().doubleValue();
					     	System.out.println("prix PrixUnitaire eau\t"+PU);
							double rano=this.getConsoEau(cpt[bc]/*,date[0]*/,d);
							System.out.println("rano\t"+rano);
								Facture fact=new Facture("Facture",client,dt);
									DetailFacture detail=new DetailFacture("detailfacture",n,client,"0",PU,rano);
										Insert insert=new Insert();
										insert.insert("Facture",fact,null); 
							insert.insert("DetailFacture",detail,null);
							String idfact=getSequence(null,"Facture");
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
					//  sql="insert into Facture Values('idPrelevement.nextval'"+","+client+","+n+","+dt+rano/*+sommepayer*/+")";
					// stmt.executeQuery(sql);
					// stmt.execute("commit");	
					// stmt.close();
					}else{
						double jiro=getConsoElectriciteperso(cpt[bc],tranche/*,date[0]*/,d);
						System.out.println(jiro);
						FindBase find=new FindBase();
						Object[] obj=find.multiFind("GetTrancheCompteur",col,val);
						// Prelevement[] p=(Prelevement[])o;
						if (obj.length == 0) {
							TarifElectricite[] te=getTarifElectricite(d);
							PU=te[0].getPelec1().doubleValue();
						}else{
							GetTrancheCompteur gtc=(GetTrancheCompteur)(obj[0]);
						Number nb=gtc.getPrixUnitaire();
						PU=nb.doubleValue();
						}
						String idTranche1="1";
						String idTranche="";
						idTranche=cpt[bc].getIdTranche();
						if (idTranche == null) {
							idTranche=idTranche1;
						}
						System.out.println("prixunitaire facture\t"+PU);
						// TrancheType[] tr=getTranche(null);
						Facture fact=new Facture("Facture",client,dt);
						// System.out.println("idfacture"+fact);
						DetailFacture detail=new DetailFacture("detailfacture",n,client,idTranche,PU,jiro);
						Insert insert=new Insert();
						insert.insert("Facture",fact,null); 
						insert.insert("DetailFacture",detail,null);
						String idfact=getSequence(null,"Facture");
						if (idfact.compareTo("1") == 0) {
							idfact="1";
						}
						PrelevementFacture pf=new PrelevementFacture("PrelevementFacture",p[bc].getIdPrelevement(),/*idfact*/getSequence(null,"PrelevementFacture"));
						insert.insert("PrelevementFacture",pf,null);
						//update etat prelevement lasa 1
						p[0].setEtat(1);
						Update update=new Update();
						Object objupdate=(Object)p[0];
						update.update(objupdate,p[0].getIdPrelevement());
						
					}
				}
			
			
			//
			// stmt.execute("commit");	
			// stmt.close();
		}
			d.getConnect().commit(); 
		}catch (Exception e) {
			throw e;
		}
		// d.getConnect().close();
		return 0;
	}
		public int setFactAvoir(Compteur[] cpt,String numberRembourser,String date)throws Exception{
		DBconnect d=new DBconnect("jirama","jirama");
		int[] ret=new int[cpt.length];
		for (int dev=0;dev<cpt.length ;dev++ ) {
			
        DetailFactureAndFacture[] df=getFact(cpt[dev],null);
      	Double d1=new Double(numberRembourser);
      	double d2=d1.doubleValue();
      	System.out.println(d2);
        if (d2 < df[0].getConsommation().doubleValue()) {
        	System.out.println("FactureAvoir");
        Insert a=new Insert();
        FactureAvoir fa=new FactureAvoir("FactureAvoir",df[0].getIdFacture(),df[0].getIdClient(),df[0].getDatyFact()); 
        Object o=(Object)fa;
        a.insert("FactureAvoir",o,null);
        String[] col=new String[1];
        String[] val=new String[1];
        	col[0]="NumCompteur";
        	val[0]=cpt[dev].getNumCompteur().toString();
        	FindBase f=new FindBase();
        	Object[] obj=f.multiFind("Compteur",col,val);
        	Compteur[] c=new Compteur[obj.length];
        	for (int k=0;k<obj.length ;k++ ) {
        		c[k]=(Compteur)obj[k];
        	}
        // col[0]="idfacture";
        // 	val[0]=cpt[dev].getNumCompteur().toString();
        Prelevement[] p=getOrder(d,cpt[dev].getNumCompteur().toString()/*,date*/);
        PrelevementFacture pf=new PrelevementFacture();
        pf.setIdPrelevement(p[0].getIdPrelevement());
        pf.setIdFacture(df[0].getIdFacture());
        Object obj2=(Object)pf;
        a.insert("PrelevFactAnnuler",obj2,null);
        double conso=0,consorendre=0;
        		// if (cpt[dev].getCategorie().equalsIgnoreCase("Eau")) {
        		if (c[0].getCategorie().equalsIgnoreCase("Eau")) {
					// conso=getConsoEau(cpt[dev],date[dev],d);
        			conso=d2;
       		}else{
       			// conso=getConsoElectriciteperso(cpt[dev],cpt[dev].getIdTranche(),date[dev],d);
       			conso=d2;
       		}
       		consorendre-=conso;
       		// DetailFacture(,n,client,idTranche,PU,jiro);
       		DetailFactureAnnuler dfa=new DetailFactureAnnuler("DetailFactureAnnuler",df[0].getIdDetailFacture(),df[0].getNumCompteur(),df[0].getIdClient(),df[0].getIdTranche(),df[0].getPrixUnitaire(),consorendre);
       		a.insert("DetailFactureAnnuler",dfa,d);
       		return 1;
       		// ret[dev]=1;
        	}else if (d2 == df[0].getConsommation().doubleValue()) {
        		System.out.println("FactureAvoirINtegrale");
        	    Insert a=new Insert();
        	FactureAnnuler fannuler=new FactureAnnuler("fa",df[0].getIdFacture(),df[0].getIdClient(),df[0].getDatyFact());
        	Object o=(Object)fannuler;
        	a.insert("FactureAnnuler",o,null);
        	FactureAvoir favoir=new FactureAvoir("fa",df[0].getIdFacture(),df[0].getIdClient(),df[0].getDatyFact());
        	o=(Object)favoir;
        	a.insert("FactureAvoir",o,null);
        	// ret[dev]=0;
        	}else {
        		throw new AvaliderException("Impossible d voir une facture d avoir plus grand que la Consommation");
        	}
		}
		return 0;
		// return ret;
	}
	public int InsertFactureAnnuler(String[] id,String numberRembourser,String[] daty)throws Exception{
				System.out.println("InsertFactureAnnuler");
           Compteur[] cpt=getStringIdToCompteur(id);
		int i=setFactAvoir(cpt,numberRembourser,daty[0]);
		
		return i;
	}
	// public String getFactAnnuler(String id)throws Exception{
	// 	String html="<html>";
	// 	try{
	// 		String[] col=new String[1];
	// 		col[0]="NumCompteur";
	// 		String[] val=new String[1];
	// 		val[0]=id;
	// 		FindBase f=new FindBase();
	// 		Object[] o=f.multiFind("DetailFactureAnnuler",col,val);
	// 		DetailFactureAnnuler[] dfa=new DetailFactureAnnuler[o.length];
	// 		int i=0;
	// 		while (i<o.length) {
	// 			dfa[i]=(DetailFactureAnnuler)o[i];
	// 			i++;
	// 		}
	// 		o=f.multiFind("DetailFacture",col,val);
	// 		DetailFacture[] df=new DetailFacture[o.length];
	// 		DetailFacture s=new DetailFacture();
	// 		i=0;
	// 		Class c=s.getClass();
	// 		Field[] field=c.getDeclaredFields();
	// 		while(i<o.length){
	// 			df[i]=(DetailFacture)o[i];
	// 			i++;
	// 		}
	// 		html+="<head>";
	// 			html+="<title>Facture Annuler</title>";
	// 		html+="</head>";
	// 		html+="<body>";
	// 			html+="<table>";			
	// 			for (int j=0;j<field.length;j++) {
	// 				html+="<th>";
	// 					html+=field[j].getName();
	// 				html+="</th>";
	// 			}
	// 			i=0;
	// 			while (i<df.length) {
	// 				html+="<tr>";
	// 					html+="<td>"+df[i].getNumCompteur()+"</td>";
	// 					html+="<td>"+df[i].getIdClient()+"</td>";
	// 					html+="<td>"+df[i].getIdTranche()+"</td>";
	// 					html+="<td>"+df[i].getPrixUnitaire()+"</td>";
	// 					double db=0;
	// 					for (int j=0;j<dfa.length ;j++ ) {
	// 					db=df[i].getConsommation().doubleValue()+dfa[j].getConsommation().doubleValue();
	// 					html+="<td>"+db+"</td>";
	// 					}
	// 				html+="</tr>";
	// 				i++;
	// 			}
	// 			html+="</table>";			
	// 		html+="</body>";
	// 		html+="</html>";
	// 	}catch (Exception e) {
	// 		throw e;
	// 	}
	// 	return html;
	// }
	public String getFactAnnuler(String[] id)throws Exception{
		String html="<html>";
		try{
			for (int dev=0;dev<id.length ;dev++ ) {
				
			String[] col=new String[1];
			col[0]="NumCompteur";
			String[] val=new String[1];
			val[0]=id[dev];
			FindBase f=new FindBase();
			Object[] o=f.multiFind("DetailFactureAnnuler",col,val);
			DetailFactureAnnuler[] dfa=new DetailFactureAnnuler[o.length];
			int i=0;
			while (i<o.length) {
				dfa[i]=(DetailFactureAnnuler)o[i];
				i++;
			}
			o=f.multiFind("DetailFacture",col,val);
			DetailFacture[] df=new DetailFacture[o.length];
			DetailFacture s=new DetailFacture();
			i=0;
			Class c=s.getClass();
			Field[] field=c.getDeclaredFields();
			while(i<o.length){
				df[i]=(DetailFacture)o[i];
				i++;
			}
			html+="<head>";
				html+="<title>Facture Annuler</title>";
			html+="</head>";
			html+="<body>";
				html+="<table>";			
				for (int j=0;j<field.length;j++) {
					html+="<th>";
						html+=field[j].getName();
					html+="</th>";
				}
				i=0;int j=0;
				while (i<df.length) {
					html+="<tr>";
						html+="<td>"+df[i].getNumCompteur()+"</td>";
						html+="<td>"+df[i].getIdClient()+"</td>";
						html+="<td>"+df[i].getIdTranche()+"</td>";
						html+="<td>"+df[i].getPrixUnitaire()+"</td>";
						while(j<dfa.length){
						double number=df[i].getConsommation().doubleValue()+dfa[j].getConsommation().doubleValue();
						html+="<td>"+number+"</td>";
						System.out.println("detailfacture"+df[i].getConsommation());
						System.out.println("DetailFactureAnnuler"+dfa[j].getConsommation());
						j++;
						}
					html+="</tr>";
					i++;
				}
				html+="</table>";			
			}
			html+="</body>";
			html+="</html>";
		}catch (Exception e) {
			throw e;
		}
		return html;
	}
	public int InsertFacture(String[] id,String tranche,String mois,String annee/*,String[] date*/,DBconnect d)throws Exception,AvaliderException{
		System.out.println("insertfacture");
           Compteur[] cpt=getStringIdToCompteur(id);
           
           int i=setFact(cpt,tranche,mois,annee/*,date*/,d);
		return 0;
	}
	public DetailFactureAndFacture[] getFact(Compteur x,DBconnect d)throws Exception{
		DetailFactureAndFacture[] result=null;
		Vector v=new Vector();
		if(d == null){
			d=new DBconnect("jirama","jirama");
		}
		// DetailFacture f=null;
		DetailFactureAndFacture f=null;
		// String sql=" select * from DetailFacture where numcompteur="+x.getNumCompteur();
		String sql=" select * from DetailFactureAndFacture where numcompteur="+x.getNumCompteur();
		Statement s=d.getConnect().createStatement();
		// ResultSet rst=s.executeQuery(sql);
		int n=0;
		// while(rst.next()){n++;}
		
		ResultSet rst2=s.executeQuery(sql);
		int p=0;
		while (rst2.next()) {
			// f=new DetailFacture(rst2.getString(1),(Number)rst2.getObject(2),rst2.getString(3),rst2.getString(4),(Number)rst2.getObject(5),(Number)rst2.getObject(6));
			f=new DetailFactureAndFacture(rst2.getString(1),(Number)rst2.getObject(2),rst2.getString(3),rst2.getString(4),(Number)rst2.getObject(5),(Number)rst2.getObject(6),rst2.getString(7),rst2.getDate(8));
			v.add(f);
			p++;
		}
		s.close();
		d.getConnect().close();
        result=new DetailFactureAndFacture[p];
        for (int i=0;i<p ;i++ ) {
        	result[i]=new DetailFactureAndFacture();
        	result[i]=(DetailFactureAndFacture)v.elementAt(i);
        }
		return result;
	}
	public String afficherClient(String id)throws Exception{
		String str="<html>";
		FindBase f=new FindBase();
		String[] col=new String[1];
		String[] val=new String[1];
		col[0]="idClient";
		val[0]=id;
		Object[] o=f.multiFind("Client",col,val);
		Client[] cl=new Client[o.length];
		for (int i=0;i<o.length ;i++ ) {
			cl[i]=new Client();
			cl[i]=(Client)o[i];
		}
		str+="<table>";
			str+="<th>Numero</th>";
			str+="<th>Nom</th>";
			str+="<th>Prenom</th>";
			for (int j=0;j<cl.length ;j++ ) {
			str+="<tr>";
				str+="<td>"+cl[j].getIdClient()+"</td>";
				str+="<td>"+cl[j].getNom()+"</td>";
				str+="<td>"+cl[j].getPrenom()+"</td>";
			str+="</tr>";
			}
		str+="</table>";
		return str;
	}
	// public String afficherFacture(String[] id,DBconnect d)throws Exception{
	// 	Compteur[] cpt=getStringIdToCompteur(id);
	// 	String str="<html>";
	// 	double total=0;
	// 	str+="<head>";
	// 		str+="<title>Affichage facture et payer</title>";
	// 	str+="</head>";
	// 	str+="<body>";
	// 	// str+="<form action='' method='post'>";
	// 		str+="<table>";
	// 		str+="<th>NumCompteur</th>";
	// 		str+="<th>idClient</th>";
	// 		str+="<th>idTranche</th>";
	// 		str+="<th>PrixUnitaire</th>";
	// 		str+="<th>Consommation</th>";
	// 			// str+=";
	// 		int i=0,l=cpt.length;
	// 			// DetailFacture[] df=getFact(cpt[i],d);
	// 			DetailFactureAndFacture[] df=getFact(cpt[i],d);
	// 		for (i=0;i<df.length ;i++ ) {
	// 			str+="<tr>";
	// 				str+="<td>"+df[i].getNumCompteur()+"</td>";
	// 				// str+="<td>"+df[0].getNumCompteur()+"</td>";
	// 				// str+="<td><a href='client.jsp?idclient="+df.getIdClient()+">"+df.getIdClient()+"</a></td>";
	// 				// str+="<td>"+df[0].getIdClient()+"</td>";
	// 				str+="<td>"+df[i].getIdClient()+"</td>";
	// 				// str+="<td>"+df[0].getIdTranche()+"</td>";
	// 				str+="<td>"+df[i].getIdTranche()+"</td>";
	// 				// str+="<td>"+df[0].getPrixUnitaire()+"</td>";
	// 				str+="<td>"+df[i].getConsommation()+"</td>";
	// 				// str+="<td>"+df[0].getConsommation()+"</td>";
	// 				total+=df[i].getConsommation().doubleValue();
	// 			str+="</tr>";
	// 		}
	// 		str+="</table>";
	// 		str+="<h4>Total:"+total+"</h4>";
	// 		str+="<input type='submit' value='Payer'>";
	// 		// str+="</form>";
	// 		str+="<form action='' method='post'>";
	// 		str+="<input type='submit' value='Reclamer'>";
	// 		str+="</form>";
	// 		str+="<a href='index.jsp'>Accueil</a>";
	// 	str+="</body>";
	// 	d.getConnect().close();
	// 	return str;
	// }
	public String afficherFacture(String[] id,String[] s,DBconnect d)throws Exception{
		Compteur[] cpt=getStringIdToCompteur(id);
		String str="<html>";
		double total=0;
		str+="<head>";
			str+="<title>Affichage facture et payer</title>";
		str+="</head>";
		str+="<body>";
		// str+="<form action='' method='post'>";
			str+="<form action='rembourser.jsp' method='post'>";
			str+="<table>";
			str+="<th>NumCompteur</th>";
			str+="<th>idClient</th>";
			str+="<th>idTranche</th>";
			str+="<th>PrixUnitaire</th>";
			str+="<th>Consommation</th>";
			str+="<th>Modifier</th>";
				// str+=";
			int i=0,l=cpt.length;
				// DetailFacture[] df=getFact(cpt[i],d);
			for (i=0;i<cpt.length ;i++ ) {
				DetailFactureAndFacture[] df=getFact(cpt[i],d);
				for (int j=0;j<df.length ;j++ ) {
					
				str+="<tr>";
					str+="<td>"+df[j].getNumCompteur()+"</td>";
					// str+="<td>"+df[0].getNumCompteur()+"</td>";
					// str+="<td><a href='client.jsp?idclient="+df.getIdClient()+">"+df.getIdClient()+"</a></td>";
					// str+="<td>"+df[0].getIdClient()+"</td>";
					str+="<td>"+df[j].getIdClient()+"</td>";
					// str+="<td>"+df[0].getIdTranche()+"</td>";
					str+="<td>"+df[j].getIdTranche()+"</td>";
					str+="<td>"+df[j].getPrixUnitaire()+"</td>";
					str+="<td>"+df[j].getConsommation()+"</td>";
							str+="<td><input type='checkbox' name='box' value='"+df[j].getNumCompteur()+"'/></td>";
							str+="<td><input type='hidden' name='dt' value='"+s[0]+"'></td>";
					// str+="<td>"+df[0].getConsommation()+"</td>";
					total+=df[j].getConsommation().doubleValue();
				str+="</tr>";
				}
				// for (int k=0;k<date.length;k++ ) {
				// 	str+="<input type='hidden' name='daty' value='"+date[k]+"'>";
				// }
			}
			str+="</table>";
			str+="<h4>Total:"+total+"</h4>";
			// str+="<input type='submit' value='Payer'/>";
			// str+="</form>";
			str+="<input type='submit' value='Reclamer'>";
			str+="</form>";
			str+="<a href='index.jsp'>Accueil</a>";
		str+="</body>";
		str+="</html>";
		d.getConnect().close();
		return str;
	}
	public void displaySplit(String[] str){
		for (int i=0;i<str.length ;i++ ) {
			System.out.print(str[i]);
		}	
	}

	public String displayRembourser(String[] str,String[] date)throws Exception{
		String html="<html>";
		html+="<head>";
			html+="<title>Affichage rembourser</title>";
		html+="</head>";
		html+="<body>";
        html+="<form action=\"traiterrembourser.jsp\">";
	 for (int i=0;i<str.length ;i++ ) {
		
	html+="<label>Argent Rembourser"+(str[i])+"</label>";
	html+="<input type=\"hidden\" name=\"box2\" value="+str[i]+">";
	html+="<input type=\"hidden\" name=\"dt\" value="+date[0]+">";
	html+="<input type=\"text\" name=\"inputNumber\">";
	
	
	} 
	html+="<input type=\"submit\" value=\"Valider\">";
	
	html+="</body>";
		html+="</html>";
	 return html;
	
	}

public DetailFactureAndFacture[] getFact(DBconnect d)throws Exception{
		DetailFactureAndFacture[] result=null;
		Vector v=new Vector();
		try{

		d=new DBconnect("jirama","jirama");
		// if(d == null){
		// 	d=new DBconnect("jirama","jirama");
		// }
		// DetailFacture f=null;
		DetailFactureAndFacture f=null;
		// String sql=" select * from DetailFacture where numcompteur="+x.getNumCompteur();
		String sql=" select * from DetailFactureAndFacture where iddetailfacture not in(select iddetailfacture from detailfactureannuler)";
		System.out.println(sql);
		Statement s=d.getConnect().createStatement();
		int n=0;
		
		ResultSet rst2=s.executeQuery(sql);
		int p=0;
		while (rst2.next()) {
			// f=new DetailFacture(rst2.getString(1),(Number)rst2.getObject(2),rst2.getString(3),rst2.getString(4),(Number)rst2.getObject(5),(Number)rst2.getObject(6));
			f=new DetailFactureAndFacture(rst2.getString(1),(Number)rst2.getObject(2),rst2.getString(3),rst2.getString(4),(Number)rst2.getObject(5),(Number)rst2.getObject(6),rst2.getString(7),rst2.getDate(8));
			v.add(f);
			p++;
		}
		s.close();
		d.getConnect().close();
        result=new DetailFactureAndFacture[p];
        for (int i=0;i<p ;i++ ) {
        	result[i]=new DetailFactureAndFacture();
        	result[i]=(DetailFactureAndFacture)v.elementAt(i);
        }
    }catch (Exception e) {
    	throw e;
    }
		return result;
	}
public DetailFactureAnnuler[] getFactAnnuler(DBconnect d)throws Exception{
		DetailFactureAnnuler[] result=null;
		Vector v=new Vector();

	try{

		d=new DBconnect("jirama","jirama");
		String sql=" select * from DetailFactureAnnuler";
		System.out.println(sql);
		Statement s=d.getConnect().createStatement();
		int n=0;
		
		ResultSet rst2=s.executeQuery(sql);
		int p=0;
		DetailFactureAnnuler f=new DetailFactureAnnuler();
		while (rst2.next()) {
			// f=new DetailFacture(rst2.getString(1),(Number)rst2.getObject(2),rst2.getString(3),rst2.getString(4),(Number)rst2.getObject(5),(Number)rst2.getObject(6));
			f=new DetailFactureAnnuler(rst2.getString(1),rst2.getString(2),(Number)rst2.getObject(3),rst2.getString(4),rst2.getString(5),(Number)rst2.getObject(6),(Number)rst2.getObject(7));
			v.add(f);
			p++;
		}
		s.close();
		d.getConnect().close();
        result=new DetailFactureAnnuler[p];
        for (int i=0;i<p ;i++ ) {
        	result[i]=new DetailFactureAnnuler();
        	result[i]=(DetailFactureAnnuler)v.elementAt(i);
        }
	}catch (Exception e) {
		throw e;
	}
	return result;
}
}