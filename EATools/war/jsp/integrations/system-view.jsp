<!DOCTYPE html>
<%@page import="com.google.appengine.api.datastore.Key"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page import="com.volvo.ea.helpers.entities.System"%>
<html>
<head>
<jsp:include page="../head.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp">
		<jsp:param value="integrations" name="selected" />
	</jsp:include>
	<h1>Systems with most integrations</h1>

	<p>
		Number of integrations taken into consideration:
		<%=((List) request.getAttribute("integrationsList")).size()%></p>

	<p class="warning">Only systems used both as sources and requestors
		and involved in more than 5 integrations are shown.</p>

	<table>
		<colgroup>
			<col width="25%"></col>
			<col width="25%"></col>
			<col width="25%"></col>
			<col width="25%"></col>
		</colgroup>
		<thead>
			<tr>
				<td>System</td>
				<td>Callers</td>
				<td>Sources</td>
				<td>Owns</td>
			</tr>
		</thead>
		<%
			Map<Key, System> systems = (HashMap<Key, System>) request
					.getAttribute("systemsList");
			if (systems.containsKey(null)) {
				systems.remove(null);
			}
			for (Key k : systems.keySet()) {
				System s = systems.get(k);
				if (s.getCallers() != null && s.getCalling() != null
						&& s.getCallers().size() > 0
						&& s.getCalling().size() > 0
						&& (s.getCallers().size() + s.getCalling().size()) > 5) {
		%>
		<tr>
			<td><a href="../systems/update/<%=KeyFactory.keyToString(k)%>"><%=k.toString()%></a></td>
			<td>
				<%
					if (s.getCallers() != null) {
								for (Key caller : s.getCallers()) {
				%> <a href="../systems/update/<%=KeyFactory.keyToString(caller)%>"><%=caller.toString()%></a>
				<%
					}
							} else {
				%>&nbsp;<%
					}
				%>
			</td>
			<td>
				<%
					if (s.getCalling() != null) {
								for (Key calling : s.getCalling()) {
				%> <a href="../systems/update/<%=KeyFactory.keyToString(calling)%>"><%=calling.toString()%></a>
				<%
					}
							} else {
				%>&nbsp;<%
					}
				%>
			</td>
			<td>
				<%
					if (s.getOwns() != null) {
								for (Key entity : s.getOwns()) {
				%> <a href="../entities/update/<%=KeyFactory.keyToString(entity)%>"><%=entity.toString()%></a>
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