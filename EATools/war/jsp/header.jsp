<% String selected = request.getParameter("selected"); %>
<header>
	<h1><a href="/home">Integration manager for Enterprise Architecture</a></h1>
	<nav>
		<ul class="yui3-g">
			<li class="yui3-u-1-6<% if("home".equals(selected)) { %> selected<% } %>"><a href="/home">Home</a></li>
			<li class="yui3-u-1-6<% if("entities".equals(selected)) { %> selected<% } %>"><a href="/entities/read">Entities</a></li>
			<li class="yui3-u-1-6<% if("systems".equals(selected)) { %> selected<% } %>"><a href="/systems/read">Systems</a></li>
			<li class="yui3-u-1-6<% if("integrations".equals(selected)) { %> selected<% } %>"><a href="/integrations/read">Integrations</a></li>
		</ul>
	</nav>
</header>
