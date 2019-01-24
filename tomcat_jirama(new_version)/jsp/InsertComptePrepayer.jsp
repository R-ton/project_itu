<%@ page import="dbconnect.*,exception.*,control.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<%
try{
	String[] ida=request.getParameterValues("idabonner");
	String num=request.getParameter("num");
	//String dureeJour=request.getParameter("dureeJour");
	Funcdlc f=new Funcdlc();
	DBconnect d=new DBconnect("jirama","jirama");
	int i=f.insertComptePrepayer(num/*,dureeJour*/,null,ida,d);
}catch (AvaliderException e) {
		response.sendRedirect("prelevement_list.jsp?msg="+e.getMessage()+"");
}
catch (Exception e) {
	throw e;
}
%>