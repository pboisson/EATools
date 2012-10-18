<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<html>
<head>
	<jsp:include page="../head.jsp" />
</head>
<body>
<jsp:include page="../header.jsp">
		<jsp:param value="systems" name="selected"/>
	</jsp:include>
	<h1>Update System</h1>

	<%
		Entity system = (Entity) request.getAttribute("system");
	%>

	<form method="post" action="../update">

		<input type="hidden"
			value="<%=KeyFactory.keyToString(system.getKey())%>" id="key"
			name="key">
		<ul>
			<li><label for="url">URL:</label><input type="text" name="url"
				id="url" value="<%=system.getProperty("url")%>" autofocus /></li>
			<li><label for="name">Name:</label><input type="text"
				name="name" id="name" value="<%=system.getProperty("name")%>"
				required /></li>
		</ul>

		<input type="submit" class="submit" title="Update" value="Update" />
	</form>

</body>
</html>