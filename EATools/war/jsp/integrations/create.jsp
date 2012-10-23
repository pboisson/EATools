<%@page import="com.volvo.ea.entities.VolvoSystem"%>
<%@page import="com.volvo.ea.entities.VolvoEntity"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../head.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp">
		<jsp:param value="integrations" name="selected" />
	</jsp:include>
	<h1>Create Integration</h1>

	<form method="post" action="create">
		<ul>
			<li><label for="id">id:</label><input type="text" name="id"
				id="id" required autofocus /></li>
			<li><label for="description">description:</label><input
				type="text" name="description" id="description" /></li>
			<li><label for="entity">entity:</label> <select id="entity"
				name="entity">
					<%
						List<VolvoEntity> entities = (List<VolvoEntity>) request
								.getAttribute("entities");
						for (VolvoEntity e : entities) {
					%>
					<option value="<%=KeyFactory.keyToString(e.getKey())%>"><%=e.getName()%></option>
					<%
						}
					%>
			</select></li>
			<li><label for="requestor">requestor:</label> <select
				id="requestor" name="requestor">
					<%
						List<VolvoSystem> systems = (List<VolvoSystem>) request
								.getAttribute("systems");
						for (VolvoSystem e : systems) {
					%>
					<option value="<%=KeyFactory.keyToString(e.getKey())%>"><%=e.getName()%></option>
					<%
						}
					%>
			</select></li>
			<li><label for="source">source:</label> <select id="source"
				name="source">
					<%
						for (VolvoSystem e : systems) {
					%>
					<option value="<%=KeyFactory.keyToString(e.getKey())%>"><%=e.getName()%></option>
					<%
						}
					%>
			</select></li>
			<li><label for="owner">owner:</label> <select id="owner"
				name="owner">
					<%
						for (VolvoSystem e : systems) {
					%>
					<option value="<%=KeyFactory.keyToString(e.getKey())%>"><%=e.getName()%></option>
					<%
						}
					%>
			</select></li>
		</ul>
		<input type="submit" title="Save" value="Save" class="submit" />
	</form>

</body>
</html>