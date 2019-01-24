package function;
import dbconnect.*;
import java.sql.*;
import java.lang.reflect.*;
public class Insert{
	public Insert(){}
	public int[] getFieldstype(Object check) throws Exception{
			Class cls=check.getClass();
			Field[] f=cls.getDeclaredFields();
			int[] tabint=new int[f.length];
			String s=new String();
			System.out.println("class anle string\t"+s.getClass().getName()+"\n");
			System.out.print("field class\t"+f[2].getType().getName()+"\n");
			for (int e=0;e<f.length ;e++ ) {
				String fieldclass=f[e].getType().getName();
				String classtring=s.getClass().getName();
				if (fieldclass.equalsIgnoreCase(classtring)) {
					tabint[e]=1;
					
				}else{
				tabint[e]=0;
				}
			}
			return tabint;
		}

		
public int insert(String table,Object o,DBconnect d)throws Exception{
		int v=0;
			try{
				if (d == null) {
						d=new DBconnect("jirama","jirama");
						d.getConnect().setAutoCommit(false);
					}
						Statement s=d.getConnect().createStatement();
					Class tabclass=o.getClass();
					String classe=tabclass.getName();
					int indice=classe.indexOf(".");
					// String classe2=classe.substring(indice+1);
					Field[] field=tabclass.getDeclaredFields();

					String g="insert into "+table+" values (";
					// afficherField(table);

					for (int w=0;w<(field.length) ;w++ ) {
					String 	attribut=field[w].getName();
					attribut=attribut.substring(0,1).toUpperCase()+attribut.substring(1);
					Method m=tabclass.getMethod("get"+attribut);
					System.out.print(attribut+"\n");
					String refinsert="id"+table;
					// System.out.print(refinsert);
					int[] type=getFieldstype(o);
							if (attribut.equalsIgnoreCase(refinsert)) {
								g=g+table+"Sequence.nextval";
								System.out.print(g);
							}else{
						if(type[w] == 1){
							g =g+ "'" +(m.invoke(o))+"'"/*+(field[w].getName())*/;
							// g =g+ "'" +attribut+"'";
						}else if ( field[w].getType().getName().equalsIgnoreCase("java.sql.Timestamp")) {
							g= g+"TIMESTAMP '"+m.invoke(o)+"'";
						}else if (field[w].getType().getName().equalsIgnoreCase("java.sql.Date")) {
							g =g+ "TO_DATE('" +(m.invoke(o))+"','YYYY-MM-DD')"/*+field[w].getName()*/;
						}
						else{
								
							g=g+(m.invoke(o))/*+field[w].getName()*/;
							}
							// g=g+attribut;
						}
						if(w<field.length - 1){
							g = g+",";
						}
						v++;
					}
			g= g+")";
					System.out.println(g);
			s.execute(g);	
			int r=s.executeUpdate("commit");
			s.close();	
			d.closeConnect();
			}catch (ClassNotFoundException e) {
				System.out.print("class not found");
			}catch (InvocationTargetException x) {
			    Throwable cause = x.getCause();
			    cause.getMessage();
			}catch (Exception e) {
			System.out.print(e.getMessage());
			
		}
		return v;
	}
}