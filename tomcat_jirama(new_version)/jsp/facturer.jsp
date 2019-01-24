<%@ page import="dbconnect.*,exception.*,control.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<%
String tranche="",mois="",annee="";
//test mbola tsy mampiditra mois sy annee
Function f=new Function();
// String boxvalue=request.getParameter("box");

// out.println(boxvalue);
// out.println(numcompteur[0]);
// out.println(numcompteur[1]);
String msg="";
String[] numcompteur=request.getParameterValues("box");
String[] dt=request.getParameterValues("dt");
out.println(numcompteur[0]);
out.println(dt[0]);
/*try{
	if(numcompteur == null){
		throw new AvaliderException("Facturation impossible");
	}
	DBconnect d=new DBconnect("jirama","jirama");
	int i=f.InsertFacture(numcompteur,tranche,mois,annee,dt,d);
	msg="Facturation Réussi";
	//response.sendRedirect("payer.jsp?num="+numcompteur);

String html=f.afficherFacture(numcompteur,dt,d);

out.print(html);
}catch (AvaliderException e) {
	//msg="Facturation impossible";
	response.sendRedirect("prelevement_list.jsp?msg="+e.getMessage()+"");
}catch (Exception e) {
	msg=e.getMessage();
	throw e;
	//response.sendRedirect("prelevement_list.jsp?msg="+msg+"");
	
}*/
%>