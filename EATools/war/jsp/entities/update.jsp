<%@page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<html>
<head>
	<jsp:include page="../head.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp">
		<jsp:param value="entities" name="selected" />
	</jsp:include>
	<h1>Update Entity</h1>
 
	<%
		Entity entity = (Entity)request.getAttribute("entity");
	%>
 
	<form method="post" action="../update" >
 
 	<input type="hidden" value="<%= KeyFactory.keyToString(entity.getKey()) %>" id="key" name="key">
		<ul>
			<li><label for="name">Name:</label><input type="text"
				name="name" id="name" required autofocus value="<%= entity.getProperty("name") %>" /></li>
		</ul>
		
		<input type="submit" class="submit" title="Update" value="Update" />
	</form>
 
</body>
</html>