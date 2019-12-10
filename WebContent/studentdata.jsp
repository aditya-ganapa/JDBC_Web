<%@page import="org.com.util.DatabaseUtil"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	table {
		border: inset red 10px;
		margin-left: 30%;
		margin-top: 10%;
	}
	td, th {
		border: solid black 1px ;
		color: blue;
		padding: 10px;
	}
</style>
</head>
<body>

<%!
	Statement st;
	ResultSet rs;
	Connection con;
%>

<%
//	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db10", "root", "password-1");
	con = DatabaseUtil.getCon();
	
	st = con.createStatement();
	rs = st.executeQuery("select * from student");
	
	ResultSetMetaData metaData = rs.getMetaData();
%>

<table>

	<tr>
		<%
			for(int x = 1; x <= metaData.getColumnCount(); x++) {
			//	out.print(metaData.getColumnName(x)+" ");
		%>
		<th><%=metaData.getColumnName(x) %></th>
		<%
			}
		%>
	</tr>
	<% 
	while(rs.next()) {
	//	out.print("<br>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
	%>
	<tr>
		<td><%=rs.getInt(1)%></td>
		<td><%=rs.getString(2)%></td>
		<td><%=rs.getString(3)%></td>
	</tr>
		<%} %>
</table>

</body>
</html>