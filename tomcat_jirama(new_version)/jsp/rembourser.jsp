<%@ page import="dbconnect.*,exception.*,control.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<%
Function f=new Function();
String[] str=request.getParameterValues("box");

String[] input=request.getParameterValues("dt");
// System.out.print(str.length);
// System.out.print(str[0]);
if (str != null) {
		String html=f.displayRembourser(str,input);
		out.print(html);
}

// Function f=new Function();
// String html=f.displayRembourser(str);
// out.print(html);
%>
