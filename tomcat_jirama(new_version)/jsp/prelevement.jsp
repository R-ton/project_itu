<%@ page import="dbconnect.*,exception.*,control.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<%
// DBconnect d=new DBconnect("jirama","jirama");
String idClient=request.getParameter("idClient");
String daty=request.getParameter("daty");
Funcdlc f=new Funcdlc();
String html=f.getPrelevement(idClient,daty);
out.print(html);
%>
