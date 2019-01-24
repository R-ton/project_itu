<%@ page import="dbconnect.*,exception.*,control.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<%
try{
	
String idClient=request.getParameter("idClient");
String Mois=request.getParameter("Mois");
String Annee=request.getParameter("Annee");
Funcdlc f=new Funcdlc();
String html=f.getSearch(idClient,Annee,Mois);
out.print(html);
}catch (Exception e) {
	throw e;
}
%>