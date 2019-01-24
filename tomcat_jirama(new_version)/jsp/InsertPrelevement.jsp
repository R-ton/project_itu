<%@ page import="dbconnect.*,exception.*,control.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<%
try{
	String idClient=request.getParameter("idClient");
	String daty=request.getParameter("daty");
	String numCompteur=request.getParameter("numCompteur");
	String categorie=request.getParameter("categorie");
	String conso=request.getParameter("consommation");
	String etat=request.getParameter("etat");
	DBconnect d=null;
	Funcdlc f=new Funcdlc();
	int i=f.setPrelevement(idClient,daty,numCompteur,categorie,conso,etat,d);

}catch (Exception e) {
	throw e;
}

%>