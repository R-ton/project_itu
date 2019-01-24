package control;
import function.*;
import java.sql.*;
// import java.util.*;
import dbconnect.*;
import table.*;
import exception.*;
import java.util.Vector;
public class Control{
	int verifierDoublonOffre(DBconnect d,String unite)throws Exception,AvaliderException{
		try{
			if (d == null) {
				d=new DBconnect("jirama","jirama");
			}
			String[] col=new String[1];
			col[0]="unite";
			String[] val=new String[1];
			val[0]=unite;
			FindBase f=new FindBase();
			Object[] o=f.multiFind("Abonnement",col,val);
			if (o.length != 0) {
			throw new AvaliderException("offre existante");
			}
		}catch (Exception e) {
			throw e;
		}
		return 0;
	}
}