<%@ page import="dbconnect.*,exception.*,control.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<%
DBconnect d=new DBconnect("jirama","jirama");
Function f=new Function();
String html=f.getPrelevementTab(d);
out.print(html);
%>