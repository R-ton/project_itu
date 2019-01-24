package affichage;
import table.*;
import java.sql.*;
import function.*;
import dbconnect.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class testprepayer {
	public static void main(String[] args)throws Exception {
		ComptePrepayer c=new ComptePrepayer();

		java.sql.Date dat=new java.sql.Date(System.currentTimeMillis());
		c.setDateDebut(dat.toString());
		System.out.println(c.getDateDebut());
	}
}