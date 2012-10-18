<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<html>
<head>
<jsp:include page="../head.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp">
		<jsp:param value="integrations" name="selected" />
	</jsp:include>
	<h1>List of all integrations</h1>

	<p class="warning">
		Not seeing the integration you were looking for, why not <a
			href="create">create an integration</a>.
	</p>

	<table>
		<colgroup>
			<col width="20%"></col>
			<col width="15%"></col>
			<col width="15%"></col>
			<col width="15%"></col>
			<col width="15%"></col>
			<col width="10%"></col>
			<col width="10%"></col>
		</colgroup>
		<thead>
			<tr>
				<td>Description</td>
				<td>Entity</td>
				<td>Requestor</td>
				<td>Source</td>
				<td>Owner</td>
				<td>Created Date</td>
				<td>Action</td>
			</tr>
		</thead>
		<%
			List<Entity> integrations = (List<Entity>) request
					.getAttribute("integrationsList");
			for (Entity e : integrations) {
		%>
		<tr>
			<td><%=e.getProperty("description")%></td>
			<%
				if (e.getProperty("entity") != null) {
			%>
			<td><a
				href="../entities/update/<%=KeyFactory.keyToString((Key) e
							.getProperty("entity"))%>"><%=e.getProperty("entity")%></a></td>
			<%
				} else {
			%>
			<td>&nbsp;</td>
			<%
				}
			%>
			<%
				if (e.getProperty("requestor") != null) {
			%>
			<td><a
				href="../systems/update/<%=KeyFactory.keyToString((Key) e
							.getProperty("requestor"))%>"><%=e.getProperty("requestor")%></a></td>
			<%
				} else {
			%>
			<td>&nbsp;</td>
			<%
				}
			%>
			<%
				if (e.getProperty("source") != null) {
			%>
			<td><a
				href="../systems/update/<%=KeyFactory.keyToString((Key) e
							.getProperty("source"))%>"><%=e.getProperty("source")%></a></td>
			<%
				} else {
			%>
			<td>&nbsp;</td>
			<%
				}
			%>
			<%
				if (e.getProperty("owner") != null) {
			%>
			<td><a
				href="../systems/update/<%=KeyFactory.keyToString((Key) e
							.getProperty("owner"))%>"><%=e.getProperty("owner")%></a></td>
			<%
				} else {
			%>
			<td>&nbsp;</td>
			<%
				}
			%>
			<%
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			%>
			<td><%=formatter.format(e.getProperty("date"))%></td>
			<td><a href="update/<%=KeyFactory.keyToString(e.getKey())%>">Update</a>
				| <a href="delete/<%=KeyFactory.keyToString(e.getKey())%>">Delete</a></td>
		</tr>
		<%
			}
		%>
	</table>
	<p>
		<a href="read?cursor=<%=request.getAttribute("cursor")%>">More
			integrations</a>
	</p>
</body>
</html>