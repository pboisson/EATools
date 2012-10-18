<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../head.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<h1>Import integration</h1>

	<form method="post" action="xml">
		<ul>
			<li><label for="xml">xml to import:</label> <textarea id="xml"
					name="xml"></textarea></li>
		</ul>
		<input type="submit" title="Save" value="Save" />
	</form>

</body>
</html>