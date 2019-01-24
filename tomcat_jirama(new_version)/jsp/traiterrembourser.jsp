<%@ page import="dbconnect.*,exception.*,control.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<%
try{
Function f=new Function();

String[] str2=request.getParameterValues("box2");
String[] input=request.getParameterValues("inputNumber");

String[] dt=request.getParameterValues("dt");
if (input != null && str2 != null) {
	int i=f.InsertFactureAnnuler(str2,input[0],dt);
	if(i == 1){

			String html=f.getFactAnnuler(str2);
			out.print(html);	
		}else{
		response.sendRedirect("formulaire.jsp?insertPrelevement=Prelevement");
		}
	}
}catch (Exception e) {
	throw e;
}
%>