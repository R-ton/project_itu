package function;
import dbconnect.*;
import java.sql.*;
import java.util.*;
import java.lang.reflect.*;
public class FindBase{	
	public FindBase(){}
	    /***********find mankany @ base***************/
	
    public Object[] multiFind(String nomTable,String[] colone, String[] value) throws Exception{            
    	Object[] val=null;
    	ResultSet rs=null;
		Statement st2=null;
	 		Connection mandray=null;
    	try{
    		 DBconnect db=new DBconnect("jirama","jirama");
			mandray=db.getConnect();
            mandray.setAutoCommit(false);
			Statement st=mandray.createStatement();
			int nombreAttrib=colone.length;
			String request;
			int l=4;
			String request0="create view vue0 as select * from " +nomTable+ " where "+colone[0]+" like '"+value[0]+"'";
			System.out.println(request0);
			// int valinyInt=st.executeUpdate(request0);
			st.execute(request0);
			int lengthMoinsUn=nombreAttrib-1;
			st.close();
			Statement st1;
			int mampiditra;
			int j;
				for(j=1;j<lengthMoinsUn;j++) {
					st1=mandray.createStatement();
					l=j-1;
					request="create view vue"+j+" as select * from view"+l+" where "+colone[j]+" like '"+value[j]+"'"; 
					System.out.println(request);
					mampiditra=st1.executeUpdate(request);
					st1.close();
				}
				l=j-1;
			 st2=mandray.createStatement();
			String requestFinal="select * from vue"+l+" where "+colone[lengthMoinsUn]+" like '"+value[lengthMoinsUn]+"'";
			System.out.println(requestFinal);            
			rs=st2.executeQuery(requestFinal);
			Vector result=new Vector();
			int s=0;			
			Class a=Class.forName("table."+nomTable);
            Object temp=a.newInstance();
			Field[] attrib=a.getDeclaredFields();
			String nomAttrib;
			while(rs.next()) {
				for(int p=0;p<attrib.length;p++) {
							Class[] args=new Class[1];
							args[0]=attrib[p].getType();
							nomAttrib=attrib[p].getName();
								nomAttrib=nomAttrib.substring(0,1).toUpperCase()+nomAttrib.substring(1);
							Method m=a.getDeclaredMethod("set"+nomAttrib,args);
							System.out.println(m.getName());
							if (m == null) {
								
							}else{
									if(attrib[p].getType().getName().equals("java.lang.String")) {
							m.invoke(temp,rs.getString(nomAttrib));
						}
						else{
							if(attrib[p].getType().getName().equals("int")) {
								m.invoke(temp,rs.getInt(nomAttrib));
							}else if (attrib[p].getType().getName().equalsIgnoreCase("java.sql.Timestamp")) {
									m.invoke(temp,rs.getTimestamp(nomAttrib));
								}else{
									if (attrib[p].getType().getName().equalsIgnoreCase("java.sql.Date")) {
										m.invoke(temp,rs.getDate(nomAttrib));	
									}else {
										
										Number ohatra=(Number) rs.getObject(nomAttrib); 
										m.invoke(temp,ohatra);
									}
								}
							
					}
							}
					
				}
				result.addElement(temp);
			}
            for(int delete=0;delete<=l;delete++) {
                Statement stfarany=mandray.createStatement();
                String suppr="drop view vue"+delete+"";                
                int effacer=stfarany.executeUpdate(suppr);
                mandray.commit();
                stfarany.close();
            }
			val=result.toArray();
			/*if (val.length == 0) {
				throw new Exception("Aucun resultat trouve");
			}*/
            mandray.commit();
    	}catch (Exception e) {
    		
         throw e;
    	}finally{
    		if (rs != null && st2 != null && mandray != null) {
    			
    		rs.close();
			st2.close();
	 		mandray.close();  
    		}
    	}
	 		return val;
    }
    /*************************/
    	   public Object[] multiFindExtended(String nomTable,String[] colone, String[] value,String extend) throws Exception{            
    	Object[] val=null;
    	ResultSet rs=null;
		Statement st2=null;
	 		Connection mandray=null;
						Vector result=new Vector();
    	try{
    		 DBconnect db=new DBconnect("jirama","jirama");
			mandray=db.getConnect();
            mandray.setAutoCommit(false);
			String request;
			for (int i=0;i<(value.length-2) ;i++ ) {
				// System.out.println(i);
		 	st2=mandray.createStatement();
				
			request="select * from "+nomTable+" where "+colone[0]+" "+extend+" TO_DATE('"+value[i]+"','YYYY-MM-DD') and TO_DATE('"+value[i+1]+"','YYYY-MM-DD') and "+colone[1]+" like '"+value[i+2]+"'";
			System.out.println(request);
			rs=st2.executeQuery(request);
			int s=0;			
			Class a=Class.forName("table."+nomTable);
			Field[] attrib=a.getDeclaredFields();
			String nomAttrib;
			while(rs.next()) {
				System.out.println(i);
				i++;
            Object temp=a.newInstance();
				for(int p=0;p<attrib.length;p++) {
							Class[] args=new Class[1];
							args[0]=attrib[p].getType();
							nomAttrib=attrib[p].getName();
								nomAttrib=nomAttrib.substring(0,1).toUpperCase()+nomAttrib.substring(1);
							Method m=a.getDeclaredMethod("set"+nomAttrib,args);
							System.out.println(m.getName());
							if (m == null) {
								
							}else{
									if(attrib[p].getType().getName().equals("java.lang.String")) {
							m.invoke(temp,rs.getString(nomAttrib));
						}
						else{
							if(attrib[p].getType().getName().equals("int")) {
								m.invoke(temp,rs.getInt(nomAttrib));
							}else if (attrib[p].getType().getName().equalsIgnoreCase("java.sql.Timestamp")) {
									m.invoke(temp,rs.getTimestamp(nomAttrib));
								}else{
									if (attrib[p].getType().getName().equalsIgnoreCase("java.sql.Date")) {
										m.invoke(temp,rs.getDate(nomAttrib));	
									}else {
										
										Number ohatra=(Number) rs.getObject(nomAttrib); 
										m.invoke(temp,ohatra);
									}
								}
							
							}
						}
					
				}
				result.add(temp);
				System.out.println("vectsize\t"+result.size());
			}
		}
			val=result.toArray();
    	    mandray.commit();
    	}catch (Exception e) {
    		
         throw e;
    	}finally{
    		if (rs != null && st2 != null && mandray != null) {
    			
    		rs.close();
			st2.close();
	 		mandray.close();  
    		}
    	}
	 		return val;
    }
    /*************************/
      public Object[] multiFindAdvanced(String nomTable,String[] colone, String[] value) throws Exception{            
    	Object[] val=null;
    	ResultSet rs=null;
		Statement st2=null;
	 		Connection mandray=null;
    	try{
    		 DBconnect db=new DBconnect("jirama","jirama");
			mandray=db.getConnect();
            mandray.setAutoCommit(false);
			int nombreAttrib=colone.length;
			System.out.print(nombreAttrib);
			String request;
			int l=0;
			int lengthMoinsUn=nombreAttrib-1;
			int mampiditra;
			int j;
			Vector result=new Vector();
			String requestFinal="";
			for (j=0;j<(nombreAttrib-1) ;j++ ) {
				
			 st2=mandray.createStatement();
			 if (colone[j+1].equalsIgnoreCase("daty")) {
			requestFinal="select * from "+nomTable+" where "+colone[j]+" like '"+value[j]+"' and "+colone[j+1]+"=TO_DATE('"+value[j+1]+"','YYYY-MM-DD')";
			 }else{
				requestFinal="select * from "+nomTable+" where "+colone[j]+" like '"+value[j]+"' and "+colone[j+1]+" '"+value[j+1]+"'"; 	
			 }
			System.out.println(requestFinal);            
			rs=st2.executeQuery(requestFinal);
			int s=0;			
			Class a=Class.forName("table."+nomTable);
            Object temp=a.newInstance();
			Field[] attrib=a.getDeclaredFields();
			String nomAttrib;
				while(rs.next()) {
					for(int p=0;p<attrib.length;p++) {
								Class[] args=new Class[1];
								args[0]=attrib[p].getType();
								nomAttrib=attrib[p].getName();
									nomAttrib=nomAttrib.substring(0,1).toUpperCase()+nomAttrib.substring(1);
								Method m=a.getDeclaredMethod("set"+nomAttrib,args);
								System.out.println(m.getName());
								if (m == null) {
									
								}else{
										if(attrib[p].getType().getName().equals("java.lang.String")) {
								m.invoke(temp,rs.getString(nomAttrib));
							}
							else{
								if(attrib[p].getType().getName().equals("int")) {
									m.invoke(temp,rs.getInt(nomAttrib));
								}else if (attrib[p].getType().getName().equalsIgnoreCase("java.sql.Timestamp")) {
										m.invoke(temp,rs.getTimestamp(nomAttrib));
									}else{
										if (attrib[p].getType().getName().equalsIgnoreCase("java.sql.Date")) {
											m.invoke(temp,rs.getDate(nomAttrib));	
										}else {
											
											Number ohatra=(Number) rs.getObject(nomAttrib); 
											m.invoke(temp,ohatra);
										}
									}
								
						}
								}
						
					}
					result.addElement(temp);
				}
				st2.close();
			}
          
			val=result.toArray();
			/*if (val.length == 0) {
				throw new Exception("Aucun resultat trouve");
			}*/
            mandray.commit();
    	}catch (Exception e) {
    		
         throw e;
    	}finally{
    		if (rs != null && st2 != null && mandray != null) {
    			
    		rs.close();
			st2.close();
	 		mandray.close();  
    		}
    	}
	 		return val;
    }
    /******************************/
    public Object[] findRowOneColumn(String nomTable,String[] col,String[] value)throws Exception{
    	Object[] val=null;
    	Connection mandray=null;
    	ResultSet rs=null;
    	Statement st2=null;
    	try{
    		 DBconnect db=new DBconnect("jirama","jirama");
			mandray=db.getConnect();
            mandray.setAutoCommit(false);
			Statement st=mandray.createStatement();
			int nombreAttrib=col.length;
			int nombreVal=value.length;
			String request;
			int l=4;
			String request0="create view vue0 as select * from " +nomTable+ " where "+col[0]+" like '"+value[0]+"'";
			System.out.println(request0);
			// int valinyInt=st.executeUpdate(request0);
			st.execute(request0);
			int lengthMoinsUn=nombreAttrib-1;
			st.close();
			Statement st1=null;
			int mampiditra=0;
			int j,n2=nombreVal-1;
			for (j=1;j<nombreVal;j++ ) {
				st1=mandray.createStatement();
				request="create view vue"+j+" as select * from " +nomTable+ " where "+col[0]+" like '"+value[j]+"'";
				System.out.println(request);
				mampiditra=st1.executeUpdate(request);			

				st1.close();
			}
			l=j-1;
			st2=mandray.createStatement();
			String requestFinal;
			int i=0;
			Vector result=new Vector();
			for (i=0;i<nombreVal;i++ ) {
				
			requestFinal="select * from vue"+l+" where "+col[0]+" like '"+value[i]+"'";
			System.out.println(requestFinal);            
			rs=st2.executeQuery(requestFinal);
			int s=0;			
			Class a=Class.forName("table."+nomTable);
            Object temp=a.newInstance();
			Field[] attrib=a.getDeclaredFields();
			String nomAttrib;
			// Class voidclass=Class.forName("java.lang.Void");

			//tester
			// Constructor meth=a.getConstructor(Void.TYPE);

				while(rs.next()) {
					for(int p=0;p<attrib.length;p++) {
								Class[] args=new Class[1];
								args[0]=attrib[p].getType();
								nomAttrib=attrib[p].getName();
									nomAttrib=nomAttrib.substring(0,1).toUpperCase()+nomAttrib.substring(1);
								Method m=a.getDeclaredMethod("set"+nomAttrib,args);
								System.out.println(m.getName());
								if (m == null) {
									
								}else{
									// temp=meth.newInstance(temp);
										if(attrib[p].getType().getName().equals("java.lang.String")) {
								m.invoke(temp,rs.getString(nomAttrib));
							}
							else{
								if(attrib[p].getType().getName().equals("int")) {
									m.invoke(temp,rs.getInt(nomAttrib));
								}else if (attrib[p].getType().getName().equalsIgnoreCase("java.sql.Timestamp")) {
										m.invoke(temp,rs.getTimestamp(nomAttrib));
									}else{
										if (attrib[p].getType().getName().equalsIgnoreCase("java.sql.Date")) {
											m.invoke(temp,rs.getDate(nomAttrib));	
										}else {
											
											Number ohatra=(Number) rs.getObject(nomAttrib); 
											m.invoke(temp,ohatra);
										}
									}
								
						}
								}
						
					}
					result.add(temp);
				}
			}
			System.out.println("vector cap"+result.size());
            for(int delete=0;delete<=l;delete++) {
                Statement stfarany=mandray.createStatement();
                String suppr="drop view vue"+delete+"";        
                System.out.println(suppr);        
                int effacer=stfarany.executeUpdate(suppr);
                mandray.commit();
                stfarany.close();
            }
			// val=result.toArray();
            int size=result.size();
			val=new Object[size];
			for (i=0;i<size ;i++ ) {
				val[i]=new Object();
				val[i]=result.elementAt(i);
			}
			/*if (val.length == 0) {
				throw new Exception("Aucun resultat trouve");
			}*/
            mandray.commit();
    	}catch (Exception e) {
    		throw e;
    	}finally{
    		if (rs != null && st2 != null && mandray != null) {
    			
    		rs.close();
			st2.close();
	 		mandray.close();  
    		}
    	}
    	return val;

    }
}