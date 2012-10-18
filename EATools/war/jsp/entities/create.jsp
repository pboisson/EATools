<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../head.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp">
		<jsp:param value="entities" name="selected" />
	</jsp:include>
	<h1>Create Entity</h1>

	<form method="post" action="create">
		<ul>
			<li><label for="id">id:</label><input type="text"
				name="id" id="id" required autofocus /></li>

			<li><label for="name">name:</label><input type="text"
				name="name" id="name" required /></li>
		</ul>
		<input type="submit" title="Save" value="Save" class="submit" />
	</form>

</body>
</html>