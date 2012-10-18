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
	<h1>Update Integration</h1>

	<%
		Entity integration = (Entity) request.getAttribute("integration");
	%>

	<form method="post" action="../update">

		<input type="hidden"
			value="<%=KeyFactory.keyToString(integration.getKey())%>" id="key"
			name="key">
		<ul>
			<li><label for="description">description:</label><input
				type="text" name="description" id="description" value="<%=integration.getProperty("description") %>" /></li>
			<li><label for="entity">entity:</label> <select id="entity"
				name="entity">
					<%
						List<Entity> entities = (List<Entity>) request
								.getAttribute("entities");
						for (Entity e : entities) {
					%>
					<option value="<%=KeyFactory.keyToString(e.getKey())%>"><%=e.getProperty("name")%></option>
					<%
						}
					%>
			</select></li>
			<li><label for="requestor">requestor:</label> <select
				id="requestor" name="requestor">
					<%
						List<Entity> systems = (List<Entity>) request
								.getAttribute("systems");
						for (Entity e : systems) {
					%>
					<option value="<%=KeyFactory.keyToString(e.getKey())%>"><%=e.getProperty("name")%></option>
					<%
						}
					%>
			</select></li>
			<li><label for="source">source:</label> <select id="source"
				name="source">
					<%
						for (Entity e : systems) {
					%>
					<option value="<%=KeyFactory.keyToString(e.getKey())%>"><%=e.getProperty("name")%></option>
					<%
						}
					%>
			</select></li>
			<li><label for="owner">owner:</label> <select id="owner"
				name="owner">
					<%
						for (Entity e : systems) {
					%>
					<option value="<%=KeyFactory.keyToString(e.getKey())%>"><%=e.getProperty("name")%></option>
					<%
						}
					%>
			</select></li>
		</ul>

		<input type="submit" class="submit" title="Update" value="Update" />
	</form>

</body>
</html>