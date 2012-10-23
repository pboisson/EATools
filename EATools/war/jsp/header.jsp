<% String selected = request.getParameter("selected"); %>
<header>
	<h1><a href="/home">Integration manager for Enterprise Architecture</a></h1>
	<nav>
		<ul class="yui3-g">
			<% if("home".equals(selected)) { %>
			<li class="yui3-u-1-6 selected"><a href="home">Home</a></li>
			<% } else { %>
			<li class="yui3-u-1-6"><a href="../home">Home</a></li>
			<% } %>
			<% if("entities".equals(selected)) { %>
			<li class="yui3-u-1-6 selected"><a href="read">Entities</a></li>
			<% } else { %>
			<li class="yui3-u-1-6"><a href="../entities/read">Entities</a></li>
			<% } %>
			<% if("systems".equals(selected)) { %>
			<li class="yui3-u-1-6 selected"><a href="read">Systems</a></li>
			<% } else { %>
			<li class="yui3-u-1-6"><a href="../systems/read">Systems</a></li>
			<% } %>
			<% if("integrations".equals(selected)) { %>
			<li class="yui3-u-1-6 selected"><a href="read">Integrations</a></li>
			<% } else { %>
			<li class="yui3-u-1-6"><a href="../integrations/read">Integrations</a></li>
			<% } %>
		</ul>
	</nav>
</header>
