package function;
import dbconnect.*;
import java.sql.*;
import table.*;
import exception.*;
import java.util.*;
import java.lang.reflect.*;
public class Update{
	 public void update(Object inserer)  throws ClassNotFoundException, SQLException,Exception {
		Class a=inserer.getClass(); 
		String fantarina=a.getName();
		fantarina=fantarina.substring(6);
        String colone0="id"+fantarina;
		DBconnect db=new DBconnect("jirama","jirama");
		Connection mandray=db.getConnect();
		// Statement st=mandray.createStatement();
		Field[] attrib=a.getDeclaredFields();
		int j=attrib.length;
		String request="";
        String sequence=fantarina+"Sequence.currval";
		int entrer;
		/*if(attrib[0].getType().getName().equals("java.lang.String")) {*/
				// request="insert into "+fantarina+" ("+colone0+") values("+sequence+")";
			/*}
			else {
				request="insert into "+fantarina+"("+attrib[0].getName()+") values("+(Number) attrib[0].get(inserer)+")";

			}*/	
			// System.out.println(request);		
			// entrer=st.executeUpdate(request);
   //          st.execute("COMMIT");
   //          st.close();
            Statement stk=mandray.createStatement();
            String req="select MAX("+colone0+") as id from "+fantarina+"";
            ResultSet rsk=stk.executeQuery(req);
			Statement st1;
            String idV=" ";
            String nomAttrib;
            while(rsk.next()) {
                idV=rsk.getString("ID");
            }
            rsk.close();
            stk.close();
		for(int k=0;k<j;k++) {
            Class[] args=new Class[1];
								args[0]=attrib[k].getType();
								nomAttrib=attrib[k].getName();
									nomAttrib=nomAttrib.substring(0,1).toUpperCase()+nomAttrib.substring(1);
								Method m=a.getDeclaredMethod("get"+nomAttrib);
								System.out.println(m.getName());
			st1=mandray.createStatement();
            if(attrib[k].getName().equals(colone0)) {

            }
            else {
                if(attrib[k].getType().getName().equals("java.lang.String")) {
                	System.out.println(attrib[k].getName());

				request="update " +fantarina+" set "+attrib[k].getName()+"='"+(m.invoke(inserer))+"' where "+colone0+"='"+idV+"'";
			    }else if (attrib[k].getType().getName().equalsIgnoreCase("java.sql.Timestamp")) {
							request="update " +fantarina+" set "+attrib[k].getName()+"='"+"TIMESTAMP '"+(Timestamp)m.invoke(inserer)+"' where "+colone0+"='"+idV+"'";
						}else if (attrib[k].getType().getName().equalsIgnoreCase("java.sql.Date")) {
							request="update " +fantarina+" set "+attrib[k].getName()+"="+"TO_DATE('" +((java.sql.Date)m.invoke(inserer))+"','YYYY-MM-DD') where "+colone0+"='"+idV+"'";
						}
                else {
                    request="update "+fantarina+" set "+attrib[k].getName()+"="+(Number)(m.invoke(inserer))+" where "+colone0+"='"+idV+"'";
                }
			st1=mandray.createStatement();
			System.out.println(request);
			entrer=st1.executeUpdate(request);
			mandray.commit();
			}
            st1.close();
            }	
            
		// st.close();
		// mandray.close();		
		}
		 public void update(Object inserer,String id)  throws ClassNotFoundException, SQLException,Exception {
		 	System.out.println("argument id in update\t"+id);
				Class a=inserer.getClass(); 
		String fantarina=a.getName();
		fantarina=fantarina.substring(6);
        String colone0="id"+fantarina;
		DBconnect db=new DBconnect("jirama","jirama");
		Connection mandray=db.getConnect();
		// Statement st=mandray.createStatement();
		Field[] attrib=a.getDeclaredFields();
		int j=attrib.length;
		String request="";
        String sequence=fantarina+"Sequence.currval";
		int entrer;
		/*if(attrib[0].getType().getName().equals("java.lang.String")) {*/
				// request="insert into "+fantarina+" ("+colone0+") values("+sequence+")";
			/*}
			else {
				request="insert into "+fantarina+"("+attrib[0].getName()+") values("+(Number) attrib[0].get(inserer)+")";

			}*/	
			// System.out.println(request);		
			// entrer=st.executeUpdate(request);
   //          st.execute("COMMIT");
   //          st.close();
   //          Statement stk=mandray.createStatement();
   //          String req="select MAX("+colone0+") as id from "+fantarina+"";
   //          ResultSet rsk=stk.executeQuery(req);
			Statement st1;
            String idV=" ";
            String nomAttrib;
            // while(rsk.next()) {
            //     idV=rsk.getString("ID");
            // }
            // rsk.close();
            // stk.close();
		for(int k=0;k<j;k++) {
            Class[] args=new Class[1];
								args[0]=attrib[k].getType();
								nomAttrib=attrib[k].getName();
									nomAttrib=nomAttrib.substring(0,1).toUpperCase()+nomAttrib.substring(1);
								Method m=a.getDeclaredMethod("get"+nomAttrib);
								System.out.println(m.getName());
			st1=mandray.createStatement();
            if(attrib[k].getName().equals(colone0)) {

            }
            else {
                if(attrib[k].getType().getName().equals("java.lang.String")) {
                	System.out.println(attrib[k].getName());

				request="update " +fantarina+" set "+attrib[k].getName()+"='"+(m.invoke(inserer))+"' where "+colone0+"='"+id+"'";
			    }else if (attrib[k].getType().getName().equalsIgnoreCase("java.sql.Timestamp")) {
							request="update " +fantarina+" set "+attrib[k].getName()+"='"+"TIMESTAMP '"+(Timestamp)m.invoke(inserer)+"' where "+colone0+"='"+id+"'";
						}else if (attrib[k].getType().getName().equalsIgnoreCase("java.sql.Date")) {
							request="update " +fantarina+" set "+attrib[k].getName()+"="+"TO_DATE('" +((java.sql.Date)m.invoke(inserer))+"','YYYY-MM-DD') where "+colone0+"='"+id+"'";
						}
                else {
                    request="update "+fantarina+" set "+attrib[k].getName()+"="+(Number)(m.invoke(inserer))+" where "+colone0+"='"+id+"'";
                }
			st1=mandray.createStatement();
			System.out.println(request);
			entrer=st1.executeUpdate(request);
			mandray.commit();
			}
            st1.close();
            }	
            
		// st.close();
		// mandray.close();		
		}
}