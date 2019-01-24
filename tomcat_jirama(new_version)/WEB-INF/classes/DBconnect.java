package dbconnect;
import java.sql.*;

public class DBconnect /*implements Connection*/{
	DriverManager m;
	Connection connect;
	Statement stat;
	String usr,pwd;
	public DBconnect(String user,String password)throws Exception{
		try{
			// String url="jdbc:postgresql://localhost:5432/oracle";
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			
			this.usr=user;
			this.pwd=password;
			// String user="postgres";
			// String pwd="1234";
			// this.m=new DriverManager();
			this.connect=this.m.getConnection(url,this.usr,this.pwd);
			this.stat=this.connect.createStatement();
			if (this.connect != null ) {
				System.out.println("DB connected");
			}else {
				System.out.println("Failed to connect");
			}
		}catch(Exception e){
			//afficher message erreur
			throw e;
		}

	}
	public Statement getStatement() throws Exception{
		return this.stat;
	}
	public Connection getConnect() throws Exception{
		return this.connect;
	}
	public void closeConnect() throws Exception{
		this.connect.close();
	}
}
