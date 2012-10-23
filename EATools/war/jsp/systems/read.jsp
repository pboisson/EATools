<!DOCTYPE html>
<%@page import="com.volvo.ea.entities.VolvoSystem"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<html>
<head>
<jsp:include page="../head.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp">
		<jsp:param value="systems" name="selected" />
	</jsp:include>
	<h1>List of systems</h1>

	<p class="warning">
		Not seeing the system you were looking for, why not <a href="create">create
			an system</a>.
	</p>

	<p>
		Maybe you prefer to see <a href="../integrations/system-view">how
			systems are integrated with each other</a>.
	</p>

	<table>
		<colgroup>
			<col width="25%"></col>
			<col width="25%"></col>
			<col width="25%"></col>
			<col width="25%"></col>
		</colgroup>
		<thead>
			<tr>
				<td>Name</td>
				<td>Yellow Pages</td>
				<td>Created Date</td>
				<td>Action</td>
			</tr>
		</thead>
		<%
			List<VolvoSystem> systems = (List<VolvoSystem>) request
					.getAttribute("systemsList");
			for (VolvoSystem e : systems) {
		%>
		<tr>
			<td><%=e.getName()%></td>
			<td><a href="<%=e.getUrl()%>"><%=e.getUrl()%></a></td>
			<%
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			%>
			<td><%=formatter.format(e.getDate())%></td>
			<td><a href="update/<%=KeyFactory.keyToString(e.getKey())%>">Update</a>
				| <a href="delete/<%=KeyFactory.keyToString(e.getKey())%>">Delete</a></td>
		</tr>
		<%
			}
		%>
	</table>
	
</body>
</html>