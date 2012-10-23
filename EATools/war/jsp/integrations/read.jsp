<!DOCTYPE html>
<%@page import="com.volvo.ea.entities.Integration"%>
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
			List<Integration> integrations = (List<Integration>) request
					.getAttribute("integrationsList");
			for (Integration e : integrations) {
		%>
		<tr>
			<td><%=e.getDescription()%></td>
			<%
				if (e.getEntity() != null) {
			%>
			<td><a
				href="../entities/update/<%=KeyFactory.keyToString(e
							.getEntity())%>"><%=e.getEntity()%></a></td>
			<%
				} else {
			%>
			<td>&nbsp;</td>
			<%
				}
			%>
			<%
				if (e.getRequestor() != null) {
			%>
			<td><a
				href="../systems/update/<%=KeyFactory.keyToString((Key) e
							.getRequestor())%>"><%=e.getRequestor()%></a></td>
			<%
				} else {
			%>
			<td>&nbsp;</td>
			<%
				}
			%>
			<%
				if (e.getSource() != null) {
			%>
			<td><a
				href="../systems/update/<%=KeyFactory.keyToString((Key) e
							.getSource())%>"><%=e.getSource()%></a></td>
			<%
				} else {
			%>
			<td>&nbsp;</td>
			<%
				}
			%>
			<%
				if (e.getOwner() != null) {
			%>
			<td><a
				href="../systems/update/<%=KeyFactory.keyToString((Key) e
							.getOwner())%>"><%=e.getOwner()%></a></td>
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