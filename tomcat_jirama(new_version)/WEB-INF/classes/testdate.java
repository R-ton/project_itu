package affichage;
import table.*;
import java.sql.*;
import function.*;
import dbconnect.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class testdate{
	public static void main(String[] args)throws Exception {
		java.sql.Date dat=new java.sql.Date(System.currentTimeMillis());
		System.out.println(dat); 
		// java.sql.Date dat = java.sql.Date.valueOf(date);
//create calander instance and get required params
		Calendar cal = Calendar.getInstance();
		// cal.setTime(dat);
		// int month = cal.get(Calendar.MONTH);
		// int day = cal.get(Calendar.DAY_OF_MONTH);
		// int year = cal.get(Calendar.YEAR);
		// System.out.println(year);
		// String year="2018";
		// String month="12";
		// String concat1=year+"-"+month+"-01";
		// String concat2=year+"-"+month+"-31";
		// FindBase f=new FindBase();
		// String[] col=new String[2];
		// String[] val=new String[3];
		// col[0]="daty";
		// col[1]="idClient";
		// val[0]=concat1;
		// val[1]=concat2;
		// val[2]="1";
		// Object[] o=f.multiFindExtended("Prelevement",col,val,"between");
		// Prelevement[] p=new Prelevement[o.length];
		// for (int i=0;i<p.length ;i++ ) {
		// 	p[i]=(Prelevement)o[i];
		// }
		// int k=0;
		// while (k<o.length) {
		// 	System.out.println(p[k].getNumCompteur());
		// 	k++;
		// }
		int maxdayofmonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("maxdayofmonth\t"+maxdayofmonth);
		cal.add(Calendar.DAY_OF_MONTH, maxdayofmonth);
		// System.out.println(cal);
		java.util.Date date=cal.getTime();
		System.out.println(date);
		int year=2018;
		int month=01;
		int dt=20;
		cal.set(year, month, dt);
		System.out.println(cal);
		int mx2=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(mx2);

	}
}