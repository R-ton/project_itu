<%@ page import="dbconnect.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<% 
Funcdlc f=new Funcdlc();
String index=f.getIndex("","",null);
%>
<!DOCTYPE html>
<html>
<head>
	<title>Index</title>
</head>

	<body>
		<% out.print(index); %>
	</body>
</html>