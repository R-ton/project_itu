<%@ page import="dbconnect.*,table.*,function.*,control.*,exception.*,javax.servlet.http.HttpSession" %>
<%
DBconnect d=new DBconnect("jirama","jirama"); 
Funcdlc f=new Funcdlc();
String tab=f.getTabAbonnement(d);
out.print(tab);
%>