<%@ page import="dbconnect.*,exception.*,control.*,table.*,function.*,javax.servlet.http.HttpSession" %>
<%
	String Msg=request.getParameter("msg");
	if(Msg!=null) { %>
	<p class="Attention"><%=Msg%></p>
	<%
	} 
%>
<%
	String anaranaClass=request.getParameter("insertPrelevement");
	String recherche=request.getParameter("rechercheFacture");
	String abonner=request.getParameter("Abonner");

	if(anaranaClass!=null) {
		Class ppl=Class.forName("table."+anaranaClass);
		Object a=ppl.newInstance();
	Formulaire fi=new Formulaire(a);
	out.print(fi.getHTML());
}else if (recherche != null) {
	Funcdlc f=new Funcdlc();
	String html=f.rechercheFacture();
	out.print(html);
}else if (abonner != null) {
	Funcdlc f=new Funcdlc();
	DBconnect d=new DBconnect("jirama","jirama");
	String html=f.getTabAbonnement(d);
	out.print(html);
}
%>