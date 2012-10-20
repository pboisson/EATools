<!DOCTYPE html>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page import="com.volvo.ea.helpers.entities.Entity"%>
<html>
<head>
<jsp:include page="../head.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp">
		<jsp:param value="integrations" name="selected" />
	</jsp:include>
	<h1>Entities with multiple owners</h1>

	<p>
		Number of integrations taken into consideration:
		<%=((List) request.getAttribute("integrationsList")).size()%></p>

	<table>
		<colgroup>
			<col width="20%"></col>
			<col width="80%"></col>
		</colgroup>
		<thead>
			<tr>
				<td>Entity</td>
				<td>Owners</td>
			</tr>
		</thead>
		<%
			Map<Key, Entity> entities = (HashMap<Key, Entity>) request
					.getAttribute("entitiesList");
			if (entities.containsKey(null)) {
				entities.remove(null);
			}
			for (Key k : entities.keySet()) {
				Entity e = entities.get(k);
				if (e.getOwnedBy() != null && e.getOwnedBy().size() > 1) {
		%>
		<tr>
			<td><a href="../entities/update/<%=KeyFactory.keyToString(k)%>"><%=k.toString()%></a></td>
			<td>
				<%
					if (e.getOwnedBy() != null) {
								for (Key owner : e.getOwnedBy()) {
				%> <a href="../systems/update/<%=KeyFactory.keyToString(owner)%>"><%=owner.toString()%></a>
				<%
					}
							} else {
				%>&nbsp;<%
					}
				%>
			</td>
		</tr>
		<%
			}
			}
		%>
	</table>
</body>
</html>